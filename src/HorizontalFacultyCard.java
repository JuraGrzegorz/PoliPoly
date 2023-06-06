import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.File;
import java.io.IOException;

public class HorizontalFacultyCard {

    //    private String frameTitle;
    private JFrame gameFrame;
    private JPanel uponPanel;
    private JPanel namePanel;
    private JPanel[] pawnPanel;
    private JPanel[] housePanel;

    public HorizontalFacultyCard(){
        gameFrame = new JFrame();
        uponPanel = new JPanel(new GridBagLayout());
        namePanel = new JPanel(new GridBagLayout());
        pawnPanel = new JPanel[4];
        for(int i=0; i<4; i++){
            pawnPanel[i] = new JPanel();
        }
        housePanel = new JPanel[3];
        for(int i=0; i<3; i++){
            housePanel[i] = new JPanel();
        }

        makeUponPanel();
        makeNamePanel();
        makePawnPanel();
        makeHousePanel();

        makeGameFrame();
    }

//    public FacultyCard(String frameTitle){
//        this.frameTitle = frameTitle;
//
//    }

    private void makeGameFrame(){
        gameFrame.setLayout(null);

        gameFrame.add(uponPanel);
        gameFrame.add(namePanel);
        for(int i=0; i<4; i++){
            gameFrame.add(pawnPanel[i]);
        }
        for(int i=0; i<3; i++){
            gameFrame.add(housePanel[i]);
        }

        gameFrame.setTitle("Karta wydzialu");
        gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        gameFrame.setSize(165,100);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);

    }
    private void makeUponPanel() {
        Rectangle uponPanelRectangle = new Rectangle(0, 0, 28, 100);
        uponPanel.setBounds(uponPanelRectangle);
        JPanel uponPanelContent = new JPanel(new BorderLayout());
        uponPanelContent.setOpaque(false);

        JLabel uponPanelLabel = new JLabel("1500$") {
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
        uponPanelLabel.setFont(new Font("Calibri", Font.BOLD, 10));

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

        uponPanel.setBackground(Color.GREEN);

        gameFrame.add(uponPanel);
    }





    private void makeNamePanel(){
        Rectangle nameRectangle = new Rectangle(28,0,14,100);
        namePanel.setBounds(nameRectangle);
        JPanel namePanelContent = new JPanel(new BorderLayout());
        namePanelContent.setOpaque(false);

        JLabel namePanelLabel = new JLabel("DMCS") {
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
        namePanelLabel.setFont(new Font("Calibri", Font.BOLD, 10));

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

        gameFrame.add(namePanel);
    }
    private void makePawnPanel() {
        for (int i = 0; i < 4; i++) {
            pawnPanel[i].setBounds(52 + i / 2 * 33, 22 + i % 2 * 30, 22, 22);
            pawnPanel[i].setOpaque(false); // Make pawnPanel transparent

            try {
                File pawnImageFile = new File("C:\\Users\\HP\\Desktop\\4.png");
                Image pawnImage = ImageIO.read(pawnImageFile);

                // Scale the pawn image to a larger size
                int scaledWidth = 15;
                int scaledHeight = 15;
                Image scaledPawnImage = pawnImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

                // Create a custom JPanel to display the pawn graphic
                JPanel pawnImagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        // Draw the scaled pawn image with transparency
                        g.drawImage(scaledPawnImage, 0, 0, null);
                    }
                };
                pawnImagePanel.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
                pawnImagePanel.setOpaque(false); // Make pawnImagePanel transparent

                // Add the pawnImagePanel to the pawnPanel
                pawnPanel[i].add(pawnImagePanel);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JLabel pawnLabel = new JLabel();
//            Border border = BorderFactory.createLineBorder(Color.BLACK);
//            pawnPanel[i].setBorder(border);
            pawnPanel[i].add(pawnLabel);
        }
    }
    private void makeHousePanel(){
        Rectangle houseRectangle0 = new Rectangle(124,3,25,25);
        housePanel[0].setBounds(houseRectangle0);
        housePanel[0].setBackground(Color.lightGray);
        JLabel houseLabel0 = new JLabel("D1");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        housePanel[0].setBorder(border);
        housePanel[0].add(houseLabel0);

        Rectangle houseRectangle1 = new Rectangle(124,35,25,25);
        housePanel[1].setBounds(houseRectangle1);
        housePanel[1].setBackground(Color.lightGray);
        JLabel houseLabel1 = new JLabel("D2");
        housePanel[1].setBorder(border);
        housePanel[1].add(houseLabel1);

        Rectangle houseRectangle2 = new Rectangle(124,67,25,25);
        housePanel[2].setBounds(houseRectangle2);
        housePanel[2].setBackground(Color.lightGray);
        JLabel houseLabel2 = new JLabel("D3");
        housePanel[2].setBorder(border);
        housePanel[2].add(houseLabel2);
    }
}
