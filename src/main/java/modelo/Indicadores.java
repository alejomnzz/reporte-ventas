package modelo;

import java.util.Map;

/** Resultado de los calculos deterministas hechos por Java (la IA no calcula nada). */
public record Indicadores(
        long valorTotal,
        int cantidadTotal,
        String productoTop,
        long valorProductoTop,
        double promedioPorRegistro,
        int numeroRegistros,
        Map<String, Long> valorPorProducto) {
}
