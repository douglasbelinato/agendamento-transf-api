package br.com.cvc.agendamento.service.impl;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.enums.FlagEnum;
import br.com.cvc.agendamento.exception.BusinessException;
import br.com.cvc.agendamento.model.Agendamento;
import br.com.cvc.agendamento.model.TipoTransacao;
import br.com.cvc.agendamento.repository.AgendamentoRepository;
import br.com.cvc.agendamento.repository.TipoTransacaoRepository;
import br.com.cvc.agendamento.service.AgendamentoService;
import br.com.cvc.agendamento.utils.MensagensUtils;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    private MensagensUtils mensagensUtils;

    @Override
    public ConsultaAgendamentosDTO listar() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        if (agendamentos == null) {
            agendamentos = new ArrayList<>();
        }

        ConsultaAgendamentosDTO consultaAgendamentosDTO = new ConsultaAgendamentosDTO();
        consultaAgendamentosDTO.setAgendamentos(agendamentos);

        return consultaAgendamentosDTO;
    }

    @Override
    public NovoAgendamentoDTO inserir(AgendamentoDTO dto) throws BusinessException {
    	validarDadosDeEntrada(dto);    	
    	
    	long qtdDiasAgendamento = dto.getDataAgendamento().until(dto.getDataTransferencia(), ChronoUnit.DAYS);

        TipoTransacao tipoTransacao = tipoTransacaoRepository.findByQtdDiasAndValor((int) qtdDiasAgendamento, dto.getValor());

        if (tipoTransacao != null) {
            NovoAgendamentoDTO novoAgendamentoDTO = new NovoAgendamentoDTO();
            Double taxa = 0d;

            if (tipoTransacao.getTaxaFixa().intValue() > 0) {
                if (tipoTransacao.getFlagFatorQtdDias().equalsIgnoreCase(FlagEnum.SIM.getCodigo())) {
                    taxa += tipoTransacao.getTaxaFixa() * qtdDiasAgendamento;
                } else {
                    taxa += tipoTransacao.getTaxaFixa();
                }
            }

            if (tipoTransacao.getTaxaPercentual().doubleValue() > 0d) {
                taxa += tipoTransacao.getTaxaPercentual() * dto.getValor();
            }

            Agendamento agendamento = new Agendamento();
            agendamento.setContaOrigem(dto.getContaOrigem());
            agendamento.setContaDestino(dto.getContaDestino());
            agendamento.setValor(dto.getValor());
            agendamento.setTaxa(taxa);
            agendamento.setDataTransferencia(dto.getDataTransferencia());
            agendamento.setDataAgendamento(dto.getDataAgendamento());

            Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

            novoAgendamentoDTO.setId(agendamentoSalvo.getId());
            novoAgendamentoDTO.setTaxa(taxa);
            return novoAgendamentoDTO;
        }
        
        List<String> mensagens = new ArrayList<>();
        mensagens.add(mensagensUtils.get("api.agendamento.novo.business.exception.taxa.nao.cadastrada", null));
        throw new BusinessException(mensagens);
    }
    
    private void validarDadosDeEntrada(AgendamentoDTO dto) {
    	List<String> mensagens = new ArrayList<>();
    	
    	long qtdDiasAgendamento = dto.getDataAgendamento().until(dto.getDataTransferencia(), ChronoUnit.DAYS);
        
        if (qtdDiasAgendamento < 0) {
        	mensagens.add(mensagensUtils.get("api.agendamento.novo.business.exception.data.transferencia.incorreta", null));
            throw new BusinessException(mensagens);
        }
    }
}
