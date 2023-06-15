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

    JButton leaveFromGameButton;
    JButton buyPropertyButton;

    JPanel inGameButtonPanel;

    Border blackline = BorderFactory.createLineBorder(Color.black);

    GamingWindow() throws IOException {
        windowFrame = new JFrame();
        gameContents = new JPanel();
        cardsPanel = new JPanel();
        inGameButtonPanel = new JPanel();
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

        gameContents.setBackground(Color.LIGHT_GRAY);
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
        for (int i = 0; i < (RECTANGULAR_FIELDS_AMOUNT / 2); i += 1) {
            horizontalFieldsArray[i] = new JPanel();
            verticalFieldsArray[i] = new JPanel();


            if (i % 2 == 0) {
                horizontalFieldsArray[i].setBounds(
                        rectangularFieldOffset,
                        0,
                        RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH,
                        RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH
                );

                verticalFieldsArray[i].setBounds(
                        0,
                        rectangularFieldOffset,
                        RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH,
                        RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH
                );
            } else {
                horizontalFieldsArray[i].setBounds(
                        rectangularFieldOffset - RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH,
                        SQUARE_PLACEMENT_OFFSET_Y,
                        RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH,
                        RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH
                );

                verticalFieldsArray[i].setBounds(
                        SQUARE_PLACEMENT_OFFSET_X,
                        rectangularFieldOffset - RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH,
                        RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH,
                        RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH
                );
            }


//            verticalFieldsArray[i].setBackground(Color.cyan);

            horizontalFieldsArray[i].setVisible(true);
            verticalFieldsArray[i].setVisible(true);
            horizontalFieldsArray[i].setBorder(blackline);
            verticalFieldsArray[i].setBorder(blackline);

            horizontalFieldsArray[i].setLayout(null);
            verticalFieldsArray[i].setLayout(null);



            int help=0;

            switch (i) {
                case 2, 3, 6, 7, 8, 11:
                    JPanel[] pawnPanel1 = new JPanel[4];
                    for (int j = 0; j < 4; j++) {
                        pawnPanel1[j] = new JPanel();
                    }
                    JPanel imagePanel = new JPanel();

                    String imagePath1 = "C:\\Users\\HP\\Desktop\\grey.png";
                    String imagePath21 = "assets\\white.png";
                    VerticalOtherCard.makeImagePanel(imagePanel, imagePath1);
                    VerticalOtherCard.makePawnPanel(pawnPanel1, imagePath21);

                    imagePanel.setLayout(new OverlayLayout(imagePanel));

                    for (int j = 0; j < 4; j++) {
                        horizontalFieldsArray[i].add(pawnPanel1[j]);
                    }
                    horizontalFieldsArray[i].add(imagePanel);

                    horizontalFieldsArray[i].setBorder(blackline);


                    JPanel[] pawnPanel21 = new JPanel[4];
                    for (int j = 0; j < 4; j++) {
                        pawnPanel21[j] = new JPanel();
                    }
                    JPanel imagePanel2 = new JPanel();



                    String imagePath12 = "C:\\Users\\HP\\Desktop\\grey.png";
                    String imagePath22 = "assets\\white.png";
                    HorizontalOtherCard.makeImagePanel(imagePanel2, imagePath12);
                    HorizontalOtherCard.makePawnPanel(pawnPanel21, imagePath22);

                    imagePanel2.setLayout(new OverlayLayout(imagePanel2));

                    for (int j = 0; j < 4; j++) {
                        verticalFieldsArray[i].add(pawnPanel21[j]);
                    }
                    verticalFieldsArray[i].add(imagePanel2);

                    verticalFieldsArray[i].setBorder(blackline);
                    if(i==8 || i==11){
                        help=1;
                    }
                    else{
                        break;
                    }


                case 0, 1, 4, 5, 9, 10, 12, 13: {
                    if(help==0) {
                        int second_help=0;
                        JPanel uponPanel = new JPanel(new GridBagLayout());
                        JPanel namePanel = new JPanel(new GridBagLayout());
                        JPanel[] pawnPanel = new JPanel[4];
                        for (int j = 0; j < 4; j++) {
                            pawnPanel[j] = new JPanel();
                        }
                        JPanel[] housePanel = new JPanel[3];
                        for (int j = 0; j < 3; j++) {
                            housePanel[j] = new JPanel();
                        }
                        String imagePath = "assets\\white.png";

                        switch(i){
                            case 9:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), 150, Color.GREEN);
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), "Inst. Fermentacji");
                                break;
                            case 13:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), 170, Color.GREEN);
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), "Inst. Biotechnologii");
                                break;
                            case 1:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), 200, Color.gray);
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), "Inst. Marketingu");
                                break;
                            case 5:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), 220, Color.gray);
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), "Inst. Zarzadzania");
                                break;
                            case 0:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), 250, Color.yellow);
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), "Inst. Materiałoznastwa");
                                break;
                            case 4:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), 280, Color.yellow);
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), "Kat. Technologii Dziewiarskich");
                                break;
                            case 10:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), 320, Color.BLUE);
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), "Kat. Budownicta Betonowego");
                                break;
                            case 12:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), 350, Color.BLUE);
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), "Inst. Architektury");
                                break;
                        }

