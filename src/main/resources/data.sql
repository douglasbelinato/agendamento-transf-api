-- Populando algumas transferências inicias para massa de dados
insert into agendamentos_transferencia (conta_origem, conta_destino, valor, taxa, data_transferencia, data_agendamento)
    values (12345, 45678, 100, 6, current_timestamp, parsedatetime('05/05/2019 18:47:52', 'dd/MM/yyyy hh:mm:ss'));

insert into agendamentos_transferencia (conta_origem, conta_destino, valor, taxa, data_transferencia, data_agendamento)
    values (12345, 78945, 250, 6, current_timestamp, current_timestamp);

-- Populando regras parametrizadas dos tipos de transação para a cobrança das taxas
insert into tipo_transacao (cod_tipo_transacao, faixa_prioridade, qtd_dias_de, qtd_dias_ate, valor_transf_de, valor_transf_ate, taxa_fixa, taxa_percentual, flag_fator_qtd_dias, data_inclusao, flag_ativa)
    values ('A', 1, 0, 0, 0, 999999.99, 3, 0.03, 'N', current_timestamp, 'S');

insert into tipo_transacao (cod_tipo_transacao, faixa_prioridade, qtd_dias_de, qtd_dias_ate, valor_transf_de, valor_transf_ate, taxa_fixa, taxa_percentual, flag_fator_qtd_dias, data_inclusao, flag_ativa)
    values ('B', 1, 1, 10, 0, 999999.99, 12, 0, 'S', current_timestamp, 'S');

insert into tipo_transacao (cod_tipo_transacao, faixa_prioridade, qtd_dias_de, qtd_dias_ate, valor_transf_de, valor_transf_ate, taxa_fixa, taxa_percentual, flag_fator_qtd_dias, data_inclusao, flag_ativa)
    values ('C', 1, 11, 20, 0, 999999.99, 0, 0.08, 'N', current_timestamp, 'S');

insert into tipo_transacao (cod_tipo_transacao, faixa_prioridade, qtd_dias_de, qtd_dias_ate, valor_transf_de, valor_transf_ate, taxa_fixa, taxa_percentual, flag_fator_qtd_dias, data_inclusao, flag_ativa)
    values ('C', 2, 21, 30, 0, 999999.99, 0, 0.06, 'N', current_timestamp, 'S');

insert into tipo_transacao (cod_tipo_transacao, faixa_prioridade, qtd_dias_de, qtd_dias_ate, valor_transf_de, valor_transf_ate, taxa_fixa, taxa_percentual, flag_fator_qtd_dias, data_inclusao, flag_ativa)
    values ('C', 3, 31, 40, 0, 999999.99, 0, 0.04, 'N', current_timestamp, 'S');

insert into tipo_transacao (cod_tipo_transacao, faixa_prioridade, qtd_dias_de, qtd_dias_ate, valor_transf_de, valor_transf_ate, taxa_fixa, taxa_percentual, flag_fator_qtd_dias, data_inclusao, flag_ativa)
    values ('C', 4, 41, 999, 100000.00, 999999.99, 0, 0.02, 'N', current_timestamp, 'S');