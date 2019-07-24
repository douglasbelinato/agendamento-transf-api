package br.com.cvc.agendamento.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class TipoTransacaoId implements Serializable {

    private static final long serialVersionUID = 7446880428681137428L;

    @Column(name = "cod_tipo_transacao")
    private String codTipoTransacao;

    @Column(name = "faixa_prioridade")
    private Integer faixaPrioridade;
}
