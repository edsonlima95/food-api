package com.edson.foodapi.api.exception;

import com.edson.foodapi.domain.exception.BadRequestException;
import com.edson.foodapi.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHanlder extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private static final String MSG_ERROR_INTERAL = "Ocorreu um erro iterno no sistema, tente novamente se persistir" +
            "entre em contato com o suporte";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> Exception() {

        String title = "Erro interno na aplicação";
        String type = "http://food-api/erro-interno";

        Problem problem = getProblem(title, type, MSG_ERROR_INTERAL, HttpStatus.INTERNAL_SERVER_ERROR)
                .build();


        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
        //return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {

        String title = "Entidade não encontrada";
        String type = "http://food-api/entidade-nao-encontrada";
        String detail = e.getMessage();

        Problem problem = getProblem(title, type, detail, HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(problem, HttpStatus.NOT_FOUND);
        //return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {

        String title = "Regra de negócio";
        String type = "http://food-api/regra-de-negocio";
        String detail = e.getMessage();

        Problem problem = getProblem(title, type, detail, HttpStatus.BAD_REQUEST)
                .userMessage(MSG_ERROR_INTERAL)
                .build();

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {

        String title = "Erro converção de tipos";
        String type = "http://food-api/erro-na-converção";
        String detail = String.format("Erro no parâmetro da url, tipo requerido 'Numérico' mas o valor" +
                "informado foi %s", e.getValue());

        Problem problem = getProblem(title, type, detail, HttpStatus.BAD_REQUEST).build();

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(){
        String title = "Erro de integridade";
        String type = "http://food-api/erro-de-itegridade";
        String detail = "Parece que o recurso está sendo usado por outra entidade";

        Problem problem = getProblem(title, type, detail, HttpStatus.CONFLICT).build();

//        return  ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
        return new ResponseEntity<>(problem, HttpStatus.CONFLICT);
    }



    //EXCEPTIONS DA SUPERCLASSE
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String title = "Dados inválidos";
        String type = "http://food-api/dados-invalidos";
        String detail = "Um ou mais campos estão incorretos, verifique os campos e tente novamente";

        List<Problem.FieldValidation> fields = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {

            String fieldName = ((FieldError) error).getField();
            //String message = error.getDefaultMessage();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new Problem.FieldValidation(fieldName, message));

        });

        Problem problem = getProblem(title, type, detail, HttpStatus.BAD_REQUEST)
                .fieldValidations(fields)
                .build();

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String title = "Metodo não permitido";
        String type = "http://food-api/metodo-nao-permitido";
        String detail = "A url que você tentou acessar não existe ou não estã acessivel";

        Problem problem = getProblem(title, type, detail, HttpStatus.METHOD_NOT_ALLOWED).build();

        return new ResponseEntity<>(problem, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String title = "Mensagem incompreensível";
        String type = "http://food-api/mensagem-incompreensivel";
        String detail = "Erro de sintáxe, verifique a sintaxe json da request se está corretamente";

        Problem problem = getProblem(title, type, detail, HttpStatus.BAD_REQUEST).build();

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }


    private Problem.ProblemBuilder getProblem(String title, String type, String detail, HttpStatus status) {
        return Problem.builder()
                .title(title)
                .status(status.value())
                .type(type)
                .detail(detail)
                .userMessage(MSG_ERROR_INTERAL)
                .dateTime(LocalDateTime.now());
    }

    private Problem.ProblemBuilder getProblem(String title, HttpStatus status) {
        return Problem.builder()
                .title(title)
                .status(status.value());
    }


    /* protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                              HttpStatus status, WebRequest request) {

         if (body == null) {
             body = Problem.builder()
                     .title(status.getReasonPhrase())
                     .status(status.value())
                     .build();
         } else if (body instanceof String) {
             body = Problem.builder()
                     .title((String) body)
                     .status(status.value())
                     .build();
         }

         return super.handleExceptionInternal(ex, body, headers, status, request);
     }
 */

}
