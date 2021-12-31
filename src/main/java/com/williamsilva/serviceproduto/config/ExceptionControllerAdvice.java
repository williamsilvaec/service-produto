package com.williamsilva.serviceproduto.config;

import com.williamsilva.serviceproduto.http.data.response.Error;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.NoResultException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @Value("${springdoc.swagger-ui.path}")
    private String urlDocumentacao;

    @Hidden
    @ResponseBody
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error noResult(NoResultException ex) {
        return new Error("X_100", ex.getMessage(), urlDocumentacao);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error noResult(MethodArgumentTypeMismatchException ex) {
        return new Error("X_200", "Parâmetro inválido", urlDocumentacao);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error exception(Exception ex) {
        return new Error("X_300", ex.getMessage(), urlDocumentacao);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error methodArgument(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return new Error("X_400", mensagem, urlDocumentacao);
    }
}
