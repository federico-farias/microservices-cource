### Levantar servicios
Se requiere ejecutar el siguiente comando para disponibilizar el servicio de mysql.

```sh
sudo docker-compose -f docker-compose.yml -p monitoring_ecommerce up -d
```
El puerto predeterminado de mysql es: 3307

### Bases de datos
Una vez que mysql se cuentre disponible, se requiere crear las siguientes base de datos manualmente:
* bo_ecommerce_products
* fo_ecommerce_orders

##### Nota
No es necesario crear los campos de BD, se generan las tablas en cuento se ejecuta cada proyecto.

### [Levantar APM](demo_apm%2FREADME.md)