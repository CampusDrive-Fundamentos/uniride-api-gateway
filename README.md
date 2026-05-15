# uniride-api-gateway
API Gateway para redirigir rutas, aplicar CORS y algunas funciones de seguridad básicas.

## 🚀 Despliegue Local con Docker Compose

Este repositorio contiene el archivo maestro `docker-compose.yml` que orquesta **todo el backend** de Uniride (bases de datos, el API Gateway y los microservicios).

### 📋 Requisitos Previos

Dado que el `docker-compose.yml` compila el código fuente de los microservicios en tiempo real, **necesitas tener todos los repositorios clonados en tu computadora y organizados en la misma carpeta**. 

Tu estructura de carpetas en tu PC debe verse exactamente así (al mismo nivel):

```text
📁 GitHub (o tu carpeta de proyectos)
 ├── 📁 uniride-iam-service       <-- (Microservicio de Identidad)
 ├── 📁 uniride-routes-service    <-- (Microservicio de Rutas)
 ├── 📁 uniride-booking-service   <-- (Microservicio de Reservas)
 └── 📁 uniride-api-gateway       <-- (ESTE REPOSITORIO)
```

> **Nota:** Todos los repositorios de los microservicios deben tener un archivo `Dockerfile` en su directorio raíz para que Docker sepa cómo compilarlos.

### 🛠️ ¿Cómo ejecutar toda la arquitectura?

1. **Abre una terminal** y asegúrate de estar dentro de la carpeta `uniride-api-gateway` (donde vive el archivo `docker-compose.yml`).
2. **Ejecuta el siguiente comando** para construir las imágenes y levantar todos los contenedores:

   ```bash
   docker-compose up --build -d
   ```
   * *`--build`: Obliga a Docker a re-compilar el código de Java en caso de que hayas hecho cambios recientes en algún microservicio.*
   * *`-d`: (Detached mode) Ejecuta los contenedores en segundo plano. Esto permite que sigas usando tu terminal sin que se bloquee mostrando logs.*

3. **¡Espera unos minutos!** Docker descargará Maven y construirá los tres microservicios desde cero. Una vez finalice, levantará 3 bases de datos PostgreSQL y 4 aplicaciones Spring Boot.

### 🌐 Accesos y Puertos

Una vez que todos los contenedores estén en estado "Running", podrás probar los servicios localmente:

- **API Gateway (Entrada principal del Frontend):** `http://localhost:8080`
- **IAM Service (Swagger UI):** `http://localhost:8081/swagger-ui/index.html`
- **Routes Service (Swagger UI):** `http://localhost:8082/swagger-ui/index.html`
- **Booking Service (Swagger UI):** `http://localhost:8083/swagger-ui/index.html`

*(Recuerda que para peticiones directas desde el Frontend o Postman, deberías apuntar siempre al Gateway en el puerto `8080`, aunque para depurar puedes usar los puertos individuales).*

### 🛑 ¿Cómo detener los servicios?

Para pausar o apagar los contenedores momentáneamente (sin perder la configuración actual):
```bash
docker-compose stop
```

Si deseas **destruir** los contenedores por completo y liberar espacio:
```bash
docker-compose down
```
