package reporte;

import modelo.Indicadores;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/** Convierte los indicadores calculados por Java en un resumen de texto y en un prompt para la IA. */
public class ResumenIA {

    public static String construirResumen(Indicadores ind) {
        NumberFormat cop = NumberFormat.getIntegerInstance(Locale.forLanguageTag("es-CO"));
        StringBuilder resumen = new StringBuilder();
        resumen.append("Registros procesados: ").append(ind.numeroRegistros()).append("\n");
        resumen.append("Valor total de ventas: $").append(cop.format(ind.valorTotal())).append(" COP\n");
        resumen.append("Cantidad total vendida: ").append(ind.cantidadTotal()).append(" unidades\n");
        resumen.append("Producto con mayor valor vendido: ").append(ind.productoTop())
                .append(" ($").append(cop.format(ind.valorProductoTop())).append(" COP)\n");
        resumen.append("Valor promedio por registro: $")
                .append(cop.format(Math.round(ind.promedioPorRegistro()))).append(" COP\n");
        resumen.append("Valor vendido por producto:\n");
        for (Map.Entry<String, Long> e : ind.valorPorProducto().entrySet()) {
            resumen.append("- ").append(e.getKey()).append(": $")
                    .append(cop.format(e.getValue())).append(" COP\n");
        }
        return resumen.toString();
    }

    public static String construirPrompt(String resumen) {
        return "Eres un analista comercial. Con base únicamente en los indicadores de abajo, "
                + "redacta un reporte ejecutivo de máximo tres párrafos para la gerencia de una "
                + "pequeña empresa. No inventes datos ni recalcules cifras: los indicadores ya "
                + "fueron calculados por el programa.\n\n"
                + "Indicadores:\n" + resumen;
    }
}
