# Iniciando

## Tecnologias usadas
- Java 14
- Spring Boot
- Maven

### Documentación 
Para mayor referencia, y ver en que se debe instalar y configurar ver las siguientes referencias:

* [Jdk 14](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)
* [Documentación de apache maven](https://maven.apache.org/guides/index.html)
* [IDE de tu preferencia, en este caso uso Intellij](https://www.jetbrains.com/es-es/idea/)
* [Instalar y configurar docker](https://www.docker.com/)

### Guias
Las siguientes guías ilustran cómo utilizar algunas funciones de forma concreta:


* [Construir una rest api](https://spring.io/guides/gs/rest-service/)
* [Consumir a servicio rest](https://spring.io/guides/gs/consuming-rest/)

## Como empezar 
una vez instalado todo, en la raiz del proyecto: 
```
mvn clean install 
docker build -t -p 8080:8080 pharmacy-service
```

[Luego podemos explorar en swagger todos los servicios expuestos y como usarlos](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

[Microservicio en la nube, ver swagger](https://pharmacy-service-dev-ftsn5nwobq-uc.a.run.app/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)


## Si quieres ver como se desarrollo:

[Desarrollo](DESARROLLO.md)




