package br.com.cvc.agendamento.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "data_transferencia")
    private LocalDate dataTransferencia;

    @Column(name = "data_agendamento")
    private LocalDate dataAgendamento;

    @Column(name = "id_usuario")
    private Long idUsuario;

    public static class AgendamentoBuilder {
        public AgendamentoBuilder agendamentoValido() {
            id = 1L;
            contaOrigem = 12345;
            contaDestino = 45678;
            valor = 100d;
            taxa = 0.06d;
            dataTransferencia = LocalDate.now();
            dataAgendamento = LocalDate.now();
            idUsuario = 1L;
            return this;
        }
    }
}
