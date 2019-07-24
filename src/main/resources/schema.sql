create table agendamentos_transferencia (
    id bigint auto_increment not null,
    conta_origem int not null,
    conta_destino int not null,
    valor double not null,
    taxa double not null,
    data_transferencia date not null,
    data_agendamento date not null,
    primary key (id)
);

-- Criando tabela de tipos de transação, responsável pela parametrização das taxas
-- que serão cobradas de acordo com a quantidade de dias agendadas para a transferência
-- e o valor da transferência
create table tipo_transacao (
    cod_tipo_transacao varchar(10) not null,
    faixa_prioridade int not null,
    qtd_dias_de int not null,
    qtd_dias_ate int not null,
    valor_transf_de double not null,
    valor_transf_ate double not null,
    taxa_fixa double not null,
    taxa_percentual double not null,
    flag_fator_qtd_dias varchar(1) not null,
    data_inclusao timestamp not null,
    flag_ativa varchar(1) not null,
    primary key (cod_tipo_transacao, faixa_prioridade)
);