//                        VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2));
//                        VerticalFacultyCard.makeNamePanel(namePanel, (i % 2));
                        VerticalFacultyCard.makePawnPanel(pawnPanel, imagePath);
                        VerticalFacultyCard.makeHousePanel(housePanel, imagePath, (i % 2));
                        horizontalFieldsArray[i].add(uponPanel);
                        horizontalFieldsArray[i].add(namePanel);
                        for (int j = 0; j < 4; j++) {
                            horizontalFieldsArray[i].add(pawnPanel[j]);
                        }
                        for (int j = 0; j < 3; j++) {
                            horizontalFieldsArray[i].add(housePanel[j]);
                        }
                    }

                    if(i!=10) {
                        verticalFieldsArray[i].setLayout(null);
                        JPanel uponPanel2 = new JPanel(new GridBagLayout());
                        JPanel namePanel2 = new JPanel(new GridBagLayout());
                        JPanel[] pawnPanel2 = new JPanel[4];
                        for (int j = 0; j < 4; j++) {
                            pawnPanel2[j] = new JPanel();
                        }
                        JPanel[] housePanel2 = new JPanel[3];
                        for (int j = 0; j < 3; j++) {
                            housePanel2[j] = new JPanel();
                        }
                        String imagePath2 = "assets\\white.png";


                        switch(i){
                            case 9:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 150, Color.orange);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "DMCS");
                                break;
                            case 13:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 170, Color.orange);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "IMSI");
                                break;
                            case 1:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 200, Color.red);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "Inst. Obrabiarek");
                                break;
                            case 5:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 220, Color.red);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "Kat. wytrzymałości materiałow");
                                break;
                            case 0:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 250, Color.red);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "Inst. Chemii Organicznej");
                                break;
                            case 4:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 280, Color.red);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "Kat. Fizyki Molekularnej");
                                break;
                            case 8:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 320, Color.orange);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "Fiz");
                                break;
                            case 12:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 350, Color.orange);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "Mat");
                                break;
                            case 11:
                                HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 350, Color.orange);
                                HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "CTI");
                                break;
                        }


//                        HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 500, Color.green);
//                        HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "TEST");
                        HorizontalFacultyCard.makePawnPanel(pawnPanel2, imagePath2);
                        HorizontalFacultyCard.makeHousePanel(housePanel2, imagePath2, (i % 2));
                        verticalFieldsArray[i].add(uponPanel2);
                        verticalFieldsArray[i].add(namePanel2);
                        for (int j = 0; j < 4; j++) {
                            verticalFieldsArray[i].add(pawnPanel2[j]);
                        }
                        for (int j = 0; j < 3; j++) {
                            verticalFieldsArray[i].add(housePanel2[j]);
                        }
                    }
                    help=0;
                    break;
                }
            }


            windowFrame.add(horizontalFieldsArray[i]);
            windowFrame.add(verticalFieldsArray[i]);

            if (i % 2 == 0) {
                rectangularFieldOffset += RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH;
            }
        }
    }


    private void paintCornerFields (JFrame windowFrame, JPanel[]squareFieldsArray){
        for (int i = 0; i < SQUARE_FIELDS_AMOUNT; i++) {
            squareFieldsArray[i] = new JPanel();
            switch (i) {
                case 0 -> squareFieldsArray[i].setBounds(0, 0, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 1 -> squareFieldsArray[i].setBounds(0, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 2 -> squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X, 0, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 3 -> squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);

            }

            squareFieldsArray[i].setVisible(true);
            squareFieldsArray[i].setBorder(blackline);

            squareFieldsArray[i].setLayout(null);
            JPanel[] pawnPanel = new JPanel[4];
            for (int j = 0; j < 4; j++) {
                pawnPanel[j] = new JPanel();
            }
            JPanel imagePanel = new JPanel();

            String imagePath1 = "C:\\Users\\HP\\Desktop\\grey.png";
            String imagePath2 = "assets\\white.png";
            CornerCard.makeImagePanel(imagePanel, imagePath1);
            CornerCard.makePawnPanel(pawnPanel, imagePath2);

            imagePanel.setLayout(new OverlayLayout(imagePanel));

            for (int j = 0; j < 4; j++) {
                squareFieldsArray[i].add(pawnPanel[j]);
            }
            squareFieldsArray[i].add(imagePanel);

            squareFieldsArray[i].setBorder(blackline);

//            squareFieldsArray[i].setBackground(Color.green);

            windowFrame.add(squareFieldsArray[i]);
        }
    }

    public void paintPlayerSection(JFrame gameWindow, JPanel cardsPanel, JPanel cashPanel, JPanel playerTurnPanel, JPanel mouseHoverInfoPanel) throws IOException {
        generateCardsPanel(cardsPanel);
        generateCashPanel(cashPanel);
        generatePlayerTurnPanel(playerTurnPanel);
        generateMouseHoverInfoPanel(mouseHoverInfoPanel);
        generateinGameButtonPanel(inGameButtonPanel);




        gameWindow.add(cardsPanel);
        gameWindow.add(cashPanel);
        gameWindow.add(playerTurnPanel);
        gameWindow.add(mouseHoverInfoPanel);
        gameWindow.add(inGameButtonPanel);

        DiceRoll diceRollPanel = new DiceRoll();
        Rectangle diceRollPanelRectangle = new Rectangle(400, 400-PLAYER_SECTION_OFFSET_Y, 230, 230);
        diceRollPanel.setBounds(diceRollPanelRectangle);
        gameWindow.add(diceRollPanel);
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

    private void generateinGameButtonPanel(JPanel inGameButtonPanel){
        Rectangle inGameButtonPanelRectangle = new Rectangle(25+ PLAYER_SECTION_OFFSET_X, 950, 725, 50);

        inGameButtonPanel.setBounds(inGameButtonPanelRectangle);
        inGameButtonPanel.setOpaque(false);


        buyPropertyButton = MenuWindow.standardButtonGenerate("Zakup");
        leaveFromGameButton = MenuWindow.standardButtonGenerate("Wyjdź");
        leaveFromGameButton.addActionListener(leaveGame -> System.exit(0));

        inGameButtonPanel.add(buyPropertyButton);
        inGameButtonPanel.add(leaveFromGameButton);
    }

}

