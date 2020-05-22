### Exemplo migração de um monolito para uma arquitetura de microservices.

Neste projeto adotamos as práticas do DDD, afim de migrar um monolito para uma arquitetura de microservices.
A arquitetura de microservices segue os seguintes padrões:
- configuração extenalizada.
	- para este requisito utilizamos a stack spring cloud config.
- uso de um gateway
	- Utilizamos o zuul, embora seja sincrono e encontra-se em manutenção (é sugerido o uso do spring cloud gateway por ser reativo).
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
Utilizamos um plugin do google, jib-maven, para facilitar a criação das images dos serviço.
Comando:
```
mvn compile jib:dockerBuild
```

Subindo os containers (pode-se montar um docker-compose.yaml)
```
docker run --name config-server -d -p 8888:8888 fabricio211/config:0.0.1-SNAPSHOT
docker run --link config-server -d -e SPRING_CLOUD_CONFIG_URI=http://config-server:8888 --name eureka-server -p 8761:8761 fabricio211/discovery:0.0.1-SNAPSHOT
```
