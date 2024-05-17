package fintrack.ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import fintrack.App;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUi extends JPanel {

        private static final int ICON_WIDTH = 25; // Desired width of the icons
        private static final int ICON_HEIGHT = 25; // Desired height of the icons

        public MainUi() {
                // Set layout for the dashboard panel
                setLayout(new BorderLayout());

                // Create side bar menu panel
                JPanel sideBarMenu = new JPanel();
                sideBarMenu.setLayout(new BoxLayout(sideBarMenu, BoxLayout.Y_AXIS));
                sideBarMenu.setBackground(Color.WHITE);
                sideBarMenu.setBorder(BorderFactory.createEmptyBorder(125, 25, 0, 0));

                // Increase width of side bar menu
                sideBarMenu.setPreferredSize(new Dimension(80, getHeight()));

                final JLabel homeLabel = new JLabel(
                                resizeImageIcon(new File("").getAbsolutePath() + "\\src\\main\\resource\\home.png",
                                                ICON_WIDTH, ICON_HEIGHT));
                homeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                final JLabel budgetLabel = new JLabel(
                                resizeImageIcon(new File("").getAbsolutePath() + "\\src\\main\\resource\\budget.png",
                                                ICON_WIDTH, ICON_HEIGHT));
                budgetLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                final JLabel historyLabel = new JLabel(
                                resizeImageIcon(new File("").getAbsolutePath() + "\\src\\main\\resource\\history.png",
                                                ICON_WIDTH, ICON_HEIGHT));
                historyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                final JLabel profileLabel = new JLabel(
                                resizeImageIcon(new File("").getAbsolutePath() + "\\src\\main\\resource\\user.png",
                                                ICON_WIDTH, ICON_HEIGHT));
                profileLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Add gaps between labels
                sideBarMenu.add(Box.createRigidArea(new Dimension(0, 0))); // Add initial gap
                sideBarMenu.add(homeLabel);
                sideBarMenu.add(Box.createRigidArea(new Dimension(0, 35))); // Add gap between home and budget
                sideBarMenu.add(budgetLabel);
                sideBarMenu.add(Box.createRigidArea(new Dimension(0, 35))); // Add gap between budget and history
                sideBarMenu.add(historyLabel);
                sideBarMenu.add(Box.createRigidArea(new Dimension(0, 35))); // Add gap between history and profile
                sideBarMenu.add(profileLabel);
                sideBarMenu.add(Box.createVerticalGlue()); // Add vertical glue to push labels to top

                // Create panel for profile image label
                JPanel profilePanel = new JPanel(new GridBagLayout());
                profilePanel.setBackground(Color.WHITE);
                profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

                // Create constraints for profileImageLabel
                GridBagConstraints powerButtonConstraints = new GridBagConstraints();
                powerButtonConstraints.gridx = 1;
                powerButtonConstraints.gridy = 0;
                powerButtonConstraints.anchor = GridBagConstraints.NORTHEAST; // Align to top-right corner
                powerButtonConstraints.insets = new Insets(7, 0, 0, 0);

                // Create a power button label with icon
                JLabel powerButtonLabel = new JLabel(
                                resizeImageIcon(new File("").getAbsolutePath() + "\\src\\main\\resource\\power.png",
                                                30, 30));
                powerButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                // Create profile image label
                profilePanel.add(powerButtonLabel, powerButtonConstraints);
                JLabel profileImageLabel = new JLabel(
                                resizeImageIcon(new File("").getAbsolutePath()
                                                + "\\src\\main\\resource\\maleProfile.png", 45, 45));
                // Add profileImageLabel to profilePanel

                // Create constraints for powerButtonLabel
                GridBagConstraints profileImageConstraints = new GridBagConstraints();
                profileImageConstraints.gridx = 2;
                profileImageConstraints.gridy = 0;
                profileImageConstraints.anchor = GridBagConstraints.NORTHEAST; // Align to top-right corner
                profileImageConstraints.insets = new Insets(0, 20, 0, 0); // Add some space between profile image and
                                                                          // power button
                profilePanel.add(profileImageLabel, profileImageConstraints);

                // Load the image for the logo
                ImageIcon logoIcon = resizeImageIcon(
                                new File("").getAbsolutePath() + "\\src\\main\\resource\\maleProfile.png", 55, 55);

                // Create menu labels with icons
                JLabel logoLabel = new JLabel(logoIcon);

                // Create label for person's name
                JLabel nameLabel = new JLabel("Hello, John");
                nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Add left inset
                Font font = nameLabel.getFont();
                nameLabel.setFont(new Font(font.getName(), Font.BOLD, 23));
                nameLabel.setForeground(Color.BLACK);

                // Create panel to hold nameLabel and profilePanel
                JPanel topPanel = new JPanel(new BorderLayout());
                topPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                topPanel.setBackground(Color.WHITE);
                topPanel.add(logoLabel, BorderLayout.WEST);
                topPanel.add(nameLabel, BorderLayout.CENTER);
                topPanel.add(profilePanel, BorderLayout.EAST);

                // Create dashboard content panel
                final JPanel mainUiContent = new JPanel(new BorderLayout());
                mainUiContent.setBackground(Color.LIGHT_GRAY);
                mainUiContent.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(Color.WHITE),
                                BorderFactory.createEmptyBorder(10, 10, 0, 0)));
                mainUiContent.add(new HomeUi(), BorderLayout.CENTER);

                // Add side bar menu panel and topPanel to the dashboard panel
                add(sideBarMenu, BorderLayout.WEST);
                add(topPanel, BorderLayout.NORTH);

                // Add the dashboard content panel to the center of the dashboard panel
                add(mainUiContent, BorderLayout.CENTER);
                // Add MouseListener to label
                homeLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                mainUiContent.removeAll();
                                mainUiContent.add(new HomeUi(), BorderLayout.CENTER);
                                mainUiContent.revalidate();
                                mainUiContent.repaint();
                        }
                });
                budgetLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                mainUiContent.removeAll();
                                mainUiContent.add(new BudgetUi(), BorderLayout.CENTER);
                                mainUiContent.revalidate();
                                mainUiContent.repaint();
                        }
                });
                historyLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                mainUiContent.removeAll();
                                mainUiContent.add(new Historyui(), BorderLayout.CENTER);
                                mainUiContent.revalidate();
                                mainUiContent.repaint();
                        }
                });
                profileLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                mainUiContent.removeAll();
                                mainUiContent.add(new ProfileUi(), BorderLayout.CENTER);
                                mainUiContent.revalidate();
                                mainUiContent.repaint();
                        }
                });

                // Add MouseListener to power button label
                powerButtonLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                int confirm = JOptionPane.showConfirmDialog(MainUi.this,
                                                "Are you sure want to Log Out?", "Confirmation",
                                                JOptionPane.YES_NO_OPTION);
                                if (confirm == JOptionPane.YES_OPTION) {
                                        // System.exit(0);
                                        SwingUtilities.invokeLater(new Runnable() {
                                                public void run() {
                                                        App.frame.getContentPane().removeAll();
                                                        App.frame.getContentPane().add(new SigninUi());
                                                        App.frame.getContentPane().revalidate();
                                                        App.frame.getContentPane().repaint();
                                                }
                                        });
                                }
                        }
                });
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
}
