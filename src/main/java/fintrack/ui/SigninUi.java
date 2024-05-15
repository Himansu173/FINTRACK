package fintrack.ui;

import javax.swing.*;
import java.awt.*;

public class SigninUi extends JPanel {
    public SigninUi() {
     // Set layout to null to make the SigninUi panel cover the whole area
     setLayout(null);

     // Get the size of the screen
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

     // Set the size of the panel to be the size of the screen
     setSize(screenSize);

     // Create a main panel
     JPanel mainPanel = new JPanel() {
         // Override the paintComponent method to paint the background image
         @Override
         protected void paintComponent(Graphics g) {
             super.paintComponent(g);
             // Load your background image (replace "path_to_your_image" with the actual path)
             ImageIcon backgroundImage = new ImageIcon("D:\\Java Project\\Image\\login background.png");
             // Draw the background image
             g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
         }
     };

     // Set layout to null to make the main panel cover the whole area
     mainPanel.setLayout(null);

     // Set the size of the mainPanel to be the size of the screen
     mainPanel.setSize(screenSize);

     // Calculate the size and position of the content panel
     int contentWidth = 600; // Adjust as needed
     int contentHeight = 500; // Adjust as needed
     int contentX = screenSize.width - contentWidth;
     int contentY = (screenSize.height - contentHeight) / 2;

     // Create a content panel
     JPanel contentPanel = new JPanel();
     contentPanel.setBackground(new Color(0, 0, 0, 0)); // Semi-transparent white background
     contentPanel.setBounds(contentX, contentY, contentWidth, contentHeight);
     contentPanel.setLayout(null); // Set layout to null to precisely position components

        // Create a label for the ImageIcon
        JLabel imageLabel = new JLabel();
        // Load your ImageIcon (replace "path_to_your_image" with the actual path)
        ImageIcon icon = new ImageIcon("D:\\Java Project\\Image\\Screenshot_2024-05-12_000549-removebg-preview.png");
        imageLabel.setIcon(icon);
        // Set the position and size of the label
        int labelWidth = icon.getIconWidth();
        int labelHeight = icon.getIconHeight();
        int labelX = (contentWidth - labelWidth) / 2; // Center horizontally
        int labelY = 20; // Adjust as needed for the vertical position
        imageLabel.setBounds(labelX, labelY, labelWidth, labelHeight);

        // Add the image label to the content panel
        contentPanel.add(imageLabel);

        // Create a label for Username or Email address
        JLabel usernameLabel = new JLabel("Username or Email address");
        // Set the position and size of the label
        int usernameLabelWidth = 200; // Adjust as needed
        int usernameLabelHeight = 20; // Adjust as needed
        int usernameLabelX = (contentWidth - usernameLabelWidth) / 2;
        int usernameLabelY = labelY + labelHeight + 20; // Below the image label with some spacing
        usernameLabel.setBounds(usernameLabelX, usernameLabelY, usernameLabelWidth, usernameLabelHeight);

        // Add the username label to the content panel
        contentPanel.add(usernameLabel);

        // Create a text field for Username
        JTextField usernameTextField = new JTextField();
        // Set the position and size of the text field
        int usernameFieldWidth = 200; // Adjust as needed
        int usernameFieldHeight = 20; // Adjust as needed
        int usernameFieldX = (contentWidth - usernameFieldWidth) / 2;
        int usernameFieldY = usernameLabelY + usernameLabelHeight + 10; // Below the username label with some spacing
        usernameTextField.setBounds(usernameFieldX, usernameFieldY, usernameFieldWidth, usernameFieldHeight);

        // Add the username text field to the content panel
        contentPanel.add(usernameTextField);

        // Create a label for Password
        JLabel passwordLabel = new JLabel("Password");
        // Set the position and size of the label
        int passwordLabelWidth = 200; // Adjust as needed
        int passwordLabelHeight = 20; // Adjust as needed
        int passwordLabelX = (contentWidth - passwordLabelWidth) / 2;
        int passwordLabelY = usernameFieldY + usernameFieldHeight + 20; // Below the username field with some spacing
        passwordLabel.setBounds(passwordLabelX, passwordLabelY, passwordLabelWidth, passwordLabelHeight);

        // Add the password label to the content panel
        contentPanel.add(passwordLabel);

        // Create a password field for Password
        JPasswordField passwordField = new JPasswordField();
        // Set the position and size of the password field
        int passwordFieldWidth = 200; // Adjust as needed
        int passwordFieldHeight = 20; // Adjust as needed
        int passwordFieldX = (contentWidth - passwordFieldWidth) / 2;
        int passwordFieldY = passwordLabelY + passwordLabelHeight + 10; // Below the password label with some spacing
        passwordField.setBounds(passwordFieldX, passwordFieldY, passwordFieldWidth, passwordFieldHeight);

        // Add the password field to the content panel
        contentPanel.add(passwordField);

        // Create a sign-in button
        JButton signInButton = new JButton("Sign In");
        // Set the position and size of the button
        int buttonWidth = 100; // Adjust as needed
        int buttonHeight = 30; // Adjust as needed
        int buttonX = (contentWidth - buttonWidth) / 2;
        int buttonY = passwordFieldY + passwordFieldHeight + 20; // Below the password field with some spacing
        signInButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        // Add the sign-in button to the content panel
        contentPanel.add(signInButton);

        // Create a label for "Do not have an account?"
        JLabel signUpLabel = new JLabel("Do not have an account?");
        // Set the position and size of the label
        int signUpLabelWidth = 200; // Adjust as needed
        int signUpLabelHeight = 20; // Adjust as needed
        int signUpLabelX = (contentWidth - signUpLabelWidth - buttonWidth - 5) / 2; // Place it to the left of the button with some spacing
        int signUpLabelY = buttonY + buttonHeight + 20; // Below the sign-in button with some spacing
        signUpLabel.setBounds(signUpLabelX, signUpLabelY, signUpLabelWidth, signUpLabelHeight);

        // Add the "Do not have an account?" label to the content panel
        contentPanel.add(signUpLabel);

        // Create a sign-up button
        JButton signUpButton = new JButton("Sign Up");
        // Set the position and size of the button
        int signUpButtonWidth = 100; // Adjust as needed
        int signUpButtonHeight = 30; // Adjust as needed
        int signUpButtonX = signUpLabelX + signUpLabelWidth + 5; // Place it to the right of the label with some spacing
        int signUpButtonY = signUpLabelY; // Same Y position as the label
        signUpButton.setBounds(signUpButtonX, signUpButtonY, signUpButtonWidth, signUpButtonHeight);

        // Add the sign-up button to the content panel
        contentPanel.add(signUpButton);

        // Add the content panel to the main panel
        mainPanel.add(contentPanel);

        // Add the main panel to the SigninUi panel
        add(mainPanel);
    }

    public static void main(String[] args) {
        // Create a JFrame to test the SigninUi
        JFrame frame = new JFrame("Signin UI Test");
        SigninUi signinUi = new SigninUi();
        frame.add(signinUi);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full screen
        frame.setVisible(true);
    }
}
