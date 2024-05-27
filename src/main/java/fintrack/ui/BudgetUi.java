package fintrack.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import fintrack.db.BudgetDB;

import java.awt.*;
import java.sql.ResultSet;

public class BudgetUi extends JPanel {
    private JTable budgetTable;
    private DefaultTableModel tableModel;

    public BudgetUi() {
        setLayout(new BorderLayout());
        setBackground(new Color(88, 133, 175));

        // Create table model with columns for time, amount, and category
        tableModel = new DefaultTableModel(new Object[] { "MONTH", "BUDGET", "EXPENSE", "SAVING", "PERFORMANCE" }, 0);

        // Create JTable with the model
        budgetTable = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        budgetTable.setDefaultRenderer(Object.class, new CenterRenderer());
        budgetTable.setFont(new Font("Calibri", Font.PLAIN, 18));
        budgetTable.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 19));
        budgetTable.getTableHeader().setPreferredSize(new Dimension(budgetTable.getWidth(), 35));
        budgetTable.setRowHeight(55);
        budgetTable.setShowGrid(false); // Hide grid lines
        budgetTable.setIntercellSpacing(new Dimension(0, 0)); // Remove cell spacing
        budgetTable.setBorder(null); // Remove table border
        budgetTable.getTableHeader().setBorder(null); // Remove header border
        JScrollPane scrollPane = new JScrollPane(budgetTable);
        scrollPane.getViewport().setBackground(new Color(88, 133, 175));
        scrollPane.setBorder(null); // Remove scroll pane border

        // Wrap the scroll pane inside another panel with EmptyBorder
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add 10px gap around the table
        tablePanel.setBackground(new Color(88, 133, 175));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);

        try {
            ResultSet rs = BudgetDB.getAllBudget(SigninUi.Email);
            while (rs.next()) {
                addExpense(rs.getString("MONTH"), rs.getInt("TOTAL_BUDGET"), rs.getInt("EXPENSE"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error in geting budget history", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }

    private void addExpense(String month, int budget, int expense) {
        int actualSaving = budget - expense;
        double savingPercentage = ((double) actualSaving / budget) * 100;
        String performance;
        Color color;

        if (actualSaving >= 0) {
            if (savingPercentage > 15) {
                performance = "Excellent";
                color = Color.GREEN;
            } else if (5 < savingPercentage && savingPercentage <= 15) {
                performance = "Nice";
                color = Color.BLUE;
            } else {
                performance = "Good";
                color = Color.ORANGE;
            }
        } else {
            performance = "Bad";
            color = Color.RED;
        }

        Object[] rowData = { month, budget, expense, actualSaving < 0 ? 0 : actualSaving, performance };
        tableModel.addRow(rowData);

        // Set color for the last cell in the added row
        budgetTable.setValueAt("<html><font color='" + getColorCode(color) + "'>" + performance + "</font></html>",
                tableModel.getRowCount() - 1, 4);
    }

    // Custom TableCellRenderer to center align cell contents
    private class CenterRenderer extends DefaultTableCellRenderer {
        private Color color1 = new Color(195, 224, 229);
        private Color color2 = new Color(88, 133, 175);

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Set horizontal alignment to center
            setHorizontalAlignment(SwingConstants.CENTER);
            // Set background color based on row index
            if (row % 2 == 0) {
                component.setBackground(color1);
            } else {
                component.setBackground(color2);
            }
            return component;
        }
    }

    // Convert Color object to HTML color code
    private String getColorCode(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
