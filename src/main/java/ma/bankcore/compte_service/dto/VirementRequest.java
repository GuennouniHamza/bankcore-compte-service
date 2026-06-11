package ma.bankcore.compte_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class VirementRequest {

    @NotNull(message = "Compte source obligatoire")
    private Long compteSourceId;

    @NotNull(message = "Compte destination obligatoire")
    private Long compteDestId;

    @NotNull
    @DecimalMin(value = "0.01", message = "Montant doit être positif")
    private BigDecimal montant;

    private String description;

    // Getters & Setters
    public Long getCompteSourceId() { return compteSourceId; }
    public void setCompteSourceId(Long id) { this.compteSourceId = id; }
    public Long getCompteDestId() { return compteDestId; }
    public void setCompteDestId(Long id) { this.compteDestId = id; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}