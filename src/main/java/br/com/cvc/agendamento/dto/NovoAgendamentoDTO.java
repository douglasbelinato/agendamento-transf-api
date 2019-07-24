package br.com.cvc.agendamento.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NovoAgendamentoDTO implements Serializable {

    private static final long serialVersionUID = -6568072843296906933L;

    private Long id;
    private Double taxa;
}
