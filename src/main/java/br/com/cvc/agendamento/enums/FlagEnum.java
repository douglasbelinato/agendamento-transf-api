package br.com.cvc.agendamento.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FlagEnum {

    SIM("S", "Sim"),
    NAO("N", "Não");

    private String codigo;
    private String descricao;
}
