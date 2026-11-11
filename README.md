# Reporte de ventas — Caso de Estudio 2

Técnicas de Programación y Laboratorio · Universidad de Antioquia

Aplicación Java (Maven) que lee `datos/ventas.csv`, calcula indicadores de ventas, genera una gráfica
y construye un prompt para que una IA redacte un reporte ejecutivo de máximo tres párrafos.

# Integrantes:
Cristian Alejandro Muñoz Bolaños
Lydia Stella Reyes Celis

## Requisitos
- JDK 17 o superior
- Maven

## Compilar
Desde la raíz del proyecto:

```bash
mvn clean package
```

El JAR queda en `target/reporte-ventas.jar`.

## Ejecutar
Desde la raíz del proyecto (para que encuentre `datos/ventas.csv`):

```bash
java -jar target/reporte-ventas.jar
```

La aplicación imprime los indicadores y crea la carpeta `salida/` con:
- `ventas_por_producto.png`: gráfica de ventas por producto.
- `prompt.txt`: prompt para la IA, con el resumen de indicadores.

La respuesta de la IA se obtiene pegando `prompt.txt` en una IA y guardando el resultado en `respuesta_ia.txt`.

## Indicadores calculados por Java
- Valor total de ventas
- Cantidad total vendida
- Producto con mayor valor vendido
- Valor promedio de venta por registro

La IA solo redacta el reporte a partir de estos valores; no hace ningún cálculo.

## Análisis de licencias

| Librería | Uso en el proyecto | Licencia | Conclusión de uso |
|---|---|---|---|
| OpenCSV 5.9 | Leer y procesar `ventas.csv` | Apache License 2.0 | Licencia permisiva: permite usar, modificar y distribuir el software, incluso en proyectos cerrados, conservando el aviso de licencia. Es compatible con un proyecto académico. |
| JFreeChart 1.5.5 | Generar la gráfica de barras en PNG | LGPL 2.1 o posterior | Licencia de copyleft débil: se puede usar como librería sin que el proyecto completo tenga que ser LGPL. Solo obliga a compartir los cambios si se modifica la propia librería y se distribuye, y a incluir su licencia al distribuirla. Compatible con un proyecto académico. |

## Evidencias
La carpeta `evidencias/` contiene la gráfica generada, el prompt, la respuesta de la IA y las capturas
de la compilación con Maven y de la ejecución del JAR desde la terminal.

## Seguridad
El proyecto no usa contraseñas, tokens ni API keys.
