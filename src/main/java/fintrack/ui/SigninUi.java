package fintrack.ui;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import fintrack.App;
import fintrack.db.SigninDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SigninUi extends JPanel {
    JTextField usernameTextField;
    JPasswordField passwordField;
    public static String Email;

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
                // Load your background image (replace "path_to_your_image" with the actual
                // path)
                ImageIcon backgroundImage = new ImageIcon(new File("src//main//resource//bg.jpg").getAbsolutePath());
                // Draw the background image
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };

        // Set layout to null to make the main panel cover the whole area
        mainPanel.setLayout(null);

        // Set the size of the mainPanel to be the size of the screen
        mainPanel.setSize(screenSize);

        // Calculate the size and position of the content panel
        int contentWidth = 400; // Adjust as needed
        int contentHeight = 500; // Adjust as needed
        int contentX = (screenSize.width - contentWidth) / 2; // Center horizontally
        int contentY = (screenSize.height - contentHeight) / 3;

        // Create a content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0, 0, 0, 0)); // Semi-transparent white background
        contentPanel.setBounds(contentX, contentY, contentWidth, contentHeight);
        contentPanel.setLayout(null); // Set layout to null to precisely position components

        // Create a label for Username or Email address
        JLabel usernameLabel = new JLabel("Email");
        // Set the position and size of the label
        int labelWidth = 80; // Adjust as needed
        int labelHeight = 30; // Adjust as needed
        int labelX = (contentWidth - labelWidth * 2) / 2;
        int labelY = 200; // Adjust as needed for the vertical position
        usernameLabel.setBounds(labelX, labelY, labelWidth, labelHeight);

        // Add the username label to the content panel
        contentPanel.add(usernameLabel);

        // Create a text field for Username
        usernameTextField = new JTextField();
        // Set the position and size of the text field
        int fieldWidth = 200; // Adjust as needed
        int fieldHeight = 30; // Adjust as needed
        int fieldX = labelX + labelWidth;
        int fieldY = labelY; // Align with the label
        usernameTextField.setBounds(fieldX, fieldY, fieldWidth, fieldHeight);

        // Add the username text field to the content panel
        contentPanel.add(usernameTextField);

        // Create a label for Password
        JLabel passwordLabel = new JLabel("Password");
        // Set the position and size of the label
        passwordLabel.setBounds(labelX, labelY + 40, labelWidth, labelHeight);

        // Add the password label to the content panel
        contentPanel.add(passwordLabel);

        // Create a password field for Password
        passwordField = new JPasswordField();
        // Set the position and size of the password field
        int passwordFieldX = fieldX;
        int passwordFieldY = fieldY + 40; // Below the username field with some spacing
        passwordField.setBounds(passwordFieldX, passwordFieldY, fieldWidth, fieldHeight);

        // Add the password field to the content panel
        contentPanel.add(passwordField);

        // Create a sign-in button
        final JButton signInButton = new JButton("Sign In");
        signInButton.setFocusable(false);
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Set the position and size of the button
        int buttonWidth = 100; // Adjust as needed
        int buttonHeight = 30; // Adjust as needed
        int buttonX = (contentWidth - labelWidth * 2) / 2;
        int buttonY = passwordFieldY + fieldHeight + 20; // Below the password field with some spacing
        signInButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Email = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    new SigninDB(Email, password);
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(SigninUi.this, "You are not registered.", "Invalid Login",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        App.frame.getContentPane().removeAll();
                        App.frame.getContentPane().add(new MainUi());
                        App.frame.getContentPane().revalidate();
                        App.frame.getContentPane().repaint();
                    }
                });
            }
        });

        // Add the sign-in button to the content panel
        contentPanel.add(signInButton);

        // Create a label for "Do not have an account?"
        JLabel signUpLabel = new JLabel("Do not have an account?");
        // Set the position and size of the label
        int signUpLabelWidth = 160; // Adjust as needed
        int signUpLabelHeight = 20; // Adjust as needed
        int signUpLabelX = (contentWidth - labelWidth * 2) / 2;
        int signUpLabelY = buttonY + buttonHeight + 20; // Below the sign-in button with some spacing
        signUpLabel.setBounds(signUpLabelX, signUpLabelY, signUpLabelWidth, signUpLabelHeight);

        // Add the "Do not have an account?" label to the content panel
        contentPanel.add(signUpLabel);

        // Create a sign-up label
        final JLabel signUpButton = new JLabel("Sign Up");
        signUpButton.setForeground(Color.BLUE); // Change color to indicate it's clickable
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovering
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle sign-up action here
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        App.frame.getContentPane().removeAll();
                        App.frame.getContentPane().add(new SignupUi());
                        App.frame.getContentPane().revalidate();
                        App.frame.getContentPane().repaint();
                    }
                });
            }

            // @Override
            // public void mouseEntered(MouseEvent e) {
            // signUpButton.setForeground(Color.RED);
            // }

            // @Override
            // public void mouseExited(MouseEvent e) {
            // signUpButton.setForeground(Color.BLUE);
            // }
        });
        // Set the position and size of the label
        int signUpButtonWidth = 100; // Adjust as needed
        int signUpButtonHeight = 30; // Adjust as needed
        int signUpButtonX = signUpLabelX + signUpLabelWidth; // Place it to the right of the label with some spacing
        int signUpButtonY = signUpLabelY - 5; // Same Y position as the label
        signUpButton.setBounds(signUpButtonX, signUpButtonY, signUpButtonWidth, signUpButtonHeight);

        // Add the sign-up label to the content panel
        contentPanel.add(signUpButton);

        // Add the content panel to the main panel
        mainPanel.add(contentPanel);

        // Add the main panel to the SigninUi panel
        add(mainPanel);
    }
}
