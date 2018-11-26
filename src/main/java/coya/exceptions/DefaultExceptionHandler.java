/**
 *
 */
package coya.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import coya.dto.ExceptionResponseDto;

/**
 * @author Raksha
 *
 */
@RestControllerAdvice
@Component("ServiceExceptionHandlerAdvice")
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private static final long serialVersionUID = -8704528029951956646L;

    private ResponseEntity<ExceptionResponseDto> errorResponse(HttpStatus status, HttpHeaders headers, String message) {
        final ExceptionResponseDto apiError = new ExceptionResponseDto(message, status.value());
        return ResponseEntity.status(status).headers(headers).body(apiError);
    }
    @ExceptionHandler(CannotInsureException.class)
    final ResponseEntity<ExceptionResponseDto> handleCannotInsureException(CannotInsureException ex) {
        return errorResponse(HttpStatus.OK, new HttpHeaders(), "Could not insure products " + ex.getMessage());
    }

}
