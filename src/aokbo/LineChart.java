/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aokbo;

/**
 *
 * @author Oytun
 */
import java.util.ArrayList;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame {

    public LineChart(String applicationTitle, String chartTitle, ArrayList<Integer> arrayList) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Generations", "Fitness Point",
                createDataset(arrayList),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        setContentPane(chartPanel);
    }

    public LineChart(String applicationTitle, String chartTitle, GraphData data) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Ingame Seconds", "Resource",
                createDataset(data),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(ArrayList<Integer> arrayList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int counter = 0;
        for (int i : arrayList) {
            dataset.addValue(i, "Fitness", String.valueOf(counter));
            counter += 1;
        }
        return dataset;
    }

    private DefaultCategoryDataset createDataset(GraphData data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        addGraph(dataset, "food", data.getFood());
        addGraph(dataset, "stone", data.getStone());
        addGraph(dataset, "wood", data.getWood());
        addGraph(dataset, "gold", data.getGold());
        addGraph(dataset, "Population", data.getPopulation());
        addGraph(dataset, "Villagers", data.getVillagerCount());
        addGraph(dataset, "IdleVillagers", data.getIdleVillagerCount());

        return dataset;
    }

    private static void addGraph(DefaultCategoryDataset dataset, String graphName, ArrayList<Integer> theData) {
        int counter = 0;
        for (int i : theData) {
            dataset.addValue(i, graphName, String.valueOf(counter));
            counter += 1;
        }
    }

    public static void run(ArrayList arrayList) {
        LineChart chart = new LineChart(
                "Generations/fitness",
                "Generations/fitness", arrayList);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public static void run(GraphData data) {
        LineChart chart = new LineChart(
                "IngameSeconds/Resources",
                "IngameSeconds/Resources", data);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public static void runWithData(GraphData data) {
        LineChart chart = new LineChart(
                "Generations/fitness",
                "Generations/fitness", data.getFood());

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

        LineChart chart1 = new LineChart(
                "Generations/fitness",
                "Generations/fitness", data.getGold());

        chart1.pack();
        RefineryUtilities.centerFrameOnScreen(chart1);
        chart1.setVisible(true);

        LineChart chart2 = new LineChart(
                "Generations/fitness",
                "Generations/fitness", data.getPopulation());

        chart2.pack();
        RefineryUtilities.centerFrameOnScreen(chart2);
        chart2.setVisible(true);

    }

    public static void main(String[] args) {

    }
}
