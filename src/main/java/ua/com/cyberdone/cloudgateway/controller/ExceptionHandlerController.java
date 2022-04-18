package ua.com.cyberdone.cloudgateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.cyberdone.cloudgateway.controller.docs.ExceptionHandlerApi;
import ua.com.cyberdone.cloudgateway.exception.AccessDeniedException;
import ua.com.cyberdone.cloudgateway.exception.AlreadyExistException;
import ua.com.cyberdone.cloudgateway.exception.AuthenticationException;
import ua.com.cyberdone.cloudgateway.exception.InternalException;
import ua.com.cyberdone.cloudgateway.exception.NotFoundException;
import ua.com.cyberdone.cloudgateway.model.RestError;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping(value = "/error", method = RequestMethod.GET)
public class ExceptionHandlerController implements ExceptionHandlerApi {
    public static final String BAD_REQUEST_MSG = "The request does not follow the correct syntax";
    public static final String NOT_ALLOWED_MSG = "No such methode exists in the server";
    public static final String INTERNAL_SERVER_ERROR_MSG = "There was an error processing the request.";
    public static final String NOT_FOUND_MSG = "Resource not found";
    public static final String ACCESS_DENIED_MSG = "Access denied";
    public static final String NO_CONTENT_MSG = "The resource is null or empty";
    public static final String CONFLICT_MSG = "Resource have a conflict with existing one in the system";
    public static final String UNAUTHORIZED_MSG = "You are unauthorized.";
    private final ObjectMapper mapper;

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<RestError> handleFeignStatusException(FeignException e, HttpServletResponse response)
            throws JsonProcessingException {
        var status = HttpStatus.valueOf(e.status());
        log.error("Feign exception. Status={} Reason={}", status, status.getReasonPhrase(), e);
        response.setStatus(e.status());
        return switch (e.status()) {
            case 401 -> buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Authentication failed: " + e.getMessage());
            case 403 -> buildResponse(FORBIDDEN, ACCESS_DENIED_MSG, "Access denied: " + e.getMessage());
            case 405 -> buildResponse(METHOD_NOT_ALLOWED, NOT_ALLOWED_MSG, "This methode not exists: " + e.getMessage());
            default -> new ResponseEntity<>(
                    mapper.readValue(e.contentUTF8(), RestError.class),
                    Optional.ofNullable(HttpStatus.resolve(e.status())).orElse(HttpStatus.INTERNAL_SERVER_ERROR)
            );
        };
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NullPointerException exception) {
        log.error("NullPointerException ", exception);
        return buildResponse(NO_CONTENT, NO_CONTENT_MSG, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestError> validationException(ConstraintViolationException exception) {
        log.error("ConstraintViolationException ", exception);
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, exception.getConstraintViolations()
                .stream().map(v -> invalidParameter(v.getInvalidValue(), v.getMessage()))
                .collect(Collectors.toSet()).toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestError> httpClientErrorException(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException ", exception);
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, exception.getBindingResult().getFieldErrors()
                .stream().map(v -> invalidParameter(v.getRejectedValue(), v.getDefaultMessage()))
                .collect(Collectors.toSet()).toString());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<RestError> httpClientErrorException(HttpClientErrorException exception) {
        log.error("HttpClientErrorException ", exception);
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, "Clients request is of the wrong format. " + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestError> httpClientErrorException(MethodArgumentTypeMismatchException exception) {
        log.error("MethodArgumentTypeMismatchException ", exception);
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, String.format("Invalid url parameter '%s' has been sent. %s",
                exception.getName(), exception.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestError> httpClientErrorException(MissingServletRequestParameterException exception) {
        log.error("MissingServletRequestParameterException ", exception);
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, "Request parameter is missing" + exception);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestError> httpClientErrorException(HttpMessageNotReadableException exception) {
        log.error("HttpMessageNotReadableException ", exception);
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AuthenticationException exception) {
        log.error("AuthenticationException ", exception);
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Authentication failed: " + exception.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(ExpiredJwtException exception) {
        log.error("ExpiredJwtException ", exception);
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "JWT token is expired: " + exception.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<RestError> noHandlerFoundException(SignatureException exception) {
        log.error("SignatureException ", exception);
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Bad JWT Signature: " + exception.getMessage());
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(MalformedJwtException exception) {
        log.error("MalformedJwtException ", exception);
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Malformed Jwt: " + exception.getMessage());
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(UnsupportedJwtException exception) {
        log.error("UnsupportedJwtException ", exception);
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Unsupported Jwt: " + exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AccessDeniedException exception) {
        log.error("AccessDeniedException ", exception);
        return buildResponse(FORBIDDEN, ACCESS_DENIED_MSG, "You have no permission to access the resource ..." + exception.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NoHandlerFoundException exception) {
        log.error("NoHandlerFoundException ", exception);
        return buildResponse(NOT_FOUND, NOT_FOUND_MSG, "Resource not found for " + exception.getRequestURL());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NotFoundException exception) {
        log.error("NotFoundException ", exception);
        return buildResponse(NOT_FOUND, NOT_FOUND_MSG, exception.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AlreadyExistException exception) {
        log.error("AlreadyExistException ", exception);
        return buildResponse(CONFLICT, CONFLICT_MSG, exception.getMessage());
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<RestError> httpClientErrorException(InternalException exception) {
        log.error("InternalException ", exception);
        return buildResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, exception.getMessage());
    }

    private String invalidParameter(Object invalidValue, String message) {
        var val = nonNull(invalidValue) ? invalidValue.toString() : StringUtils.EMPTY;
        var msg = nonNull(message) ? (" Reason: " + message) : StringUtils.EMPTY;
        return "Value '" + val + "' is invalid." + msg;
    }

    private ResponseEntity<RestError> buildResponse(HttpStatus httpStatus, String msg, String details) {
        return new ResponseEntity<>(
                RestError.builder().error(httpStatus.getReasonPhrase()).title(msg).detail(details).build(),
                httpStatus
        );
    }
}
