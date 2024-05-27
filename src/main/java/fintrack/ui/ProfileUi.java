package fintrack.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.w3c.dom.events.MouseEvent;

import fintrack.db.BudgetDB;
import fintrack.db.ProfileDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProfileUi extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel professionLabel;
    private JLabel genderLabel;
    private JLabel ageLabel;

    public ProfileUi() {
        // Set up the main profile panel
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(88, 133, 175));

        String data[];
        try {
            data = ProfileDB.getDetails(SigninUi.Email);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Profile not found!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the top panel for the user image
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(88, 133, 175));
        topPanel.setLayout(new BorderLayout());

        // Add the user image label at the center
        JLabel userImageLabel = new JLabel();
        userImageLabel.setBorder(new EmptyBorder(40, 0, 40, 0));
        userImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon imageIcon;
        if (data[4].equalsIgnoreCase("Female")) {
            imageIcon = new ImageIcon(new File("src//main//resource//femaleProfile.png").getAbsolutePath());
        } else {
            imageIcon = new ImageIcon(new File("src//main//resource//maleProfile.png").getAbsolutePath());
        }
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        userImageLabel.setIcon(new ImageIcon(image));
        topPanel.add(userImageLabel, BorderLayout.CENTER);

        // Create a panel for the rating label
        JPanel ratingPanel = new JPanel(new BorderLayout());
        ratingPanel.setBackground(new Color(88, 133, 175));
        ratingPanel.setPreferredSize(new Dimension(400, 30));
        JLabel ratingLabel = new JLabel("Rating: 4.5");
        ratingLabel.setForeground(Color.WHITE);
        ratingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ratingPanel.add(ratingLabel, BorderLayout.EAST);

        // Add the rating panel to the top panel
        topPanel.add(ratingPanel, BorderLayout.NORTH);

        // Create the second panel for user profile data
        final JPanel dataPanel = new JPanel();
        dataPanel.setBackground(new Color(88, 133, 175));
        dataPanel.setLayout(new GridLayout(3, 2, 5, 5)); // Two columns with 6 rows
        dataPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Add profile data fields in titled panels
        nameLabel = addProfileField(dataPanel, "Name", data[0]);
        emailLabel = addProfileField(dataPanel, "Email", SigninUi.Email);
        phoneLabel = addProfileField(dataPanel, "Phone", data[1]);
        professionLabel = addProfileField(dataPanel, "Profession", data[2]);
        ageLabel = addProfileField(dataPanel, "Age", data[3]);
        genderLabel = addProfileField(dataPanel, "Gender", data[4]);

        // Create buttons
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(new Color(195, 224, 229));
        changePasswordButton.setFocusable(false);
        changePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.setBackground(new Color(195, 224, 229));
        editProfileButton.setFocusable(false);
        editProfileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton setBudgetButton = new JButton("Set Budget");
        setBudgetButton.setBackground(new Color(195, 224, 229));
        setBudgetButton.setFocusable(false);
        setBudgetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add action listener to Change Password button
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a panel to hold the password fields
                JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
                JPasswordField oldPasswordField = new JPasswordField();
                JPasswordField newPasswordField = new JPasswordField();
                JPasswordField reEnterPasswordField = new JPasswordField();
                panel.add(new JLabel("Old Password:"));
                panel.add(oldPasswordField);
                panel.add(new JLabel("New Password:"));
                panel.add(newPasswordField);
                panel.add(new JLabel("Re-enter Password:"));
                panel.add(reEnterPasswordField);

                // Show option dialog
                int result = JOptionPane.showOptionDialog(dataPanel, panel, "Change Password",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, new String[] { "Change", "Close" }, "Change");

                // Process result
                if (result == JOptionPane.OK_OPTION) {
                    String oldPassword = new String(oldPasswordField.getPassword());
                    String newPassword = new String(newPasswordField.getPassword());
                    String reEnterPassword = new String(reEnterPasswordField.getPassword());

                    // Check if passwords match
                    if (!newPassword.equals(reEnterPassword)) {
                        JOptionPane.showMessageDialog(dataPanel, "New password and re-entered password do not match.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            new ProfileDB().changePassword(SigninUi.Email, oldPassword, newPassword);
                        } catch (Exception ee) {
                            System.out.println(ee);
                            JOptionPane.showMessageDialog(dataPanel,
                                    "Invalid Password",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        JOptionPane.showMessageDialog(dataPanel,
                                "Password Changed.",
                                "Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        // Add action listener to Edit Profile button
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a panel to hold the profile details
                JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
                JTextField nameField = new JTextField(
                        nameLabel.getText().substring(nameLabel.getText().indexOf(":") + 1).trim());
                JTextField phoneField = new JTextField(
                        phoneLabel.getText().substring(phoneLabel.getText().indexOf(":") + 1).trim());
                JTextField professionField = new JTextField(
                        professionLabel.getText().substring(professionLabel.getText().indexOf(":") + 1).trim());
                JTextField ageField = new JTextField(
                        ageLabel.getText().substring(ageLabel.getText().indexOf(":") + 1).trim());
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Phone:"));
                panel.add(phoneField);
                panel.add(new JLabel("Profession:"));
                panel.add(professionField);
                panel.add(new JLabel("Age:"));
                panel.add(ageField);

                // Show option dialog
                int result = JOptionPane.showOptionDialog(dataPanel, panel, "Edit Profile",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, new String[] { "Save", "Close" }, "Save");

                // Process result
                if (result == JOptionPane.OK_OPTION) {
                    // Update profile details
                    try {
                        new ProfileDB().editProfile(SigninUi.Email, nameField.getText(),
                                Integer.parseInt(phoneField.getText()), professionField.getText(),
                                Integer.parseInt(ageField.getText()));
                    } catch (Exception ee) {
                        System.out.println(ee);
                        JOptionPane.showMessageDialog(dataPanel,
                                "Unable to Save Changes.",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    MainUi.mainUiContent.removeAll();
                    MainUi.mainUiContent.add(new ProfileUi(), BorderLayout.CENTER);
                    MainUi.mainUiContent.revalidate();
                    MainUi.mainUiContent.repaint();
                    MainUi.nameLabel.setText("Hello, " + nameField.getText());
                }
            }
        });

        // Add action listener to Set Budget button
        setBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a panel to hold the budget field
                JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
                JTextField budgetField = new JTextField();
                panel.add(new JLabel("Set Budget For The Month:"));
                panel.add(budgetField);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
                String formattedDate = LocalDate.now().format(formatter);

                // Show option dialog
                int result = JOptionPane.showOptionDialog(dataPanel, panel, "Set Budget", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, new String[] { "Save", "Close" }, null);

                // Process result
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        int budget = Integer.parseInt(budgetField.getText());
                        JOptionPane.showMessageDialog(dataPanel, "Monthly Budget: " + budget, "Budget Set",
                                JOptionPane.INFORMATION_MESSAGE);
                        new BudgetDB().setBudget(SigninUi.Email, formattedDate, budget);
                    } catch (NumberFormatException ne) {
                        JOptionPane.showMessageDialog(dataPanel, "Enter Budget correctly", "Invalid Budget",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(dataPanel, "Unable to save the budget.", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Create a panel for the buttons and add them to it
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(88, 133, 175));
        buttonPanel.setBorder(new EmptyBorder(35, 30, 35, 30));
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(editProfileButton);
        buttonPanel.add(setBudgetButton);

        // Add panels to the main profile panel
        add(topPanel, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to add a profile field with label and data to the data panel
    private JLabel addProfileField(JPanel panel, String label, String data) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setBackground(new Color(88, 133, 175));
        TitledBorder titledBorder = BorderFactory.createTitledBorder(label);
        titledBorder.setTitleColor(Color.WHITE);
        fieldPanel.setBorder(titledBorder);
        JLabel fieldLabel = new JLabel(data);
        fieldLabel.setForeground(Color.WHITE);
        fieldPanel.add(fieldLabel);
        panel.add(fieldPanel);
        return fieldLabel;
    }
}
