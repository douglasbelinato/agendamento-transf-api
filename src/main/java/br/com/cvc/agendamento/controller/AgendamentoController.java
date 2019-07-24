package br.com.cvc.agendamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.exception.BusinessException;
import br.com.cvc.agendamento.service.AgendamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "agendamento-transf-api", description = "Operações para agendamento de transferências")
@RestController
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @ApiOperation(value = "Lista todos os agendamentos de transferências", response = ConsultaAgendamentosDTO.class)
    @GetMapping(value = "/agendamentos")
    public ResponseEntity<ConsultaAgendamentosDTO> listar() {
        ConsultaAgendamentosDTO consultaAgendamentosDTO = agendamentoService.listar();

        return ResponseEntity.ok().body(consultaAgendamentosDTO);
    }

    @ApiOperation(value = "Salva um novo agendamento de transferência de um usuário", response = NovoAgendamentoDTO.class)
    @PostMapping(value = "/agendamentos", consumes = "application/json")
    public ResponseEntity<NovoAgendamentoDTO> novo(@RequestBody AgendamentoDTO dto) throws BusinessException {
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        return ResponseEntity.ok().body(novoAgendamentoDTO);
    }
}