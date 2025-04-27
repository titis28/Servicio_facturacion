# Factura - Backend

Este servicio implementa el backend de un sistema de facturación utilizando Spring Boot. Proporciona servicios REST para la gestión de facturas. 

Los Endpoints principales son: 
1. GET/facturas - Mostrar toda la lista de los tickets 
2. GET/facturas/{id} - Obtener un ticket en específico por medio de su id
3. POST/facturas - Crear una nueva factura 
4. PUT/facturas/{id} - Modificar y actualizar una factura ya existente por el id 
5. DELETE/facturas/{id} - Eliminar una factura por medio de id 

## Tecnologías utilizadas

- Java
- Spring web
- Spring Data JPA
- H2 Database
- Postman para pruebas 

## Estructura general

- Factura.java: Entidad principal de factura, aqui se maneja los datos principales como el id de la factura, identificador, fecha y hora. 
Se hacen los calculos necesarios para calcular un subtotal, el IVA y el total ( la suma del subtotal con el IVA)
- DetalleFactura.java: Objeto para productos en la factura, aqui se muestran los detalles de la compra como el id del producto, su nombre, la cantidad de los productos, el precio unitario del producto y el total (el precio unitario * la cantidad de productos) 
- FacturaController.java: Controlador REST (GET, POST, PUT, DETELE)
- FacturaRepository.java: Acceso a datos por medio de la interfaz.
- FacturaNotFoundException.java y FacturaNotFoundAdvice: Manejo de errores.
- FacturaApplication.java: Configuración de base de datos en memoria (H2).
- LoadDatabase.java : Carga de datos para probar el servicio en Postman. 