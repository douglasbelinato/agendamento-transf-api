package br.com.cvc.agendamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cvc.agendamento.dto.ResponseErroDTO;
import br.com.cvc.agendamento.enums.TipoErroEnum;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -6403647326719815387L;
	
	private final ResponseErroDTO response;

    public BusinessException() {
    	super();
    	response = new ResponseErroDTO();
    }

    public BusinessException(String mensagem) {
        super();
        response = new ResponseErroDTO();
        response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setHttpStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMensagem(mensagem);
        response.setTipoErro(TipoErroEnum.NEGOCIO.getDescricao());
    }

    public ResponseErroDTO getResponse() {
        return response;
    }

}
