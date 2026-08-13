# tessa-support

Librería de soporte de la plataforma **TESSA**, desarrollada por CIC Consulting.
Proporciona el modelo de dominio, utilidades comunes y clientes REST compartidos entre los distintos servicios de la plataforma.

## Versión actual

`2.6.0-SNAPSHOT`

## Requisitos

| Herramienta | Versión mínima |
|---|---|
| Java | 17 |
| Maven | 3.8+ |
| Spring Boot | 3.5.11 |

## Estructura del proyecto

El proyecto es un multi-módulo Maven con la siguiente jerarquía de dependencias:

```
tessa-support (POM raíz)
├── tessa-common            <- Base: utilidades, excepciones, CMS, caché, consultas
├── tessa-core-model        <- Modelo de dominio + DTOs (depende de tessa-common)
├── tessa-excel-model       <- Soporte Excel/POI (depende de tessa-core-model)
├── tessa-lookup-model      <- Modelo de expresiones lookup (depende de tessa-core-model)
└── tessa-client            <- Clientes REST (depende de tessa-lookup-model)
```

---

### tessa-common (`tessa-support-common`)

Módulo base con las utilidades transversales de la plataforma.

**Almacenamiento de ficheros binarios (`es.cic.tessa.common.cms`)**

Abstracción intercambiable para el almacenamiento de ficheros binarios, configurable mediante la propiedad `tessa.binary-files.file-storage.implementation`.

| Implementación | Clase | Activación |
|---|---|---|
| Base de datos relacional | `DatabaseFileStorageService` | `implementation=Database` |
| SeaweedFS (S3-compatible) | `SeaweedFSStorageService` | configuración de bean manual |

```properties
# application.properties
tessa.binary-files.file-storage.implementation=Database
```

**Consultas dinámicas Neo4j (`es.cic.tessa.common.query`)**

- `AbstractQueryManager` — generación de consultas Cypher con filtros dinámicos (operadores aritméticos, lógicos, orden y paginación).
- `QueryManagerUtils` — mapeo entre nombres de propiedades de dominio y nombres de campo en la base de datos.

**Filtros (`es.cic.tessa.common.filter`)**

Modelo de filtros reutilizables para consultas: `Filter`, `PropertyFilter`, `AttributeOrder`, `RelationshipFilter` y variantes optimizadas.

**Utilidades**

| Clase | Descripción |
|---|---|
| `CronUtils` | Cálculo de próximas/últimas ejecuciones a partir de expresiones CRON Quartz, periodos y zonas horarias |
| `CompressUtils` | Compresión/descompresión GZIP de bytes |
| `GroupsUtils` | Construcción de etiquetas Cypher para el control de grupos |
| `ServiceUtils` | Utilidades de paginación para respuestas de servicio |
| `UniqueStringGenerator` | Generación de identificadores únicos de 20 caracteres (Base64 URL-safe) |

**Caché**

Integración con Redis mediante Jedis. El modelo de clave de caché se define en `CacheKey`.

**Excepciones**

Jerarquía propia: `TessaBaseException` -> `TessaException` / `CMSException`.

---

### tessa-core-model (`tessa-support-model`)

Modelo de dominio principal persistido en **Neo4j** y sus DTOs para la capa REST.

**Entidades principales**

| Entidad | Descripción |
|---|---|
| `Asset` | Activo principal de la plataforma |
| `Template` | Plantilla con atributos tipados |
| `TemplateAttribute` | Atributo de plantilla |
| `Organizer` | Organizador / categoría jerárquica |
| `Hashtag` | Etiqueta de clasificación |
| `AssetValue` | Valor de un atributo de un activo |
| `AssetReference` | Referencia entre activos |
| `Function` | Función de cálculo dinámico |

Todas las entidades persisten el histórico de cambios mediante el conjunto de clases `Historical*` (`HistoricalAsset`, `HistoricalTemplate`, etc.).

**DTOs**

Cada entidad dispone de par `*Request` / `*Response`. Las clases de respuesta extienden de:

- `AbstractIdentificableEntityResponse` — base con `id` e identidad JSON (`@JsonIdentityInfo`).
- `AbstractEntityResponse` — añade `name`, `description`, `version`, `nemonic`, `groups` y fechas de auditoría.

**Tipos enumerados (`es.cic.tessa.model.types`)**

`AssetType`, `TemplateType`, `TemplateAttributeType`, `OrganizerType`, `ContentType`, `ReferenceType`, `RelationType`, `LookupType`.

