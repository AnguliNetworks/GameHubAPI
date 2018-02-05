package li.angu.gamehub.api.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 *
 * ANGULI NETWORKS CONFIDENTIAL
 * __________________
 *
 *  [2014] - [2018] Anguli Networks 
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Anguli Networks. The intellectual and 
 * technical concepts contained herein are proprietary to 
 * Anguli Networks and may be covered by German/EU and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Anguli Networks
 *
 * This File belongs to the GameHubAPI from Anguli Networks
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));

        exception.getBindingResult().getGlobalErrors()
                .forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), errors);

        return handleExceptionInternal(exception, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(),
                exception.getParameterName() + " parameter is missing"
        );

        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException exception,
            WebRequest request
    ) {
        List<String> errors = new ArrayList<>();

        exception.getConstraintViolations()
                .forEach(violation -> errors.add(
                        violation.getRootBeanClass().getName() + " " +
                                violation.getPropertyPath() + ": " +
                                violation.getMessage())
                );

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), errors);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            WebRequest request
    ) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(),
                exception.getName() + " should be of type " + exception.getRequiredType().getName()
        );

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                exception.getLocalizedMessage(),
                "No handler found for " + exception.getHttpMethod() + " " + exception.getRequestURL()
        );

        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        StringBuilder builder = new StringBuilder(exception.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");

        exception.getSupportedHttpMethods().forEach(method -> {
            builder.append(method);
            builder.append(" ");
        });

        ApiError apiError = new ApiError(
                HttpStatus.METHOD_NOT_ALLOWED,
                exception.getLocalizedMessage(),
                builder.toString()
        );

        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getLocalizedMessage(),
                "error occured"
        );

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
