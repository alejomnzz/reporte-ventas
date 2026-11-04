import modelo.Indicadores;
import modelo.Venta;
import reporte.GraficaVentas;
import reporte.ResumenIA;
import servicio.CalculadorIndicadores;
import servicio.LectorVentas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/** Ejecutar desde la raíz del proyecto: java -jar target/reporte-ventas.jar */
public class Main {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "true"); // sin ventana gráfica

        try {
            Path salida = Path.of("salida");
            Files.createDirectories(salida);

            // 1. Leer el CSV y calcular indicadores
            List<Venta> ventas = LectorVentas.leer(Path.of("datos/ventas.csv"));
            Indicadores ind = CalculadorIndicadores.calcular(ventas);
            imprimirIndicadores(ind);

            // 2. Gráfica
            Path grafica = salida.resolve("ventas_por_producto.png");
            GraficaVentas.generarPNG(ind, grafica);
            System.out.println("\nGráfica generada: " + grafica);

            // 3. Resumen y prompt para la IA
            String prompt = ResumenIA.construirPrompt(ResumenIA.construirResumen(ind));
            Path archivoPrompt = salida.resolve("prompt.txt");
            Files.writeString(archivoPrompt, prompt);
            System.out.println("Prompt generado: " + archivoPrompt);
            System.out.println("Pega ese prompt en una IA y guarda su respuesta en salida/respuesta_ia.txt");

            System.out.println("\nEjecución finalizada correctamente.");

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void imprimirIndicadores(Indicadores ind) {
        NumberFormat cop = NumberFormat.getIntegerInstance(Locale.forLanguageTag("es-CO"));
        System.out.println("=== INDICADORES ===");
        System.out.println("Valor total de ventas      : $" + cop.format(ind.valorTotal()));
        System.out.println("Cantidad total vendida     : " + ind.cantidadTotal());
        System.out.println("Producto con mayor valor   : " + ind.productoTop()
                + " ($" + cop.format(ind.valorProductoTop()) + ")");
        System.out.println("Valor promedio por registro: $" + cop.format(Math.round(ind.promedioPorRegistro())));
    }
}
