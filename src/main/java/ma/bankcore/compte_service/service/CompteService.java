package ma.bankcore.compte_service.service;

import java.util.List;

import ma.bankcore.compte_service.dto.CompteRequest;
import ma.bankcore.compte_service.dto.CompteResponse;
import ma.bankcore.compte_service.dto.VirementRequest;

public interface CompteService {
	 CompteResponse ouvrirCompte(CompteRequest request);

	    CompteResponse getCompteById(Long id);

	    List<CompteResponse> getComptesByClientId(Long clientId);

	    CompteResponse effectuerVirement(VirementRequest request);

	    void bloquerCompte(Long id);

	    void cloturerCompte(Long id);

}
