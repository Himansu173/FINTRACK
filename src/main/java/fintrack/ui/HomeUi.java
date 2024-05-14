package fintrack.ui;

import javax.swing.*;
import java.awt.*;

public class HomeUi {
    public static JPanel HomeUiPanel() {
        JPanel homePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        homePanel.setBackground(Color.GRAY);
        homePanel.add(new AddTodayExpense());
        homePanel.add(new ExpenseTrend());
        homePanel.add(new DisplayTodayExpense());
        homePanel.add(new AddFutureExpense());
        return homePanel;
    }
}
