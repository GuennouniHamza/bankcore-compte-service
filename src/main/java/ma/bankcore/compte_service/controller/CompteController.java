package ma.bankcore.compte_service.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ma.bankcore.compte_service.dto.CompteRequest;
import ma.bankcore.compte_service.dto.CompteResponse;
import ma.bankcore.compte_service.dto.VirementRequest;
import ma.bankcore.compte_service.service.CompteService;

@RestController
@RequestMapping("/api/v1/comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CompteResponse>ouvrirCompte(
    		@Valid @RequestBody CompteRequest request){
    	 return ResponseEntity.status(HttpStatus.CREATED)
    		        .body(compteService.ouvrirCompte(request));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSEILLER')")
    
    public ResponseEntity<CompteResponse> getCompte(
            @PathVariable Long id) {
        return ResponseEntity.ok(compteService.getCompteById(id));
    }
    
    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSEILLER')")
    public ResponseEntity<List<CompteResponse>> getComptesByClient(
            @PathVariable Long clientId) {
        return ResponseEntity.ok(
            compteService.getComptesByClientId(clientId));
    }
    @PostMapping("/virement")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONSEILLER')")
    public ResponseEntity<CompteResponse>effectuerVirement(
    		@Valid @RequestBody VirementRequest request){
    	return ResponseEntity.ok(compteService.effectuerVirement(request));
    }
    
    @PatchMapping("/{id}/bloquer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> bloquerCompte(@PathVariable Long id) {
        compteService.bloquerCompte(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cloturerCompte(@PathVariable Long id) {
        compteService.cloturerCompte(id);
        return ResponseEntity.noContent().build();
    }// build pcq réponse sans body 


}