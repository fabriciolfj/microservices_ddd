package br.com.spark.service.order.api.exceptions;

import br.com.spark.service.order.domain.exceptions.OrderDuplicatePaymentException;
import br.com.spark.service.order.domain.exceptions.OrderItemNotFoundException;
import br.com.spark.service.order.domain.exceptions.OrderNotFoundException;
import br.com.spark.service.order.domain.exceptions.PaymentNotFoundException;
import br.com.spark.service.order.domain.exceptions.ProductClientException;
import br.com.spark.service.order.domain.exceptions.StatusCartInvalidException;
import br.com.spark.service.order.domain.exceptions.StatusOrderInvalidException;
import br.com.spark.service.order.domain.exceptions.StatusPaymentInvalidException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
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
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String ERRO_INTERNO_INESPERADO = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entrar em contato com o adminstrador do sistema";

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).headers(headers ).build();
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(ex instanceof NoHandlerFoundException){
            return handleNotFound((NoHandlerFoundException) ex, headers, status, request);
        }

        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleNotFound(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
        var problem = createProblemBuilder(status, detail).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(status, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var rootCause = ExceptionUtils.getRootCause(e);

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        if(rootCause instanceof PropertyBindingException){
            return handlePropertyException((PropertyBindingException) rootCause, headers, status, request);
        }

        var problem = createProblemBuilder(status, "O corpo da requisição está inválida. Verifique erro de sintaxe.").build();
        return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlePropertyException(PropertyBindingException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = e.getPath().stream().map(ex -> ex.getFieldName()).collect(Collectors.joining("."));
        String detail = String.format("A propriedade '%s', encontra-se ignorada ou não existe no objeto correspondente.", path);

        var problem =  createProblemBuilder(status, detail)
                .userMessage(ERRO_INTERNO_INESPERADO)
                .build();;

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = e.getPath().stream().map(m -> m.getFieldName()).collect(Collectors.joining("."));

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é um tipo inválido. Corrija e informe um valor compátivel com o tipo %s.",
                path, e.getValue(), e.getTargetType().getSimpleName());

        var problem =  createProblemBuilder(status, detail).userMessage(ERRO_INTERNO_INESPERADO).build();;

        return handleExceptionInternal(e, problem, headers, status, request);
    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    public ResponseEntity<?> handleOrderItemNotFoundException(OrderItemNotFoundException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(StatusCartInvalidException.class)
    public ResponseEntity<?> handleStatusCartInvalidException(StatusCartInvalidException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(OrderDuplicatePaymentException.class)
    public ResponseEntity<?> handleOrderDuplicatePaymentException(OrderDuplicatePaymentException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<?> handlePaymentNotFoundException(PaymentNotFoundException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(StatusOrderInvalidException.class)
    public ResponseEntity<?> handleStatusOrderInvalidException(StatusOrderInvalidException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ProductClientException.class)
    public ResponseEntity<?> handleStatusProductClientException(ProductClientException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(StatusPaymentInvalidException.class)
    public ResponseEntity<?> handleStatusPaymentInvalidException(StatusPaymentInvalidException e, WebRequest request){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(ERRO_INTERNO_INESPERADO)
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(ERRO_INTERNO_INESPERADO)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleErrorSistema(Exception ex, WebRequest request){
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var problem = createProblemBuilder(status, ERRO_INTERNO_INESPERADO).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, String detail){
        return Problem.builder()
                .title(status.getReasonPhrase())
                .detail(detail)
                .status(status.value())
                .timestamp(OffsetDateTime.now());
    }

}
