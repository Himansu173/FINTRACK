package fintrack.ui;

import javax.swing.*;
import java.awt.*;

public class HomeUi extends JPanel {
    public HomeUi() {
        setLayout(new GridLayout(2, 2, 10, 10));
        setBackground(new Color(65, 114, 159));
        add(new AddTodayExpense());
        add(new ExpenseTrend());
        add(new DisplayTodayExpense());
        add(new FutureExpense());
    }
}
