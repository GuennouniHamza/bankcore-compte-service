package ma.bankcore.compte_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.math.BigDecimal;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SoldeInsuffisantException extends RuntimeException {

    public SoldeInsuffisantException(BigDecimal solde, BigDecimal montant) {
        super("Solde insuffisant. Solde actuel : " + solde +
              " | Montant demandé : " + montant);
    }
}