package ma.bankcore.compte_service.mapper;

import ma.bankcore.compte_service.dto.CompteRequest;
import ma.bankcore.compte_service.dto.CompteResponse;
import ma.bankcore.compte_service.entity.Compte;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class CompteMapper {

    public Compte toEntity(CompteRequest request) {
        Compte compte = new Compte();
        compte.setClientId(request.getClientId());
        compte.setType(request.getType());
        compte.setRib(generateRib());
        return compte;
    }

    public CompteResponse toResponse(Compte compte) {
        CompteResponse response = new CompteResponse();
        response.setId(compte.getId());
        response.setRib(compte.getRib());
        response.setSolde(compte.getSolde());
        response.setType(compte.getType());
        response.setStatut(compte.getStatut());
        response.setClientId(compte.getClientId());
        response.setDateOuverture(compte.getDateOuverture());
        return response;
    }

    private String generateRib() {
        return "MA" + UUID.randomUUID().toString()
                          .replace("-", "")
                          .substring(0, 22)
                          .toUpperCase();
    }
}