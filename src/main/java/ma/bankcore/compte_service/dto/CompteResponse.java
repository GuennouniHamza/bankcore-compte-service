package ma.bankcore.compte_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ma.bankcore.compte_service.entity.StatutCompte;
import ma.bankcore.compte_service.entity.TypeCompte;

public class CompteResponse {
	private Long id;
    private String rib;
    private BigDecimal solde;
    private TypeCompte type;
    private StatutCompte statut;
    private Long clientId;
    private LocalDateTime dateOuverture;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	public BigDecimal getSolde() {
		return solde;
	}
	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}
	public TypeCompte getType() {
		return type;
	}
	public void setType(TypeCompte type) {
		this.type = type;
	}
	public StatutCompte getStatut() {
		return statut;
	}
	public void setStatut(StatutCompte statut) {
		this.statut = statut;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public LocalDateTime getDateOuverture() {
		return dateOuverture;
	}
	public void setDateOuverture(LocalDateTime dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
    
}
