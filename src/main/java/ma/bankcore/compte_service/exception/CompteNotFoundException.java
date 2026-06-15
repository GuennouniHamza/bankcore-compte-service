package ma.bankcore.compte_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompteNotFoundException extends RuntimeException {
	
	public CompteNotFoundException(Long id) {
        super("Compte introuvable avec l'ID : " + id);
    }

    public CompteNotFoundException(String rib) {
        super("Compte introuvable avec le RIB : " + rib);
    }

}
