package ma.bankcore.compte_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_transaction")

public class Transaction {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal montant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOperation typeOperation;

    @Column(length = 255)
    private String description;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateOperation;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="compte_id", nullable=false)
    private Compte compte;
    
    @PrePersist
    public void prePersist() {
        this.dateOperation = LocalDateTime.now();
    }


}
