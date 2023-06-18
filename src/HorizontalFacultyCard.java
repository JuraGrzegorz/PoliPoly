import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HorizontalFacultyCard {

    //    private String frameTitle;
//    private JFrame gameFrame;
//    private JPanel uponPanel;
//    private JPanel namePanel;
//    private JPanel[] pawnPanel;
//    private JPanel[] housePanel;

    public HorizontalFacultyCard(){
//        gameFrame = new JFrame();
//        uponPanel = new JPanel(new GridBagLayout());
//        namePanel = new JPanel(new GridBagLayout());
//        pawnPanel = new JPanel[4];
//        for(int i=0; i<4; i++){
//            pawnPanel[i] = new JPanel();
//        }
//        housePanel = new JPanel[3];
//        for(int i=0; i<3; i++){
//            housePanel[i] = new JPanel();
//        }
//
//        makeUponPanel();
//        makeNamePanel();
//        makePawnPanel();
//        makeHousePanel();
//
//        makeGameFrame();
    }

//    public FacultyCard(String frameTitle){
//        this.frameTitle = frameTitle;
//
//    }

    //    private void makeGameFrame(){
//        gameFrame.setLayout(null);
//
//        gameFrame.add(uponPanel);
//        gameFrame.add(namePanel);
//        for(int i=0; i<4; i++){
//            gameFrame.add(pawnPanel[i]);
//        }
//        for(int i=0; i<3; i++){
//            gameFrame.add(housePanel[i]);
//        }
//
//        gameFrame.setTitle("Karta wydzialu");
//        gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        gameFrame.setSize(165,100);
//        gameFrame.setLocationRelativeTo(null);
//        gameFrame.setResizable(false);
//        gameFrame.setVisible(true);
//
//    }

    public static void makeUponPanel(JPanel uponPanel, int x, int price, Color color) {
        Rectangle uponPanelRectangle;
        if(x==1){uponPanelRectangle = new Rectangle(0, 0, 28, 100);}
        else{uponPanelRectangle = new Rectangle(137, 0, 28, 100);}
        uponPanel.setBounds(uponPanelRectangle);
        JPanel uponPanelContent = new JPanel(new BorderLayout());
        uponPanelContent.setOpaque(false);

        JLabel uponPanelLabel = new JLabel(price+" P$") {
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


//        gameFrame.add(uponPanel);
    }





    public static void makeNamePanel(JPanel namePanel, int x, String name){
        Rectangle nameRectangle;
        if(x==1){nameRectangle = new Rectangle(28,0,24,100);}
        else{nameRectangle = new Rectangle(113,0,24,100);}
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

//        gameFrame.add(namePanel);
    }
    public static void makePawnPanel(JPanel[] pawnPanel, String ImagePath) {
        for (int i = 0; i < 4; i++) {
            pawnPanel[i].setBounds(52 + i / 2 * 33, 22 + i % 2 * 30, 22, 22);
            pawnPanel[i].setOpaque(false); // Make pawnPanel transparent

            try {
                File pawnImageFile = new File(ImagePath);
                Image pawnImage = ImageIO.read(pawnImageFile);

                double rotationAngle = Math.PI / 2;
                int scaledWidth = 15;
                int scaledHeight = 15;
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

//            JLabel pawnLabel = new JLabel();
//            Border border = BorderFactory.createLineBorder(Color.BLACK);
//            pawnPanel[i].setBorder(border);
//            pawnPanel[i].add(pawnLabel);
        }
    }
    public static void makeHousePanel(JPanel[] housePanel, String ImagePath, int x){

        for (int i = 0; i < 3; i++) {
            if (x == 1) {
                housePanel[i].setBounds(124, 5 + (32*i), 25, 25);
            } else {
                housePanel[i].setBounds(16, 5 + (32*i), 25, 25);
            }

            housePanel[i].setOpaque(false);

            try {
                File houseImageFile = new File(ImagePath);
                Image houseImage = ImageIO.read(houseImageFile);

                double rotationAngle = Math.PI / 2;
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

//            JLabel pawnLabel = new JLabel();
//            Border border = BorderFactory.createLineBorder(Color.BLACK);
//            pawnPanel[i].setBorder(border);
//            pawnPanel[i].add(pawnLabel);
        }
    }


//        Rectangle houseRectangle0 = new Rectangle(124,3,25,25);
//        housePanel[0].setBounds(houseRectangle0);
//        housePanel[0].setBackground(Color.lightGray);
//        JLabel houseLabel0 = new JLabel("D1");
//        Border border = BorderFactory.createLineBorder(Color.BLACK);
//        housePanel[0].setBorder(border);
//        housePanel[0].add(houseLabel0);
//
//        Rectangle houseRectangle1 = new Rectangle(124,35,25,25);
//        housePanel[1].setBounds(houseRectangle1);
//        housePanel[1].setBackground(Color.lightGray);
//        JLabel houseLabel1 = new JLabel("D2");
//        housePanel[1].setBorder(border);
//        housePanel[1].add(houseLabel1);
//
//        Rectangle houseRectangle2 = new Rectangle(124,67,25,25);
//        housePanel[2].setBounds(houseRectangle2);
//        housePanel[2].setBackground(Color.lightGray);
//        JLabel houseLabel2 = new JLabel("D3");
//        housePanel[2].setBorder(border);
//        housePanel[2].add(houseLabel2);
//    }
}