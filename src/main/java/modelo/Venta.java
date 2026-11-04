package modelo;

import java.time.LocalDate;

/** Un registro del archivo ventas.csv. El campo valor es el total de esa venta en pesos (COP). */
public record Venta(LocalDate fecha, String producto, int cantidad, long valor) {
}
