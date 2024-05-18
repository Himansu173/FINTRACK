
package fintrack.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import fintrack.App;

public class SignupUi extends JPanel {

    private JTextField fullNameTextField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;
    private JTextField professionTextField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ImageIcon backgroundImage; // Image to be added to the background

    public SignupUi() {
        // Load the background image

        

        // Set layout to null to make the SignupUi panel cover the whole area
        setLayout(null);

        // Get the size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Set the size of the panel to be the size of the screen
        setSize(screenSize);

        // Create a main panel
        final JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                backgroundImage = new ImageIcon( "FINTRACK//src//main//resource//bg.jpg");

                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set layout to null to make the main panel cover the whole area
        mainPanel.setLayout(null);

        // Set the size of the mainPanel to be the size of the screen
        mainPanel.setSize(screenSize);

        // Calculate the size and position of the content panel
        int contentWidth = 500; // Adjust as needed
        int contentHeight = 500; // Adjust as needed
        int contentX = (screenSize.width - contentWidth) / 2; // Center horizontally
        int contentY = (screenSize.height - contentHeight) / 2; // Center vertically

        // Create a content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0, 0, 0, 0)); // Semi-transparent white background
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
        // Set the position and size of the label
        int labelY = 20; // Adjust as needed for the vertical position
        fullNameLabel.setBounds(labelX, labelY, 100, 20);
        contentPanel.add(fullNameLabel);

        // Create a text field for Full name
        fullNameTextField = new JTextField();
        // Set the position and size of the text field
        fullNameTextField.setBounds(fieldX, labelY, fieldWidth, fieldHeight);
        contentPanel.add(fullNameTextField);

        // Create a label for "Email address"
        JLabel emailLabel = new JLabel("Email address");
        // Set the position and size of the label
        emailLabel.setBounds(labelX, labelY + 40, 100, 20);
        contentPanel.add(emailLabel);

        // Create a text field for Email
        emailTextField = new JTextField();
        // Set the position and size of the text field
        emailTextField.setBounds(fieldX, labelY + 40, fieldWidth, fieldHeight);
        contentPanel.add(emailTextField);

        // Create a label for "Phone number"
        JLabel phoneNumberLabel = new JLabel("Phone number");
        // Set the position and size of the label
        phoneNumberLabel.setBounds(labelX, labelY + 80, 100, 20);
        contentPanel.add(phoneNumberLabel);

        // Create a text field for Phone number
        phoneNumberTextField = new JTextField();
        // Set the position and size of the text field
        phoneNumberTextField.setBounds(fieldX, labelY + 80, fieldWidth, fieldHeight);
        contentPanel.add(phoneNumberTextField);

        // Create a label for "Profession"
        JLabel professionLabel = new JLabel("Profession");
        // Set the position and size of the label
        professionLabel.setBounds(labelX, labelY + 120, 100, 20);
        contentPanel.add(professionLabel);

        // Create a text field for Profession
        professionTextField = new JTextField();
        // Set the position and size of the text field
        professionTextField.setBounds(fieldX, labelY + 120, fieldWidth, fieldHeight);
        contentPanel.add(professionTextField);

        // Create a label for "Gender"
        JLabel genderLabel = new JLabel("Gender");
        // Set the position and size of the label
        genderLabel.setBounds(labelX, labelY + 160, 100, 20);
        contentPanel.add(genderLabel);

        // Create radio buttons for gender selection
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maleRadioButton.setBounds(fieldX, labelY + 160, 80, 20);
        maleRadioButton.setBackground(new Color(0, 0, 0, 0));
        contentPanel.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        femaleRadioButton.setBackground(new Color(0, 0, 0, 0));
        femaleRadioButton.setBounds(fieldX + 80, labelY + 160, 100, 20);
        contentPanel.add(femaleRadioButton);

        // Create a button group for radio buttons
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);

        // Create a label for "Password"
        JLabel passwordLabel = new JLabel("Password");
        // Set the position and size of the label
        passwordLabel.setBounds(labelX, labelY + 200, 100, 20);
        contentPanel.add(passwordLabel);

        // Create a password field for Password
        passwordField = new JPasswordField();
        // Set the position and size of the password field
        passwordField.setBounds(fieldX, labelY + 200, fieldWidth, fieldHeight);
        contentPanel.add(passwordField);

        // Create a label for "Confirm Password"
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        // Set the position and size of the label
        confirmPasswordLabel.setBounds(labelX, labelY + 240, 120, 20);
        contentPanel.add(confirmPasswordLabel);

        // Create a password field for Confirm Password
        confirmPasswordField = new JPasswordField();
        // Set the position and size of the password field
        confirmPasswordField.setBounds(fieldX, labelY + 240, fieldWidth, fieldHeight);
        contentPanel.add(confirmPasswordField);

        // Create a sign-up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Set the position and size of the button
        signUpButton.setBounds(labelX, labelY + 280, 200, 30);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate all fields
                String fullName = fullNameTextField.getText();
                String email = emailTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                String profession = professionTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

                if (fullName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() ||
                        profession.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "All fields are required.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(mainPanel, "Passwords do not match.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // All fields are filled and passwords match
                    StringBuilder message = new StringBuilder();
                    message.append("Full Name: ").append(fullName).append("\n");
                    message.append("Email: ").append(email).append("\n");
                    message.append("Phone Number: ").append(phoneNumber).append("\n");
                    message.append("Profession: ").append(profession).append("\n");
                    message.append("Gender: ").append(maleRadioButton.isSelected() ? "Male" : "Female").append("\n");
                    // Display the message
                    JOptionPane.showMessageDialog(mainPanel, message.toString(), "Signup Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        contentPanel.add(signUpButton);

        // Create a label for "Have an account?"
        JLabel haveAccountLabel = new JLabel("Have an account?");
        // Set the position and size of the label
        int haveAccountLabelWidth = 150; // Adjust as needed
        int haveAccountLabelHeight = 20; // Adjust as needed
        int haveAccountLabelX = (contentWidth - haveAccountLabelWidth - 50) / 2; // Place it centered with some spacing
                                                                                 // to the right
        int haveAccountLabelY = labelY + 330; // Adjust as needed
        haveAccountLabel.setBounds(haveAccountLabelX, haveAccountLabelY, haveAccountLabelWidth, haveAccountLabelHeight);
        contentPanel.add(haveAccountLabel);

        // Create a clickable label for "Sign In"
        final JLabel signInLabel = new JLabel("Sign In");
        signInLabel.setForeground(Color.BLUE); // Set text color to blue to indicate it's clickable
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

            // @Override
            // public void mouseEntered(MouseEvent e) {
            // signInLabel.setForeground(Color.RED);
            // }

            // @Override
            // public void mouseExited(MouseEvent e) {
            // signInLabel.setForeground(Color.BLUE);
            // }
        });

        contentPanel.add(signInLabel);

        // Add the content panel to the main panel
        mainPanel.add(contentPanel);

        // Add the main panel to the SignupUi panel
        add(mainPanel);
    }
}
