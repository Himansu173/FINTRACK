package fintrack.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import fintrack.App;
import fintrack.db.SignupDB;

public class SignupUi extends JPanel {

    private JTextField fullNameTextField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;
    private JTextField professionTextField;
    private JTextField ageTextField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;

    public SignupUi() {
        // Set layout to null to make the SignupUi panel cover the whole area
        setLayout(null);
        setForeground(Color.WHITE);

        // Get the size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Set the size of the panel to be the size of the screen
        setSize(screenSize);

        // Create a main panel
        final JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(39, 68, 114));
        // {
        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         // Draw the background image
        //         backgroundImage = new ImageIcon(new File("src//main//resource//bg.jpg").getAbsolutePath());
        //         g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        //     }
        // };

        // Set layout to null to make the main panel cover the whole area
        mainPanel.setLayout(null);

        // Set the size of the mainPanel to be the size of the screen
        mainPanel.setSize(screenSize);

        // Calculate the size and position of the content panel
        int contentWidth = 500; // Adjust as needed
        int contentHeight = 540; // Adjust as needed
        int contentX = (screenSize.width - contentWidth) / 2; // Center horizontally
        int contentY = (screenSize.height - contentHeight) / 2; // Center vertically

        // Create a content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(39, 68, 114)); // Semi-transparent white background
        contentPanel.setBounds(contentX, contentY, contentWidth, contentHeight);
        contentPanel.setLayout(null); // Set layout to null to precisely position components

        // Common X position for labels
        int labelX = 20;

        // Common X position for fields
        int fieldX = 200;

        // Common width and height for text fields
        int fieldWidth = 250; // Adjust as needed
        int fieldHeight = 35; // Adjust as needed

        // Create a label for "Full name"
        JLabel fullNameLabel = new JLabel("Full name");
        fullNameLabel.setForeground(Color.WHITE);
        // Set the position and size of the label
        int labelY = 20; // Adjust as needed for the vertical position
        fullNameLabel.setBounds(labelX, labelY, 100, 20);
        contentPanel.add(fullNameLabel);

        // Create a text field for Full name
        fullNameTextField = new JTextField();
        fullNameTextField.setBackground(new Color(195, 224, 229));
        // Set the position and size of the text field
        fullNameTextField.setBounds(fieldX, labelY, fieldWidth, fieldHeight);
        contentPanel.add(fullNameTextField);

        // Create a label for "Email address"
        JLabel emailLabel = new JLabel("Email address");
        emailLabel.setForeground(Color.WHITE);
        // Set the position and size of the label
        emailLabel.setBounds(labelX, labelY + 40, 100, 20);
        contentPanel.add(emailLabel);

        // Create a text field for Email
        emailTextField = new JTextField();
        emailTextField.setBackground(new Color(195, 224, 229));
        // Set the position and size of the text field
        emailTextField.setBounds(fieldX, labelY + 40, fieldWidth, fieldHeight);
        contentPanel.add(emailTextField);

        // Create a label for "Phone number"
        JLabel phoneNumberLabel = new JLabel("Phone number");        
        phoneNumberLabel.setForeground(Color.WHITE);

        // Set the position and size of the label
        phoneNumberLabel.setBounds(labelX, labelY + 80, 100, 20);
        contentPanel.add(phoneNumberLabel);

        // Create a text field for Phone number
        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBackground(new Color(195, 224, 229));
        // Set the position and size of the text field
        phoneNumberTextField.setBounds(fieldX, labelY + 80, fieldWidth, fieldHeight);
        contentPanel.add(phoneNumberTextField);

        // Create a label for "Profession"
        JLabel professionLabel = new JLabel("Profession");
        professionLabel.setForeground(Color.WHITE);
        // Set the position and size of the label
        professionLabel.setBounds(labelX, labelY + 120, 100, 20);
        contentPanel.add(professionLabel);

        // Create a text field for Profession
        professionTextField = new JTextField();
        professionTextField.setBackground(new Color(195, 224, 229));
        // Set the position and size of the text field
        professionTextField.setBounds(fieldX, labelY + 120, fieldWidth, fieldHeight);
        contentPanel.add(professionTextField);

        // Create a label for "Age"
        JLabel ageLabel = new JLabel("Age");
        ageLabel.setForeground(Color.WHITE);
        // Set the position and size of the label
        ageLabel.setBounds(labelX, labelY + 160, 100, 20);
        contentPanel.add(ageLabel);

        // Create a text field for Age
        ageTextField = new JTextField();
        ageTextField.setBackground(new Color(195, 224, 229));
        // Set the position and size of the text field
        ageTextField.setBounds(fieldX, labelY + 160, fieldWidth, fieldHeight);
        contentPanel.add(ageTextField);

        // Create a label for "Gender"
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setForeground(Color.WHITE);
        // Set the position and size of the label
        genderLabel.setBounds(labelX, labelY + 200, 100, 20);
        contentPanel.add(genderLabel);

