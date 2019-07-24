package br.com.cvc.agendamento.dto;

import br.com.cvc.agendamento.model.Agendamento;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsultaAgendamentosDTO implements Serializable {

    private static final long serialVersionUID = -8808507295757697554L;

    private List<Agendamento> agendamentos;

    public static class ConsultaAgendamentosDTOBuilder {
        public ConsultaAgendamentosDTOBuilder comListaAgendamentos() {
            agendamentos = new ArrayList<>();
            Agendamento agendamento1 = new Agendamento();
            agendamentos.add(agendamento1);
            return this;
        }
    }

}
