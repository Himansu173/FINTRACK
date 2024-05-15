package fintrack.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fintrack.App;

public class WelcomeUi extends JPanel {

    private JLabel fintrackLabel;
    private ImageIcon logoImage;

    public WelcomeUi() {
        // Load logo image
        logoImage = new ImageIcon("");

        // Create and configure the Fintrack label
        fintrackLabel = new JLabel("Fintrack");
        fintrackLabel.setFont(new Font("Arial", Font.BOLD, 90)); // Adjust font and size as needed
        fintrackLabel.setForeground(new Color(233, 255, 255)); // Adjust color as needed
        fintrackLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text horizontally
        fintrackLabel.setVerticalAlignment(JLabel.CENTER); // Center the text vertically

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Add logo and Fintrack label to the panel
        add(new JLabel(logoImage), BorderLayout.NORTH); // Logo above the Fintrack label
        add(fintrackLabel, BorderLayout.CENTER); // Fintrack label at the center
        setBackground(Color.CYAN);

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
