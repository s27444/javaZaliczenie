package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found: Invalid endpoint (IC0001)");
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        if (ex.getMessage().contains("No static resource")) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("MovieService zwraca 500, a RentalService zwraca 502 (IC0004)");
        } else if (ex.getMessage().contains("Connection refused")) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("MovieService zwraca informacje o odrzuconym połączeniu, a RentalService zwraca 504 (IC0099)");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage() + " (IC0005)");
    }
}
