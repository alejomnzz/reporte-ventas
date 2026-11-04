package servicio;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import modelo.Venta;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Lee ventas.csv usando OpenCSV. Las filas invalidas se reportan y se omiten. */
public class LectorVentas {

    public static List<Venta> leer(Path archivo) throws IOException {
        List<Venta> ventas = new ArrayList<>();
        try (CSVReaderHeaderAware lector =
                     new CSVReaderHeaderAware(Files.newBufferedReader(archivo, StandardCharsets.UTF_8))) {
            Map<String, String> fila;
            int numeroFila = 1; // la fila 1 es el encabezado
            while ((fila = lector.readMap()) != null) {
                numeroFila++;
                try {
                    ventas.add(new Venta(
                            LocalDate.parse(fila.get("fecha").trim()),
                            fila.get("producto").trim(),
                            Integer.parseInt(fila.get("cantidad").trim()),
                            Long.parseLong(fila.get("valor").trim())));
                } catch (RuntimeException e) {
                    System.err.println("Aviso: fila " + numeroFila + " ignorada (datos incompletos o con formato invalido).");
                }
            }
        } catch (CsvValidationException e) {
            throw new IOException("El archivo CSV no es valido: " + e.getMessage(), e);
        }
        return ventas;
    }
}
