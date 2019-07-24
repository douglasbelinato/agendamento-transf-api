package br.com.cvc.agendamento.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Entity
@Table(name = "agendamentos_transferencia")
public class Agendamento implements Serializable {

    private static final long serialVersionUID = -8533322178744805225L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "conta_origem")
    private Integer contaOrigem;

    @Column(name = "conta_destino")
    private Integer contaDestino;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "taxa")
    private Double taxa;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_transferencia")
    private LocalDate dataTransferencia;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_agendamento")
    private LocalDate dataAgendamento;

    public static class AgendamentoBuilder {
        public AgendamentoBuilder agendamentoValido() {
            id = 1L;
            contaOrigem = 12345;
            contaDestino = 45678;
            valor = 100d;
            taxa = 0.06d;
            dataTransferencia = LocalDate.now();
            dataAgendamento = LocalDate.now();
            return this;
        }
    }
}
