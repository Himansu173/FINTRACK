package fintrack;

import javax.swing.*;
import fintrack.ui.HomeUi;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FINTRACK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setBackground(Color.MAGENTA);
        frame.setFont(new Font("Calibri", Font.BOLD, 14));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);

        frame.add(HomeUi.HomeUiPanel());
        frame.setVisible(true);
    }
}
