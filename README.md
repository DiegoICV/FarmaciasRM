# Descripción
SingleRest backend con el proposito de actuar como middleware para el manejo de solicitudes HTTP bloqueadas por CORS

## Tecnologias usadas
- Java 14
- Spring Boot
- Maven

## Ide de desarrollo
- Spring Tools Suite 4-4.8.1

### Web RestApi Utilizada
- Metodo HTTP : GET
- ApiURL: https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion?
- Parametros: id_region=7

### Deploy
 - El servicio fue levantado en Heroku https://farmacias-en-turno-api.herokuapp.com/
 - Se puede acceder a la documentacion de Swagger generada para la rest en el enlace: https://farmacias-en-turno-api.herokuapp.com/swagger-ui.html

 ### Instalación
 - Se recomienda utilizar Spring Tools Suite, Importar la carpeta del proyecto './farmacias.de.turno.api' 
 - Click derecho en el proyecto -> Run As -> Maven Generate-Sources
 - Luego Iniciar el proyecto en modo Debug utilizando el Boot Dashboard de Spring tools.

