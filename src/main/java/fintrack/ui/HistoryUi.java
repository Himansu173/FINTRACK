package fintrack.ui;

import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class HistoryUi extends JPanel {
    private JComboBox<String> timePeriodComboBox;
    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private JDateChooser dateChooser;
    private JTable historyTable;

    public HistoryUi() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.75;
        gbc.fill = GridBagConstraints.BOTH;

        // Create combo box panel
        String[] timePeriods = { "Last 7 days", "1 Month", "3 Months", "6 Months", "1 Year" };
        timePeriodComboBox = new JComboBox<>(timePeriods);
        timePeriodComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        timePeriodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataset((String) timePeriodComboBox.getSelectedItem());
            }
        });

        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        comboBoxPanel.setBackground(Color.WHITE);
        comboBoxPanel.add(timePeriodComboBox);

        // Create chart panel
        dataset = createDataset("Last 7 days");
        chart = createChart(dataset);
        customizeChart(chart);
        ChartPanel chartPanel = new ChartPanel(chart);

        // Add combo box panel and chart panel to top section
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(comboBoxPanel, BorderLayout.NORTH);
        topPanel.add(chartPanel, BorderLayout.CENTER);

        add(topPanel, gbc);

        // Create history panel
        gbc.gridy = 1;
        gbc.weighty = 0.25;
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBackground(Color.WHITE);
        historyPanel.setBorder(BorderFactory.createTitledBorder("Expense History"));

        // Add label "Check expense history" and date chooser
        JPanel checkDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkDatePanel.setBackground(Color.WHITE);
        JLabel checkLabel = new JLabel("Check Expense History");
        checkDatePanel.add(checkLabel);
        dateChooser = new JDateChooser();
        dateChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setDate(new Date()); // Set default to today's date
        dateChooser.setMaxSelectableDate(new Date()); // Allow only past dates
        dateChooser.setPreferredSize(new Dimension(150, 25));
        checkDatePanel.add(dateChooser);
        historyPanel.add(checkDatePanel, BorderLayout.NORTH);

        // Add search button
        JButton searchButton = new JButton("Search");
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setBackground(new Color(63, 184, 39));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchHistory();
            }
        });
        checkDatePanel.add(searchButton);

        // Create table for displaying history
        historyTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(historyTable);
        historyPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(historyPanel, gbc);
    }

    private DefaultCategoryDataset createDataset(String timePeriod) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Sample data for different time periods
        if (timePeriod.equals("Last 7 days")) {
            dataset.addValue(200, "Expenses", "Healthcare");
            dataset.addValue(744, "Expenses", "Food");
            dataset.addValue(353, "Expenses", "Transportation");
            dataset.addValue(873, "Expenses", "Entertainment");
            dataset.addValue(150, "Expenses", "Clothing");
            dataset.addValue(300, "Expenses", "Taxes");
            dataset.addValue(1200, "Expenses", "Housing");
            dataset.addValue(114, "Expenses", "Utility");
            dataset.addValue(179, "Expenses", "Shopping");
            dataset.addValue(551, "Expenses", "Others");
        } else if (timePeriod.equals("1 Month")) {
            dataset.addValue(400, "Expenses", "Healthcare");
            dataset.addValue(163, "Expenses", "Food");
            dataset.addValue(654, "Expenses", "Transportation");
            dataset.addValue(356, "Expenses", "Entertainment");
            dataset.addValue(200, "Expenses", "Clothing");
            dataset.addValue(1200, "Expenses", "Taxes");
            dataset.addValue(4800, "Expenses", "Housing");
            dataset.addValue(356, "Expenses", "Utility");
            dataset.addValue(30, "Expenses", "Shopping");
            dataset.addValue(55, "Expenses", "Others");
        } else if (timePeriod.equals("3 Months")) {
            dataset.addValue(1200, "Expenses", "Healthcare");
            dataset.addValue(543, "Expenses", "Food");
            dataset.addValue(634, "Expenses", "Transportation");
            dataset.addValue(643, "Expenses", "Entertainment");
            dataset.addValue(600, "Expenses", "Clothing");
            dataset.addValue(3600, "Expenses", "Taxes");
            dataset.addValue(14400, "Expenses", "Housing");
            dataset.addValue(342, "Expenses", "Utility");
            dataset.addValue(330, "Expenses", "Shopping");
            dataset.addValue(756, "Expenses", "Others");
        } else if (timePeriod.equals("6 Months")) {
            dataset.addValue(2400, "Expenses", "Healthcare");
            dataset.addValue(290, "Expenses", "Food");
            dataset.addValue(720, "Expenses", "Transportation");
            dataset.addValue(290, "Expenses", "Entertainment");
            dataset.addValue(1200, "Expenses", "Clothing");
            dataset.addValue(7200, "Expenses", "Taxes");
            dataset.addValue(28800, "Expenses", "Housing");
            dataset.addValue(650, "Expenses", "Utility");
            dataset.addValue(150, "Expenses", "Shopping");
            dataset.addValue(604, "Expenses", "Others");
        } else if (timePeriod.equals("1 Year")) {
            dataset.addValue(4800, "Expenses", "Healthcare");
            dataset.addValue(410, "Expenses", "Food");
            dataset.addValue(290, "Expenses", "Transportation");
            dataset.addValue(780, "Expenses", "Entertainment");
            dataset.addValue(2400, "Expenses", "Clothing");
            dataset.addValue(14400, "Expenses", "Taxes");
            dataset.addValue(57600, "Expenses", "Housing");
            dataset.addValue(370, "Expenses", "Utility");
            dataset.addValue(310, "Expenses", "Shopping");
            dataset.addValue(160, "Expenses", "Others");
        }
        

        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Expenses by Category", // chart title
                "Category", // domain axis label
                "Amount", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true,
                false);

        // Set custom fonts for title and axis labels
        chart.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setLabelFont(new Font("Calibri", Font.PLAIN, 16));
        plot.getRangeAxis().setLabelFont(new Font("Calibri", Font.PLAIN, 16));

        return chart;
    }

    private void customizeChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = new CustomBarRenderer(
                new Paint[] {
                        new Color(255, 100, 100),
                        new Color(100, 255, 100),
                        new Color(100, 100, 255),
                        new Color(255, 255, 100),
                        new Color(255, 200, 100),
                        new Color(200, 200, 200),
                        new Color(255, 150, 150),
                        new Color(100, 255, 255), 
                        new Color(200, 100, 255), 
                        new Color(255, 100, 200)
                });
        plot.setRenderer(renderer);

        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
    }

    private void updateDataset(String timePeriod) {
        dataset.clear();
        DefaultCategoryDataset newDataset = createDataset(timePeriod);

        for (int row = 0; row < newDataset.getRowCount(); row++) {
            for (int col = 0; col < newDataset.getColumnCount(); col++) {
                dataset.addValue(newDataset.getValue(row, col), newDataset.getRowKey(row),
                        newDataset.getColumnKey(col));
            }
        }
    }

    static class CustomBarRenderer extends BarRenderer {
        private Paint[] colors;

        public CustomBarRenderer(Paint[] colors) {
            this.colors = colors;
        }

        @Override
        public Paint getItemPaint(int row, int column) {
            return colors[column % colors.length];
        }
    }

    private void searchHistory() {
        // Get the selected date from the date chooser
        Date selectedDate = dateChooser.getDate();
        // Implement search functionality using the selectedDate
        // Dummy data for demonstration
        Object[][] data = {
                { "2024-05-15", "10:00 AM", "$50", "Food", "Groceries" },
                { "2024-05-15", "02:30 PM", "$20", "Entertainment", "Movie tickets" },
                { "2024-05-15", "06:45 PM", "$30", "Shopping", "Clothing" },
                { "2024-05-15", "09:00 PM", "$15", "Others", "Miscellaneous" },
                { "2024-05-16", "11:30 AM", "$40", "Food", "Restaurant" },
                { "2024-05-16", "03:00 PM", "$25", "Transportation", "Gasoline" },
                { "2024-05-16", "07:00 PM", "$10", "Utility", "Electricity bill" },
                { "2024-05-16", "08:30 PM", "$50", "Entertainment", "Concert tickets" },
                { "2024-05-16", "10:00 PM", "$20", "Others", "Miscellaneous" },
                // Add more rows as needed
        };

        String[] columns = { "Date", "Time", "Amount", "Category", "Description" };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        historyTable.setModel(model);
        historyTable.setBorder(null); // Remove table border
        historyTable.getTableHeader().setBorder(null); // Remove header border
        historyTable.setShowGrid(false); // Hide grid lines
        // Inside the searchHistory method, after setting the table model

        // Center align table content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setForeground(Color.BLACK); // Set text color to black

                // Alternate row colors
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(new Color(240, 240, 240));
                }

                // Center align text
                setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };

        // Apply the custom renderer to all columns
        for (int i = 0; i < historyTable.getColumnCount(); i++) {
            historyTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Increase row height
        historyTable.setRowHeight(30);
    }
}
