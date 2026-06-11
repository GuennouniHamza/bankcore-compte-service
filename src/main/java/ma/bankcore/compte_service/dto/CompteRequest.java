package ma.bankcore.compte_service.dto;

import jakarta.validation.constraints.NotNull;
import ma.bankcore.compte_service.entity.TypeCompte;

public class CompteRequest {
	@NotNull(message = "Le client ID est obligatoire")
    private Long clientId;

    @NotNull(message = "Le type de compte est obligatoire")
    private TypeCompte type;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public TypeCompte getType() {
		return type;
	}

	public void setType(TypeCompte type) {
		this.type = type;
	}
    
}