        // Create radio buttons for gender selection
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maleRadioButton.setBounds(fieldX, labelY + 200, 80, 20);
        maleRadioButton.setBackground(new Color(39, 68, 114));
        maleRadioButton.setForeground(Color.WHITE);
        maleRadioButton.setFocusable(false);
        contentPanel.add(maleRadioButton);
        
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        femaleRadioButton.setFocusable(false);
        // femaleRadioButton.setBackground(new Color(0, 0, 0, 0));
        femaleRadioButton.setBounds(fieldX + 80, labelY + 200, 100, 20);
        femaleRadioButton.setBackground(new Color(39, 68, 114));
        femaleRadioButton.setForeground(Color.WHITE);
        contentPanel.add(femaleRadioButton);

        // Create a button group for radio buttons
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);
        
        // Create a label for "Password"
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        // Set the position and size of the label
        passwordLabel.setBounds(labelX, labelY + 240, 100, 20);
        contentPanel.add(passwordLabel);

        // Create a password field for Password
        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(195, 224, 229));
        // Set the position and size of the password field
        passwordField.setBounds(fieldX, labelY + 240, fieldWidth, fieldHeight);
        contentPanel.add(passwordField);
        
        // Create a label for "Confirm Password"
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setForeground(Color.WHITE);
        // Set the position and size of the label
        confirmPasswordLabel.setBounds(labelX, labelY + 280, 120, 20);
        contentPanel.add(confirmPasswordLabel);
        
        // Create a password field for Confirm Password
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBackground(new Color(195, 224, 229));
        // Set the position and size of the password field
        confirmPasswordField.setBounds(fieldX, labelY + 280, fieldWidth, fieldHeight);
        contentPanel.add(confirmPasswordField);
        
        // Create a sign-up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFocusable(false);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.setBackground(new Color(195, 224, 229));
        // Set the position and size of the button
        signUpButton.setBounds(labelX, labelY + 320, 200, 30);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate all fields
                String fullName = fullNameTextField.getText();
                String email = emailTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                String profession = professionTextField.getText();
                String age = ageTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

                if (fullName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() ||
                profession.isEmpty() || age.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "All fields are required.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(mainPanel, "Passwords do not match.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                    // All fields are filled and passwords match
                    // Validate age input
                    try {
                        int ageValue = Integer.parseInt(age);
                        if (ageValue <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainPanel, "Age must be a positive integer.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // save data in databse
                            try {
                        new SignupDB(email, fullName, Integer.parseInt(phoneNumber), profession, Integer.parseInt(age),
                                maleRadioButton.isSelected() ? "Male" : "Female", password);
                    } catch (Exception ee) {
                        JOptionPane.showMessageDialog(mainPanel, "Some error occurred.", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // Display the message
                    JOptionPane.showMessageDialog(mainPanel, "You are registered, Please login to continue.",
                            "Signup Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            App.frame.getContentPane().removeAll();
                            App.frame.getContentPane().add(new SigninUi());
                            App.frame.getContentPane().revalidate();
                            App.frame.getContentPane().repaint();
                        }
                    });
                }
            }
        });
        contentPanel.add(signUpButton);

        // Create a label for "Have an account?"
        JLabel haveAccountLabel = new JLabel("Have an account?");
        haveAccountLabel.setForeground(Color.WHITE);
        // Set the position and size of the label
        int haveAccountLabelWidth = 150; // Adjust as needed
        int haveAccountLabelHeight = 20; // Adjust as needed
        int haveAccountLabelX = (contentWidth - haveAccountLabelWidth - 50) / 2; // Place it centered with some spacing
                                                                                 // to the right
        int haveAccountLabelY = labelY + 370; // Adjust as needed
        haveAccountLabel.setBounds(haveAccountLabelX, haveAccountLabelY, haveAccountLabelWidth, haveAccountLabelHeight);
        contentPanel.add(haveAccountLabel);

        // Create a clickable label for "Sign In"
        final JLabel signInLabel = new JLabel("Sign In");
        signInLabel.setForeground(Color.WHITE); // Set text color to blue to indicate it's clickable
        signInLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovered
        // Set the position and size of the label
        int signInLabelWidth = 50; // Adjust as needed
        int signInLabelHeight = 20; // Adjust as needed
        int signInLabelX = haveAccountLabelX + haveAccountLabelWidth + 5; // Place it next to "Have an account?"
        int signInLabelY = haveAccountLabelY; // Same Y position as "Have an account?" label
        signInLabel.setBounds(signInLabelX, signInLabelY, signInLabelWidth, signInLabelHeight);
        signInLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        App.frame.getContentPane().removeAll();
                        App.frame.getContentPane().add(new SigninUi());
                        App.frame.getContentPane().revalidate();
                        App.frame.getContentPane().repaint();
                    }
                });
            }
        });

        contentPanel.add(signInLabel);

        // Add the content panel to the main panel
        mainPanel.add(contentPanel);

        // Add the main panel to the SignupUi panel
        add(mainPanel);
    }
}
