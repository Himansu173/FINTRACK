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
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        String[] categories = { "-- select --", "Food", "Transportation", "Entertainment", "Utilities", "Shopping",
                "Others" };
        categoryComboBox = new JComboBox<>(categories);
        addExpensePanel.add(categoryComboBox);

        addExpensePanel.add(new JLabel(""));

        // Create a DefaultListModel instance for future expenses
        futureExpensesListModel = new DefaultListModel<>();

        // Add a button to submit the future expense
        final JButton submitButton = new JButton("Submit");
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
                System.out.println("Amount: " + amount + ", Category: " + category + ", Future Date: " +
                        futureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        });

        addExpensePanel.add(submitButton);
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        categoryComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                categoryComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                categoryComboBox.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
        addExpense("18-11-2025", "200", "Shopping");

        // Add futureExpensesPanel to the bottom section with a border
        add(futureExpensesPanel, BorderLayout.CENTER);
    }

    private void addExpense(String date, String amount, String category) {
        tableModel.addRow(new Object[] { date, "$" + amount, category });
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
