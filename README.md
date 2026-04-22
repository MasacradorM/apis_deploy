# Apis-deploy-products-1

Repositorio monorepo con tres mini APIs listas para despliegue:

- `Api-Node`
- `Api-SpringBoot`
- `Api-Django`

## Entidad: Product

Cada API gestiona productos con los campos:
- `name` — nombre del producto
- `price` — precio (decimal)
- `stock` — unidades disponibles (entero)
- `category` — categoría del producto

## Endpoints disponibles (todas las APIs)

| Método | Ruta              | Descripción                  |
|--------|-------------------|------------------------------|
| GET    | `/`               | Mensaje de bienvenida        |
| GET    | `/products`       | Listar todos los productos   |
| GET    | `/products/:id`   | Obtener producto por ID      |
| POST   | `/products`       | Crear nuevo producto         |
| PUT    | `/products/:id`   | Actualizar producto por ID   |
| DELETE | `/products/:id`   | Eliminar producto por ID     |

## Despliegue con Render + GitHub Actions

1. Crea tres servicios web en Render usando este repositorio.
2. Usa `render.yaml` como blueprint o crea cada servicio por separado con estas rutas raíz:
   - `Api-Node`
   - `Api-SpringBoot`
   - `Api-Django`
3. Copia el deploy hook de cada servicio y guárdalo en GitHub Secrets con estos nombres:
   - `RENDER_DEPLOY_HOOK_NODE`
   - `RENDER_DEPLOY_HOOK_SPRING`
   - `RENDER_DEPLOY_HOOK_DJANGO`
4. Cada push a `main` dispara el workflow, que valida el proyecto y luego llama el hook de Render.

-----------------------------------------------------------------

🎬 GUION – DESPLIEGUE AUTOMATIZADO CON GITHUB ACTIONS Y RENDER
1. Introducción

En este proyecto se implementa el despliegue automatizado de tres aplicaciones desarrolladas en diferentes tecnologías: Node.js, Django y Spring Boot.

El objetivo es demostrar el uso de integración y despliegue continuo (CI/CD) mediante GitHub Actions, permitiendo que cada cambio en el repositorio se despliegue automáticamente en la plataforma Render.

2. Arquitectura General

El flujo de funcionamiento del sistema es el siguiente:

Se realiza un cambio en el código fuente.
Se hace un push al repositorio en GitHub.
Se ejecuta automáticamente un workflow de GitHub Actions.
El workflow construye la aplicación.
Se envía una solicitud a Render mediante un deploy hook.
Render despliega la aplicación actualizada.

Este proceso permite automatizar completamente la entrega del software.

3. Estructura del Proyecto

El proyecto está organizado en tres aplicaciones independientes:

API desarrollada en Node.js
API desarrollada en Django
API desarrollada en Spring Boot

Además, se incluye la carpeta .github/workflows, donde se definen los archivos de configuración de los pipelines de despliegue.

4. Configuración de GitHub Actions

Cada tecnología cuenta con su propio archivo YAML que define el proceso de despliegue automático.

4.1 Node.js

El workflow realiza las siguientes acciones:

Descarga el código del repositorio
Instala las dependencias con npm
Ejecuta el proceso de construcción si es necesario
Envía una solicitud HTTP al deploy hook de Render
4.2 Django

El workflow incluye:

Configuración del entorno de Python
Instalación de dependencias desde requirements.txt
Ejecución del comando collectstatic para producción
Llamado al deploy hook de Render
4.3 Spring Boot

El proceso consiste en:

Configuración del entorno de Java
Construcción del proyecto utilizando Maven Wrapper
Ejecución del comando de build
Despliegue mediante el deploy hook
5. Configuración en Render

Cada aplicación está configurada como un servicio web en Render, conectado al repositorio de GitHub.

Render proporciona un deploy hook, que es una URL que permite activar el despliegue de forma remota.

Este hook es utilizado dentro de los workflows para automatizar el proceso.

6. Uso de Variables de Entorno

Para garantizar la seguridad, el deploy hook no se expone directamente en el código.

En su lugar, se almacena como un secreto en GitHub, utilizando la sección de Secrets del repositorio.

De esta manera se protege la información sensible y se mantiene una buena práctica de seguridad.

7. Demostración del Proceso

Al realizar un cambio en el código y hacer push a la rama principal:

Se ejecuta automáticamente el workflow
Se construye la aplicación
Se realiza el despliegue en Render
La aplicación queda disponible en línea con los cambios aplicados

Este proceso ocurre sin intervención manual.

8. Conclusión

La implementación de CI/CD con GitHub Actions y Render permite automatizar el ciclo de desarrollo, reduciendo errores y mejorando la eficiencia en la entrega de software.

Además, demuestra la capacidad de trabajar con múltiples tecnologías bajo un mismo flujo de despliegue automatizado.
