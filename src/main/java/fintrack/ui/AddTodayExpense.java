package fintrack.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import fintrack.db.ExpenseDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddTodayExpense extends JPanel {
    private JTextField amountField, timeField;
    private JComboBox<String> categoryComboBox;
    private JTextArea descriptionArea;
    private JButton addButton;

    public AddTodayExpense() {
        setBackground(Color.white);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(300, 200));
        setLayout(new GridLayout(7, 2, 10, 10)); // 7 rows, 2 columns, with gaps
        setFont(new Font("Calibri", Font.PLAIN, 14));
        JLabel hederLabel = new JLabel("Add Today's Expenses");
        hederLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        add(hederLabel);
        // Today's date label
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        JLabel todayDateLabel = new JLabel(LocalDate.now().format(formatter));
        todayDateLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        add(todayDateLabel);

        // Total and remaining budget labels
        JLabel totalBudgetLabel = new JLabel("Total Budget: $1000"); // Replace with actual total budget
        JLabel remainingBudgetLabel = new JLabel("Remaining Budget: $800"); // Replace with actual remaining budget
        add(totalBudgetLabel);
        add(remainingBudgetLabel);

        // Amount
        JLabel amountLabel = new JLabel("Amount:");
        add(amountLabel);
        amountField = new JTextField(10);
        add(amountField);

        // Time
        JLabel timeLabel = new JLabel("Time:");
        add(timeLabel);
        timeField = new JTextField(10);
        add(timeField);

        // Category
        JLabel categoryLabel = new JLabel("Category:");
        add(categoryLabel);
        String[] categories = { "-- select --", "Healthcare", "Food", "Transportation", "Entertainment", "Clothing",
                "Taxes", "Housing", "Utilities", "Shopping",
                "Others" };
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(categoryComboBox);

        // Description
        JLabel descriptionLabel = new JLabel("Description:");
        add(descriptionLabel);
        descriptionArea = new JTextArea(4, 20); // 4 rows, 20 columns
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // Set preferred size with desired height
        descriptionArea.setPreferredSize(new Dimension(descriptionArea.getPreferredSize().width, 200));

        add(new JScrollPane(descriptionArea));

        // Add Expense button
        add(new JLabel());
        addButton = new JButton("Add Expense");
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check if any field is empty
                    if (amountField.getText().isEmpty() || timeField.getText().isEmpty()
                            || descriptionArea.getText().isEmpty()
                            || categoryComboBox.getSelectedItem().equals("-- select --")) {
                        JOptionPane.showMessageDialog(AddTodayExpense.this, "All fields are mandatory!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // get all data
                    int amount = Integer.parseInt(amountField.getText());
                    String time = timeField.getText();
                    String category = (String) categoryComboBox.getSelectedItem();
                    String description = descriptionArea.getText();
                    // Processing the input
                    new ExpenseDB(SigninUi.Email, LocalDate.now().format(formatter), amount, time, category, description);
                    
                    //refresh the view
                    MainUi.mainUiContent.removeAll();
                    MainUi.mainUiContent.add(new HomeUi(), BorderLayout.CENTER);
                    MainUi.mainUiContent.revalidate();
                    MainUi.mainUiContent.repaint();
                } catch (NumberFormatException ne) {
                    JOptionPane.showMessageDialog(AddTodayExpense.this, "Expense must be a Integer value", "Error",
                            JOptionPane.ERROR_MESSAGE);
                            return;
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(AddTodayExpense.this, "Some Error occure! Try After Some Time.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(AddTodayExpense.this, "The Expense Recorded.", "Successful",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(addButton);
    }
}
