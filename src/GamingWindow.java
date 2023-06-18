import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class GamingWindow {


    final private int RECTANGULAR_FIELDS_AMOUNT = 28;
    final private int SQUARE_FIELDS_AMOUNT = 4;
    final private int SQUARE_FIELDS_SIDE_LENGTH = 165;
    final private int RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH = 100;
    final private int RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH = 165;
    final private int WINDOW_SIDE_LENGTH = 1920;
    final private int WINDOW_SIDE_HEIGHT = 1080;


    final private int SQUARE_PLACEMENT_OFFSET_X = 864;
    final private int SQUARE_PLACEMENT_OFFSET_Y = 884;

    final private int PLAYER_SECTION_OFFSET_X = 1085;
    final private int PLAYER_SECTION_OFFSET_Y = 5;

    final private int FULL_SCREEN_OFFSET = 20;

    final private int CARD_PANEL_X = 25 + PLAYER_SECTION_OFFSET_X;

    final private int CARD_PANEL_Y = 340 - PLAYER_SECTION_OFFSET_Y;

    public int playerCashValue;


    public int AmountOfCardsInCardPanel = 0;

    public int playerCashValue;


    JFrame windowFrame;
    JPanel gameContents;
    JPanel cardsPanel;
    JPanel cashPanel;
    JPanel playerTurnPanel;
    JPanel mouseHoverInfoPanel;

    JPanel[] verticalFieldsArray;
    JPanel[] horizontalFieldsArray;
    JPanel[] squareFieldsArray;

    JButton endRound;
    JButton buyPropertyButton;

    JPanel inGameButtonPanel;

    JPanel[][] pawnPanel;

    CardOnCardPanelPanel[] cardsOnCardPanelPanel;

    DiceRoll diceRollPanel;
    JLabel playerCash;



    JPanel test;

    int[] facultyPrices;
    String[] facultyNames;

    Border blackline = BorderFactory.createLineBorder(Color.black);

    int counter;
    PrintWriter fromClient;

    GamingWindow(PrintWriter fromClient) throws IOException {
        this.fromClient = fromClient;
        windowFrame = new JFrame();
        gameContents = new JPanel();
        cardsPanel = new JPanel();
        test = new JPanel();
        cardsOnCardPanelPanel = new CardOnCardPanelPanel[15];
        inGameButtonPanel = new JPanel();
        cashPanel = new JPanel();
        playerTurnPanel = new JPanel();
        mouseHoverInfoPanel = new JPanel();


        counter = 0;

        facultyPrices = new int[32];
        facultyNames = new String[32];
        initializeFacultyPricesAndNames();

        counter=0;

        pawnPanel = new JPanel[32][4];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 4; j++) {
                pawnPanel[i][j] = new JPanel();
            }
        }


        windowFrame.setUndecorated(true);
        gameContents.setPreferredSize(new Dimension(WINDOW_SIDE_LENGTH, WINDOW_SIDE_HEIGHT));
        windowFrame.getContentPane().add(gameContents);
        windowFrame.pack();

        windowFrame.setTitle("Polipoly");
        windowFrame.setLayout(null);
        windowFrame.setVisible(true);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setResizable(false);


        gameContents.setBackground(new Color(0xe3dec4));
        gameContents.setVisible(true);
        gameContents.setLayout(null);

        verticalFieldsArray = new JPanel[14];
        horizontalFieldsArray = new JPanel[14];
        squareFieldsArray = new JPanel[4];

        paintCornerFields(windowFrame, squareFieldsArray);
        paintRectangularFields();
        paintPlayerSection();
        windowFrame.add(gameContents);

        windowFrame.invalidate();
        windowFrame.validate();
        windowFrame.repaint();
    }


    private void paintRectangularFields() {
        int rectangularFieldOffset = SQUARE_FIELDS_SIDE_LENGTH + FULL_SCREEN_OFFSET;
        for (int i = 0; i < (RECTANGULAR_FIELDS_AMOUNT / 2); i += 1) {
            horizontalFieldsArray[i] = new JPanel();
            verticalFieldsArray[i] = new JPanel();


            if (i % 2 == 0) {
                horizontalFieldsArray[i].setBounds(
                        rectangularFieldOffset,
                        FULL_SCREEN_OFFSET,
                        RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH,
                        RECTANGULAR_FIELDS_LONGER_SIDE_LENGTH
                );

                verticalFieldsArray[i].setBounds(
                        FULL_SCREEN_OFFSET,
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
                        SQUARE_PLACEMENT_OFFSET_X + FULL_SCREEN_OFFSET,
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


            int help = 0;

            switch (i) {

                case 2, 3, 6, 7, 8, 11:
                    int help_image = 0;

               

                    JPanel imagePanel = new JPanel();

                    String imagePath1 = "assets\\grey.png";
                    String imagePath21 = "assets\\white.png";
//                    if (i == 2 || i == 3) imagePath1 = "assets\\question_mark.jpg";
//                    if (i == 6 || i == 7) imagePath1 = "assets\\dorm.jpg";
//                    if (i == 11) imagePath1 = "assets\\dollar.png";
                    VerticalOtherCard.makeImagePanel(imagePanel, imagePath1);
                    VerticalOtherCard.makePawnPanel(pawnPanel[counter], imagePath21);

                    imagePanel.setLayout(new OverlayLayout(imagePanel));

                    for (int j = 0; j < 4; j++) {
                        horizontalFieldsArray[i].add(pawnPanel[counter][j]);
                    }
                    horizontalFieldsArray[i].add(imagePanel);

                    horizontalFieldsArray[i].setBorder(blackline);


                    JPanel imagePanel2 = new JPanel();


                    String imagePath12 = "assets\\white.png";
                    String imagePath22 = "assets\\white.png";

                    HorizontalOtherCard.makeImagePanel(imagePanel2, imagePath12);
                    HorizontalOtherCard.makePawnPanel(pawnPanel[counter + 14], imagePath21);

                    imagePanel2.setLayout(new OverlayLayout(imagePanel2));

                    for (int j = 0; j < 4; j++) {
                        verticalFieldsArray[i].add(pawnPanel[counter + 14][j]);
                    }
                    verticalFieldsArray[i].add(imagePanel2);

                    verticalFieldsArray[i].setBorder(blackline);
                    if (i == 8 || i == 11) {
                        help = 1;
                    } else {
                        break;
                    }



                case 0, 1, 4, 5, 9, 10, 12, 13: {
                    if (help == 0) {

                        JPanel uponPanel = new JPanel(new GridBagLayout());
                        JPanel namePanel = new JPanel(new GridBagLayout());

                        JPanel[] housePanel = new JPanel[3];
                        for (int j = 0; j < 3; j++) {
                            housePanel[j] = new JPanel();
                        }
                        String imagePath = "assets\\white.png";

                        switch (i) {
                            case 9:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[27], Color.GREEN);
                                /* Inst. Fermentacji 320 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[27]);
                                break;
                            case 13:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[25], Color.GREEN);
                                /* Inst. Biotechnologii 300 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[25]);
                                break;
                            case 1:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[31], Color.gray);
                                /* Inst. Marketingu 400 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[31]);
                                break;
                            case 5:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[29], Color.gray);
                                /* Inst. Zarządzania 350 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[29]);
                                break;
                            case 0:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[9], Color.yellow);
                                /* Inst. Materiałoznawstwa 140 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[9]);
                                break;
                            case 4:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[11], Color.yellow);
                                /* Kat. Technologii Dziewiarskich 160 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[11]);
                                break;
                            case 10:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[14], Color.BLUE);
                                /* Kat. Budownictwa Betonowego 180 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[14]);
                                break;
                            case 12:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[15], Color.BLUE);
                                /* Inst. Architektury 200 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[15]);
                                break;
                        }

//                        VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2));
//                        VerticalFacultyCard.makeNamePanel(namePanel, (i % 2));
                        VerticalFacultyCard.makePawnPanel(pawnPanel[counter], imagePath);
                        //VerticalFacultyCard.makeHousePanel(housePanel, imagePath, (i % 2));
                        horizontalFieldsArray[i].add(uponPanel);
                        horizontalFieldsArray[i].add(namePanel);
                        for (int j = 0; j < 4; j++) {
                            horizontalFieldsArray[i].add(pawnPanel[counter][j]);
                        }
                        for (int j = 0; j < 3; j++) {
                            horizontalFieldsArray[i].add(housePanel[j]);
                        }
                    }


                    verticalFieldsArray[i].setLayout(null);
                    JPanel uponPanel2 = new JPanel(new GridBagLayout());
                    JPanel namePanel2 = new JPanel(new GridBagLayout());
                    JPanel[] housePanel2 = new JPanel[3];
                    for (int j = 0; j < 3; j++) {
                        housePanel2[j] = new JPanel();
                    }
                    String imagePath2 = "assets\\white.png";



                    switch (i) {
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

//                            case 10:
//                                JPanel imagePanel3 = new JPanel();
//                                String imagePath10 = "assets\\grey.png";
//                                HorizontalOtherCard.makeImagePanel(imagePanel3,imagePath10);
//                                HorizontalOtherCard.makePawnPanel(pawnPanel[counter+14], imagePath2);
//                                verticalFieldsArray[i].add(imagePanel3);
//                                for(int j=0; j<4; j++){
//                                    verticalFieldsArray[i].add(pawnPanel[counter+14][j]);
//                                    break;
                    }


//                        HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 500, Color.green);
//                        HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "TEST");

                    HorizontalFacultyCard.makePawnPanel(pawnPanel[counter + 14], imagePath2);


                    //HorizontalFacultyCard.makeHousePanel(housePanel2, imagePath2, (i % 2));
                    verticalFieldsArray[i].add(uponPanel2);
                    verticalFieldsArray[i].add(namePanel2);
                    for (int j = 0; j < 4; j++) {

                        verticalFieldsArray[i].add(pawnPanel[counter + 14][j]);


                    }
                    for (int j = 0; j < 3; j++) {
                        verticalFieldsArray[i].add(housePanel2[j]);
                    }
                }
                help = 0;
                break;
            }


            windowFrame.add(horizontalFieldsArray[i]);
            windowFrame.add(verticalFieldsArray[i]);

            counter++;
            if (i % 2 == 0) {
                rectangularFieldOffset += RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH;
            }
        }
    }


    void paintCornerFields(JFrame windowFrame, JPanel[] squareFieldsArray) {
        for (int i = 0; i < SQUARE_FIELDS_AMOUNT; i++) {
            squareFieldsArray[i] = new JPanel();
            switch (i) {


                case 0 -> squareFieldsArray[i].setBounds(FULL_SCREEN_OFFSET, FULL_SCREEN_OFFSET, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 1 ->
                        squareFieldsArray[i].setBounds(FULL_SCREEN_OFFSET, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 2 ->
                        squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X + FULL_SCREEN_OFFSET, FULL_SCREEN_OFFSET, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                case 3 ->
                        squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X + FULL_SCREEN_OFFSET, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);

            }

            squareFieldsArray[i].setVisible(true);
            squareFieldsArray[i].setBorder(blackline);

            squareFieldsArray[i].setLayout(null);

            JPanel imagePanel = new JPanel();

            String imagePath1 = "assets\\grey.png";
            String imagePath2 = "assets\\white.png";
            CornerCard.makeImagePanel(imagePanel, imagePath1);

            CornerCard.makePawnPanel(pawnPanel[28 + i], imagePath2);


            imagePanel.setLayout(new OverlayLayout(imagePanel));

            for (int j = 0; j < 4; j++) {
                squareFieldsArray[i].add(pawnPanel[28 + i][j]);
            }
            squareFieldsArray[i].add(imagePanel);

            squareFieldsArray[i].setBorder(blackline);

//            squareFieldsArray[i].setBackground(Color.green);

            windowFrame.add(squareFieldsArray[i]);
        }
    }




    public void paintPlayerSection() throws IOException {

        generateCardsPanel(cardsPanel);
        generateCashPanel(cashPanel);
        generatePlayerTurnPanel(playerTurnPanel);
        generateMouseHoverInfoPanel(mouseHoverInfoPanel);

        generateinGameButtonPanel(inGameButtonPanel);

        fillCardsPanel(cardsOnCardPanelPanel);

        addCardToPanel(Color.BLUE,"");
        addCardToPanel(Color.RED,"");
        addCardToPanel(Color.RED,"");


        windowFrame.add(cardsPanel);

        windowFrame.add(cashPanel);

        windowFrame.add(playerTurnPanel);
        windowFrame.add(mouseHoverInfoPanel);
        windowFrame.add(inGameButtonPanel);





        diceRollPanel = new DiceRoll(fromClient);
        Rectangle diceRollPanelRectangle = new Rectangle(470, 430 - PLAYER_SECTION_OFFSET_Y, DiceRoll.DICE_SIZE, DiceRoll.DICE_SIZE);
        diceRollPanel.setBounds(diceRollPanelRectangle);
        windowFrame.add(diceRollPanel);




    }

    public void fillCardsPanel(CardOnCardPanelPanel[] cardsOnCardPanelPanel) {

        int cardX = CARD_PANEL_X+18;
        int cardY = CARD_PANEL_Y+22;
        int xOffset = 0; //45
        int yOffset = 0; //36

        for(int i=0;i<=14;i++){

            if(i==5 || i==10){
                xOffset = 0;
                yOffset += (36 + 165);
            }

            Rectangle cardsOnCardPanelPanelRectangle = new Rectangle(cardX + xOffset, cardY + yOffset, 100, 165);
            cardsOnCardPanelPanel[i] = new CardOnCardPanelPanel();
            cardsOnCardPanelPanel[i].setVisible(true);
            cardsOnCardPanelPanel[i].setBounds(cardsOnCardPanelPanelRectangle);
            cardsOnCardPanelPanel[i].setBackground(Color.white);
            cardsOnCardPanelPanel[i].setOpaque(true);
            cardsOnCardPanelPanel[i].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

            cardsOnCardPanelPanel[i].setVisible(false);
            windowFrame.add(cardsOnCardPanelPanel[i]);
            xOffset+=(45 + 100);
        }
    }

    public void generateCardsPanel(JPanel cardsPanel) throws IOException {
        Rectangle cardsPanelRectangle = new Rectangle(CARD_PANEL_X, 340 - PLAYER_SECTION_OFFSET_Y, 725, 614);
        cardsPanel.setBounds(cardsPanelRectangle);
        BufferedImage backgroundImage = ImageIO.read(new File("assets\\karciochy.png"));
        Image scaledImage = backgroundImage.getScaledInstance(cardsPanel.getWidth(), cardsPanel.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        cardsPanel.add(backgroundLabel);
        cardsPanel.setOpaque(false); // Ustawienie przezroczystości tła panelu
    }

    public void generateCashPanel(JPanel cashPanel) throws IOException {
        Rectangle cashPanelRectangle = new Rectangle(450 + PLAYER_SECTION_OFFSET_X, 25 - PLAYER_SECTION_OFFSET_Y, 300, 50);
        cashPanel.setBounds(cashPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\manymanymany.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        playerCashValue = 0;
        playerCash = new JLabel();
        playerCash.setText(Integer.toString(playerCashValue));
        playerCash.setFont(new Font("Calibri", Font.BOLD, 20));
        playerCash.setAlignmentX(Component.CENTER_ALIGNMENT);

        //   cashPanel.add(picLabel);
        cashPanel.add(playerCash);
        cashPanel.setBorder(blackline);
        cashPanel.setBackground(Color.black);
//        cashPanel.setBackground(Color.CYAN);
    }

    private void generatePlayerTurnPanel(JPanel playerTurnPanel) throws IOException {
        Rectangle playerTurnPanelRectangle = new Rectangle(25 + PLAYER_SECTION_OFFSET_X, 25 - PLAYER_SECTION_OFFSET_Y, 400, 50);
        playerTurnPanel.setBounds(playerTurnPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\turagracz.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        playerTurnPanel.add(picLabel);
        playerTurnPanel.setBorder(blackline);
        playerTurnPanel.setBackground(Color.black);
//        playerTurnPanel.setBackground(Color.CYAN);
    }

    private void generateMouseHoverInfoPanel(JPanel mouseHoverInfoPanel) throws IOException {
        Rectangle mouseHoverInfoPanelRectangle = new Rectangle(25 + PLAYER_SECTION_OFFSET_X, 100 - PLAYER_SECTION_OFFSET_Y, 725, 220);
        mouseHoverInfoPanel.setBounds(mouseHoverInfoPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\info.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        mouseHoverInfoPanel.add(picLabel);
        mouseHoverInfoPanel.setBorder(blackline);
        mouseHoverInfoPanel.setBackground(Color.black);
//        mouseHoverInfoPanel.setBackground(Color.CYAN);
    }

    private void generateinGameButtonPanel(JPanel inGameButtonPanel) {
        Rectangle inGameButtonPanelRectangle = new Rectangle(25 + PLAYER_SECTION_OFFSET_X, 950, 725, 50);

        inGameButtonPanel.setBounds(inGameButtonPanelRectangle);
        inGameButtonPanel.setOpaque(false);


        buyPropertyButton = MenuWindow.standardButtonGenerate("Zakup");
        endRound = MenuWindow.standardButtonGenerate("koniec tury");
        buyPropertyButton.addActionListener(back -> {
            fromClient.println("Buy");
        });
        endRound.addActionListener(back -> {
            fromClient.println("next");
        });

        /*leaveFromGameButton.addActionListener(
                leaveGame -> System.exit(0));*/

        inGameButtonPanel.add(buyPropertyButton);
        inGameButtonPanel.add(endRound);
    }


    public void addCardToPanel(Color colour,String text){




        cardsOnCardPanelPanel[AmountOfCardsInCardPanel].setString("szymon");
        cardsOnCardPanelPanel[AmountOfCardsInCardPanel].topColorLabel.setBackground(colour);
        cardsOnCardPanelPanel[AmountOfCardsInCardPanel].midColorLabel.setBackground(colour.darker());
        cardsOnCardPanelPanel[AmountOfCardsInCardPanel].setVisible(true);



        AmountOfCardsInCardPanel++;
    }




    private void initializeFacultyPricesAndNames(){
        facultyPrices[1] = 60;
        facultyNames[1] = "Mat";
        facultyPrices[3] = 60;
        facultyNames[3] = "Fiz";
        facultyPrices[4] = 200; //akademik nr1
        facultyPrices[5] = 100;
        facultyNames[5] = "Inst. Fizyki Molekularnej";
        facultyPrices[7] = 120;
        facultyNames[7] = "Inst. Chemii Organicznej";
        facultyPrices[9] = 140;
        facultyNames[9] = "Inst. Materiałoznawstwa";
        facultyPrices[11] = 160;
        facultyNames[11] = "Kat. Technologii Dziewiarskich";
        facultyPrices[12] = 200; //akademik nr2
        facultyPrices[14] = 180;
        facultyNames[14] = "Kat. Budownictwa Betonowego";
        facultyPrices[15] = 200;
        facultyNames[15] = "Inst. Architektury";
        facultyPrices[17] = 220;
        facultyNames[17] = "Inst. Obrabiarek";
        facultyPrices[19] = 240;
        facultyNames[19] = "Kat. Wytrzymałości Materiałów";
        facultyPrices[20] = 200; //akademik nr3
        facultyPrices[21] = 260;
        facultyNames[21] = "DMCS";
        facultyPrices[22] = 260;
        facultyNames[22] = "CTI";
        facultyPrices[23] = 280;
        facultyNames[23] = "IMSI";
        facultyPrices[25] = 300;
        facultyNames[25] = "Inst. Biotechnologii";
        facultyPrices[27] = 320;
        facultyNames[27] = "Inst. Fermentacji";
        facultyPrices[28] = 200; //akademik nr4
        facultyPrices[29] = 350;
        facultyNames[29] = "Inst. Zarządzania";
        facultyPrices[31] = 400;
        facultyNames[31] = "Inst. Marketingu";
    }


    private void initializeFacultyPricesAndNames(){
        facultyPrices[1] = 60;
        facultyNames[1] = "Mat";
        facultyPrices[3] = 60;
        facultyNames[3] = "Fiz";
        facultyPrices[4] = 200; //akademik nr1
        facultyPrices[5] = 100;
        facultyNames[5] = "Inst. Fizyki Molekularnej";
        facultyPrices[7] = 120;
        facultyNames[7] = "Inst. Chemii Organicznej";
        facultyPrices[9] = 140;
        facultyNames[9] = "Inst. Materiałoznawstwa";
        facultyPrices[11] = 160;
        facultyNames[11] = "Kat. Technologii Dziewiarskich";
        facultyPrices[12] = 200; //akademik nr2
        facultyPrices[14] = 180;
        facultyNames[14] = "Kat. Budownictwa Betonowego";
        facultyPrices[15] = 200;
        facultyNames[15] = "Inst. Architektury";
        facultyPrices[17] = 220;
        facultyNames[17] = "Inst. Obrabiarek";
        facultyPrices[19] = 240;
        facultyNames[19] = "Kat. Wytrzymałości Materiałów";
        facultyPrices[20] = 200; //akademik nr3
        facultyPrices[21] = 260;
        facultyNames[21] = "DMCS";
        facultyPrices[22] = 260;
        facultyNames[22] = "CTI";
        facultyPrices[23] = 280;
        facultyNames[23] = "IMSI";
        facultyPrices[25] = 300;
        facultyNames[25] = "Inst. Biotechnologii";
        facultyPrices[27] = 320;
        facultyNames[27] = "Inst. Fermentacji";
        facultyPrices[28] = 200; //akademik nr4
        facultyPrices[29] = 350;
        facultyNames[29] = "Inst. Zarządzania";
        facultyPrices[31] = 400;
        facultyNames[31] = "Inst. Marketingu";
    }
}

