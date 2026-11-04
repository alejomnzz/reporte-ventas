package servicio;

import modelo.Indicadores;
import modelo.Venta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Calcula los indicadores del reporte. Toda la aritmética la hace Java. */
public class CalculadorIndicadores {

    public static Indicadores calcular(List<Venta> ventas) {
        if (ventas.isEmpty()) {
            throw new IllegalArgumentException("No hay ventas válidas para procesar.");
        }

        long valorTotal = 0;
        int cantidadTotal = 0;
        Map<String, Long> valorPorProducto = new HashMap<>();

        for (Venta v : ventas) {
            valorTotal += v.valor();
            cantidadTotal += v.cantidad();
            valorPorProducto.merge(v.producto(), v.valor(), Long::sum);
        }

        // Producto con mayor valor vendido
        String productoTop = null;
        long valorTop = 0;
        for (Map.Entry<String, Long> e : valorPorProducto.entrySet()) {
            if (productoTop == null || e.getValue() > valorTop) {
                productoTop = e.getKey();
                valorTop = e.getValue();
            }
        }

        double promedio = (double) valorTotal / ventas.size();

        return new Indicadores(valorTotal, cantidadTotal, productoTop, valorTop,
                promedio, ventas.size(), valorPorProducto);
    }
}
