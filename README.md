# Encurtador de url
Esse projeto é um encurtador de url escrito em Java 11, Spring Boot, Spring MVC, Spring Data JPA and H2DB

- Gerar um código para compor a url
- Salva informação da url para depois redirecioná-la

### Uso e instalação

A melhor forma de executar o projeto é com IDE compativel com o plugin do maven

Existe duas opções de banco de dados que podem ser utilizadas: mysql or h2 em memória. 
Para executar o projeto com um banco de dados específico tem informar no application.properties na propriedade spring.profiles.active a seguinte informação:

> spring.profiles.active=h2

ou 

> spring.profiles.active=mysql

Ainda no arquivo application.properties na propriedade url.expiration deverá ser informado o tempo de expiração da url em milissegundos:

> url.expiration=60000

Para executar a aplicaticação deverá seguir os endpoints na sequência conforme o exemplo abaixo:

Encurtador de URL
```
[POST] http://localhost:8080
{
	"url": "http://www.zambas.com.br"
}
```
Redirecionamento da URL
```
[GET] http://localhost:8080/{code}
```
H2 Console (Se for preciso checar o conteúdo do banco de dados em memória)
```
[GET] http://localhost:8080/h2-console
```