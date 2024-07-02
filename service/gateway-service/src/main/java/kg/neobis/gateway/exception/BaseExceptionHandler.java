package kg.neobis.gateway.exception;

import jakarta.ws.rs.BadRequestException;
import kg.neobis.core.exception.BadCredentialsException;
import kg.neobis.core.exception.EntityAlreadyExistException;
import kg.neobis.core.exception.ErrorResponseBody;
import kg.neobis.core.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<?> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponseBody
                                .builder()
                                .errorCode(null)
                                .message(exception.getMessage())
                                .fields(null)
                                .build()
                );
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorResponseBody> handleBadCredentialsException(BadCredentialsException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ErrorResponseBody
                                .builder()
                                .errorCode(null)
                                .message(exception.getMessage())
                                .build()
                );
    }


    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponseBody> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponseBody
                                .builder()
                                .errorCode(null)
                                .message(exception.getMessage())
                                .build()
                );
    }


    @ExceptionHandler({EntityAlreadyExistException.class})
    public ResponseEntity<ErrorResponseBody> handleSpecificRegistrationException(EntityAlreadyExistException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponseBody
                                .builder()
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseBody> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponseBody
                                .builder()
                                .message(exception.getMessage())
                                .build()
                );
    }
}

