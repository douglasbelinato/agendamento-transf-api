package br.com.cvc.agendamento.controller;

import br.com.cvc.agendamento.dto.AgendamentoDTO;
import br.com.cvc.agendamento.dto.ConsultaAgendamentosDTO;
import br.com.cvc.agendamento.dto.FiltroConsultaAgendamentoDTO;
import br.com.cvc.agendamento.dto.NovoAgendamentoDTO;
import br.com.cvc.agendamento.exception.BusinessException;
import br.com.cvc.agendamento.service.AgendamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "agendamento-transf-api", description = "Operações para agendamento de transferências")
@RestController
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @ApiOperation(value = "Lista todos os agendamentos de transferências de um usuário", response = ConsultaAgendamentosDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idUsuario", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pagina", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "qtdRegistrosPagina", required = true, paramType = "query", example = "10"),
            @ApiImplicitParam(name = "colunaOrdenacao", required = false, paramType = "query", example = "4")
    })
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

    @ApiOperation(value = "Salva um novo agendamento de transferência de um usuário", response = NovoAgendamentoDTO.class)
    @PostMapping(value = "/agendamentos", consumes = "application/json")
    public ResponseEntity<?> novo(@RequestBody AgendamentoDTO dto) throws BusinessException {
        NovoAgendamentoDTO novoAgendamentoDTO = agendamentoService.inserir(dto);

        return ResponseEntity.ok().body(novoAgendamentoDTO);
    }
}