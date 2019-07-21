package br.com.cvc.agendamento.service;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.exception.BusinessException;

public interface AgendamentoService {

    ConsultaAgendamentosDTO listar(FiltroConsultaAgendamentoDTO dto);

    NovoAgendamentoDTO inserir(AgendamentoDTO dto) throws BusinessException;
}
