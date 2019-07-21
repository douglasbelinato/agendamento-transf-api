package br.com.cvc.agendamento.service;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.model.Agendamento;
import br.com.cvc.agendamento.model.TipoTransacao;
import br.com.cvc.agendamento.repository.AgendamentoRepository;
import br.com.cvc.agendamento.repository.TipoTransacaoRepository;
import br.com.cvc.agendamento.service.impl.AgendamentoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService = new AgendamentoServiceImpl();

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private TipoTransacaoRepository tipoTransacaoRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveListarTodosOsAgendamentosDeUmUsuario() {
        // Cenário
        FiltroConsultaAgendamentoDTO filtroDto = FiltroConsultaAgendamentoDTO.builder().filtroListarAgendamentosDeUmUsuario().build();

        Agendamento agendamento1 = Agendamento.builder().agendamentoValido().build();
        Agendamento agendamento2 = Agendamento.builder().agendamentoValido().build();
        Agendamento agendamento3 = Agendamento.builder().agendamentoValido().build();

        List<Agendamento> agendamentos = new ArrayList<>();
        agendamentos.add(agendamento1);
        agendamentos.add(agendamento2);
        agendamentos.add(agendamento3);

        when(agendamentoRepository.findByIdUsuario(filtroDto.getIdUsuario())).thenReturn(agendamentos);

        // Ação
        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar(filtroDto);

        // Verificação
        assertThat(consultaAgendamentosDTO.getAgendamentos().size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void naoDeveEncontrarAgendamentosDeUmUsuario() {
        // Cenário
        FiltroConsultaAgendamentoDTO filtroDto = FiltroConsultaAgendamentoDTO.builder().filtroListarAgendamentosDeUmUsuario().build();

        when(agendamentoRepository.findByIdUsuario(filtroDto.getIdUsuario())).thenReturn(new ArrayList<>());

        // Ação
        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar(filtroDto);

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

}
