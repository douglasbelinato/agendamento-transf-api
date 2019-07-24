package br.com.cvc.agendamento.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cvc.agendamento.dto.ResponseErroDTO;
import br.com.cvc.agendamento.enums.TipoErroEnum;
import br.com.cvc.agendamento.exception.BusinessException;
import br.com.cvc.agendamento.utils.MensagensUtils;
import io.swagger.annotations.Api;

@Api(value = "agendamento-transf-api", hidden = true)
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private static Logger Logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @Autowired
    private MensagensUtils mensagensUtils;

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ResponseErroDTO> handleBusinessException(BusinessException e) {
    	Logger.error("handleBusinessException()", e);

        ResponseErroDTO response = e.getResponse();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	Logger.error("handleMissingServletRequestParameter()", e);

        String mensagem = mensagensUtils.get("api.erro.request.parametro.nao.encontrado", new String[] { e.getParameterName() });

        ResponseErroDTO response = new ResponseErroDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), mensagem, TipoErroEnum.TECNICO.getDescricao());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ResponseErroDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    	Logger.error("handleMethodArgumentTypeMismatchException()", e);

    	String mensagem = mensagensUtils.get("api.erro.request.tipo.parametro.invalido", new String[] { e.getName() });

        ResponseErroDTO response = new ResponseErroDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), mensagem, TipoErroEnum.NEGOCIO.getDescricao());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseErroDTO> heandleException(Exception e) {
    	Logger.error("heandleException()", e);

    	String mensagem = mensagensUtils.get("api.erro.request.internal.server.error", null);

        ResponseErroDTO response = new ResponseErroDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), mensagem, TipoErroEnum.TECNICO.getDescricao());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
