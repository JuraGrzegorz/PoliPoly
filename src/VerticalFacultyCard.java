import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VerticalFacultyCard extends JPanel {

    public VerticalFacultyCard() {

        setBackground(Color.WHITE);

    }

    public static void makeUponPanel(JPanel uponPanel, int x, int price, Color color) {
        Rectangle uponPanelRectangle;
        if (x == 1) {
            uponPanelRectangle = new Rectangle(0, 0, 100, 28);
        } else {
            uponPanelRectangle = new Rectangle(0, 137, 100, 28);
        }
        uponPanel.setBounds(uponPanelRectangle);
        JLabel uponPanelLabel = new JLabel(price+"P$");
        uponPanelLabel.setFont(new Font("Calibri", Font.BOLD, 14));

        //

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        uponPanel.setBorder(border);

        uponPanel.setBackground(color);
        uponPanel.add(uponPanelLabel);
    }

//    public static void setColorAndName(String name, Color color){
//
//    }

    public static void makeNamePanel(JPanel namePanel, int x, String name) {
        Rectangle nameRectangle;
        if (x == 1) {
            nameRectangle = new Rectangle(0, 28, 100, 24);
        } else {
            nameRectangle = new Rectangle(0, 123, 100, 24);
        }
        namePanel.setBounds(nameRectangle);
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 12));

        //

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        namePanel.setBorder(border);

        namePanel.setBackground(Color.WHITE);
        namePanel.add(nameLabel);
    }

    public static void makePawnPanel(JPanel[] pawnPanel, String ImagePath) {
        for (int i = 0; i < 4; i++) {


            pawnPanel[i].setBounds(9 + i % 2 * 42, 32 + i / 2 * 42, 40, 40);
            pawnPanel[i].setOpaque(false); // Make pawnPanel transparent

            try {
                File pawnImageFile = new File(ImagePath);
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


//            JLabel pawnLabel = new JLabel();
//            Border border = BorderFactory.createLineBorder(Color.BLACK);
//            pawnPanel[i].setBorder(border);
//            pawnPanel[i].add(pawnLabel);
        }
    }

    public static void makeHousePanel(JPanel[] housePanel, String ImagePath, int x) {

        for (int i = 0; i < 3; i++) {
            if (x == 1) {
                housePanel[i].setBounds(5 + (32 * i), 124, 25, 25);
            } else {
                housePanel[i].setBounds(5 + (32 * i), 12, 25, 25);
            }

            housePanel[i].setOpaque(false);

            try {
                File houseImageFile = new File(ImagePath);
                Image houseImage = ImageIO.read(houseImageFile);

                int scaledWidth = 20;
                int scaledHeight = 20;
                Image scaledHouseImage = houseImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

                JPanel houseImagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(scaledHouseImage, 0, 0, null);
                    }
                };
                houseImagePanel.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
                houseImagePanel.setOpaque(false);

                housePanel[i].add(houseImagePanel);
            } catch (IOException e) {
                e.printStackTrace();
            }


//            Rectangle houseRectangle0 = new Rectangle(3, 124, 25, 25);
//            housePanel[0].setBounds(houseRectangle0);
//            housePanel[0].setBackground(Color.lightGray);
//            JLabel houseLabel0 = new JLabel("D1");
//            Border border = BorderFactory.createLineBorder(Color.BLACK);
//            housePanel[0].setBorder(border);
//            housePanel[0].add(houseLabel0);
        }
    }
}

