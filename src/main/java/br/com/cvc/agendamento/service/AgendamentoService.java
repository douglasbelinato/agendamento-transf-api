package br.com.cvc.agendamento.service;

import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;

public interface AgendamentoService {

    ConsultaAgendamentosDTO listar(FiltroConsultaAgendamentoDTO dto);

}
