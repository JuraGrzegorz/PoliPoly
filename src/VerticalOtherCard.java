import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

    public class VerticalOtherCard extends JFrame {

//        private JLayeredPane layeredPane;
//        private JPanel imagePanel;
//        private JPanel[] pawnPanel;

        public VerticalOtherCard() {
//
        }

        public static void makeImagePanel(JPanel imagePanel, String ImagePath) {
//            Rectangle imageRectangle = new Rectangle(0, 0, 80, 140);
//            imagePanel.setBounds(imageRectangle);
//            imagePanel.setLayout(null);

//            imagePanel.setBackground(Color.WHITE);

            try {
                File imageFile = new File(ImagePath);
                Image image = ImageIO.read(imageFile);

                JLabel imageLabel = new JLabel(new ImageIcon(ImagePath));

                imagePanel.add(imageLabel);
                imagePanel.setBounds(1,1,98,163);
                imagePanel.setOpaque(false);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void makePawnPanel(JPanel[] pawnPanel, String ImagePath) {
            for (int i = 0; i < 4; i++) {
                pawnPanel[i].setBounds(22 + i % 2 * 30, 52 + i / 2 * 33, 22, 22);
                pawnPanel[i].setOpaque(false); // Make pawnPanel transparent

                try {
                    File pawnImageFile = new File(ImagePath);
                    Image pawnImage = ImageIO.read(pawnImageFile);

                    int scaledWidth = 15;
                    int scaledHeight = 15;
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
