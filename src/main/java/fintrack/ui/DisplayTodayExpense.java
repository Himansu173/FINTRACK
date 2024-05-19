package fintrack.ui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DisplayTodayExpense extends JPanel {
    private JTable expensesTable;
    private DefaultTableModel tableModel;

    public DisplayTodayExpense() {
        setLayout(new BorderLayout());

        // Create header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        JLabel headerLabel = new JLabel("Today's Expenses");
        headerLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Create table model with columns for time, amount, and category
        tableModel = new DefaultTableModel(new Object[] { "Time", "Amount", "Category" }, 0);

        // Create JTable with the model
        expensesTable = new JTable(tableModel);
        expensesTable.setDefaultRenderer(Object.class, new CenterRenderer());
        expensesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        expensesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        expensesTable.setRowHeight(30);
        expensesTable.setShowGrid(false); // Hide grid lines
        expensesTable.setIntercellSpacing(new Dimension(0, 0)); // Remove cell spacing
        expensesTable.setBorder(null); // Remove table border
        expensesTable.getTableHeader().setBorder(null); // Remove header border
        JScrollPane scrollPane = new JScrollPane(expensesTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null); // Remove scroll pane border

        // Wrap the scroll pane inside another panel with EmptyBorder
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(0, 10, 0, 10)); // Add 10px gap around the table
        tablePanel.setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);

        // Example: Add some dummy expense entries for today
        addExpense("12:00 PM", "100", "Food");
        addExpense("02:30 PM", "50", "Transportation");
        addExpense("06:00 PM", "200", "Shopping");
        addExpense("12:00 PM", "100", "Food");
        addExpense("02:30 PM", "50", "Transportation");
        addExpense("06:00 PM", "200", "Shopping");
        addExpense("12:00 PM", "100", "Food");
        addExpense("02:30 PM", "50", "Transportation");
        addExpense("06:00 PM", "200", "Shopping");
        addExpense("06:00 PM", "200", "Shopping");
        addExpense("12:00 PM", "100", "Food");
        addExpense("02:30 PM", "50", "Transportation");
        addExpense("06:00 PM", "200", "Shopping");
        // Add more dummy entries...
    }

    private void addExpense(String time, String amount, String category) {
        tableModel.addRow(new Object[] { time, amount, category });
    }

    // Custom TableCellRenderer to center align cell contents
    private class CenterRenderer extends DefaultTableCellRenderer {
        private Color color1 = Color.WHITE;
        private Color color2 = new Color(240, 240, 240); // Light gray

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
