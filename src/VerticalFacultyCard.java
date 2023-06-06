import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VerticalFacultyCard {

//    private String frameTitle;
    private JFrame gameFrame;
    private JPanel uponPanel;
    private JPanel namePanel;
    private JPanel[] pawnPanel;
    private JPanel[] housePanel;

    public VerticalFacultyCard(){
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
        gameFrame.setSize(100,165);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);

    }
    private void makeUponPanel(){
        Rectangle uponPanelRectangle = new Rectangle(0,0,100,28);
        uponPanel.setBounds(uponPanelRectangle);
        JLabel uponPanelLabel = new JLabel("1500$");
        uponPanelLabel.setFont(new Font("Calibri", Font.BOLD, 10));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.CENTER;

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        uponPanel.setBorder(border);

        uponPanel.setBackground(Color.green);
        uponPanel.add(uponPanelLabel, constraints);
    }

    private void makeNamePanel(){
        Rectangle nameRectangle = new Rectangle(0,28,100,14);
        namePanel.setBounds(nameRectangle);
        JLabel nameLabel = new JLabel("DMCS");
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 10));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.CENTER;

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        namePanel.setBorder(border);

        namePanel.setBackground(Color.WHITE);
        namePanel.add(nameLabel,constraints);
    }
    private void makePawnPanel() {
        for (int i = 0; i < 4; i++) {
            pawnPanel[i].setBounds(22 + i % 2 * 30, 52 + i / 2 * 33, 22, 22);
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
        Rectangle houseRectangle0 = new Rectangle(3,124,25,25);
        housePanel[0].setBounds(houseRectangle0);
        housePanel[0].setBackground(Color.lightGray);
        JLabel houseLabel0 = new JLabel("D1");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        housePanel[0].setBorder(border);
        housePanel[0].add(houseLabel0);

        Rectangle houseRectangle1 = new Rectangle(35,124,25,25);
        housePanel[1].setBounds(houseRectangle1);
        housePanel[1].setBackground(Color.lightGray);
        JLabel houseLabel1 = new JLabel("D2");
        housePanel[1].setBorder(border);
        housePanel[1].add(houseLabel1);

        Rectangle houseRectangle2 = new Rectangle(67,124,25,25);
        housePanel[2].setBounds(houseRectangle2);
        housePanel[2].setBackground(Color.lightGray);
        JLabel houseLabel2 = new JLabel("D3");
        housePanel[2].setBorder(border);
        housePanel[2].add(houseLabel2);
    }
}
