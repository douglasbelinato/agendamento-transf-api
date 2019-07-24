# API de Agendamento de Transferências

API com endpoints (resources) para consulta e gravação de agendamentos de transferências.

### Compilação

```
mvn clean install
```

### Modo de execução - Opção 1
Na pasta raiz da aplicação, execute o comando maven:
```
mvn spring-boot:run
```

### Modo de execução - Opção 2
Na pasta raiz da aplicação, execute o comando maven:
```
java -jar target/agendamento-transf-api.jar
```

### URL e endpoints (resources da API) para serem acessados localmente
Após a aplicação ser inicializada, ela estará disponível através do seguinte endereço: http://localhost:8080/
- Operações:
1. GET /agendamentos - Lista todos os agendamentos criados
2. POST /agendamentos - Cadastra um novo agendamento

### H2 - Banco em Memória- Console administrativo
http://localhost:8080/h2-console

### Swagger UI - Documentação da API e interface amigável para navegração
http://localhost:8080/swagger-ui.html
