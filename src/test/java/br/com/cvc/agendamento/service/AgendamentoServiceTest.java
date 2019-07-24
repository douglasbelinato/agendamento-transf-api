package br.com.cvc.agendamento.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.exception.BusinessException;
import br.com.cvc.agendamento.model.Agendamento;
import br.com.cvc.agendamento.model.TipoTransacao;
import br.com.cvc.agendamento.repository.AgendamentoRepository;
import br.com.cvc.agendamento.repository.TipoTransacaoRepository;
import br.com.cvc.agendamento.service.impl.AgendamentoServiceImpl;
import br.com.cvc.agendamento.utils.MensagensUtils;

public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService = new AgendamentoServiceImpl();

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Mock
    private MensagensUtils mensagensUtils;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveListarTodosOsAgendamentos() {
        // Cenário
        Agendamento agendamento1 = Agendamento.builder().agendamentoValido().build();
        Agendamento agendamento2 = Agendamento.builder().agendamentoValido().build();
        Agendamento agendamento3 = Agendamento.builder().agendamentoValido().build();

        List<Agendamento> agendamentos = new ArrayList<>();
        agendamentos.add(agendamento1);
        agendamentos.add(agendamento2);
        agendamentos.add(agendamento3);

        when(agendamentoRepository.findAll()).thenReturn(agendamentos);

        // Ação
        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar();

        // Verificação
        assertThat(consultaAgendamentosDTO.getAgendamentos().size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void naoDeveEncontrarAgendamentos() {
        // Cenário
        when(agendamentoRepository.findAll()).thenReturn(new ArrayList<>());

        // Ação
        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar();

        // Verificação
        assertThat(consultaAgendamentosDTO.getAgendamentos().size(), is(0));
    }

    @Test
    public void deveAgendarTransfNoMesmoDia() {
        // Cenário
        AgendamentoDTO dto = AgendamentoDTO.builder().novoAgendamentoComDataTransfNoMesmoDia().build();
        TipoTransacao tipoTransacao = TipoTransacao.builder().tipoAPrioridade1().build();

        when(tipoTransacaoRepository.findByQtdDiasAndValor(anyInt(), anyDouble())).thenReturn(tipoTransacao);
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(new Agendamento());

        // Ação
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        // Verificação
        assertThat(novoAgendamentoDTO.getTaxa(), is(tipoTransacao.getTaxaFixa() + (dto.getValor() * tipoTransacao.getTaxaPercentual())));
    }

    @Test
    public void deveAgendarTransfAte10Dias() {
        // Cenário
        AgendamentoDTO dto = AgendamentoDTO.builder().novoAgendamentoComDataTransfAte10Dias().build();
        TipoTransacao tipoTransacao = TipoTransacao.builder().tipoBPrioridade1().build();

        long qtdDiasAgendamento = LocalDate.now().until(dto.getDataTransferencia(), ChronoUnit.DAYS);

        when(tipoTransacaoRepository.findByQtdDiasAndValor(anyInt(), anyDouble())).thenReturn(tipoTransacao);
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(new Agendamento());

        // Ação
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        // Verificação
        assertThat(novoAgendamentoDTO.getTaxa(), is(tipoTransacao.getTaxaFixa() * qtdDiasAgendamento));
    }

    @Test
    public void deveAgendarTransfEntre10E20Dias() {
        // Cenário
        AgendamentoDTO dto = AgendamentoDTO.builder().novoAgendamentoComDataTransfEntre10E20Dias().build();
        TipoTransacao tipoTransacao = TipoTransacao.builder().tipoCPrioridade1().build();

        when(tipoTransacaoRepository.findByQtdDiasAndValor(anyInt(), anyDouble())).thenReturn(tipoTransacao);
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(new Agendamento());

        // Ação
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        // Verificação
        assertThat(novoAgendamentoDTO.getTaxa(), is(dto.getValor() * 0.08));
    }

    @Test
    public void deveAgendarTransfEntre20E30Dias() {
        // Cenário
        AgendamentoDTO dto = AgendamentoDTO.builder().novoAgendamentoComDataTransfEntre20E30Dias().build();
        TipoTransacao tipoTransacao = TipoTransacao.builder().tipoCPrioridade2().build();

        when(tipoTransacaoRepository.findByQtdDiasAndValor(anyInt(), anyDouble())).thenReturn(tipoTransacao);
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(new Agendamento());

        // Ação
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        // Verificação
        assertThat(novoAgendamentoDTO.getTaxa(), is(dto.getValor() * 0.06));
    }

    @Test
    public void deveAgendarTransfEntre30E40Dias() {
        // Cenário
        AgendamentoDTO dto = AgendamentoDTO.builder().novoAgendamentoComDataTransfEntre30E40Dias().build();
        TipoTransacao tipoTransacao = TipoTransacao.builder().tipoCPrioridade3().build();

        when(tipoTransacaoRepository.findByQtdDiasAndValor(anyInt(), anyDouble())).thenReturn(tipoTransacao);
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(new Agendamento());

        // Ação
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        // Verificação
        assertThat(novoAgendamentoDTO.getTaxa(), is(dto.getValor() * 0.04));
    }

    @Test
    public void deveAgendarTransfAcima40DiasEValorAcimaCemMil() {
        // Cenário
        AgendamentoDTO dto = AgendamentoDTO.builder().novoAgendamentoComDataTransfAcima40DiasAcimaCemMil().build();
        TipoTransacao tipoTransacao = TipoTransacao.builder().tipoCPrioridade4().build();

        when(tipoTransacaoRepository.findByQtdDiasAndValor(anyInt(), anyDouble())).thenReturn(tipoTransacao);
        when(agendamentoRepository.save(any(Agendamento.class))).thenReturn(new Agendamento());

        // Ação
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        // Verificação
        assertThat(novoAgendamentoDTO.getTaxa(), is(dto.getValor() * 0.02));
    }

    @Test
    public void deveLancarBusinessExceptionQuandoAgendarTransfAcima40DiasEValorAteCemMil() {
        // Cenário
        AgendamentoDTO dto = AgendamentoDTO.builder().novoAgendamentoComDataTransfAcima40DiasAteCemMil().build();

        when(tipoTransacaoRepository.findByQtdDiasAndValor(anyInt(), anyDouble())).thenReturn(null);
        when(mensagensUtils.get("api.agendamento.novo.business.exception.taxa.nao.cadastrada", null)).thenReturn("Nao existe taxa aplicavel para esse tipo de transacao.");

        try {
            // Ação
            agendamentoService.inserir(dto);
        } catch (BusinessException e) {
            assertThat(e.getResponse().getMensagem(), is("Nao existe taxa aplicavel para esse tipo de transacao."));
        }
    }
    
    @Test
    public void deveLancarBusinessExceptionQuandoDataTransfMenorQueDatadeHoje() {
    	// Cenário
        AgendamentoDTO dto = AgendamentoDTO.builder().novoAgendamentoComDataTransfMenorQueDatadeHoje().build();

        when(tipoTransacaoRepository.findByQtdDiasAndValor(anyInt(), anyDouble())).thenReturn(null);
        when(mensagensUtils.get("api.agendamento.novo.business.exception.data.transferencia.incorreta", null)).thenReturn("A data de transferencia deve ser maior ou igual a data de hoje.");

        try {
            // Ação
            agendamentoService.inserir(dto);
        } catch (BusinessException e) {
            assertThat(e.getResponse().getMensagem(), is("A data de transferencia deve ser maior ou igual a data de hoje."));
        }
    }
}
