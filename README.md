# Investigation
Proyecto completo de la asignatura Acceso a Datos de 2ºDAM 

Gestor de Investigaciones usando el Modelo Vista Controlador con el patrón DAO, que se encarga de gestionar la conexión a las diferentes BBDD y en diferentes tecnologías

## Descripción del funcionamiento

Implementa con cada uno de los JDBC con MariaDB, Hibernate con MariaDB, ObjectDB y MongoDB. 
Queda a elección del usuario cual de ellos se usará para la conexión en la ejecución de la aplicación, concretamente lo elegirá en la primera pantalla de "Login" donde también introducirá los datos para la conexión necesarios.
Una vez elegida mediante el JComboBox en el JDialog, se iniciará a aplicación en sí.
Mediante un menú principal, podrémos acceder a las ventanas de cada una de las JFramses con las tablas. Estas implementan la interfaz Tablas, que la clase controlador usará como variable e instanciará según la elección del usuario en el controlador principal.
Las Clases DAOManager* localizadas en los paquetes DAO de cada uno de los 4 servicios implementan la interfaz DAOManager, que el controlador utiliza para comunicarse con las clases de acceso a datos de bajo nivel y extraer los objetos que estas cargan de forma cómoda y modular. 

## Modelo (Clases Java Beans)

- Campo
    - campo_id: identificador del campo
    - nombre :nombre del campo de estudio
    - ramal :rama general del campo de estudio
- Entidad
    - entidad_id :identificador de la entidad
    - nombre: nombre de la entidad de investigación (mayoritariamente universidades)
    - ubicación :ubicación de la entidad
- Investigador
    - investigador_id: identificador de el investigador
    - nombre: nombre del investigador
    - titulo: titulo académico principal del investigador
    - salario: el salario que el investigador percibe por su contrubución al proyecto
    - proyecto: el proyecto en el que actualmente trabaja el investigador
    - campos: los campos de estudio en los que se especializa el investigador
- Proyecto
    - proyecto_id: identificador del proyecto/investigación
    - nombre: nombre completo del proyecto/investigación
    - capital: presupuesto asignado al proyecto
    - fecha_inicio: fecha en la que se inició el proyecto
    - entidades: entidad/es que promueven y financian el proyecto
    - investigadores: Investigadores que trabajan en el proyecto
    - campo: Campo de estudio que abarca el proyecto
    - finalizado: Informa de si el proyecto ya ha sido finalizado o no
    - coste: El coste final del proyecto
    - fecha_fin: la fecha en la que se finalizó el proyecto

En las 4 ventanas el usuario podrá hacer las operaciones CRUD más comunes, los métodos de la clase DAO disponibles son:
- insertar: inserta el objeto y le carga su ID autogenerada por la BBDD usada
- modificar: modifica el objeto 
- eliminar: elimina el objeto
- obtener: busca en la BBDD el objeto por su ID pasado como parámetro y lo devuelve, acepta una variable opcional para controlar la profundidad de carga de los datos, necesaria para evitar recursividad en tecnologías como JDBC y MongoDB
- obtenerTodos: devuelve una Lista con todos los objetos de la tabla/colección...

## Apuntes

Las anotaciones JPA son usadas por Hibernate y ObjecDB, he considerado no configurar Hibernate por ficheros xml pues ya lo tenía visto y JPA es la API por excelencia cuando se trabaja con Hibernate.
Los mapeos de relaciones usan exclusivamente REFRESH como tipos de cascada, para que los objetos secundarios se vuelvan a cargar desde la BBDD cuando se actualiza el objeto principal. Pues otras acciones automáticas o no son necesarias o me perjudicarian en el caso de mi aplicación.
Como MongoDB no usa claves numéricas auto incrementales por defecto, lo he configurado manualemnte para que las genere de tal forma, para ello utiliza una coleccion a modo de contador en la BBDD que el DAOManager de Mongo creará si no existe, y una función en las clases servicio de Mongo para obtener el siguente identificador.

Cualquier duda, crítica, sugerencias de mejora, problema o bug encontrado en este proyecto no dude en comunicarlo por correo o por Github.
