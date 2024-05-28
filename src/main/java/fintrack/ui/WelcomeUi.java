package fintrack.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import fintrack.App;

public class WelcomeUi extends JPanel {

    private JLabel fintrackLabel;
    private ImageIcon logoImage;

    public WelcomeUi() {
        // Load logo image
        logoImage = new ImageIcon(new File("src//main//resource//logo.png").getAbsolutePath());

        // Create and configure the Fintrack label
        fintrackLabel = new JLabel("FINTRACK");
        fintrackLabel.setFont(new Font("Arial", Font.BOLD, 80)); // Adjust font and size as needed
        fintrackLabel.setForeground(new Color(255, 127, 39));
        fintrackLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text horizontally
        fintrackLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 0)); // Add left inset

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Add logo and Fintrack label to the panel
        add(new JLabel(logoImage), BorderLayout.CENTER); // Logo above the Fintrack label
        add(fintrackLabel,BorderLayout.SOUTH); 
        setBackground(new Color(39, 68, 114));

        // Show welcome panel for 3 seconds then switch to sign in panel
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        App.frame.getContentPane().removeAll();
                        App.frame.getContentPane().add(new SigninUi());
                        App.frame.getContentPane().revalidate();
                        App.frame.getContentPane().repaint();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
    }
}
