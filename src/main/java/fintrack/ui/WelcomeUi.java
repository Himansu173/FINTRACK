package fintrack.ui;

import javax.swing.*;
import java.awt.*;

public class WelcomeUi extends JPanel {

    private JLabel fintrackLabel;
    private ImageIcon logoImage;

    public WelcomeUi() {
        // Load logo image
        logoImage = new ImageIcon("D:\\Java Project\\Image\\pngimg.com - letter_f_PNG80.png");

        // Create and configure the Fintrack label
        fintrackLabel = new JLabel("Fintrack");
        fintrackLabel.setFont(new Font("Arial", Font.BOLD, 90)); // Adjust font and size as needed
        fintrackLabel.setForeground(new Color(233,255,255)); // Adjust color as needed
        fintrackLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text horizontally
        fintrackLabel.setVerticalAlignment(JLabel.CENTER); // Center the text vertically

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Add logo and Fintrack label to the panel
        add(new JLabel(logoImage), BorderLayout.NORTH); // Logo above the Fintrack label
        add(fintrackLabel, BorderLayout.CENTER); // Fintrack label at the center
        setBackground(Color.CYAN);
    }

    // public static void main(String[] args) {
    //     // Create a JFrame to hold the WelcomeUi panel
    //     JFrame frame = new JFrame("Welcome to Fintrack");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setSize(800, 600); // Adjust size as needed
    //     frame.setLocationRelativeTo(null); // Center the frame on the screen

    //     // Create an instance of the WelcomeUi panel
    //     WelcomeUi welcomePanel = new WelcomeUi();

    //     // Add the WelcomeUi panel to the JFrame
    //     frame.add(welcomePanel);

    //     // Make the JFrame visible
    //     frame.setVisible(true);
    // }
}
