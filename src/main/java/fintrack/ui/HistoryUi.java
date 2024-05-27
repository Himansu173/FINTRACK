package fintrack.ui;

import com.toedter.calendar.JDateChooser;

import fintrack.db.ExpenseDB;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        timePeriodComboBox.setBackground(new Color(195, 224, 229));
        timePeriodComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        timePeriodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataset((String) timePeriodComboBox.getSelectedItem());
            }
        });

        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        comboBoxPanel.setBackground(new Color(88, 133, 175));
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
        historyPanel.setBackground(new Color(88, 133, 175));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Expense History");
        titledBorder.setTitleColor(Color.WHITE); // Set the title color to white

        // Set the titled border with the custom title color to the historyPanel
        historyPanel.setBorder(titledBorder);

        // Add label "Check expense history" and date chooser
        JPanel checkDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkDatePanel.setBackground(new Color(88, 133, 175));
        JLabel checkLabel = new JLabel("Check Expense History");
        checkLabel.setForeground(Color.WHITE);
        checkDatePanel.add(checkLabel);
        dateChooser = new JDateChooser();
        dateChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dateChooser.setDateFormatString("dd-MMM-yyyy");
        dateChooser.setDate(new Date()); // Set default to today's date
        dateChooser.setMaxSelectableDate(new Date()); // Allow only past dates
        dateChooser.setPreferredSize(new Dimension(150, 25));
        checkDatePanel.add(dateChooser);
        historyPanel.add(checkDatePanel, BorderLayout.NORTH);

        // Add search button
        JButton searchButton = new JButton("Search");
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusable(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setBackground(new Color(39, 68, 114));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchHistory();
            }
        });
        checkDatePanel.add(searchButton);

        // Create table for displaying history
        historyTable = new JTable() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        JScrollPane tableScrollPane = new JScrollPane(historyTable);
        tableScrollPane.getViewport().setBackground(new Color(88, 133, 175));
        historyPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(historyPanel, gbc);
    }

    String[] categories = { "Healthcare", "Food", "Transportation", "Entertainment", "Clothing",
            "Taxes", "Housing", "Utilities", "Shopping", "Others" };
    int expense = 0;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
    LocalDate currentDate = LocalDate.now();

    private DefaultCategoryDataset createDataset(String timePeriod) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (timePeriod.equals("Last 7 days")) {
            for (int i = 0; i < 10; i++) {
                try {
                    expense = ExpenseDB.getTotalExpenseCategoryWise(SigninUi.Email, currentDate.format(formatter),
                            currentDate.minusDays(6).format(formatter), categories[i]);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Some error occure in the expense history!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
                dataset.addValue(expense, "Expense", categories[i]);
            }
        } else if (timePeriod.equals("1 Month")) {
            for (int i = 0; i < 10; i++) {
                try {
                    expense = ExpenseDB.getTotalExpenseCategoryWise(SigninUi.Email, currentDate.format(formatter),
                            currentDate.minusDays(30).format(formatter), categories[i]);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Some error occure in the expense history!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
                dataset.addValue(expense, "Expense", categories[i]);
            }
        } else if (timePeriod.equals("3 Months")) {
            for (int i = 0; i < 10; i++) {
                try {
                    expense = ExpenseDB.getTotalExpenseCategoryWise(SigninUi.Email, currentDate.format(formatter),
                            currentDate.minusDays(90).format(formatter), categories[i]);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Some error occure in the expense history!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
                dataset.addValue(expense, "Expense", categories[i]);
            }
        } else if (timePeriod.equals("6 Months")) {
            for (int i = 0; i < 10; i++) {
                try {
                    expense = ExpenseDB.getTotalExpenseCategoryWise(SigninUi.Email, currentDate.format(formatter),
                            currentDate.minusDays(180).format(formatter), categories[i]);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Some error occure in the expense history!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
                dataset.addValue(expense, "Expense", categories[i]);
            }
        } else if (timePeriod.equals("1 Year")) {
            for (int i = 0; i < 10; i++) {
                try {
                    expense = ExpenseDB.getTotalExpenseCategoryWise(SigninUi.Email, currentDate.format(formatter),
                            currentDate.minusMonths(12).format(formatter), categories[i]);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Some error occure in the expense history!", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    System.out.println(e);
                }
                dataset.addValue(expense, "Expense", categories[i]);
            }
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

        chart.setBackgroundPaint(new Color(88, 133, 175));
        // Set custom fonts for title and axis labels
        Font labelFont = new Font("Calibri", Font.PLAIN, 16);
        chart.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
        chart.getTitle().setPaint(Color.WHITE); // Set title color to white
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setLabelFont(labelFont);
        plot.setBackgroundPaint(new Color(88, 133, 175));
        plot.getDomainAxis().setLabelPaint(Color.WHITE); // Set domain axis label color to white
        plot.getDomainAxis().setTickLabelFont(labelFont);
        plot.getDomainAxis().setTickLabelPaint(Color.WHITE); // Set domain axis tick label color to white
        plot.getRangeAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setLabelPaint(Color.WHITE); // Set range axis label color to white
        plot.getRangeAxis().setTickLabelFont(labelFont);
        plot.getRangeAxis().setTickLabelPaint(Color.WHITE); // Set range axis tick label color to white

        chart.getLegend().setItemPaint(Color.WHITE);
        chart.getLegend().setBackgroundPaint(new Color(88, 133, 175));

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

        plot.setBackgroundPaint(new Color(88, 133, 175));
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
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
        // Define the date format
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");

        // Format the selected date to a string
        String formattedDate = formatter.format(selectedDate);

        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Date", "Time", "Amount", "Category", "Description" }, 0);

        ResultSet expense;
        try {
            expense = ExpenseDB.returnExpenses(SigninUi.Email, formattedDate);
            while (expense.next()) {
                model.addRow(
                        new Object[] { formatter.format(expense.getDate("EXPENSE_DATE")), expense.getString("TIME"),
                                "" + expense.getInt("AMOUNT"),
                                expense.getString("CATEGORY"), expense.getString("DESCRIPTION") });
                ;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error in fatching expense.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
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
                    c.setBackground(new Color(195, 224, 229));
                } else {
                    c.setBackground(new Color(88, 133, 175));
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
