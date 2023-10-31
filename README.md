# Elaboración de Workshop reserva de hotel. 

---

Para utilizar el siguiente aplicativo, recomendamos agregar primero los datos de los clientes y habitaciones a la base de datos MySQL a través de los scripts cliente y habitación que puede encontrar en `src/main/resources`. También puede agregarlos mediante los siguientes endpoints

### Cliente 

http://localhost:8080/api/clientes/crear

```
{
    "nombres": "Juan",
    "apellidos": "Perez",
    "cedula": "1234567890",
    "direccion": "Calle 123, Ciudad",
    "edad": 30,
    "email": "juan.perez@email.com"
}
```

### Habitacion 

http://localhost:8080/api/habitaciones/crear

```
{
  "tipoHabitacion": "PREMIUM",
  "precioBase": 180.0
}
```

### Reserva

No es recomendable crear directamente las reservas en las bases de datos MySQL, se recomienda hacerlo a través del siguiente endpoint:

http://localhost:8080/api/habitaciones/crear

```
  {
    "clienteCedula": "3333333333",
    "habitacionId": 9,
    "fechaReserva": "2023-11-24"
  }
```

En la carpeta `resources` pueden tambier encontrar otros ejemplos para agregar reservas de acuerdo a la información de los clientes y habitaciones que se proporcionó, lo encuentra en un JSON llamado reserva.

## Endpoints

En `src/main/resources` puede también encontrar un JSON llamado endpoints, ahí se encuentran todos los métodos HTTP para realizar el CRUD de todas las entidades, lo único que tiene que hacer es descargarlo e importarlo a una herramienta tal como `Postman` o `Insomnia`.
