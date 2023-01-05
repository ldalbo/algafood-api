package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceprionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.JSON_INVALIDO;

        String detail = "Formato inválido no JSON";
        Problem problem = createProblemBuilder(status,problemType, detail).build();

        return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = e.getMessage();
        Problem problem = createProblemBuilder(status,problemType, detail).build();

        return handleExceptionInternal(e,problem,new HttpHeaders(),status,request);
    }



    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException e, WebRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = e.getMessage();
        Problem problem = createProblemBuilder(status,problemType, detail).build();

        return handleExceptionInternal(e,problem,new HttpHeaders(),status,request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = e.getMessage();
        Problem problem = createProblemBuilder(status,problemType, detail).build();
        return handleExceptionInternal(e,problem,new HttpHeaders(),status,request);
    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null){
            body = Problem.builder()
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .build();



        }
        else if (body instanceof String){
            body = Problem.builder()
                    .status(status.value())
                    .title((String) body)
                    .build();

        }

        return super.handleExceptionInternal(ex, body, headers, status, request);


    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                        ProblemType problemType,
                                                        String detail){
        return Problem.builder()
                .title(problemType.getTitle())
                .type(problemType.getUri())
                .status(status.value())
                .detail(detail);




    }
}
