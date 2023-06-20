import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HorizontalFacultyCard {

    public HorizontalFacultyCard() {
    }

    public static void makeUponPanel(JPanel uponPanel, int x, int price, Color color) {
        Rectangle uponPanelRectangle;
        if (x == 1) {
            uponPanelRectangle = new Rectangle(0, 0, 28, 100);
        } else {
            uponPanelRectangle = new Rectangle(137, 0, 28, 100);
        }
        uponPanel.setBounds(uponPanelRectangle);
        JPanel uponPanelContent = new JPanel(new BorderLayout());
        uponPanelContent.setOpaque(false);

        JLabel uponPanelLabel = new JLabel(price + " P$") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.translate(getWidth() / 2, getHeight() / 2);
                g2d.rotate(-Math.PI / 2);
                g2d.setFont(getFont());
                g2d.setColor(getForeground());
                g2d.drawString(getText(), -getFontMetrics(getFont()).stringWidth(getText()) / 2, getFontMetrics(getFont()).getAscent() / 2);
                g2d.dispose();
            }
        };
        uponPanelLabel.setFont(new Font("Calibri", Font.BOLD, 14));

        uponPanelLabel.setVerticalAlignment(JLabel.CENTER);
        uponPanelLabel.setHorizontalAlignment(JLabel.CENTER);

        uponPanelLabel.setPreferredSize(new Dimension(uponPanelRectangle.height, uponPanelRectangle.width));

        uponPanelContent.add(Box.createVerticalGlue(), BorderLayout.NORTH);
        uponPanelContent.add(Box.createHorizontalGlue(), BorderLayout.WEST);
        uponPanelContent.add(uponPanelLabel, BorderLayout.CENTER);
        uponPanelContent.add(Box.createHorizontalGlue(), BorderLayout.EAST);
        uponPanelContent.add(Box.createVerticalGlue(), BorderLayout.SOUTH);

        uponPanel.setLayout(new BorderLayout());
        uponPanel.add(uponPanelContent, BorderLayout.CENTER);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        uponPanel.setBorder(border);

        uponPanel.setBackground(color);


    }


    public static void makeNamePanel(JPanel namePanel, int x, String name) {
        Rectangle nameRectangle;
        if (x == 1) {
            nameRectangle = new Rectangle(28, 0, 24, 100);
        } else {
            nameRectangle = new Rectangle(113, 0, 24, 100);
        }
        namePanel.setBounds(nameRectangle);
        JPanel namePanelContent = new JPanel(new BorderLayout());
        namePanelContent.setOpaque(false);

        JLabel namePanelLabel = new JLabel(name) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.translate(getWidth() / 2, getHeight() / 2);
                g2d.rotate(-Math.PI / 2);
                g2d.setFont(getFont());
                g2d.setColor(getForeground());
                g2d.drawString(getText(), -getFontMetrics(getFont()).stringWidth(getText()) / 2, getFontMetrics(getFont()).getAscent() / 2);
                g2d.dispose();
            }
        };
        namePanelLabel.setFont(new Font("Calibri", Font.BOLD, 12));
        namePanelLabel.setVerticalAlignment(JLabel.CENTER);
        namePanelLabel.setHorizontalAlignment(JLabel.CENTER);
        namePanelLabel.setPreferredSize(new Dimension(nameRectangle.height, nameRectangle.width));
        namePanelContent.add(Box.createVerticalGlue(), BorderLayout.NORTH);
        namePanelContent.add(Box.createHorizontalGlue(), BorderLayout.WEST);
        namePanelContent.add(namePanelLabel, BorderLayout.CENTER);
        namePanelContent.add(Box.createHorizontalGlue(), BorderLayout.EAST);
        namePanelContent.add(Box.createVerticalGlue(), BorderLayout.SOUTH);
        namePanel.setLayout(new BorderLayout());
        namePanel.add(namePanelContent, BorderLayout.CENTER);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        namePanel.setBorder(border);
        namePanel.setBackground(Color.WHITE);
    }

    public static void makePawnPanel(JPanel[] pawnPanel, int x) {
        String pawnImagePath = "";

        for (int i = 0; i < 4; i++) {

            if (i == 0) pawnImagePath = "assets\\blu.png";
            else if (i == 1) pawnImagePath = "assets\\Mintus.png";
            else if (i == 2) pawnImagePath = "assets\\orang.png";
            else if (i == 3) pawnImagePath = "assets\\purpul.png";

            if (x == 1) pawnPanel[i].setBounds(47 + i % 2 * 42, 5 + i / 2 * 42, 40, 40);
            else pawnPanel[i].setBounds(35 + i % 2 * 42, 5 + i / 2 * 42, 40, 40);
            pawnPanel[i].setOpaque(false); // Make pawnPanel transparent

            try {
                File pawnImageFile = new File(pawnImagePath);
                Image pawnImage = ImageIO.read(pawnImageFile);

                int scaledWidth = 40;
                int scaledHeight = 40;
                Image scaledPawnImage = pawnImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

                JPanel pawnImagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(scaledPawnImage, 0, 0, null);
                    }
                };
                pawnImagePanel.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
                pawnImagePanel.setOpaque(false);

                pawnPanel[i].add(pawnImagePanel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void makeHousePanel(JPanel[] housePanel, String ImagePath, int x) {

        for (int i = 0; i < 3; i++) {
            if (x == 1) {
                housePanel[i].setBounds(128, 5 + (32 * i), 25, 25);
            } else {
                housePanel[i].setBounds(10, 5 + (32 * i), 25, 25);
            }

            housePanel[i].setOpaque(false);

            try {
                File houseImageFile = new File(ImagePath);
                Image houseImage = ImageIO.read(houseImageFile);
                double rotationAngle;
                if (x == 1) rotationAngle = Math.PI / 2 * 3;
                else rotationAngle = Math.PI / 2;
                int scaledWidth = 20;
                int scaledHeight = 20;
                BufferedImage rotatedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);

                Graphics2D g2d = (Graphics2D) rotatedImage.getGraphics();
                g2d.rotate(rotationAngle, scaledWidth / 2, scaledHeight / 2);
                g2d.drawImage(houseImage, 0, 0, scaledWidth, scaledHeight, null);
                g2d.dispose();

                JPanel houseImagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(rotatedImage, 0, 0, null);
                    }
                };
                houseImagePanel.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
                houseImagePanel.setOpaque(false);

                housePanel[i].add(houseImagePanel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}