package br.com.cvc.agendamento.model;

import br.com.cvc.agendamento.enums.FlagEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tipo_transacao")
public class TipoTransacao implements Serializable {

    private static final long serialVersionUID = -7877292826859116141L;

    @EmbeddedId
    private TipoTransacaoId id;

    @Column(name = "qtd_dias_de")
    private Integer qtdDiasDe;

    @Column(name = "qtd_dias_ate")
    private Integer qtdDiasAte;

    @Column(name = "valor_transf_de")
    private Double valorTransfDe;

    @Column(name = "valor_transf_ate")
    private Double valorTransfAte;

    @Column(name = "taxa_fixa")
    private Double taxaFixa;

    @Column(name = "taxa_percentual")
    private Double taxaPercentual;

    @Column(name = "flag_fator_qtd_dias")
    private String flagFatorQtdDias;

    @Column(name = "data_inclusao")
    private LocalDateTime dataInclusao;

    @Column(name = "flag_ativa")
    private String flagAtiva;

    public static class TipoTransacaoBuilder {
        public TipoTransacaoBuilder tipoAPrioridade1() {
            TipoTransacaoId tipoTransacaoId = new TipoTransacaoId();
            tipoTransacaoId.setCodTipoTransacao("A");
            tipoTransacaoId.setFaixaPrioridade(1);

            id = tipoTransacaoId;
            qtdDiasDe = 0;
            qtdDiasAte = 0;
            valorTransfDe = 0d;
            valorTransfAte = 999999.99d;
            taxaFixa = 3d;
            taxaPercentual = 0.03d;
            flagFatorQtdDias = FlagEnum.NAO.getCodigo();
            flagAtiva = FlagEnum.SIM.getCodigo();
            dataInclusao = LocalDateTime.now();
            return this;
        }

        public TipoTransacaoBuilder tipoBPrioridade1() {
            TipoTransacaoId tipoTransacaoId = new TipoTransacaoId();
            tipoTransacaoId.setCodTipoTransacao("B");
            tipoTransacaoId.setFaixaPrioridade(1);

            id = tipoTransacaoId;
            qtdDiasDe = 1;
            qtdDiasAte = 10;
            valorTransfDe = 0d;
            valorTransfAte = 999999.99d;
            taxaFixa = 12d;
            taxaPercentual = 0d;
            flagFatorQtdDias = FlagEnum.SIM.getCodigo();
            flagAtiva = FlagEnum.SIM.getCodigo();
            dataInclusao = LocalDateTime.now();
            return this;
        }

        public TipoTransacaoBuilder tipoCPrioridade1() {
            TipoTransacaoId tipoTransacaoId = new TipoTransacaoId();
            tipoTransacaoId.setCodTipoTransacao("C");
            tipoTransacaoId.setFaixaPrioridade(1);

            id = tipoTransacaoId;
            qtdDiasDe = 11;
            qtdDiasAte = 20;
            valorTransfDe = 0d;
            valorTransfAte = 999999.99d;
            taxaFixa = 0d;
            taxaPercentual = 0.08d;
            flagFatorQtdDias = FlagEnum.SIM.getCodigo();
            flagAtiva = FlagEnum.SIM.getCodigo();
            dataInclusao = LocalDateTime.now();
            return this;
        }

        public TipoTransacaoBuilder tipoCPrioridade2() {
            TipoTransacaoId tipoTransacaoId = new TipoTransacaoId();
            tipoTransacaoId.setCodTipoTransacao("C");
            tipoTransacaoId.setFaixaPrioridade(2);

            id = tipoTransacaoId;
            qtdDiasDe = 21;
            qtdDiasAte = 30;
            valorTransfDe = 0d;
            valorTransfAte = 999999.99d;
            taxaFixa = 0d;
            taxaPercentual = 0.06d;
            flagFatorQtdDias = FlagEnum.SIM.getCodigo();
            flagAtiva = FlagEnum.SIM.getCodigo();
            dataInclusao = LocalDateTime.now();
            return this;
        }

        public TipoTransacaoBuilder tipoCPrioridade3() {
            TipoTransacaoId tipoTransacaoId = new TipoTransacaoId();
            tipoTransacaoId.setCodTipoTransacao("C");
            tipoTransacaoId.setFaixaPrioridade(3);

            id = tipoTransacaoId;
            qtdDiasDe = 31;
            qtdDiasAte = 40;
            valorTransfDe = 0d;
            valorTransfAte = 999999.99d;
            taxaFixa = 0d;
            taxaPercentual = 0.04d;
            flagFatorQtdDias = FlagEnum.SIM.getCodigo();
            flagAtiva = FlagEnum.SIM.getCodigo();
            dataInclusao = LocalDateTime.now();
            return this;
        }

        public TipoTransacaoBuilder tipoCPrioridade4() {
            TipoTransacaoId tipoTransacaoId = new TipoTransacaoId();
            tipoTransacaoId.setCodTipoTransacao("C");
            tipoTransacaoId.setFaixaPrioridade(4);

            id = tipoTransacaoId;
            qtdDiasDe = 41;
            qtdDiasAte = 360;
            valorTransfDe = 200000d;
            valorTransfAte = 999999.99d;
            taxaFixa = 0d;
            taxaPercentual = 0.02d;
            flagFatorQtdDias = FlagEnum.SIM.getCodigo();
            flagAtiva = FlagEnum.SIM.getCodigo();
            dataInclusao = LocalDateTime.now();
            return this;
        }
    }
}

