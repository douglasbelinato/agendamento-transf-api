package br.com.cvc.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErroDTO implements Serializable {

    private static final long serialVersionUID = 1816224101512105796L;

    private int httpStatusCode;

    private String httpStatusMessage;

    private List<String> mensagens;
}
