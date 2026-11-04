package reporte;

import modelo.Indicadores;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DecimalFormat;

/**
 * Gráfica de barras de ventas por producto (JFreeChart).
 * Tiene título, ejes con nombre y el valor escrito sobre cada barra,
 * así que no depende solo del color para comunicar el resultado.
 */
public class GraficaVentas {

    public static void generarPNG(Indicadores ind, Path destino) throws IOException {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        ind.valorPorProducto().forEach((producto, valor) ->
                datos.addValue(valor / 1_000_000.0, "Ventas", producto));

        JFreeChart grafica = ChartFactory.createBarChart(
                "Ventas por producto",
                "Producto",
                "Valor vendido (millones de COP)",
                datos,
                PlotOrientation.VERTICAL,
                false, false, false);

        CategoryPlot plot = grafica.getCategoryPlot();
        Font letra = new Font("SansSerif", Font.PLAIN, 14);
        plot.getDomainAxis().setTickLabelFont(letra);
        plot.getRangeAxis().setTickLabelFont(letra);

        // Valor numérico encima de cada barra
        BarRenderer render = (BarRenderer) plot.getRenderer();
        render.setDefaultItemLabelGenerator(
                new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("#,##0.00")));
        render.setDefaultItemLabelFont(letra);
        render.setDefaultItemLabelsVisible(true);

        ChartUtils.saveChartAsPNG(destino.toFile(), grafica, 800, 500);
    }
}
