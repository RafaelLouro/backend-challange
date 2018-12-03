# Invillia recruitment challenge

## Dev Session

Olá pessoal. Montei apenas um service nesse período de 2 dias, vou tentar destacar a linha que segui, e o porquê das decisões.

O projeto foi todo montado usando Spring-Boot e Spring-Cloud. Separei por pastas, cada server ou serviço criado, com seu respectivo Dockerfile. Eles podem ser compilados e empacotados todos na pasta do projeto pai (pom.xml com os módulos). Para fazer isso, entrar na pasta do projeto /backend-challange e usar o comando padrão do Maven:
```
mvnw clean package
```
Tentei aplicar testes sobre o service de **Providers**, por ser aonde as regras de negócio estão. Os testes vão rodar junto ao compilar e empacotar, mas caso precise rodar os testes sozinhos, comando padrão do Maven:
```
mvnw clean test
```
Isso pode ser feito por projeto também, só entrar em cada pasta e usar os mesmos comandos.

O banco utilizado foi o MySql, bem simples, container próprio. Quando você subir os containers, ele iniciará, mas não utilizei volumes, então assim que o container for desligado, o banco será apagado. Deixei cada service que conecta no banco resposável por criar as suas tabelas, a partir do mapeamento do Spring-Data, para não perder tempo nesse ponto de gerar os scripts.

Tentei montar um ambiente todo com containers, usando o **Docker**. Então cada service/server roda no seu container separado dos demais. Todos os módulos desse projeto buscam suas configurações no Config-server, e não acessam diretamente um ao outro, para isso utilizam o Discovery-server. Segui essa linha pensando na **escalabilidade**, apesar que não consegui testar isso rodando, então acredito que alguns pontos ainda não foram atendidos para que os services estejam totalmente stateless e eu devo ter deixado algo passar.

Para rodar os containers, após todos os projetos estarem empacotados, entrar na pasta do projeto /backend-challange e usar os comandos:
```
docker-compose build
```
para contruir as imagens do Docker e
```
docker-compose up
```
para subir o ambiente todo.

Eu fiz vários testes sobre como subir esses containers em ordem de uma forma mais elegante, mas o 'depends_on' com healthchecks foi o que melhor funcionou até o momento. Talvez esse caminho não seja o melhor, mas eu tentei perder o menor tempo possível nesse detalhe.

### API do service **Provider**:

Sobre as URLs das funcionalidades, a porta 8080 do container do service **Invillia** está exposta, a parti dela você pode acessar a API de Providers:

**POST**: http://localhost:8080/invillia/provider

**PUT**: http://localhost:8080/invillia/provider

**GET**: http://localhost:8080/invillia/provider/{idProvider}

No corpo da Request do POST e PUT é necessário o JSON do objeto, por exemplo:
```
{
    "name": "José",
    "address": "Rua 1, n 2, Bairro 3"
}
```

Outras portas de outros services estão expostas, isso pode ser checado no arquivo docker-compose.yml na raiz do projeto.

### Adicionais:

O **AWS propose** eu não utilizei, não conhecia, e vou dar uma olhada depois. Achei melhor não tentar incluir esse item, já que não tinha prática.

A parte de **Security** eu não consegui terminar infelizmente. Foi a último ponto que tentei adicionar, pensando no service **Invillia** como uma gateway para validar, mas não deu tempo de fazer, tive muitos problemas e também não domino muito essa parte, é algo que eu preciso melhorar.

E por fim, sobre **Asynchronous processing**, eu pensei em usar RabbitMQ para controlar os cadastros dos Providers, já usei ele em um projeto, mas faltou tempo também.

## -----

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* **Completed:** Create a **Provider**
* **Completed:** Update a **Provider** information
* **Completed:** Retrieve a **Provider** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Provider** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* Asynchronous processing
* Database
* Docker - https://www.docker.com/
* AWS propose - https://github.com/localstack/localstack
* Security
* Swagger - https://swagger.io/
* Clean Code
