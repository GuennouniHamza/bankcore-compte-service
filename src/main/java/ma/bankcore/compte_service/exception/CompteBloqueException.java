package ma.bankcore.compte_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CompteBloqueException extends RuntimeException {

    public CompteBloqueException(Long compteId) {
        super("Compte bloqué : " + compteId);
    }
}