### Exemplo migração de um monolito para uma arquitetura de microservices.

Neste projeto adotamos as práticas do DDD, afim de migrar um monolito para uma arquitetura de microservices.
A arquitetura de microservices segue os seguintes padrões:
- configuração extenalizada.
	- para este requisito utilizamos a stack spring cloud config.
- uso de um gateway
	- utilizamos o novo gateway do spring.
- discovery service
	- uso do eureka
- circuite breaker
	- o hystrix é utilizado para callback de falhas, no entando o mesmo encontra-se em manutenção até o momento desta poc (recomenda o uso do resilience4j).
	 Abaixo um exemplo de uso do hystrix dashboard
- load balance
	- uso do ribbon, pois o mesmo encontra-se no eureka.
- rastreamento
	- utilizou-se o sleuth que gera um hash a cada requisição externa e uso do zipkin para acompanhamento visual.				


###### Detalhes do uso do hystrix dashboard

- expor o endpoint pelo actuator do serviço correspondente: 
```
http://localhost:9090/actuator/hystrix.stream
```
- inserir o endpoint acima na dash: 
```
http://localhost:8988/hystrix
```

### Montando o ambiente
```
docker-compose up

```
