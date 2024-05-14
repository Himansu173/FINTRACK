package fintrack.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainUi extends JPanel {

    private static final int ICON_WIDTH = 50; // Desired width of the icons
    private static final int ICON_HEIGHT = 50; // Desired height of the icons

    public MainUi() {
        // Set layout for the dashboard panel
        setLayout(new BorderLayout());

        // Create side bar menu panel
        JPanel sideBarMenu = new JPanel();
        sideBarMenu.setLayout(new BoxLayout(sideBarMenu, BoxLayout.Y_AXIS));
        sideBarMenu.setBackground(Color.LIGHT_GRAY);

        // Increase width of side bar menu
        sideBarMenu.setPreferredSize(new Dimension(80, getHeight()));

        // Load the image for the logo
        ImageIcon logoIcon = resizeImageIcon(
                "D:\\Java Project\\Image\\Screenshot_2024-05-12_000549-removebg-preview.png", ICON_WIDTH, ICON_HEIGHT);

        // Create menu labels with icons
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the logo horizontally
        JLabel homeLabel = new JLabel(resizeImageIcon("D:\\Java Project\\Image\\home.png", ICON_WIDTH, ICON_HEIGHT));
        JLabel budgetLabel = new JLabel(
                resizeImageIcon("D:\\Java Project\\Image\\budget.png", ICON_WIDTH, ICON_HEIGHT));
        JLabel historyLabel = new JLabel(
                resizeImageIcon("D:\\Java Project\\Image\\history.png", ICON_WIDTH, ICON_HEIGHT));
        JLabel profileLabel = new JLabel(
                resizeImageIcon("D:\\Java Project\\Image\\profile-user.png", ICON_WIDTH, ICON_HEIGHT));

        // Add gaps between labels
        sideBarMenu.add(Box.createRigidArea(new Dimension(0, 0))); // Add initial gap
        sideBarMenu.add(logoLabel);
        sideBarMenu.add(Box.createRigidArea(new Dimension(0, 20))); // Add gap between logo and home
        sideBarMenu.add(homeLabel);
        sideBarMenu.add(Box.createRigidArea(new Dimension(0, 20))); // Add gap between home and budget
        sideBarMenu.add(budgetLabel);
        sideBarMenu.add(Box.createRigidArea(new Dimension(0, 20))); // Add gap between budget and history
        sideBarMenu.add(historyLabel);
        sideBarMenu.add(Box.createRigidArea(new Dimension(0, 20))); // Add gap between history and profile
        sideBarMenu.add(profileLabel);
        sideBarMenu.add(Box.createVerticalGlue()); // Add vertical glue to push labels to top

        // Create panel for profile image label
        JPanel profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBackground(Color.LIGHT_GRAY);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        // Create constraints for logoLabel
        GridBagConstraints logoConstraints = new GridBagConstraints();
        logoConstraints.gridx = 0;
        logoConstraints.gridy = 0;
        logoConstraints.anchor = GridBagConstraints.NORTHWEST; // Align to top-left corner

        // Create constraints for profileImageLabel
        GridBagConstraints profileImageConstraints = new GridBagConstraints();
        profileImageConstraints.gridx = 1;
        profileImageConstraints.gridy = 0;
        profileImageConstraints.anchor = GridBagConstraints.NORTHEAST; // Align to top-right corner

        // Create profile image label
        JLabel profileImageLabel = new JLabel(
                resizeImageIcon("D:\\Java Project\\Image\\profile-user.png", ICON_WIDTH, ICON_HEIGHT));

        // Add logoLabel and profileImageLabel to profilePanel
        profilePanel.add(logoLabel, logoConstraints);
        profilePanel.add(profileImageLabel, profileImageConstraints);

        // Create label for person's name
        JLabel nameLabel = new JLabel("Hello, John");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0)); // Add left inset
        Font font = nameLabel.getFont();
        nameLabel.setFont(new Font(font.getName(), Font.BOLD, 20));

        // Create panel to hold nameLabel and profilePanel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.add(nameLabel, BorderLayout.WEST);
        topPanel.add(profilePanel, BorderLayout.EAST);

        // Create dashboard content panel
        JPanel dashboardContent = new JPanel(new BorderLayout());
        dashboardContent.setBackground(Color.gray);
        dashboardContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray), // Set a red border for visibility
                BorderFactory.createEmptyBorder(15, 15, 5, 10) // Add gap at left and top
        ));

        // Add some content to the dashboard content panel
        // JLabel contentLabel = new JLabel("Welcome to your dashboard!");
        // contentLabel.setFont(new Font(font.getName(), Font.PLAIN, 18));
        dashboardContent.add(HomeUi.HomeUiPanel(), BorderLayout.CENTER);

        // Add side bar menu panel and topPanel to the dashboard panel
        add(sideBarMenu, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);

        // Add the dashboard content panel to the center of the dashboard panel
        add(dashboardContent, BorderLayout.CENTER);
    }

    private ImageIcon resizeImageIcon(String imagePath, int width, int height) {
        try {
            // Load the image
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Resize the image
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Create and return ImageIcon from resized image
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public static void main(String[] args) {
    //     // Create the main frame
    //     JFrame frame = new JFrame("Dashboard Panel");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setSize(800, 600);

    //     // Add the dashboard panel to the frame
    //     frame.add(new DashboardPanel());

    //     // Set the frame visible
    //     frame.setVisible(true);
    // }
}
