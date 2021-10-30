package com.bank.credit.exception;

import com.bank.credit.web.ResponseError;
import com.bank.credit.web.ResponseMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalResponseException extends ResponseEntityExceptionHandler {

    // handleHttpMediaTypeNotSupported : triggers when the JSON in invalid
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders httpHeaders,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        List<String> details = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ex.getContentType());
        stringBuilder.append(" media type is not supported.Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> stringBuilder.append(t).append(", "));

        details.add(stringBuilder.toString());

        ResponseError error = new ResponseError(LocalDateTime.now(), "Invalid JSON", details);

        return ResponseMapper.errorToEntity(error, HttpStatus.BAD_REQUEST);
    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders httpHeaders,
            HttpStatus httpStatus,
            WebRequest webRequest
    ) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ResponseError error = new ResponseError(LocalDateTime.now(), "Malformed JSON request", details);

        return ResponseMapper.errorToEntity(error, HttpStatus.BAD_REQUEST);
    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders httpHeaders,
            HttpStatus httpStatus,
            WebRequest webRequest
    ) {
        List<String> details;
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ResponseError error = new ResponseError(LocalDateTime.now(), "Validation Errors", details);

        return ResponseMapper.errorToEntity(error, HttpStatus.BAD_REQUEST);
    }

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders httpHeaders,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        List<String> details = new ArrayList<>();
        details.add(ex.getParameterName() + " parameter is missing");

        ResponseError error = new ResponseError(LocalDateTime.now(), "Missing Parameters", details);

        return ResponseMapper.errorToEntity(error, HttpStatus.BAD_REQUEST);
    }

    // handleMethodArgumentTypeMismatch : triggers when a parameter's type does not match
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            WebRequest webRequest
    ) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ResponseError error = new ResponseError(LocalDateTime.now(), "Mismatch Type", details);

        return ResponseMapper.errorToEntity(error, HttpStatus.BAD_REQUEST);
    }

    // handleConstraintViolationException : triggers when @Validated fails
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(Exception exception, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());
        ResponseError error = new ResponseError(LocalDateTime.now(), "Constraint Violation", details);

        return ResponseMapper.errorToEntity(error, HttpStatus.BAD_REQUEST);
    }

    // handleResourceNotFoundException : triggers when there is no resource with the specified ID in BDD
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(CustomerNotFoundException exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());
        ResponseError error = new ResponseError(LocalDateTime.now(), "Resource Not Found", details);

        return ResponseMapper.errorToEntity(error, HttpStatus.BAD_REQUEST);
    }

    // handleNoHandlerFoundException : triggers when the handler method is invalid
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            HttpHeaders httpHeaders,
            HttpStatus httpStatus,
            WebRequest webRequest
    ) {
        List<String> details = new ArrayList<>();
        details.add(String.format("Could not find the %s method for URL %s", exception.getHttpMethod(),
                exception.getRequestURL()));
        ResponseError error = new ResponseError(LocalDateTime.now(), "Method Not Found", details);

        return ResponseMapper.errorToEntity(error, HttpStatus.BAD_REQUEST);
    }

}
