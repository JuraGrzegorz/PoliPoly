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


//        try {
//            File imageFile = new File(ImagePath);
//            Image image = ImageIO.read(imageFile);
//
//            JLabel imageLabel = new JLabel(new ImageIcon(ImagePath));
//
//            imagePanel.add(imageLabel);
//            imagePanel.setBounds(1,1,98,163);
//            imagePanel.setOpaque(false);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
    }

    public static void makePawnPanel(JPanel[] pawnPanel, String ImagePath) {
        for (int i = 0; i < 4; i++) {
            pawnPanel[i].setBounds(52-18 + i % 2 * (33+18), 52-18 + i / 2 * (33+18), 40, 40);
            pawnPanel[i].setOpaque(false); // Make pawnPanel transparent

            try {
                File pawnImageFile = new File(ImagePath);
                Image pawnImage = ImageIO.read(pawnImageFile);

                double rotationAngle = Math.PI / 2;
                int scaledWidth = 40;
                int scaledHeight = 40;
                BufferedImage rotatedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);

                Graphics2D g2d = (Graphics2D) rotatedImage.getGraphics();
                g2d.rotate(rotationAngle, scaledWidth / 2, scaledHeight / 2);
                g2d.drawImage(pawnImage, 0, 0, scaledWidth, scaledHeight, null);
                g2d.dispose();

                // Create a custom JPanel to display the pawn graphic
                JPanel pawnImagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        // Draw the scaled pawn image with transparency
                        g.drawImage(rotatedImage, 0, 0, null);
                    }
                };
                pawnImagePanel.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
                pawnImagePanel.setOpaque(false); // Make pawnImagePanel transparent

                // Add the pawnImagePanel to the pawnPanel
                pawnPanel[i].add(pawnImagePanel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
