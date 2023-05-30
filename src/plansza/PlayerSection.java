package plansza;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerSection {
    private int gameFrameX;
    private int gameFrameY;
    private String gameFrameTitle;
    private JFrame gameFrame;
    private JPanel cardsPanel;
    private JPanel cashPanel;
    private JPanel playerTurnPanel;
    private JPanel mouseHoverInfoPanel;

    public PlayerSection(){}

    public PlayerSection(int x, int y, String title) throws IOException {
        this.gameFrameX = x;
        this.gameFrameY = y;
        this.gameFrameTitle = title;
        gameFrame = new JFrame();
        cardsPanel = new JPanel();
        cashPanel = new JPanel();
        playerTurnPanel = new JPanel();
        mouseHoverInfoPanel = new JPanel();

        generateCardsPanel(cardsPanel);
        generateCashPanel(cashPanel);
        generatePlayerTurnPanel(playerTurnPanel);
        generateMouseHoverInfoPanel(mouseHoverInfoPanel);


        generatePlayerSection();
    }

    private void generatePlayerSection() {
        gameFrame.add(cardsPanel);
        gameFrame.add(cashPanel);
        gameFrame.add(playerTurnPanel);
        gameFrame.add(mouseHoverInfoPanel);
        gameFrame.setSize(this.gameFrameX,this.gameFrameY);
        gameFrame.setTitle(this.gameFrameTitle);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
    }

    public void generateCardsPanel(JPanel cardsPanel) throws IOException {
        Rectangle cardsPanelRectangle = new Rectangle(25, 340, 725, 630);
        cardsPanel.setBounds(cardsPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\karciochy.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        cardsPanel.add(picLabel);
//        cardsPanel.setBackground(Color.CYAN);
    }

    public void generateCashPanel(JPanel cashPanel) throws IOException {
        Rectangle cashPanelRectangle = new Rectangle(450, 25, 300, 50);
        cashPanel.setBounds(cashPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\manymanymany.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        cashPanel.add(picLabel);
//        cashPanel.setBackground(Color.CYAN);
    }

    private void generatePlayerTurnPanel(JPanel playerTurnPanel) throws IOException {
        Rectangle playerTurnPanelRectangle = new Rectangle(25, 25, 400, 50);
        playerTurnPanel.setBounds(playerTurnPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\turagracz.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        playerTurnPanel.add(picLabel);
//        playerTurnPanel.setBackground(Color.CYAN);
    }

    private void generateMouseHoverInfoPanel(JPanel mouseHoverInfoPanel) throws IOException {
        Rectangle mouseHoverInfoPanelRectangle = new Rectangle(25, 100, 725, 220);
        mouseHoverInfoPanel.setBounds(mouseHoverInfoPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\info.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        mouseHoverInfoPanel.add(picLabel);
//        mouseHoverInfoPanel.setBackground(Color.CYAN);
    }
}