**Generación de tipos TypeScript**

El módulo incluye configuración del plugin `typescript-generator-maven-plugin` para generar automáticamente los tipos TypeScript (`tessamodel.ts`) a partir de los DTOs Java.

---

### tessa-excel-model (`tessa-support-excel-model`)

Soporte para importación y exportación de datos en formato Excel mediante **Apache POI 5.5.1**.

Proporciona las clases: `AssetCSV`, `OrganizerCSV`, `TemplateCSV`, `TessaCSVElement`.

---

### tessa-lookup-model (`tessa-support-lookup-model`)

Modelo para la evaluación de expresiones de tipo *lookup* y funciones de cálculo.

Incluye generación automática de tipos TypeScript (`lookupmodel.ts`) mediante el plugin `typescript-generator-maven-plugin`.

---

### tessa-client (`tessa-support-client`)

Clientes REST para consumir la API de TESSA desde otros servicios.

| Cliente | Clase |
|---|---|
| Activos | `AssetRestClient` |
| Plantillas | `TemplateRestClient` |
| Organizadores | `OrganizerRestClient` |
| Hashtags | `HashtagRestClient` |
| Lookup | `LookupRestClient` |

La configuración de los endpoints se gestiona mediante `RestClientConfig` y `LookupRestClientConfig`, que extienden de `AbtractClientConfig`.

---

## Dependencias principales

| Librería | Versión | Uso |
|---|---|---|
| Spring Boot | 3.5.11 | Framework base |
| Spring Data Neo4j | (BOM Spring Boot) | Persistencia en grafo |
| AWS SDK S3 v2 | 2.42.6 | Almacenamiento SeaweedFS/S3 |
| Jedis | (BOM Spring Boot) | Cliente Redis |
| Jackson Databind | (BOM Spring Boot) | Serialización JSON |
| cron-utils | 9.2.1 | Evaluación de expresiones CRON Quartz |
| Apache POI OOXML | 5.5.1 | Lectura/escritura Excel |
| Apache Commons Lang3 | (BOM Spring Boot) | Utilidades generales |

## Compilación

```bash
mvn clean install
```

Sin ejecutar los tests:

```bash
mvn clean install -DskipTests
```

## Tests

Los tests unitarios usan **JUnit 5** y **Mockito**:

```bash
mvn test
```

## Publicación de artefactos

Los artefactos se publican en el repositorio Maven corporativo de CIC:

| Tipo | URL |
|---|---|
| Snapshots | `https://ic-atenea.corp.cic.es/repository/cic-tessa-maven-snapshots/` |
| Releases | `https://ic-atenea.corp.cic.es/repository/cic-tessa-maven-releases/` |
| Público (resolve) | `https://ic-atenea.corp.cic.es/repository/cic-tessa-maven-group/` |

## Uso como dependencia

Añadir el repositorio corporativo al `pom.xml` del proyecto consumidor:

```xml
<repositories>
    <repository>
        <id>tessa-maven-public</id>
        <url>https://ic-atenea.corp.cic.es/repository/cic-tessa-maven-group/</url>
    </repository>
</repositories>
```

Incluir los módulos necesarios:

```xml
<!-- Modelo de dominio y DTOs -->
<dependency>
    <groupId>es.cic.tessa</groupId>
    <artifactId>tessa-support-model</artifactId>
    <version>2.6.0-SNAPSHOT</version>
</dependency>

<!-- Utilidades comunes, CMS, CRON, caché -->
<dependency>
    <groupId>es.cic.tessa</groupId>
    <artifactId>tessa-support-common</artifactId>
    <version>2.6.0-SNAPSHOT</version>
</dependency>

<!-- Clientes REST -->
<dependency>
    <groupId>es.cic.tessa</groupId>
    <artifactId>tessa-support-client</artifactId>
    <version>2.6.0-SNAPSHOT</version>
</dependency>

<!-- Soporte Excel -->
<dependency>
    <groupId>es.cic.tessa</groupId>
    <artifactId>tessa-support-excel-model</artifactId>
    <version>2.6.0-SNAPSHOT</version>
</dependency>

<!-- Modelo lookup -->
<dependency>
    <groupId>es.cic.tessa</groupId>
    <artifactId>tessa-support-lookup-model</artifactId>
    <version>2.6.0-SNAPSHOT</version>
</dependency>
```

## Repositorio

`https://gitlab.corp.cic.es/CIC/tessa/tessa-support.git`
