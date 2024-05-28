package fintrack;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;

import fintrack.db.GlobalConnection;
import fintrack.ui.WelcomeUi;

public class App {
    public static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("FINTRACK");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setBackground(new Color(39, 68, 114));
        frame.setFont(new Font("Calibri", Font.PLAIN, 14));
        ImageIcon logoIcon = new ImageIcon(new File("src//main//resource//logo.png").getAbsolutePath());
        frame.setIconImage(logoIcon.getImage());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(1000, 700));

        // create a connection object to initilize the Connection
        new GlobalConnection();

        // add the welcome page the frame to start the application
        frame.add(new WelcomeUi());
        frame.setVisible(true);

        // close the connection when the window is closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GlobalConnection.closeConnection();
                frame.dispose();
            }
        });
    }
}
