package ma.bankcore.compte_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.bankcore.compte_service.entity.Compte;

public interface CompteRepository extends JpaRepository<Compte,Long> {
	List<Compte> findByClientId(Long clientId);//spring data genere autom apartir du nom de la méthode

    Optional<Compte> findByRib(String rib);

    boolean existsByRib(String rib);

    @Query("SELECT c FROM Compte c WHERE c.clientId = :clientId " +
           "AND c.statut = 'ACTIF'")
    List<Compte> findComptesActifsByClientId(Long clientId);

}
