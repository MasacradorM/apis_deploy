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
