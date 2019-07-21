package br.com.cvc.agendamento.service.impl;

import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;
import br.com.cvc.agendamento.model.Agendamento;
import br.com.cvc.agendamento.repository.AgendamentoRepository;
import br.com.cvc.agendamento.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;


    @Override
    public ConsultaAgendamentosDTO listar(FiltroConsultaAgendamentoDTO dto) {
        List<Agendamento> agendamentos = agendamentoRepository.findByIdUsuario(dto.getIdUsuario());

        if (agendamentos == null) {
            agendamentos = new ArrayList<>();
        }

        ConsultaAgendamentosDTO consultaAgendamentosDTO = new ConsultaAgendamentosDTO();
        consultaAgendamentosDTO.setAgendamentos(agendamentos);
        consultaAgendamentosDTO.setPagina(dto.getPagina());
        consultaAgendamentosDTO.setQtdRegistrosPagina(dto.getQtdRegistrosPagina());
        consultaAgendamentosDTO.setColunaOrdenacao(dto.getColunaOrdenacao());

        return consultaAgendamentosDTO;
    }

}
