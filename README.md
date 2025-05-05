CONFIGURACION POSTMAN
POST /clientes
{
    "nombre": "Marianela Montalvo",
    "identificacion": "098254785",
    "contrasena": "5678",
    "estado": true
}

POST /cuentas
{
    "numeroCuenta": "225487",
    "tipo": "Corriente",
    "saldoInicial": 100,
    "clienteId": 2
}

POST /movimientos
{
    "valor": 600,
    "numeroCuenta": "225487"
}

GET /reportes?clienteId=2&fechaInicio=2024-01-01&fechaFin=2024-12-31

GET Todos los clientes: Retorna lista completa de clientes con estado HTTP 200
GET http://localhost:8080/clientes

GET Cliente específico: Busca por ID y retorna 404 si no existe
Ejemplo: GET http://localhost:8080/clientes/1

PUT Actualización: Actualiza todos los campos editables, Valida existencia del cliente
{
    "nombre": "Nuevo Nombre",
    "estado": false,
    "contrasena": "nueva-contraseña"
}

DELETE Eliminación: Elimina lógicamente (si usas borrado físico, agregar @Transactional), Retorna 204 No Content
DELETE http://localhost:8080/clientes/3
