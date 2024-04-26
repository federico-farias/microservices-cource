### Levantar servicios

[Levantar servicios](../README.md)

### APM
Para visualizar los datos en el APM es necesario compilar generar el archivo jar del proyecto, posteriormente ejecutar el siguiente comando:

```sh
java -javaagent:./elastic-apm-agent.jar -jar ./target/ecommerce-backoffice-products-0.0.1-SNAPSHOT.jar
```

#### Nota
Tomese en cuenta que el nombre de servicio se obtiene del valor de la etiqueta **artifactId** del archivo **pom.xml**
