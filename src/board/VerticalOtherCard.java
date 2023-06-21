import javax.swing.*;
import java.awt.*;
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
//            imagePanel.setLayout(FlowLayout);

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
            String pawnImagePath = "";

            for (int i = 0; i < 4; i++) {

                if(i==0) pawnImagePath = "assets\\blu.png";
                else if(i==1) pawnImagePath = "assets\\Mintus.png";
                else if(i==2) pawnImagePath = "assets\\orang.png";
                else if(i==3) pawnImagePath = "assets\\purpul.png";

                pawnPanel[i].setBounds(9 + i % 2 * 42, 32 + i / 2 * 42, 40, 40);
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
