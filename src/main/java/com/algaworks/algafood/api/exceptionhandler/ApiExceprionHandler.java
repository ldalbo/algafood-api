package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.HandlerExceptionResolver;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceprionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_TO_USER = "Erro do sistema chame o administardor";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);



        if (rootCause instanceof InvalidFormatException){
            System.out.println("PEGUEI 01");
            return handleInvalidFormatException( (InvalidFormatException) rootCause, headers,  status,  request);
        }
        else if (rootCause instanceof PropertyBindingException) {
            System.out.println("PEGUEI 02");
                return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.JSON_INVALIDO;
        String detail = "Formato inválido no JSON";
        Problem problem = createProblemBuilder(status,problemType, detail)
                .userMessage(MSG_TO_USER)
                .localtime(LocalDateTime.now())
                .build();
        return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleQqNome(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {


        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format("O parâmetro de url '%s' "
                       + " está com o valor %s é uma parâmetro inválido",
                        ex.getParameter().getParameterName(), ex.getValue()) ;


        Problem problem = createProblemBuilder(status,problemType, detail).build();
        return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);

    }

/*
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        MethodArgumentTypeMismatchException exMethod = null;
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String path = "";
        String detail = "";
        if (ex instanceof MethodArgumentTypeMismatchException){
            exMethod = (MethodArgumentTypeMismatchException)ex;
            detail = String.format("O parâmetro de url '%s' "
                            + " está com o valor %s é uma parâmetro inválido",
                    exMethod.getParameter().getParameterName(), ex.getValue()) ;
            Problem problem = createProblemBuilder(status, problemType, detail).build();
            return handleExceptionInternal(exMethod, problem, headers, status, request);
        }
        else{
            detail = String.format("O parâmetro de url  "
                            + " está com o valor %s é uma parâmetro inválido",
                    ex.getValue()) ;
            Problem problem = createProblemBuilder(status, problemType, detail).build();
            return handleExceptionInternal(ex, problem, headers, status, request);

        }

    }
*/
    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {


        // Criei o método joinPath para reaproveitar em todos os métodos que precisam
        // concatenar os nomes das propriedades (separando por ".")
        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.JSON_INVALIDO;
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);
        Problem problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_TO_USER).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }
    // NoHandlerFoundException
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExpecionGenerica(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_INESPERADO;
        String detail = "Ocorreu um erro interno inesperado no sistema. "
                + "Tente novamente e se o problema persistir, entre em contato "
                + "com o administrador do sistema.";

        // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
        // fazendo logging) para mostrar a stacktrace no console
        // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
        // para você durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.JSON_INVALIDO;
        String detail = String.format("O recurso '%s' que você tentou acessar não existe",
                ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);

    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.JSON_INVALIDO;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compativel com o tipo %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
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
                    .localtime(LocalDateTime.now())
                    .build();



        }
        else if (body instanceof String){
            body = Problem.builder()
                    .status(status.value())
                    .title((String) body)
                    .localtime(LocalDateTime.now())
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

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }


}
