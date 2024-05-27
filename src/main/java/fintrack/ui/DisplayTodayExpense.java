package fintrack.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import fintrack.db.FutureExpenseDB;
import fintrack.db.ExpenseDB;

import java.awt.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DisplayTodayExpense extends JPanel {
    private JTable expensesTable;
    private DefaultTableModel tableModel;

    public DisplayTodayExpense() {
        setLayout(new BorderLayout());

        // Create header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(88, 133, 175));
        headerPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        JLabel headerLabel = new JLabel("Today's Expenses");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Create table model with columns for time, amount, and category
        tableModel = new DefaultTableModel(new Object[] { "Time", "Amount", "Category" }, 0);

        // Create JTable with the model
        expensesTable = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        expensesTable.setDefaultRenderer(Object.class, new CenterRenderer());
        expensesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        expensesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        expensesTable.setRowHeight(30);
        expensesTable.setShowGrid(false); // Hide grid lines
        expensesTable.setIntercellSpacing(new Dimension(0, 0)); // Remove cell spacing
        expensesTable.setBorder(null); // Remove table border
        expensesTable.getTableHeader().setBorder(null); // Remove header border
        JScrollPane scrollPane = new JScrollPane(expensesTable);
        scrollPane.getViewport().setBackground(new Color(88, 133, 175));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); // Remove scroll pane border

        // Wrap the scroll pane inside another panel with EmptyBorder
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(0, 10, 0, 10)); // Add 10px gap around the table
        tablePanel.setBackground(new Color(88, 133, 175));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        String date = LocalDate.now().format(formatter);

        // add data to the table
        ResultSet expense;
        try {
            expense = ExpenseDB.returnExpenses(SigninUi.Email, date);
            while (expense.next()) {
                addExpense(expense.getString("TIME"), "" + expense.getInt("AMOUNT"), expense.getString("CATEGORY"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error in fatching today's expense.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addExpense(String time, String amount, String category) {
        tableModel.addRow(new Object[] { time, amount, category });
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
}
