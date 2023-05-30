import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OtherCard extends JFrame {

    private JLayeredPane layeredPane;
    private JPanel imagePanel;
    private JPanel[] pawnPanel;

    public OtherCard() {
        layeredPane = new JLayeredPane();
        imagePanel = new JPanel();
        pawnPanel = new JPanel[4];
        for (int i = 0; i < 4; i++) {
            pawnPanel[i] = new JPanel();
        }

        makeImagePanel();
        makePawnPanel();

        makeCardFrame();
    }

    public void makeCardFrame() {
        layeredPane.setPreferredSize(new Dimension(400, 600));

        layeredPane.add(imagePanel, 0);
        for (int i = 0; i < 4; i++) {
            layeredPane.add(pawnPanel[i], new Integer(1));
        }

        getContentPane().add(layeredPane);

        setTitle("Karta narozna");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void makeImagePanel() {
        Rectangle imageRectangle = new Rectangle(0, 0, 400, 600);
        imagePanel.setBounds(imageRectangle);
        imagePanel.setBackground(Color.WHITE);

        try {
            File imageFile = new File("C:\\Users\\HP\\Desktop\\pł_graphic.jpg");
            Image image = ImageIO.read(imageFile);

            JLabel imageLabel = new JLabel(new ImageIcon(image));

            imagePanel.add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makePawnPanel() {
        for (int i = 0; i < 4; i++) {
            pawnPanel[i].setBounds(90 + i % 2 * 120, 190 + i / 2 * 120, 80, 80);
            pawnPanel[i].setOpaque(false); // Make pawnPanel transparent

            try {
                File pawnImageFile = new File("C:\\Users\\HP\\Desktop\\4.png");
                Image pawnImage = ImageIO.read(pawnImageFile);

                int scaledWidth = 60;
                int scaledHeight = 60;
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OtherCard otherCard = new OtherCard();
        });
    }
}
