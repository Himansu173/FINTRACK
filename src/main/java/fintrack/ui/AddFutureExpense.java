package fintrack.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.toedter.calendar.JDateChooser;

public class AddFutureExpense extends JPanel {
    private JTextField amountField;
    private JComboBox<String> categoryComboBox;
    private JDateChooser dateChooser;
    private DefaultListModel<String> futureExpensesListModel;
    private JTable expensesTable;
    private DefaultTableModel tableModel;
    private Calendar calendar;

    public AddFutureExpense() {
        setLayout(new BorderLayout());

        // Create panel for adding future expenses
        JPanel addExpensePanel = new JPanel(new GridLayout(5, 2, 7, 7)); // 5 rows, 2 columns, with gaps
        addExpensePanel.setBackground(Color.WHITE);
        addExpensePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Add label for message
        JLabel messageLabel = new JLabel("Add Future Expense");
        messageLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        addExpensePanel.add(messageLabel);
        addExpensePanel.add(new JLabel()); // placeholder for the second column

        // Add components to the addExpensePanel
        addExpensePanel.add(new JLabel("Future Date:"));
        dateChooser = new JDateChooser();
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Add 1 day to the current date
        dateChooser.setMinSelectableDate(calendar.getTime()); // Set minimum selectable date to tomorrow's date
        addExpensePanel.add(dateChooser);

        addExpensePanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        addExpensePanel.add(amountField);

        addExpensePanel.add(new JLabel("Category:"));
        String[] categories = { "-- select --", "Healthcare", "Food", "Transportation", "Entertainment", "Clothing", "Taxes", "Housing", "Utilities", "Shopping",
                "Others" };
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addExpensePanel.add(categoryComboBox);

        addExpensePanel.add(new JLabel(""));

        // Create a DefaultListModel instance for future expenses
        futureExpensesListModel = new DefaultListModel<>();

        // Add a button to submit the future expense
        final JButton submitButton = new JButton("Submit");
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addExpensePanel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered values
                String amount = amountField.getText();
                String category = (String) categoryComboBox.getSelectedItem();
                LocalDate futureDate = null;

                // Check if any field is empty or if no date is selected
                if (amount.isEmpty() || category.equals("-- select --") || dateChooser.getDate() == null) {
                    JOptionPane.showMessageDialog(AddFutureExpense.this, "Please fill in all fields.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if any field is empty or if no date is selected
                }

                // Convert the selected date to LocalDate
                try {
                    java.util.Date selectedDate = dateChooser.getDate();
                    if (selectedDate != null) {
                        futureDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    } else {
                        JOptionPane.showMessageDialog(AddFutureExpense.this, "Please select a future date.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddFutureExpense.this, "Error converting date.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Add the future expense to the list
                addExpense(futureDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), amount, category);
                JOptionPane.showMessageDialog(AddFutureExpense.this, "The Future expense added.", "Successful",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add addExpensePanel to the top section with a border
        add(addExpensePanel, BorderLayout.NORTH);

        // Create an empty border to add space between panels
        Border emptyBorder = BorderFactory.createEmptyBorder(15, 0, 0, 0);

        // Create panel for displaying future expense data with an empty border
        JPanel futureExpensesPanel = new JPanel(new BorderLayout());
        futureExpensesPanel.setBorder(emptyBorder);
        JLabel futureExpensesLabel = new JLabel("Future Expenses");
        futureExpensesLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        futureExpensesPanel.add(futureExpensesLabel, BorderLayout.NORTH);
        futureExpensesPanel.setBackground(Color.WHITE);
        futureExpensesPanel.setBorder(new EmptyBorder(0, 10, 0, 10));

        tableModel = new DefaultTableModel(new Object[] { "Date", "Amount", "Category" }, 0);

        // Create JTable with the model
        expensesTable = new JTable(tableModel);
        expensesTable.setDefaultRenderer(Object.class, new CenterRenderer());
        expensesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        expensesTable.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        expensesTable.setRowHeight(30);
        expensesTable.setShowGrid(false); // Hide grid lines
        expensesTable.setIntercellSpacing(new Dimension(0, 0)); // Remove cell spacing
        expensesTable.setBorder(null); // Remove table border
        expensesTable.getTableHeader().setBorder(null); // Remove header border
        JScrollPane scrollPane = new JScrollPane(expensesTable);
        scrollPane.setBorder(null); // Remove scroll pane border
        futureExpensesPanel.add(scrollPane, BorderLayout.CENTER);

        addExpense("18-11-2025", "100", "Food");
        addExpense("18-11-2025", "50", "Transportation");
        addExpense("18-11-2025", "200", "Shopping");
        addExpense("18-11-2025", "100", "Food");
        addExpense("18-11-2025", "50", "Transportation");
        addExpense("18-11-2025", "200", "Shopping");
        addExpense("18-11-2025", "100", "Food");
        addExpense("18-11-2025", "50", "Transportation");
        addExpense("18-11-2025", "200", "Shopping");
        addExpense("18-11-2025", "100", "Food");
        addExpense("18-11-2025", "50", "Transportation");
        addExpense("18-05-2024", "200", "Shopping");

        // Add futureExpensesPanel to the bottom section with a border
        add(futureExpensesPanel, BorderLayout.CENTER);

        // Create a Timer to call checkFutureExpenses() after 2 seconds
        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFutureExpenses();
            }
        });
        timer.setRepeats(false); // Set to execute only once
        timer.start();
    }

    private void addExpense(String date, String amount, String category) {
        tableModel.addRow(new Object[] { date, "$" + amount, category });
    }

    private void checkFutureExpenses() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Object[]> matchingExpenses = new ArrayList<>();

        // Iterate through the table rows to find matching dates
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String dateStr = (String) tableModel.getValueAt(i, 0);
            LocalDate expenseDate = LocalDate.parse(dateStr, formatter);

            if (expenseDate.equals(today)) {
                // Collect matching expense details
                Object[] row = new Object[tableModel.getColumnCount()];
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    row[j] = tableModel.getValueAt(i, j);
                }
                matchingExpenses.add(row);
            }
        }

        // If there are matching expenses, show a dialog box
        if (!matchingExpenses.isEmpty()) {
            for (Object[] expense : matchingExpenses) {
                int result = JOptionPane.showConfirmDialog(
                        this,
                        "Expense details:\nDate: " + expense[0] + "\nAmount: " + expense[1] + "\nCategory: "
                                + expense[2] +
                                "\nDo you want to confirm this expense?",
                        "Confirm Expense",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
                    JTextField timeField = new JTextField();
                    panel.add(new JLabel("Enter Expense Time: "));
                    panel.add(timeField);

                    // Show option dialog
                    int val = JOptionPane.showOptionDialog(this, panel, "Expense Time",
                            JOptionPane.OK_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, new String[] { "Save" }, null);
                    if (val != JOptionPane.OK_OPTION)
                        return;
                    System.out.println("Expense confirmed: " + expense[1]);
                } else {
                    System.out.println("Expense cancelled: " + expense[1]);
                }
            }
        }
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
