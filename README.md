## API PAGOS
API para la gestión de pagos.

## FEIGN CLIENT PARA CONEXION CON OTRA API
- Se utilizo Feign Cliente como cliente para poder conectar con otras APIS, para ejemplo con la api de Saldos y poder interactuar con la consulta de saldo y la actualización de saldo.
- El API Requiere el Token de Autorización desde el Header para poder enviar al API de Saldos tambien por Header y poder conectar.

## BASE DE DATOS H2
- Se utilizó la base de datos H2 como base para registro de cambios de divisa y para registros de pagos por concepto de microservicios en la cual el API debe conectar con su propia base de datos.
- Es necesario poder registrar primero un tipo de cambi para poder registrar exitosamente un pago.