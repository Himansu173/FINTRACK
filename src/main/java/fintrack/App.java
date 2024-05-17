package fintrack;

import java.awt.*;
import javax.swing.*;
import fintrack.ui.WelcomeUi;

public class App {
    public static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("FINTRACK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setBackground(Color.MAGENTA);
        frame.setFont(new Font("Calibri", Font.PLAIN, 14));
        // frame.setLocation(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(1000, 700));

        frame.add(new WelcomeUi());
        frame.setVisible(true);
    }
}
