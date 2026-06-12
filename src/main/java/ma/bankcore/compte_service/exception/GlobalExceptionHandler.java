package ma.bankcore.compte_service.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ma.bankcore.compte_service.exception.CompteNotFoundException;
import ma.bankcore.compte_service.exception.SoldeInsuffisantException;
import ma.bankcore.compte_service.exception.CompteBloqueException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	//record genere le constructeur getters setters .....
    record ErrorResponse(int status, String message, LocalDateTime timestamp) {}
    @ExceptionHandler(CompteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            CompteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(SoldeInsuffisantException.class)
    public ResponseEntity<ErrorResponse> handleSolde(
            SoldeInsuffisantException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(400, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(CompteBloqueException.class)
    public ResponseEntity<ErrorResponse> handleBloque(
            CompteBloqueException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ErrorResponse(403, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(e -> e.getField() + " : " + e.getDefaultMessage())
            .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(400, message, LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(500, "Erreur interne du serveur",
                LocalDateTime.now()));
    }

}
