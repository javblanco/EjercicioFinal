# Museo HEL-ARTE 

Un proyecto desarrollado por Javier Blanco para el museo HEL-ARTE 

## Requisitos solicitados por el cliente

-Necesidad de una aplicación intuitiva, sencilla y amigable con el cliente.
-Conectividad entre la parte de servidor y la parte de cliente para su correcta ejecución.
-Correcta ejecución de los posibles casos de uso de cada una de las entidades.
-Listado de las entidades correspondientes en cada uno de sus apartados.
-Correcto funcionamiento de la carga de las obras de arte dentro de cada estilo artístico.

## Futuras actualizaciones

-No se tiene en cuenta el orden ni la duplicidad.
-Añadir una opcion que al deshabilitar un estilo no te deje modificarlo
-Añadir imagenes un campo imagen a las obras para poder verlo en el catálogo


## Conexion servidor-cliente

Es necesario realizar un pequeño set-up de arranque antes de poder navegar por la aplicación y visualizar las vistas.
Para ello necesitaremos ejecutar ng serve desde el proyecto Angular y mvn spring-boot:run desde la raíz del proyecto que concuerda con el apartado de servidor de Spring.

## MANEJO PRINCIPAL ENTRE VISTAS

El cliente puede iniciar su navegación por la aplicación desde la url 'http://localhost:4200' .
Al navegar al índice de la aplicación podremos acceder a las dos listas disponibles, tanto a la lista de estilos artísticos pulsando el botón de ESTILOS como a la lista de obras de arte pulsando en el botón de OBRAS.

Para crear una obra, pulsamos en el botón de crear. Rellenamos el formulario (que tiene unos campos obligatorios) y pulsamos en CREAR. En este momento ya tenemos una obra en la lista. Desde aquí, podremos modificar o borrar la obra recién creada o, crear una obra nueva.

Para crear un estilo, pulsamos el botón de crear. Rellenaremos los campos del formulario y pulsamos en botón de CREAR. Con esto ya tenemos un nuevo estilo artístico creado. Desde esta página se puede modificar el estilo, borrar, o crear uno nuevo. Si pulsamos en el botón de ACTUALIZAR se despliega el formulario con los campos ya rellenos y además, aparece un desplegable desde el cual se pueden añadir las obras de arte a cada estilo artístico.

Cada una de las páginas tiene un botón de SALIR (excepto la principal), este botón te manda a la pantalla anterior.