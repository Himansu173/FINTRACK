package fintrack.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ProfileUi extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel occupationLabel;
    private JLabel genderLabel;
    private JLabel ageLabel;

    public ProfileUi() {
        // Set up the main profile panel
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);
        // Create the top panel for the user image
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new BorderLayout());
        // topPanel.setPreferredSize(new Dimension(400, 250));

        // Add the user image label at the center
        JLabel userImageLabel = new JLabel();
        userImageLabel.setBorder(new EmptyBorder(40, 0, 40, 0));
        userImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon imageIcon = new ImageIcon(new File("").getAbsolutePath() + "\\src\\main\\resource\\maleProfile.png");
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        userImageLabel.setIcon(new ImageIcon(image));
        topPanel.add(userImageLabel, BorderLayout.CENTER);

        // Create a panel for the rating label
        JPanel ratingPanel = new JPanel(new BorderLayout());
        ratingPanel.setBackground(Color.WHITE);

        ratingPanel.setPreferredSize(new Dimension(400, 30));
        JLabel ratingLabel = new JLabel("Rating: 4.5");
        ratingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ratingPanel.add(ratingLabel, BorderLayout.EAST);

        // Add the rating panel to the top panel
        topPanel.add(ratingPanel, BorderLayout.NORTH);

        // Create the second panel for user profile data
        JPanel dataPanel = new JPanel();
        dataPanel.setBackground(Color.WHITE);
        dataPanel.setLayout(new GridLayout(3, 2, 5, 5)); // Two columns with 6 rows
        dataPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Add profile data fields in titled panels
        nameLabel = addProfileField(dataPanel, "Name", "John Doe");
        emailLabel = addProfileField(dataPanel, "Email", "john.doe@example.com");
        phoneLabel = addProfileField(dataPanel, "Phone", "+1234567890");
        occupationLabel = addProfileField(dataPanel, "Occupation", "Teacher");
        genderLabel = addProfileField(dataPanel, "Gender", "Male");
        ageLabel = addProfileField(dataPanel, "Age", "30");

        // Create buttons
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
                int result = JOptionPane.showOptionDialog(null, panel, "Change Password", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, new String[] { "Change", "Close" }, "Change");

                // Process result
                if (result == JOptionPane.OK_OPTION) {
                    String oldPassword = new String(oldPasswordField.getPassword());
                    String newPassword = new String(newPasswordField.getPassword());
                    String reEnterPassword = new String(reEnterPasswordField.getPassword());

                    // Check if passwords match
                    if (!newPassword.equals(reEnterPassword)) {
                        JOptionPane.showMessageDialog(null, "New password and re-entered password do not match.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Perform password change logic
                        // For demo purpose, just displaying the entered passwords
                        JOptionPane.showMessageDialog(null,
                                "Old Password: " + oldPassword + "\nNew Password: " + newPassword, "Password Changed",
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
                JTextField occupationField = new JTextField(
                        occupationLabel.getText().substring(occupationLabel.getText().indexOf(":") + 1).trim());
                JTextField ageField = new JTextField(
                        ageLabel.getText().substring(ageLabel.getText().indexOf(":") + 1).trim());
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Phone:"));
                panel.add(phoneField);
                panel.add(new JLabel("Occupation:"));
                panel.add(occupationField);
                panel.add(new JLabel("Age:"));
                panel.add(ageField);

                // Show option dialog
                int result = JOptionPane.showOptionDialog(null, panel, "Edit Profile", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, new String[] { "Save", "Close" }, "Save");

                // Process result
                if (result == JOptionPane.OK_OPTION) {
                    // Update profile details
                    nameLabel.setText(nameField.getText());
                    phoneLabel.setText(phoneField.getText());
                    occupationLabel.setText(occupationField.getText());
                    ageLabel.setText(ageField.getText());
                }
            }
        });

        // Create a panel for the buttons and add them to it
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(35, 30, 35, 30));
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(editProfileButton);

        // Add panels to the main profile panel
        add(topPanel, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to add a profile field with label and data to the data panel
    private JLabel addProfileField(JPanel panel, String label, String data) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setBorder(BorderFactory.createTitledBorder(label));
        fieldPanel.setBackground(Color.WHITE);
        JLabel fieldLabel = new JLabel(data);
        fieldPanel.add(fieldLabel);
        panel.add(fieldPanel);
        return fieldLabel;
    }
}
