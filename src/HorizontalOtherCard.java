import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HorizontalOtherCard extends JFrame {

//        private JLayeredPane layeredPane;
//        private JPanel imagePanel;
//        private JPanel[] pawnPanel;

    public HorizontalOtherCard() {
    }

    public static void makeImagePanel(JPanel imagePanel, String ImagePath) {
//            Rectangle imageRectangle = new Rectangle(0, 0, 80, 140);
//            imagePanel.setBounds(imageRectangle);
//            imagePanel.setLayout(null);

//            imagePanel.setBackground(Color.WHITE);

//        imagePanel.setOpaque(false);
//        imagePanel.setBounds(0,0,100,165);

        try {
            File imageFile = new File(ImagePath);
            Image image = ImageIO.read(imageFile);

            JLabel imageLabel = new JLabel(new ImageIcon(ImagePath));

            imagePanel.add(imageLabel);
            imagePanel.setBounds(1,1,163,98);
            imagePanel.setOpaque(false);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public static void makePawnPanel(JPanel[] pawnPanel, String ImagePath, int x) {
        for (int i = 0; i < 4; i++) {

            if(x==1) pawnPanel[i].setBounds(47 + i % 2 * 42, 5 + i / 2 * 42, 40, 40);
            else pawnPanel[i].setBounds(35 + i % 2 * 42, 5 + i / 2 * 42, 40, 40);
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
        }
    }
}
