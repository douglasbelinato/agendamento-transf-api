package br.com.cvc.agendamento.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgendamentoDTO implements Serializable {

    private static final long serialVersionUID = -545550541020409288L;

    @ApiModelProperty(notes = "Conta de origem", required = true, example = "12345")
    private Integer contaOrigem;

    @ApiModelProperty(notes = "Conta de destino", required = true, example = "45678")
    private Integer contaDestino;

    @ApiModelProperty(notes = "Valor da transferência", required = true, example = "100")
    private Double valor;

    @ApiModelProperty(notes = "Data de efetivação da transferência", required = true, example = "21/07/2019")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTransferencia;

    public static class AgendamentoDTOBuilder {
        public AgendamentoDTOBuilder novoAgendamentoComDataTransfNoMesmoDia() {
            popularDadosComuns();
            valor = 100d;
            dataTransferencia = LocalDate.now();
            return this;
        }

        public AgendamentoDTOBuilder novoAgendamentoComDataTransfAte10Dias() {
            popularDadosComuns();
            valor = 100d;
            dataTransferencia = LocalDate.now().plusDays(10);
            return this;
        }

        public AgendamentoDTOBuilder novoAgendamentoComDataTransfEntre10E20Dias() {
            popularDadosComuns();
            valor = 100d;
            dataTransferencia = LocalDate.now().plusDays(15);
            return this;
        }

        public AgendamentoDTOBuilder novoAgendamentoComDataTransfEntre20E30Dias() {
            popularDadosComuns();
            valor = 100d;
            dataTransferencia = LocalDate.now().plusDays(25);
            return this;
        }

        public AgendamentoDTOBuilder novoAgendamentoComDataTransfEntre30E40Dias() {
            popularDadosComuns();
            valor = 100d;
            dataTransferencia = LocalDate.now().plusDays(35);
            return this;
        }

        public AgendamentoDTOBuilder novoAgendamentoComDataTransfAcima40DiasAcimaCemMil() {
            popularDadosComuns();
            valor = 200000d;
            dataTransferencia = LocalDate.now().plusDays(45);
            return this;
        }

        public AgendamentoDTOBuilder novoAgendamentoComDataTransfAcima40DiasAteCemMil() {
            popularDadosComuns();
            valor = 100000d;
            dataTransferencia = LocalDate.now().plusDays(45);
            return this;
        }
        
        public AgendamentoDTOBuilder novoAgendamentoComDataTransfMenorQueDatadeHoje() {
            popularDadosComuns();
            valor = 100000d;
            dataTransferencia = LocalDate.now().minusDays(5);
            return this;
        }

        private void popularDadosComuns() {
            contaOrigem = 12345;
            contaDestino = 45678;            
        }
    }
}
