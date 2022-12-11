# Clean Arch example

Exemplo de arquitetura clean.

# Requisitos

- Java 17

# Comandos

Compilar o projeto:

```sh
./gradlew build -x test
```

Rodar os testes:

```sh
./gradlew test
```

Executar o projeto:

```sh
./gradlew run
```

Verificar cobertura dos testes:

```sh
./gradlew jacocoRootReport
```

# Sobre

As camadas foram separadas em start, entrypoint, use case, gateway e entity, visando as dependências e tecnologias
utilizadas por cada uma delas.

Os subprojetos `base` são as interfaces adapters, possuindo interfaces e contratos de request/response para fazer a
inversão de dependência entre as camadas.

# Problemas

Foi necessário adicionar uma lib para gerenciar transações na camada de usecaseimpl, o que não faz muito sentido, já que
essa camada não deveria saber se os processos dos gateways foram feitos através de banco de dados ou nāo.

Foi necessário adicionar a lib do spring nas camadas de gatewayimpl e usecaseimpl por conta da anotação @Service, o que
não faz muito sentido, já que elas deveriam ser executadas independente do framework utilizado no projeto como um todo.

