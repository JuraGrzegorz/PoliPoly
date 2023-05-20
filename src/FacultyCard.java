import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FacultyCard {

    private String frameTitle;
    private JFrame gameFrame;
    private JPanel uponPanel;
    private JPanel namePanel;
    private JPanel[] pawnPanel;
    private JPanel[] housePanel;

    public FacultyCard(){}

    public FacultyCard(String frameTitle){
        this.frameTitle = frameTitle;
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
        gameFrame.setSize(400,600);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);

    }
    private void makeUponPanel(){
        Rectangle uponPanelRectangle = new Rectangle(0,0,400,100);
        uponPanel.setBounds(uponPanelRectangle);
        JLabel uponPanelLabel = new JLabel("1500$");
        uponPanelLabel.setFont(new Font("Calibri", Font.BOLD, 30));

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
        Rectangle nameRectangle = new Rectangle(0,100,400,50);
        namePanel.setBounds(nameRectangle);
        JLabel nameLabel = new JLabel("DMCS");
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 30));

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
    private void makePawnPanel(){
        Rectangle pawnRectangle0 = new Rectangle(90,190,80,80);
        pawnPanel[0].setBounds(pawnRectangle0);
        pawnPanel[0].setBackground(Color.WHITE);
        JLabel pawnLabel0 = new JLabel("PIO1");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        pawnPanel[0].setBorder(border);
        pawnPanel[0].add(pawnLabel0);

        Rectangle pawnRectangle1 = new Rectangle(210,190,80,80);
        pawnPanel[1].setBounds(pawnRectangle1);
        pawnPanel[1].setBackground(Color.WHITE);
        JLabel pawnLabel1 = new JLabel("PIO2");
        pawnPanel[1].setBorder(border);
        pawnPanel[1].add(pawnLabel1);

        Rectangle pawnRectangle2 = new Rectangle(90,310,80,80);
        pawnPanel[2].setBounds(pawnRectangle2);
        pawnPanel[2].setBackground(Color.WHITE);
        JLabel pawnLabel2 = new JLabel("PIO3");
        pawnPanel[2].setBorder(border);
        pawnPanel[2].add(pawnLabel2);

        Rectangle pawnRectangle3 = new Rectangle(210,310,80,80);
        pawnPanel[3].setBounds(pawnRectangle3);
        pawnPanel[3].setBackground(Color.WHITE);
        JLabel pawnLabel3 = new JLabel("PIO4");
        pawnPanel[3].setBorder(border);
        pawnPanel[3].add(pawnLabel3);
    }
    private void makeHousePanel(){
        Rectangle houseRectangle0 = new Rectangle(10,450,100,100);
        housePanel[0].setBounds(houseRectangle0);
        housePanel[0].setBackground(Color.WHITE);
        JLabel houseLabel0 = new JLabel("D1");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        housePanel[0].setBorder(border);
        housePanel[0].add(houseLabel0);

        Rectangle houseRectangle1 = new Rectangle(140,450,100,100);
        housePanel[1].setBounds(houseRectangle1);
        housePanel[1].setBackground(Color.WHITE);
        JLabel houseLabel1 = new JLabel("D2");
        housePanel[1].setBorder(border);
        housePanel[1].add(houseLabel1);

        Rectangle houseRectangle2 = new Rectangle(270,450,100,100);
        housePanel[2].setBounds(houseRectangle2);
        housePanel[2].setBackground(Color.WHITE);
        JLabel houseLabel2 = new JLabel("D3");
        housePanel[2].setBorder(border);
        housePanel[2].add(houseLabel2);
    }
}
