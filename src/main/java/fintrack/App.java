package fintrack;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import fintrack.db.globalConnection;
import fintrack.ui.WelcomeUi;

public class App {
    public static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("FINTRACK");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setBackground(Color.MAGENTA);
        frame.setFont(new Font("Calibri", Font.PLAIN, 14));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(1000, 700));

        // create a connection object to initilize the Connection
        final globalConnection conn = new globalConnection();

        // add the welcome page the frame to start the application
        frame.add(new WelcomeUi());
        frame.setVisible(true);

        // close the connection when the window is closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                conn.closeConnection();
                frame.dispose();
            }
        });
    }
}
