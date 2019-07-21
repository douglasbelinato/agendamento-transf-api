package br.com.cvc.agendamento.exception;

import br.com.cvc.agendamento.dto.ResponseErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    private ResponseErroDTO response;

    public BusinessException() {
        super();
    }

    public BusinessException(List<String> mensagens) {
        super();
        response = new ResponseErroDTO();
        response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setHttpStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMensagens(mensagens);
    }

    public ResponseErroDTO getResponse() {
        return response;
    }

}
