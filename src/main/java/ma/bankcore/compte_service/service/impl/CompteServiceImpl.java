package ma.bankcore.compte_service.service.impl;

import java.util.List;

import jakarta.transaction.Transactional;
import ma.bankcore.compte_service.dto.CompteRequest;
import ma.bankcore.compte_service.dto.CompteResponse;
import ma.bankcore.compte_service.dto.VirementRequest;
import ma.bankcore.compte_service.entity.Compte;
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
	public CompteResponse getCompteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompteResponse> getComptesByClientId(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompteResponse effectuerVirement(VirementRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bloquerCompte(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cloturerCompte(Long id) {
		// TODO Auto-generated method stub
		
	}
    

}
