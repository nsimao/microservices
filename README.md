# Microservices

Spring Microservices - Netflix OSS

`Feign` - HTTP client.

`Ribbon` - Load Balance.

`Eureka` - Service Discovery.

`Zuul` - API Gateway / Reverse Proxy.

`Hystrix` - Fault tolerance, circuit breaker.

Pattern: `http://localhost:8765/{application.name}/currency-exchange/from/EUR/to/BRL`

Example: `http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/BRL`

`Sleuth` - Tracing.

## Configurations

### Spring Cloud Configuration Server

Configurations are under the follow git repository:
https://github.com/nsimao/microservices-spring-cloud-config-repo

### Configurations step by step

> Notice: All projects has `spring.application.name` definied.

1. Project `spring-cloud-config-server` is configured to read from git repository `https://github.com/nsimao/microservices-spring-cloud-config-repo`

| Configuration | Value |
| ------------- | ------------- |
| `server.port` | `8888` |
| `spring.cloud.config.server.git.uri` | `https://github.com/nsimao/microservices-spring-cloud-config-repo` |

2. Project `limitsservice` is configured to read from spring cloud config server.

| Configuration | Value |
| ------------- | ------------- |
| `spring.cloud.config.uri` | `http://localhost:8888` |

> Notice: `8888` is the server port configuration for `spring-cloud-config-server`.

## Ports

|     Application       |     Port          |
| ------------- | ------------- |
| Limits Service | 8080, 8081, ... |
| Spring Cloud Config Server | 8888 |
|  |  |
| Currency Exchange Service | 8000, 8001, 8002, ..  |
| Currency Conversion Service | 8100, 8101, 8102, ... |
| Netflix Eureka Naming Server | 8761 |
| Netflix Zuul API Gateway Server | 8765 |
| Zipkin Distributed Tracing Server | 9411 |


## URLs

|     Application       |     URL          |
| ------------- | ------------- |
| Limits Service | http://localhost:8080/limits POST -> http://localhost:8080/actuator/refresh |
| Spring Cloud Config Server| http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev |
| Currency Converter Service | http://localhost:8100/currency-converter/from/USD/to/BRL/quantity/10 |
| Currency Exchange Service | http://localhost:8000/currency-exchange/from/EUR/to/BRL http://localhost:8001/currency-exchange/from/USD/to/BRL |
| Eureka | http://localhost:8761/ |
| Zuul - Currency Exchange & Exchange Services | http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/BRL http://localhost:8765/currency-conversion-service/currency-converter/from/USD/to/BRL/quantity/10 |
| Zipkin | http://localhost:9411/zipkin/ |