import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VerticalFacultyCard extends JPanel {

    //    private String frameTitle;
//    private JPanel gameFrame;
//    private static JPanel uponPanel;
//    private static JPanel namePanel;
//    private static JPanel[] pawnPanel;
//    private static JPanel[] housePanel;

    public VerticalFacultyCard() {
//        gameFrame = new JPanel(new GridBagLayout());
//        uponPanel = new JPanel(new GridBagLayout());
//        namePanel = new JPanel(new GridBagLayout());
//        pawnPanel = new JPanel[4];
//        for (int i = 0; i < 4; i++) {
//            pawnPanel[i] = new JPanel();
//        }
//        housePanel = new JPanel[3];
//        for (int i = 0; i < 3; i++) {
//            housePanel[i] = new JPanel();
//        }

//        makeUponPanel();
//        makeNamePanel();
//        makePawnPanel();
//        makeHousePanel();

//        makeGameFrame();
    }

//    public FacultyCard(String frameTitle){
//        this.frameTitle = frameTitle;
//
//    }

//    public void makeGameFrame() {
//        gameFrame.setLayout(null);
//
//        gameFrame.add(uponPanel);
//        gameFrame.add(namePanel);
//        for (int i = 0; i < 4; i++) {
//            gameFrame.add(pawnPanel[i]);
//        }
//        for (int i = 0; i < 3; i++) {
//            gameFrame.add(housePanel[i]);
//        }
//
//
//        gameFrame.setBounds(0, 0, 100, 165);
//        gameFrame.setVisible(true);
//        add(gameFrame);

//    }

    public static void makeUponPanel(JPanel uponPanel, int x) {
        Rectangle uponPanelRectangle;
        if (x == 1) {
            uponPanelRectangle = new Rectangle(0, 0, 100, 28);
        } else {
            uponPanelRectangle = new Rectangle(0, 137, 100, 28);
        }
        uponPanel.setBounds(uponPanelRectangle);
        JLabel uponPanelLabel = new JLabel("1500$");
        uponPanelLabel.setFont(new Font("Calibri", Font.BOLD, 10));

        //

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        uponPanel.setBorder(border);

        uponPanel.setBackground(Color.green);
        uponPanel.add(uponPanelLabel);
    }

    public static void makeNamePanel(JPanel namePanel, int x) {
        Rectangle nameRectangle;
        if (x == 1) {
            nameRectangle = new Rectangle(0, 28, 100, 14);
        } else {
            nameRectangle = new Rectangle(0, 123, 100, 14);
        }
        namePanel.setBounds(nameRectangle);
        JLabel nameLabel = new JLabel("DMCS");
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 10));

        //

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        namePanel.setBorder(border);

        namePanel.setBackground(Color.WHITE);
        namePanel.add(nameLabel);
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

