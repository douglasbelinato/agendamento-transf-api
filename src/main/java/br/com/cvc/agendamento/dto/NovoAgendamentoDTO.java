package br.com.cvc.agendamento.dto;

import br.com.cvc.agendamento.model.TipoTransacao;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

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
