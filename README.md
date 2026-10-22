# Prácticas de clases de SpringBoot 3  
## Inyección de Dependencias (ID)
<p>
Codigo de Trabajo en SpringBoot 3 acerca de todo lo relacionado con el concepto de Inyección de dependencias.

  Ejemplo de manejo de Productos.
</p>

![Dependencias SpringBoot](https://www.davidgomezrubio.com/wp-content/uploads/2020/06/Inyeccion-de-dependencias-Spring-Framework.jpg "Dependencias SpringBoot")

---

## Objetivo

Proyecto de práctica del curso de Spring Boot 3 para entender cómo Spring crea y entrega los componentes (*beans*) a las clases que los necesitan. Con un ejemplo de productos se trabaja la inyección por interfaces, la elección entre varias implementaciones (`@Primary` / `@Qualifier`), la creación de beans con `@Bean`, la lectura de valores externos con `@Value` y los distintos *scopes* de un componente. No usa base de datos: los productos están en memoria o en un archivo JSON.

## Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 21 |
| Spring Boot (starter parent) | 3.2.1 |
| Spring Web | gestionada por Spring Boot |
| Spring Boot Actuator | gestionada por Spring Boot |
| Spring Boot DevTools | gestionada por Spring Boot |
| Jackson (`ObjectMapper`) | incluido en Spring Web |
| Maven (Maven Wrapper incluido) | - |

## Estructura del proyecto

```
src/main/java/com/joaquin/springboot/di/app/springbootdi
├── SpringbootDiApplication.java        # Clase principal
├── ValuesConfig.java                   # @Configuration: carga values.properties y define un @Bean @Primary (ProductRepositoryJson)
├── controllers
│   └── SomeController.java             # Endpoints REST en /api
├── models
│   └── Product.java                    # Modelo (id, name, price) que implementa Cloneable
├── repositories
│   ├── IProductRepository.java         # Interfaz: findAll / findById
│   ├── ProductRepository.java          # @Repository con una lista de productos en memoria
│   ├── ProductRepositoryFoo.java       # @Repository alternativo que devuelve siempre un "Monitor Asus 27"
│   └── ProductRepositoryJson.java      # Lee los productos desde resources/json/product.json (registrado vía @Bean)
└── services
    ├── IProductService.java            # Interfaz del servicio
    └── ProductService.java             # @Service: inyecta el repositorio con @Qualifier y aplica el impuesto
src/main/resources
├── application.properties              # Sin configuración adicional
├── values.properties                   # value.tax = 1.25
└── json/product.json                   # Datos de productos para ProductRepositoryJson
```

### ¿Qué repositorio se usa?

Hay tres implementaciones de `IProductRepository`. `ProductRepositoryJson` está marcado como `@Primary` en `ValuesConfig`, pero `ProductService` indica explícitamente `@Qualifier("productRepository")`, por lo que el servicio utiliza **`ProductRepository`** (lista en memoria). Cambiando el *qualifier* se puede probar cualquiera de las otras implementaciones.

## Conceptos aplicados

- **Inyección de dependencias con `@Autowired`**: Spring asigna una instancia (singleton) sin necesidad de hacer `new`. Según los comentarios, también puede hacerse sobre un *setter* o mediante el constructor (sin `@Autowired`).
- **Programar contra interfaces** (`IProductRepository`, `IProductService`) para poder cambiar la implementación sin tocar a quien la usa.
- **Estereotipos**: `@Repository`, `@Service`, `@RestController`, `@Component`.
- **`@Primary`**: define la implementación por defecto cuando hay varias del mismo tipo (solo una puede tenerlo).
- **`@Qualifier("nombreBean")`**: elige una implementación concreta por nombre (el nombre del bean va con la primera letra en minúscula) y tiene prioridad sobre `@Primary`.
- **`@Configuration` + `@Bean`**: forma alternativa de registrar un componente de Spring (usado para `ProductRepositoryJson`).
- **`@PropertySources` / `@PropertySource`** para cargar `values.properties` (UTF-8) y **`@Value("${value.tax}")`** para inyectar el impuesto.
- **Scopes de los beans** (documentados en `ProductRepository`): singleton por defecto, `@RequestScope` (por petición), `@SessionScope` (durante la sesión HTTP) y `@ApplicationScope`.
- **Inmutabilidad de los datos del singleton**: como el repositorio es compartido, el servicio trabaja sobre una **copia** de cada producto (`Product.clone()`) antes de modificar el precio, para que los datos originales no queden alterados.
- **Lectura de un JSON del classpath** con `ClassPathResource` y `ObjectMapper` para convertirlo en objetos Java.
- **Streams y lambdas** (`stream().map(...)`, `filter`, `findFirst`).

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api` | Lista los productos con el precio multiplicado por el impuesto `value.tax` (1.25). |
| GET | `/api/{id}` | Retorna el producto con ese id (precio original, sin impuesto). |

## Configuración

No requiere base de datos. El único valor configurable es el impuesto en `src/main/resources/values.properties`:

```properties
value.tax = 1.25
```

## Cómo ejecutarlo

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Luego probar en Postman o el navegador:

```
GET http://localhost:8080/api
GET http://localhost:8080/api/1
```
