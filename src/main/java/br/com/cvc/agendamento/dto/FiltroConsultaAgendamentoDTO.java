package br.com.cvc.agendamento.dto;

import lombok.*;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FiltroConsultaAgendamentoDTO implements Serializable {

    private static final long serialVersionUID = 2587027965278424699L;

    private Long idUsuario;
    private Integer pagina;
    private Integer qtdRegistrosPagina;
    private Integer colunaOrdenacao;

    public static class FiltroConsultaAgendamentoDTOBuilder {
        public FiltroConsultaAgendamentoDTOBuilder filtroListarAgendamentosDeUmUsuario() {
            idUsuario = 10L;
            pagina = 1;
            qtdRegistrosPagina = 5;
            colunaOrdenacao = 4;
            return this;
        }
    }
}
