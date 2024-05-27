package fintrack.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.DefaultCategoryDataset;

import fintrack.db.ExpenseDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpenseTrend extends JPanel {
    private DefaultCategoryDataset dataset;
    private JComboBox<String> timeRangeComboBox;
    private ChartPanel chartPanel;

    public ExpenseTrend() {
        setBackground(new Color(88, 133, 175));
        setBorder(new EmptyBorder(0, 10, 0, 10));
        setPreferredSize(new Dimension(200, 100));

        setLayout(new BorderLayout());

        // Create combo box for selecting time range
        timeRangeComboBox = new JComboBox<>(
                new String[] { "Last 7 days", "1 month", "3 months", "6 months", "1 year" });
        timeRangeComboBox.setBackground(new Color(195, 224, 229));
        timeRangeComboBox.setSelectedIndex(0); // Default selection
        timeRangeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selectedTimeRange = (String) combo.getSelectedItem();
                updateChart(selectedTimeRange);
            }
        });
        timeRangeComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Create panel for combo box
        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // comboBoxPanel.add(new JLabel("Select Time Range:"));
        comboBoxPanel.add(timeRangeComboBox);
        comboBoxPanel.setBackground(new Color(88, 133, 175));
        add(comboBoxPanel, BorderLayout.NORTH);

        // Create dataset
        dataset = createDataset(7);

        // Create chart
        JFreeChart chart = createChart(dataset);

        // Create chart panel
        chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    private void updateChart(String timeRange) {
        int days;
        switch (timeRange) {
            case "Last 7 days":
                days = 7;
                break;
            case "1 month":
                days = 30;
                break;
            case "3 months":
                days = 90;
                break;
            case "6 months":
                days = 180;
                break;
            case "1 year":
                days = 365;
                break;
            default:
                days = 7;
        }

        dataset = createDataset(days);
        JFreeChart chart = createChart(dataset);
        chartPanel.setChart(chart);
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "Expense Trend",
                "Expense Date",
                "Expense Amount",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        chart.setBackgroundPaint(new Color(88, 133, 175));
        // Customize plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(88, 133, 175));
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);

        // Customize axes
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotate x-axis labels
        domainAxis.setTickLabelPaint(Color.WHITE); // Set x-axis tick label color
        domainAxis.setAxisLinePaint(Color.WHITE); // Set x-axis line color
        domainAxis.setLabelPaint(Color.WHITE); // Set x-axis label color

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits()); // Adjust y-axis ticks
        rangeAxis.setTickLabelPaint(Color.WHITE); // Set y-axis tick label color
        rangeAxis.setAxisLinePaint(Color.WHITE); // Set y-axis line color
        rangeAxis.setLabelPaint(Color.WHITE); // Set y-axis label color

        // Use LineAndShapeRenderer with interpolation for smooth graph line
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        // Set title color
        chart.getTitle().setPaint(Color.WHITE);

        // Set legend text color
        chart.getLegend().setItemPaint(Color.WHITE);
        chart.getLegend().setBackgroundPaint(new Color(88, 133, 175));

        return chart;
    }

    private DefaultCategoryDataset createDataset(int days) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Generate data for the specified number of days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(days - 1);
        int expense = 0;
        switch (days) {
            case 7:
                for (int i = 0; i < days; i++) {
                    LocalDate date = startDate.plusDays(i);
                    String day = date.format(formatter);
                    try {
                        expense = ExpenseDB.getTotalExpenseOfTheDay(SigninUi.Email, day);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Some error occure in the expense trend!", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        System.out.println(e);
                    }
                    dataset.addValue(expense, "Expense", day);
                }
                break;
            case 30:
                for (int i = 0; i < days / 2; i++) {
                    LocalDate date = startDate.plusDays(i * 2);
                    String day = date.format(formatter);
                    try {
                        expense = ExpenseDB.getTotalExpense(SigninUi.Email, day, date.minusDays(2).format(formatter));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Some error occure in the expense trend!", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        System.out.println(e);
                    }
                    dataset.addValue(expense, "Expense", day);
                }
                break;
            case 90:
                for (int i = 0; i < days / 6; i++) {
                    LocalDate date = startDate.plusDays(i * 6);
                    String day = date.format(formatter);
                    try {
                        expense = ExpenseDB.getTotalExpense(SigninUi.Email, day, date.minusDays(6).format(formatter));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Some error occure in the expense trend!", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        System.out.println(e);
                    }
                    dataset.addValue(expense, "Expense", day);
                }
                break;
            case 180:
                for (int i = 0; i < days / 12; i++) {
                    LocalDate date = startDate.plusDays(i * 12);
                    String day = date.format(formatter);
                    try {
                        expense = ExpenseDB.getTotalExpense(SigninUi.Email, day, date.minusDays(12).format(formatter));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Some error occure in the expense trend!", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        System.out.println(e);
                    }
                    dataset.addValue(expense, "Expense", day);
                }
                break;
            case 365:
                for (int i = 0; i < 12; i++) {
                    LocalDate date = startDate.plusMonths(i);
                    String day = date.format(formatter);
                    try {
                        expense = ExpenseDB.getTotalExpense(SigninUi.Email, day, date.minusDays(30).format(formatter));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Some error occure in the expense trend!", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        System.out.println(e);
                    }
                    dataset.addValue(expense, "Expense", day);
                }
                break;
            default:
                break;
        }

        return dataset;
    }
}
