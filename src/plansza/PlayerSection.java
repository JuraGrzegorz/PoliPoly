package plansza;

import javax.swing.*;
import java.awt.*;

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

    public PlayerSection(int x, int y, String title){
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

    public void generateCardsPanel(JPanel cardsPanel){
        Rectangle cardsPanelRectangle = new Rectangle(25, 340, 935, 600);
        cardsPanel.setBounds(cardsPanelRectangle);
        JLabel cardsPanelLabel = new JLabel("Posiadane karty:");
        cardsPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        cardsPanel.add(cardsPanelLabel);
        cardsPanel.setBackground(Color.green);
    }

    public void generateCashPanel(JPanel cashPanel) {
        Rectangle cashPanelRectangle = new Rectangle(660, 210, 300, 110);
        cashPanel.setBounds(cashPanelRectangle);
        JLabel cashPanelLabel = new JLabel("Fundusze:");
        cashPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        cashPanel.add(cashPanelLabel);
        cashPanel.setBackground(Color.YELLOW);
    }

    private void generatePlayerTurnPanel(JPanel playerTurnPanel) {
        Rectangle playerTurnPanelRectangle = new Rectangle(25, 25, 610, 50);
        playerTurnPanel.setBounds(playerTurnPanelRectangle);
        JLabel playerTurnPanelLabel = new JLabel("Tura gracza:");
        playerTurnPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        playerTurnPanel.add(playerTurnPanelLabel);
        playerTurnPanel.setBackground(Color.CYAN);
    }

    private void generateMouseHoverInfoPanel(JPanel mouseHoverInfoPanel) {
        Rectangle mouseHoverInfoPanelRectangle = new Rectangle(25, 100, 610, 220);
        mouseHoverInfoPanel.setBounds(mouseHoverInfoPanelRectangle);
        JLabel mouseHoverInfoPanelLabel = new JLabel("<html>*tu sie wyswitli info jak najedziesz<br/>na cos na planszy np. karte - wlasciciel itd.*</html>");
        mouseHoverInfoPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        mouseHoverInfoPanel.add(mouseHoverInfoPanelLabel);
        mouseHoverInfoPanel.setBackground(Color.GRAY);
    }
}