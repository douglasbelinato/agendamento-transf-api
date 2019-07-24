package br.com.cvc.agendamento.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoErroEnum {
	
	NEGOCIO(1, "NEGOCIO"),
    TECNICO(2, "TECNICO");

    private int codigo;
    private String descricao;

}
