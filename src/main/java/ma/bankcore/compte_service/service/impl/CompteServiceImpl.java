package ma.bankcore.compte_service.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import ma.bankcore.compte_service.dto.CompteRequest;
import ma.bankcore.compte_service.dto.CompteResponse;
import ma.bankcore.compte_service.dto.VirementRequest;
import ma.bankcore.compte_service.entity.Compte;
import ma.bankcore.compte_service.entity.StatutCompte;
import ma.bankcore.compte_service.entity.Transaction;
import ma.bankcore.compte_service.entity.TypeOperation;
import ma.bankcore.compte_service.exception.CompteBloqueException;
import ma.bankcore.compte_service.exception.CompteNotFoundException;
import ma.bankcore.compte_service.exception.SoldeInsuffisantException;
import ma.bankcore.compte_service.feign.ClientFeignClient;
import ma.bankcore.compte_service.mapper.CompteMapper;
import ma.bankcore.compte_service.repository.CompteRepository;
import ma.bankcore.compte_service.service.CompteService;

public class CompteServiceImpl implements CompteService {
	private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;
    private final ClientFeignClient clientFeignClient;
    
    public CompteServiceImpl(CompteRepository compteRepository,
            CompteMapper compteMapper,
            ClientFeignClient clientFeignClient) {
this.compteRepository = compteRepository;
this.compteMapper = compteMapper;
this.clientFeignClient = clientFeignClient;
}

	@Override
	@Transactional
	public CompteResponse ouvrirCompte(CompteRequest request) {
		
	    clientFeignClient.getClientById(request.getClientId());
	    Compte compte = compteMapper.toEntity(request);
	    Compte saved = compteRepository.save(compte);
		return compteMapper.toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public CompteResponse getCompteById(Long id) {
		Compte compte= compteRepository.findById(id)
			.orElseThrow(()->new CompteNotFoundException(id));
		return compteMapper.toResponse(compte);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompteResponse> getComptesByClientId(Long clientId) {
		return compteRepository.findByClientId(clientId)
				.stream()
				.map(compteMapper::toResponse)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public CompteResponse effectuerVirement(VirementRequest request) {
		
		if (request.getMontant().compareTo(BigDecimal.ZERO)<=0) {
			throw new IllegalArgumentException("Montant doit être positif");
		}
		Compte source=compteRepository.findById(request.getCompteSourceId())
			.orElseThrow(()->new CompteNotFoundException(request.getCompteSourceId()));
		
		Compte dest=compteRepository.findById(request.getCompteDestId())
			.orElseThrow(()->new CompteNotFoundException(request.getCompteDestId()));
		if(source.getStatut()!=StatutCompte.ACTIF) {
			throw new CompteBloqueException(source.getId());
		}
		if (dest.getStatut() != StatutCompte.ACTIF) {
		    throw new CompteBloqueException(dest.getId());
		}
		if (source.getSolde().compareTo(request.getMontant())<0) {
			throw new SoldeInsuffisantException(source.getSolde(),request.getMontant());
		}
		source.setSolde(source.getSolde().subtract(request.getMontant()));
		dest.setSolde(dest.getSolde().add(request.getMontant()));
		
		Transaction txSource = new Transaction();
		txSource.setMontant(request.getMontant());
		txSource.setTypeOperation(TypeOperation.DEBIT);
		txSource.setDescription("Virement vers " + dest.getRib());
		txSource.setCompte(source);
		source.getTransactions().add(txSource);
		
		Transaction txDest = new Transaction();
		txDest.setMontant(request.getMontant());
		txDest.setTypeOperation(TypeOperation.CREDIT);
		txDest.setDescription("Virement depuis " + source.getRib());
		txDest.setCompte(dest);
		dest.getTransactions().add(txDest);
		
		return  compteMapper.toResponse(source);
	}

	@Override
	@Transactional
	public void bloquerCompte(Long id) {
		Compte compte = compteRepository.findById(id)
		        .orElseThrow(() -> new CompteNotFoundException(id));
		    compte.setStatut(StatutCompte.BLOQUE);
	}

	@Override
	@Transactional
	public void cloturerCompte(Long id) {
		Compte compte = compteRepository.findById(id)
		        .orElseThrow(() -> new CompteNotFoundException(id));
		    compte.setStatut(StatutCompte.CLOTURE);
		
	}
    

}
