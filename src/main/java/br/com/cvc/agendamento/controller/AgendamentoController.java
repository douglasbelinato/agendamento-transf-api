package br.com.cvc.agendamento.controller;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.exception.BusinessException;
import br.com.cvc.agendamento.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping(value = "/agendamentos")
    public ResponseEntity<?> listar(@RequestParam(value="idUsuario", required = true) Long idUsuario,
                                    @RequestParam(value="pagina", required = true) Integer pagina,
                                    @RequestParam(value="qtdRegistrosPagina", required = true) Integer qtdRegistrosPagina,
                                    @RequestParam(value="colunaOrdenacao", required = false) Integer colunaOrdenacao) {

        FiltroConsultaAgendamentoDTO dto = new FiltroConsultaAgendamentoDTO();
        dto.setIdUsuario(idUsuario);
        dto.setPagina(pagina);
        dto.setQtdRegistrosPagina(qtdRegistrosPagina);
        dto.setColunaOrdenacao(colunaOrdenacao);

        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar(dto);

        return ResponseEntity.ok().body(consultaAgendamentosDTO);
    }

    @PostMapping(value = "/agendamentos", consumes = "application/json")
    public ResponseEntity<?> novo(@RequestBody AgendamentoDTO dto) throws BusinessException {
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        return ResponseEntity.ok().body(novoAgendamentoDTO);
    }
}
