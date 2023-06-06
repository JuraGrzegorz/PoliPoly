import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamingWindow {
    final private int RECTANGULAR_FIELDS_AMOUNT = 28;
    final private int SQUARE_FIELDS_AMOUNT = 4;
    final private int SQUARE_FIELDS_SIDE_LENGTH = 165;
    final private int RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH = 100;
    final private int RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH = 165;
    final private int WINDOW_SIDE_LENGTH = 1920;
    final private int WINDOW_SIDE_HEIGHT = 1080;

    final private int SQUARE_PLACEMENT_OFFSET_X = 864;
    final private int SQUARE_PLACEMENT_OFFSET_Y = 864;

    final private int PLAYER_SECTION_OFFSET_X = 1085;
    final private int PLAYER_SECTION_OFFSET_Y = 25;

    JFrame windowFrame;
    JPanel gameContents;
    JPanel cardsPanel;
    JPanel cashPanel;
    JPanel playerTurnPanel;
    JPanel mouseHoverInfoPanel;

    JPanel[] verticalFieldsArray;
    JPanel[] horizontalFieldsArray;
    JPanel[] squareFieldsArray;

    Border blackline = BorderFactory.createLineBorder(Color.black);

    GamingWindow() throws IOException {
        windowFrame = new JFrame();
        gameContents = new JPanel();
        cardsPanel = new JPanel();
        cashPanel = new JPanel();
        playerTurnPanel = new JPanel();
        mouseHoverInfoPanel = new JPanel();

        gameContents.setPreferredSize(new Dimension(WINDOW_SIDE_LENGTH,WINDOW_SIDE_HEIGHT));
        windowFrame.getContentPane().add(gameContents);
        windowFrame.pack();

        windowFrame.setTitle("Polipoly");
        windowFrame.setLayout(null);
        windowFrame.setVisible(true);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setResizable(false);

        gameContents.setBackground(Color.white);
        gameContents.setVisible(true);
        gameContents.setLayout(null);

        verticalFieldsArray = new JPanel[14];
        horizontalFieldsArray = new JPanel[14];
        squareFieldsArray = new JPanel[4];

        paintCornerFields(windowFrame, squareFieldsArray);
        paintRectangularFields();
        paintPlayerSection(windowFrame, cardsPanel, cashPanel, playerTurnPanel, mouseHoverInfoPanel);
        windowFrame.add(gameContents);

        windowFrame.invalidate();
        windowFrame.validate();
        windowFrame.repaint();
    }




    private void paintRectangularFields() {
        int rectangularFieldOffset = SQUARE_FIELDS_SIDE_LENGTH;
        for(int i=0; i<(RECTANGULAR_FIELDS_AMOUNT/2)-1; i+=2){
            horizontalFieldsArray[i] = new JPanel();
            horizontalFieldsArray[i+1] = new JPanel();
            verticalFieldsArray[i] = new JPanel();
            verticalFieldsArray[i+1] = new JPanel();

            horizontalFieldsArray[i].setBounds(
                    rectangularFieldOffset,
                    0,
                    RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH,
                    RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH
            );
            horizontalFieldsArray[i+1].setBounds(
                    rectangularFieldOffset,
                    SQUARE_PLACEMENT_OFFSET_Y,
                    RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH,
                    RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH
            );

            verticalFieldsArray[i].setBounds(
                    0,
                    rectangularFieldOffset,
                    RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH,
                    RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH
            );
            verticalFieldsArray[i+1].setBounds(
                    SQUARE_PLACEMENT_OFFSET_X,
                    rectangularFieldOffset,
                    RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH,
                    RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH
            );

            horizontalFieldsArray[i].setBackground(Color.cyan);
            horizontalFieldsArray[i+1].setBackground(Color.cyan);
            verticalFieldsArray[i].setBackground(Color.cyan);
            verticalFieldsArray[i+1].setBackground(Color.cyan);

            horizontalFieldsArray[i].setVisible(true);
            horizontalFieldsArray[i+1].setVisible(true);
            verticalFieldsArray[i].setVisible(true);
            verticalFieldsArray[i+1].setVisible(true);

            horizontalFieldsArray[i].setBorder(blackline);
            horizontalFieldsArray[i+1].setBorder(blackline);
            verticalFieldsArray[i].setBorder(blackline);
            verticalFieldsArray[i+1].setBorder(blackline);

            windowFrame.add(horizontalFieldsArray[i]);
            windowFrame.add(horizontalFieldsArray[i+1]);
            windowFrame.add(verticalFieldsArray[i]);
            windowFrame.add(verticalFieldsArray[i+1]);

            rectangularFieldOffset += RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH;
        }
    }

    private void paintCornerFields(JFrame windowFrame, JPanel[] squareFieldsArray) {
        for(int i=0; i<SQUARE_FIELDS_AMOUNT; i++){
            squareFieldsArray[i] = new JPanel();
            switch (i) {
                case 0 -> squareFieldsArray[i].setBounds(0, 0, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 1 -> squareFieldsArray[i].setBounds(0, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 2 -> squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X, 0, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 3 -> squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);

            }
            squareFieldsArray[i].setBackground(Color.green);
            squareFieldsArray[i].setVisible(true);
            squareFieldsArray[i].setBorder(blackline);
            windowFrame.add(squareFieldsArray[i]);
        }
    }

    public void paintPlayerSection(JFrame gameWindow, JPanel cardsPanel, JPanel cashPanel, JPanel playerTurnPanel, JPanel mouseHoverInfoPanel) throws IOException {
        generateCardsPanel(cardsPanel);
        generateCashPanel(cashPanel);
        generatePlayerTurnPanel(playerTurnPanel);
        generateMouseHoverInfoPanel(mouseHoverInfoPanel);

        gameWindow.add(cardsPanel);
        gameWindow.add(cashPanel);
        gameWindow.add(playerTurnPanel);
        gameWindow.add(mouseHoverInfoPanel);
    }

    public void generateCardsPanel(JPanel cardsPanel) throws IOException {
        Rectangle cardsPanelRectangle = new Rectangle(25+ PLAYER_SECTION_OFFSET_X, 340-PLAYER_SECTION_OFFSET_Y, 725, 630);
        cardsPanel.setBounds(cardsPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\karciochy.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        cardsPanel.add(picLabel);
        cardsPanel.setBorder(blackline);
        cardsPanel.setBackground(Color.black);
//        cardsPanel.setBackground(Color.CYAN);
    }

    public void generateCashPanel(JPanel cashPanel) throws IOException {
        Rectangle cashPanelRectangle = new Rectangle(450+ PLAYER_SECTION_OFFSET_X, 25-PLAYER_SECTION_OFFSET_Y, 300, 50);
        cashPanel.setBounds(cashPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\manymanymany.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        cashPanel.add(picLabel);
        cashPanel.setBorder(blackline);
        cashPanel.setBackground(Color.black);
//        cashPanel.setBackground(Color.CYAN);
    }

    private void generatePlayerTurnPanel(JPanel playerTurnPanel) throws IOException {
        Rectangle playerTurnPanelRectangle = new Rectangle(25+ PLAYER_SECTION_OFFSET_X, 25-PLAYER_SECTION_OFFSET_Y, 400, 50);
        playerTurnPanel.setBounds(playerTurnPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\turagracz.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        playerTurnPanel.add(picLabel);
        playerTurnPanel.setBorder(blackline);
        playerTurnPanel.setBackground(Color.black);
//        playerTurnPanel.setBackground(Color.CYAN);
    }

    private void generateMouseHoverInfoPanel(JPanel mouseHoverInfoPanel) throws IOException {
        Rectangle mouseHoverInfoPanelRectangle = new Rectangle(25+ PLAYER_SECTION_OFFSET_X, 100-PLAYER_SECTION_OFFSET_Y, 725, 220);
        mouseHoverInfoPanel.setBounds(mouseHoverInfoPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\info.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        mouseHoverInfoPanel.add(picLabel);
        mouseHoverInfoPanel.setBorder(blackline);
        mouseHoverInfoPanel.setBackground(Color.black);
//        mouseHoverInfoPanel.setBackground(Color.CYAN);
    }
}