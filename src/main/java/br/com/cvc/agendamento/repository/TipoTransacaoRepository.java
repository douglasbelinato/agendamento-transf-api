package br.com.cvc.agendamento.repository;

import br.com.cvc.agendamento.model.TipoTransacao;
import br.com.cvc.agendamento.model.TipoTransacaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoTransacaoRepository extends JpaRepository<TipoTransacao, TipoTransacaoId> {

    @Query("select  tt " +
            "from   TipoTransacao tt " +
            "where  :qtdDiasAgendamento between tt.qtdDiasDe and tt.qtdDiasAte " +
            "  and  :valor between tt.valorTransfDe and tt.valorTransfAte " +
            "  and  tt.flagAtiva = 'S' " +
            "order by id.codTipoTransacao, id.faixaPrioridade")
    TipoTransacao findByQtdDiasAndValor(@Param("qtdDiasAgendamento") Integer qtdDiasAgendamento,
                                        @Param("valor") Double valor);
}
