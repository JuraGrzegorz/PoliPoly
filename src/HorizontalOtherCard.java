import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HorizontalOtherCard extends JFrame {
    public static void makeImagePanel(JPanel imagePanel, String ImagePath) {
        File imageFile = new File(ImagePath);
        JLabel imageLabel = new JLabel(new ImageIcon(ImagePath));

        imagePanel.add(imageLabel);
        imagePanel.setBounds(1,1,163,98);
        imagePanel.setOpaque(false);
    }

    public static void makePawnPanel(JPanel[] pawnPanel, String ImagePath, int x) {
        String pawnImagePath = "";

        for (int i = 0; i < 4; i++) {
            if(i==0) pawnImagePath = "assets\\blu.png";
            else if(i==1) pawnImagePath = "assets\\Mintus.png";
            else if(i==2) pawnImagePath = "assets\\orang.png";
            else if(i==3) pawnImagePath = "assets\\purpul.png";

            if(x==1) pawnPanel[i].setBounds(47 + i % 2 * 42, 5 + i / 2 * 42, 40, 40);
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
}
