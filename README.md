# Star Wars Resistance Social Network API ![badge](https://img.shields.io/github/languages/top/willyoliv/star_wars_resistance_api_auth)

## Tabela de conteúdos
<!--ts-->
   * [Sobre o projeto](#sobre-o-projeto)
   * [Requisitos do projeto](#requisitos-funcionais-do-projeto)
   * [Executando o projeto](#executando-o-projeto)
   * [EndPoints](#endpoints)
     * [Login](#login)
     * [Adicionar rebelde](#adicionar-rebelde)
     * [Atualizar localização do rebelde (ADMIN)](#atualizar-localização-do-rebeldeadmin)
     * [Relatórios](#relatórios)
     * [Deletar rebelde](#deletar-rebelde)
     * [Listar os rebeldes cadastrados](#listar-os-rebeldes-cadastrados)
     * [Listar os rebeldes cadastrados(com paginação)](#listar-os-rebeldes-cadastradoscom-paginação)
     * [Obter rebelde pelo id](#obter-rebelde-pelo-id)
     * [Obter o inventário de rebelde pelo id](#obter-o-inventário-de-rebelde-pelo-id)
     * [Reportar um rebelde como traidor](#reportar-um-rebelde-como-traidor)
     * [Atualizar localização do rebelde (REBEL)](#atualizar-localização-do-rebelderebel)
     * [Negociar itens](#negociar-itens)
     * [Obter seus dados](#obter-seus-dados)
<!--te-->


## Sobre o projeto
Construção de uma API REST como Projeto final do Módulo **Desenvolvimento web** do curso da Let 's Code **Santander Coders Web Full Stack**. O projeto tem como principal objetivo pôr em prática os conceitos adquiridos em aula. A API foi construída com Springboot utilizando as dependências do Postgres para banco, JPA para criar as tabelas e relacionamentos entre as entidades, Mapper Struct para fazer o processo de mapeamento das entidades.

*"O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os rebeldes. Você, como um soldado da resistência, foi designado para desenvolver um sistema
para compartilhar recursos entre os rebeldes."*

## Requisitos Funcionais do Projeto
### Requisitos para rebel com Role Admin
Desenvolver uma API REST, ao qual irá armazenar informação sobre os rebeldes, bem como os recursos que eles possuem. Abaixo é apresentada a listagem dos requisitos.

- [X] **Adicionar rebelde**<br>Um rebelde deve ter um nome, idade, gênero, localização (latitude, longitude e nome, na galáxia, da base ao qual faz parte). Um rebelde também possui um inventário que deverá ser passado na requisição com
  os recursos em sua posse.

- [X] **Atualizar localização do rebelde**<br>Um rebelde deve possuir a capacidade de reportar sua última localização, armazenando a nova latitude/longitude/nome (não é necessário rastrear as
  localizações, apenas sobrescrever a última é o suficiente).

- [X] **Relatórios**<br>A API deve oferecer os seguintes relatórios:
1. Porcentagem de traidores.
2. Porcentagem de rebeldes.
3. Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde).
4. Pontos perdidos devido a traidores.

- [X] **Deletar rebelde**<br>Rebeldes com role ADMIN podem excluir rebeldes da base.

- [X] **Listar os rebeldes cadastrados**<br>Rebeldes com role ADMIN podem visualizar a lista de rebeldes cadastrados.

- [X] **Listar os rebeldes cadastrados(com paginação)**<br>Rebeldes com role ADMIN podem visualizar a lista paginada de rebeldes cadastrados ordenados pelo atributo `name`.

- [X] **Obter rebelde pelo `id`**<br>Acessar dados de um rebelde expecífico pelo atributo `id`.

- [X] **Obter o inventário de rebelde pelo `id`**<br>Acessar dados do inventário de um rebelde expecífico pelo atributo `id`.

### Requisitos para rebel com Role REBEL
- [X] **Atualizar localização do rebelde**<br>Um rebelde deve possuir a capacidade de reportar sua última localização, armazenando a nova latitude/longitude/nome (não é necessário rastrear as
  localizações, apenas sobrescrever a última é o suficiente).

- [X] **Reportar o rebelde como um traidor**<br>Eventualmente algum rebelde irá trair a resistência e se aliar ao império. Quando isso acontecer, nós precisamos informar que o rebelde é um traidor.
  Um traidor não pode negociar os recursos com os demais rebeldes, não pode manipular seu inventário, nem ser exibido em relatórios.<br>Um rebelde é marcado como traidor quando, ao menos, três outros rebeldes
  reportarem a traição.<br>Uma vez marcado como traidor, os itens do inventário se tornam inacessíveis (eles não
  podem ser negociados com os demais).

- [X] **Rebeldes não podem Adicionar/Remover itens do seu inventário**<br>Seus pertences devem ser declarados quando eles são registrados no sistema. Após
  isso eles só poderão mudar seu inventário através de negociação com os outros
  rebeldes.

- [X] **Negociar itens**<br>Os rebeldes poderão negociar itens entre eles. Para isso, eles devem respeitar a tabela de preços abaixo, onde o valor do item é
  descrito em termo de pontos.<br>Ambos os lados deverão oferecer a mesma quantidade de pontos. Por exemplo, 1 arma e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3).
  <br>A negociação em si não será armazenada, mas os itens deverão ser transferidos de um rebelde a outro.

  | ITEM           |  PONTOS  |
  |----------------|:--------:|
  | 1 ARMA         |     4    |
  | 1 MUNIÇÃO      |     3    |
  | 1 ÁGUA         |     2    |
  | 1 COMIDA       |     1    |
- [X] **Obter seus dados**<br> Um rebelde com role `REBEL` poderá acessar todos os seus dados.

## Executando o projeto
Abaixo são apresentados os passos necessários para que o projeto possa ser executado.

### Pré-requisitos
O projeto foi criado utilizando o `Gradle` como gerenciador de dependências. Será necessário o uso do `Docker`. Também será necessário utilizar alguma IDE para visualizar o código como por exemplo: Intellij (utilizada), Netbeans, Eclipse ou qualquer um editor de sua preferência. 

### Rodando o projeto
#### Clone o repositório e abre o local do projeto
```bash
# Clone este repositório
$ git clone https://github.com/willyoliv/star_wars_resistance_api_auth.git

# Acesse a pasta do projeto no terminal/cmd
$ cd star_wars_resistance_api_auth
```
#### Executar o build do projeto
```bash
$ ./gradlew bootJar
```
#### Criar o build da imagem
```bash
$ docker build -t coders/starwars-resistance .
```
#### Executando os containers
```bash
$ docker-compose up
```


## EndPoints
Os endpoints estão organizados seguindo a ordem dos requisidos funcionais do projeto. A documentação pode ser acessada importando o arquivo **`StarWars API.postman_collection.json`** no Postman.
### Login
As requisições para esta API necessitam de autenticação, então, para realizar qualquer requisição é necessário efetuar o login passando o
`username` e o `password`.

**OBS.** Ao executar o projeto será adicionado um usuário com username **rebel** e password **password** e role **ADMIN**
para que seja possível rodar as operações básicas. Abaixo é apresentado um exemplo de requisição para efetuar login cuja a resposta será o token de acesso.

Método **POST** localhost:8080/starwars-resistance/login

```
{
   "username": "rebel",
   "password": "password"
}
```

### Adicionar Rebelde
Para adicionar o rebelde é necessário informar os campos name, age, username, password, genre, location(galaxyName, latitude, longitude) e inventory(items). 

**OBS.** Os campos possuem algumas validações, valores `null` e `""` não são aceitos para campos do tipo `String`. A lista de itens deve conter obrigatoriamente os 
quatro itens `WEAPON, WATER, BULLET e FOOD` com seus respectivos valores, não podendo serem **null** ou **NEGATIVOS**. 
Além do body, deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `ADMIN` podem ter acesso a este endpoint.
Abaixo são apresentados o endpoint e o exemplo de body para realizar a adição de um novo rebelde.  

Método **POST** localhost:8080/starwars-resistance/admin/rebel/save

```
{
   "name": "Nome do Rebelde",
   "age": 20,
   "username": "rebelde",
   "password": "password",
   "genre": "FEMALE",
   "location": {
      "galaxyName": "Nome da galáxia",
      "latitude": 1223432423,
      "longitude": 1321243123
   },
   "inventory": {
      "items": [
         {
            "name": "WEAPON",
            "quantity": 2
         },
         {
            "name": "BULLET",
            "quantity": 35
         },
         {
            "name": "WATER",
            "quantity": 4
         },
         {
            "name": "FOOD",
            "quantity": 10
         }
      ]
   }
}
```
### Atualizar localização do rebelde(ADMIN)
Para atualizar a localização de um rebelde é necessário informar os novos valores para galaxyName, longitude e latitude. Também deve ser passado na URL o **`id`** do rebelde.
 Abaixo são apresentados o endpoint e o exemplo de body para realizar a atualização da localização de um rebelde.

**OBS.** O `id` deve obrigatoriamente pertencer a um rebelde existente, caso contrário a resposta para a requisição é `BAD_REQUEST`.
Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `ADMIN` podem ter acesso a este endpoint.

Método **PUT** localhost:8080/starwars-resistance/admin/rebel/**`id`**/update-location
```
{
   "galaxyName": "Nova galáxia",
   "latitude": 50234324,
   "longitude": 27212431
}
```

### Relatórios
Abaixo é apresentado o endpoint para obter o relatório e um exemplo de resposta para a requisição.

**OBS.** Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `ADMIN` podem ter acesso a este endpoint.

Método **GET** localhost:8080/starwars-resistance/admin/report
```
{
   "percentageOfTraitors": 0.2,
   "percentageOfRebels": 0.8,
   "averageOfItems": {
      "WATER": 9.5,
      "FOOD": 9.75,
      "BULLET": 28.75,
      "WEAPON": 4.25
   },
   "lostPoints": 21
}
```
### Deletar rebelde
Abaixo é apresentado o endpoint para deletar um rebelde. É necessário passar o `id` do rebelde.

**OBS.** Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `ADMIN` podem ter acesso a este endpoint.

Método **DELETE** localhost:8080/starwars-resistance/admin/rebel/delete/**`id`**

### Listar os rebeldes cadastrados
Para obter a lista completa dos rebeldes basta utilizar o endpoint abaixo.

**OBS.** Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `ADMIN` podem ter acesso a este endpoint.

Método **GET** localhost:8080/starwars-resistance/admin/list

### Listar os rebeldes cadastrados(com paginação)
Para obter a lista dos rebeldes com paginação, basta utilizar o endpoint abaixo.

**OBS.** Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `ADMIN` podem ter acesso a este endpoint.

Método **GET** localhost:8080/starwars-resistance/admin

### Obter rebelde pelo `id`
Para obter um rebelde específico basta utilizar o endpoint abaixo, onde o **`id`** de um rebelde cadastrado deve ser passado.

**OBS.** Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `ADMIN` podem ter acesso a este endpoint.

Método **GET** localhost:8080/starwars-resistance/admin/rebel/find/**`id`**

### Obter o inventário de rebelde pelo `id`
Para obter o inventário de rebelde específico basta utilizar o endpoint abaixo, onde o **`id`** de um rebelde cadastrado deve ser passado.

**OBS.** Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `ADMIN` podem ter acesso a este endpoint.

Método **GET** localhost:8080/starwars-resistance/admin/rebel/**`id`**/inventory

### Atualizar localização do rebelde(REBEL)
Para atualizar a localização de um rebelde é necessário informar os novos valores para galaxyName, longitude e latitude. 
O rebelde será identificado a partir das informações do token de autenticação.

Abaixo são apresentados o endpoint e o exemplo de body para realizar a atualização da localização de um rebelde.

**OBS.** Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `REBEL` podem ter acesso a este endpoint.

Método **PUT** localhost:8080/starwars-resistance/rebels/update-location
```
{
   "galaxyName": "Nova galáxia",
   "latitude": 50234324,
   "longitude": 27212431
}
```

### Reportar um rebelde como traidor
Para reportar um rebelde basta informa o seu id(`accusedId`) e a razão(`reason`) do reporte. Além disto, o rebelde acusador
será identificado a partir dos dados no token de autenticação.

**OBS.** O`accusedId` deve obrigatoriamente pertencer a rebelde existente, caso contrário a resposta para a requisição é `BAD_REQUEST`.
Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `REBEL` podem ter acesso a este endpoint.

Método **POST** localhost:8080/starwars-resistance/rebels/report-traitor
```
{
   "accusedId": 1,
   "reason": "Motivo do reporte."
}
```
### Negociar itens
Para negociar os itens é necessário passar o `fromRebel` e `toRebel` contendo os repectivos **ids** e a lista de itens para a troca.
 
**OBS.** Uma série de validações é aplicada para este método. Os dois rebeldes devem existir, a quantidade de itens de cada rebelde é avaliada não sendo possível 
trocar itens que não possui ou que excedem a quantidade salva. Também é bloqueado a troca de itens com **traidores**. Sobre o body para a requisição, todos os campos
são obrigatórios e com um detalhe para a lista de itens que deve conter pelo menos **um** item e no máximo **quatro**. Abaixo é apresentado o exemplo do body para a
requisição e a URL para negociar os itens. Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `REBEL` podem ter acesso a este endpoint.

Método **POST** localhost:8080/starwars-resistance/rebels/inventory/trade
```
{
   "fromRebel": {
      "rebelId": 1,
      "items": [
         {
            "name": "WEAPON",
            "quantity": 2
         },
         {
            "name": "WATER",
            "quantity": 1
         }
      ]
   },
   "toRebel": {
      "rebelId": 2,
      "items": [
         {
            "name": "BULLET",
            "quantity": 2
         }
      ]
   }
}
```

### Obter seus dados
Um rebelde cadastrado pode obter seus dados a partir deste endpoint.

**OBS.** Deverá ser enviado o token de autenticação no **header**. Somente rebeldes com role `REBEL` podem ter acesso a este endpoint.

Método **GET** localhost:8080/starwars-resistance/rebels/find

