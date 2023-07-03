package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_TO_USER = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato  com o administrador do sistema.";
    // MethodArgumentNotValidException

    @Autowired
    private MessageSource messageSource;

/*  O PROFESSOR ENSINOU A FAZER COM LAMBDA
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        BindingResult bindingResult = ex.getBindingResult();

        List<Problem.Field> problemObjects = bindingResult.getAllErrors()
                .stream().map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String objetoTexto = objectError.getObjectName();

                    if (objectError instanceof FieldError){
                        objetoTexto = ((FieldError) objectError).getField();
                    }



                   return Problem.Field.builder()
                            .name(objetoTexto)
                            .userMessage(message).build();
                }).collect(Collectors.toList());



        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();


        return handleExceptionInternal(ex,problem,new HttpHeaders(),status,request);

    }
*/


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        //return super.handleBindException(ex, headers, status, request);
        return handleValidationInternal(ex, status, request, ex.getBindingResult() );
    }

    // É IGUAL DO ACIMA, PORÉM COM LOOP E TAMBÉM SEM BUILD DO OBJETO
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        return handleValidationInternal(ex, status, request, ex.getBindingResult() );

    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex,
                                                            HttpStatus status,
                                                            WebRequest request,
                                                            BindingResult bindingResult) {
        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        // Input
        List<ObjectError> objectErrorList = bindingResult.getAllErrors();
        // Output
        List<Problem.Field> problemObjects = new ArrayList<Problem.Field>();
        // Percorro os erros
        for (ObjectError objectError : objectErrorList){
            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            String objetoTexto = objectError.getObjectName();

            if (objectError instanceof FieldError){
                objetoTexto = ((FieldError) objectError).getField();
            }

            var problemField = new Problem.Field();
            problemField.setName(objetoTexto);
            problemField.setUserMessage(message);

            problemObjects.add(problemField);

            /* FIZ DA FORMA MANUAL, MAS DÁ PARA FAZER COM O CONCEITO DE BUILD
            problemObjects.add(Problem.Field.builder()
                    .name(objetoTexto)
                    .userMessage(message).build());

             */
        }

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();


        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);



        if (rootCause instanceof InvalidFormatException){

            return handleInvalidFormatException( (InvalidFormatException) rootCause, headers,  status,  request);
        }
        else if (rootCause instanceof PropertyBindingException) {

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

    /* MESMA COISA QUE ACIMA
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

    // ESSE É PARA TRATAR O INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExpecionGenerica(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_INESPERADO;
        System.out.println("#ApiExceptionHandler.handleExpecionGenerica");
        String detail = MSG_TO_USER;

        // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
        // fazendo logging) para mostrar a stacktrace no console
        // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
        // para você durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_TO_USER)
                .localtime(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    // TRATA JSON EM FORMATO INVÁLIDO, ERRO DE SINTAXE
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.JSON_INVALIDO;
        String detail = String.format("O recurso '%s' que você tentou acessar não existe",
                ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);

    }
    // TRATA TIPO DE DADO INVÁLIDO, EXEMPLO ATRIBUIR STRING ONDE É NUMERO
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

    // ESSE DÁ O ERRO BONITINHO EM CASO DE ENTIDADE NÃO ENCONTRADA
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = e.getMessage();
        Problem problem = createProblemBuilder(status,problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(e,problem,new HttpHeaders(),status,request);
    }


    // TRATA ENTIDADE EM USO
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
                    .userMessage(MSG_TO_USER)
                    .build();



        }
        else if (body instanceof String){
            body = Problem.builder()
                    .status(status.value())
                    .title((String) body)
                    .localtime(LocalDateTime.now())
                    .userMessage(MSG_TO_USER)
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