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

Subindo os containers manualmente (primeira alternativa):
```
docker run --name config-server -d -p 8888:8888 fabricio211/config:0.0.1-SNAPSHOT

docker run --link config-server -d -e SPRING_CLOUD_CONFIG_URI=http://config-server:8888 --name eureka-server -p 8761:8761 fabricio211/discovery:0.0.1-SNAPSHOT

docker run --name api-gateway --link config-server --link eureka-server -d -e SPRING_CLOUD_CONFIG_URI=http://config-server:8888  -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/ -p 8222:8222 fabricio211/gateway:0.0.1-SNAPSHOT

docker run --name zipkin -d -p 9411:9411 openzipkin/zipkin

docker run --name hystrix-dashboard --link config-server --link eureka-server -d -e SPRING_CLOUD_CONFIG_URI=http://config-server:8888  -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/ -p 8988:8988 fabricio211/dash-hytrix:0.0.1-SNAPSHOT

docker run --name product-service --link config-server --link eureka-server --link mysql --link zipkin -d -e SPRING_CLOUD_CONFIG_URI=http://config-server:8888  -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/ -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/product -e SPRING_ZIPKIN_BASE_URL=http://zipkin:9411 -p 9990:9990 fabricio211/product:0.0.1-SNAPSHOT

docker run --name order-service --link config-server --link eureka-server --link mysql --link zipkin -d -e SPRING_CLOUD_CONFIG_URI=http://config-server:8888  -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/ -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ordertest -e SPRING_ZIPKIN_BASE_URL=http://zipkin:9411 -p 9991:9991 fabricio211/order:0.0.1-SNAPSHOT

docker run --name customer-service --link config-server --link eureka-server --link mysql --link zipkin -d -e SPRING_CLOUD_CONFIG_URI=http://config-server:8888  -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/ -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/customer -e SPRING_ZIPKIN_BASE_URL=http://zipkin:9411 -p 9992:9992 fabricio211/customer:0.0.1-SNAPSHOT

```
