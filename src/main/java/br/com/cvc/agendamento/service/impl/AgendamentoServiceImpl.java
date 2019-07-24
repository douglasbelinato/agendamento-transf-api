package br.com.cvc.agendamento.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger log = LoggerFactory.getLogger(AgendamentoServiceImpl.class);

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Autowired
    private MensagensUtils mensagensUtils;

    @Override
    public ConsultaAgendamentosDTO listar() {
        log.info("Iniciando consulta de agendamentos");
    	
    	List<Agendamento> agendamentos = agendamentoRepository.findAll();

    	log.info("Consulta de agendamentos realizada com sucess");

        ConsultaAgendamentosDTO consultaAgendamentosDTO = new ConsultaAgendamentosDTO();
        consultaAgendamentosDTO.setAgendamentos(agendamentos);

        return consultaAgendamentosDTO;
    }

    @Override
    public NovoAgendamentoDTO inserir(AgendamentoDTO dto) {
    	validarDadosDeEntrada(dto);
    	
    	log.info("Inciando processo de inclusão de novo agendamento de transferencia");
    	
    	LocalDate dataHoje = LocalDate.now();
    	long qtdDiasAgendamento = dataHoje.until(dto.getDataTransferencia(), ChronoUnit.DAYS);

        TipoTransacao tipoTransacao = tipoTransacaoRepository.findByQtdDiasAndValor((int) qtdDiasAgendamento, dto.getValor());

        if (tipoTransacao != null) {
        	log.info("Transação possui taxa cadastrada - Tipo da Taxa: {} - Prioridade {}",tipoTransacao.getId().getCodTipoTransacao(), tipoTransacao.getId().getFaixaPrioridade());
            
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
            agendamento.setDataAgendamento(dataHoje);

            Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
            
            log.info("Agendamento de transferencia incluído com sucesso");

            novoAgendamentoDTO.setId(agendamentoSalvo.getId());
            novoAgendamentoDTO.setTaxa(taxa);
            return novoAgendamentoDTO;
        }
        
        String mensagem = mensagensUtils.get("api.agendamento.novo.business.exception.taxa.nao.cadastrada", null);
       throw new BusinessException(mensagem);
    }
    
    private void validarDadosDeEntrada(AgendamentoDTO dto) {
    	log.info("Iniciando validação dos dados de entrada do novo agendamento");
    	
    	String mensagem = null;
    	
    	long qtdDiasAgendamento = LocalDate.now().until(dto.getDataTransferencia(), ChronoUnit.DAYS);
        
        if (qtdDiasAgendamento < 0) {
        	mensagem = mensagensUtils.get("api.agendamento.novo.business.exception.data.transferencia.incorreta", null);
            throw new BusinessException(mensagem);
        }
    }
}