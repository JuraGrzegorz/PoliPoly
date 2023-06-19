import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
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


    JFrame windowFrame;
    JPanel gameContents;
    JPanel cardsPanel;
    JPanel cashPanel;
    JPanel playerTurnPanel;
    HoverInfoPanel mouseHoverInfoPanel;
    JPanel InfoPanel;

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
    Color[] facultyColor;

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
        mouseHoverInfoPanel = new HoverInfoPanel(25 + PLAYER_SECTION_OFFSET_X, 100 - PLAYER_SECTION_OFFSET_Y);
        InfoPanel = new JPanel();


        counter = 0;

        facultyPrices = new int[32];
        facultyNames = new String[32];
        facultyColor = new Color[32];
        initializeFacultyPricesAndNames();

        counter = 0;

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

        ImageIcon icon = new ImageIcon("assets\\icon.png");
        windowFrame.setIconImage(icon.getImage());

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
            int help_10=0;

            switch (i) {

                case 2, 3, 6, 7, 8, 10, 11:
                    int help_image = 0;
                    JPanel imagePanel = new JPanel();

                    String imagePath1 = "assets\\CS.png";
                    String imagePath21 = "assets\\mintus.png";
                    if (i == 2 || i == 3) imagePath1 = "assets\\Red_chance.png";
                    if (i == 6 || i == 7) imagePath1 = "assets\\dorm_down.png";
                    if (i == 11) imagePath1 = "assets\\blue_chance.png";

                    if(i!=10) {

                        VerticalOtherCard.makeImagePanel(imagePanel, imagePath1);
                        VerticalOtherCard.makePawnPanel(pawnPanel[counter], imagePath21);

                        imagePanel.setLayout(new OverlayLayout(imagePanel));


                        for (int j = 0; j < 4; j++) {
                            horizontalFieldsArray[i].add(pawnPanel[counter][j]);
                        }
                        horizontalFieldsArray[i].add(imagePanel);

                        horizontalFieldsArray[i].setBorder(blackline);
                    }


                    JPanel imagePanel2 = new JPanel();


                    String imagePath12 = "";
                    String imagePath22 = "assets\\mintus.png";

                    if(i==2){
                        imagePath12 = "assets\\Blue_chance_rotated.png";
                    }
                    if(i==3){
                        imagePath12 = "assets\\tax.png";
                    }
                    if(i==6){
                        imagePath12 = "assets\\dorm_left.png";
                    }
                    if(i==7){
                        imagePath12 = "assets\\dorm_right.png";
                    }
                    if(i==10){
                        imagePath12 = "assets\\CJ.png";
                    }


                    HorizontalOtherCard.makeImagePanel(imagePanel2, imagePath12);
                    HorizontalOtherCard.makePawnPanel(pawnPanel[counter + 14], imagePath21, i%2);

                    imagePanel2.setLayout(new OverlayLayout(imagePanel2));

                    for (int j = 0; j < 4; j++) {
                        verticalFieldsArray[i].add(pawnPanel[counter + 14][j]);
                    }
                    verticalFieldsArray[i].add(imagePanel2);

                    verticalFieldsArray[i].setBorder(blackline);
                    if (i == 8 || i == 11  || i==10) {
                        if(i!=10) help=1;
                    } else {
                        break;
                    }


                case 0, 1, 4, 5, 9, 12, 13: {
                    if (help == 0) {
                        JPanel uponPanel = new JPanel(new GridBagLayout());
                        JPanel namePanel = new JPanel(new GridBagLayout());

                        JPanel[] housePanel = new JPanel[3];
                        for (int j = 0; j < 3; j++) {
                            housePanel[j] = new JPanel();
                        }
                        String imagePath = "assets\\mintus.png";

                        switch (i) {
                            case 9:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[27], facultyColor[27]);
                                /* Inst. Fermentacji 320 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[27]);
                                break;
                            case 13:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[25], facultyColor[25]);
                                /* Inst. Biotechnologii 300 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[25]);
                                break;
                            case 1:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[31], facultyColor[31]);
                                /* Inst. Marketingu 400 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[31]);
                                break;
                            case 5:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[29], facultyColor[29]);
                                /* Inst. Zarządzania 350 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[29]);
                                break;
                            case 0:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[9], facultyColor[9]);
                                /* Inst. Materiałoznawstwa 140 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[9]);
                                break;
                            case 4:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[11], facultyColor[11]);
                                /* Kat. Technologii Dziewiarskich 160 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[11]);
                                break;

                            case 12:
                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[15], facultyColor[15]);

                                /* Inst. Architektury 200 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[15]);
                                break;

                            case 10:

                                VerticalFacultyCard.makeUponPanel(uponPanel, (i % 2), facultyPrices[14], facultyColor[14]);
                                /* Kat. Budownictwa Betonowego 180 */
                                VerticalFacultyCard.makeNamePanel(namePanel, (i % 2), facultyNames[14]);
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

                        if(i==10) break;
                    }


                    verticalFieldsArray[i].setLayout(null);
                    verticalFieldsArray[i].setBackground(Color.white);
                    JPanel uponPanel2 = new JPanel(new GridBagLayout());
                    JPanel namePanel2 = new JPanel(new GridBagLayout());
                    JPanel[] housePanel2 = new JPanel[3];
                    for (int j = 0; j < 3; j++) {
                        housePanel2[j] = new JPanel();
                    }
                    String imagePath2 = "assets\\mintus.png";

                    switch (i) {
                        case 9:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[21], facultyColor[21]);
                           /* DMCS 260 */
                             HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[21]);
                            break;
                        case 13:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[23], facultyColor[21]);
                                     /* IMSI 280*/
                            HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[23]);
                            break;
                        case 1:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[17], facultyColor[19]);
                               /* Inst. Obrabiarek 220 */
                            HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[17]);
                            break;
                        case 5:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[19], facultyColor[19]);
                                  /* Kat. Wyrzymałości Materiałów 240 */
                            HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[19]);
                            break;
                        case 0:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[7], facultyColor[5]);
                                /* Inst. Chemii Organicznej 100 */
                            HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[7]);
                            break;
                        case 4:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[5], facultyColor[5]);
                           /* Kat. Fizyki Molekularnej 100 */
                            HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[5]);
                            break;
                        case 8:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[3], facultyColor[1]);
                              /* Fiz 60 */
                            HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[3]);;
                            break;
                        case 12:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[1], facultyColor[1]);
                            /* Mat, 60 */
                            HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[1]);
                            break;
                        case 11:
                            HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), facultyPrices[22], facultyColor[21]);
                           /* CTI 260 */
                            HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), facultyNames[22]);

                            break;
                      case 10:
                                JPanel imagePanel3 = new JPanel();
                                String imagePath10 = "assets\\CJ.png";
                                HorizontalOtherCard.makeImagePanel(imagePanel3,imagePath10);
                                verticalFieldsArray[i].add(imagePanel3);
//                                for(int j=0; j<4; j++){
//                                    verticalFieldsArray[i].add(pawnPanel[counter+14][j]);
//                                    break;
                    }



//                        HorizontalFacultyCard.makeUponPanel(uponPanel2, (i % 2), 500, Color.green);
//                        HorizontalFacultyCard.makeNamePanel(namePanel2, (i % 2), "TEST");

                    HorizontalFacultyCard.makePawnPanel(pawnPanel[counter + 14], imagePath2, i%2);


                    //HorizontalFacultyCard.makeHousePanel(housePanel2, imagePath2, (i % 2));
                    verticalFieldsArray[i].add(uponPanel2);
                    verticalFieldsArray[i].add(namePanel2);
                    verticalFieldsArray[i].setBackground(Color.white);
                    for (int j = 0; j < 4; j++) {

                        verticalFieldsArray[i].add(pawnPanel[counter + 14][j]);


                    }
                    for (int j = 0; j < 3; j++) {
                        verticalFieldsArray[i].add(housePanel2[j]);
                    }
                }
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


    void paintCornerFields(JFrame windowFrame, JPanel[] squareFieldsArray) throws IOException {


        for (int i = 0; i < SQUARE_FIELDS_AMOUNT; i++) {
            squareFieldsArray[i] = new JPanel();
            String imagePath2 = "assets\\mintus.png";


            CornerCard.makePawnPanel(pawnPanel[28 + i], imagePath2);


            for (int j = 0; j < 4; j++) {
                squareFieldsArray[i].add(pawnPanel[28 + i][j]);

                switch (i) {
                    case 0 -> squareFieldsArray[i].setBounds(FULL_SCREEN_OFFSET, FULL_SCREEN_OFFSET, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                    case 1 -> squareFieldsArray[i].setBounds(FULL_SCREEN_OFFSET, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                    case 2 -> squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X + FULL_SCREEN_OFFSET, FULL_SCREEN_OFFSET, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                    case 3 -> squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X + FULL_SCREEN_OFFSET, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                }

            }

            squareFieldsArray[i].setBorder(blackline);

            windowFrame.add(squareFieldsArray[i]);

            switch (i) {

                case 0 -> {
                    squareFieldsArray[i].setBounds(FULL_SCREEN_OFFSET, FULL_SCREEN_OFFSET, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                    JPanel imagePanel = new JPanel();
                    String imagePath1 = "assets\\Wiezienie.png";
                    CornerCard.makeImagePanel(imagePanel, imagePath1);
                    imagePanel.setLayout(new OverlayLayout(imagePanel));
                    squareFieldsArray[i].add(imagePanel);


                }

                case 1 -> {
                    squareFieldsArray[i].setBounds(FULL_SCREEN_OFFSET, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                    JPanel imagePanel = new JPanel();
                    String imagePath1 = "assets\\Start.png";
                    CornerCard.makeImagePanel(imagePanel, imagePath1);
                    imagePanel.setLayout(new OverlayLayout(imagePanel));
                    imagePanel.setOpaque(true);
                    squareFieldsArray[i].add(imagePanel);
                }


                case 2 -> {
                    squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X + FULL_SCREEN_OFFSET, FULL_SCREEN_OFFSET, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                    JPanel imagePanel = new JPanel();
                    String imagePath1 = "assets\\Parking.png";
                    CornerCard.makeImagePanel(imagePanel, imagePath1);
                    imagePanel.setLayout(new OverlayLayout(imagePanel));
                    squareFieldsArray[i].add(imagePanel);
                }

                case 3 -> {
                    squareFieldsArray[i].setBounds(SQUARE_PLACEMENT_OFFSET_X + FULL_SCREEN_OFFSET, SQUARE_PLACEMENT_OFFSET_Y, SQUARE_FIELDS_SIDE_LENGTH, SQUARE_FIELDS_SIDE_LENGTH);
                    JPanel imagePanel = new JPanel();
                    String imagePath1 = "assets\\Do_wiezienia.png";
                    CornerCard.makeImagePanel(imagePanel, imagePath1);
                    imagePanel.setLayout(new OverlayLayout(imagePanel));
                    squareFieldsArray[i].add(imagePanel);
                }


            }

            squareFieldsArray[i].setVisible(true);
            //squareFieldsArray[i].setBackground(Color.white);
            squareFieldsArray[i].setBorder(blackline);

            squareFieldsArray[i].setLayout(null);


        }
    }

    public void paintPlayerSection() throws IOException {

        generateCardsPanel(cardsPanel);
        generateCashPanel(cashPanel);
        generatePlayerTurnPanel(playerTurnPanel);
        generateInfoPanel(InfoPanel);

        generateinGameButtonPanel(inGameButtonPanel);

        fillCardsPanel(cardsOnCardPanelPanel);

        windowFrame.add(cardsPanel);

        windowFrame.add(cashPanel);

        windowFrame.add(playerTurnPanel);
        windowFrame.add(mouseHoverInfoPanel);
        windowFrame.add(InfoPanel);
        windowFrame.add(inGameButtonPanel);



        diceRollPanel = new DiceRoll(fromClient);
        Rectangle diceRollPanelRectangle = new Rectangle(470, 430 - PLAYER_SECTION_OFFSET_Y, DiceRoll.DICE_SIZE, DiceRoll.DICE_SIZE);
        diceRollPanel.setBounds(diceRollPanelRectangle);
        windowFrame.add(diceRollPanel);


    }

    public void fillCardsPanel(CardOnCardPanelPanel[] cardsOnCardPanelPanel) {

        int cardX = CARD_PANEL_X + 18;
        int cardY = CARD_PANEL_Y + 22;
        int xOffset = 0; //45
        int yOffset = 0; //36

        for (int i = 0; i <= 14; i++) {

            if (i == 5 || i == 10) {
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
            xOffset += (45 + 100);
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

    private void generateInfoPanel(JPanel InfoPanel) throws IOException {
        Rectangle InfoPanelRectangle = new Rectangle(25 + PLAYER_SECTION_OFFSET_X, 100 - PLAYER_SECTION_OFFSET_Y, 725, 220);
        InfoPanel.setBounds(InfoPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\info.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        InfoPanel.add(picLabel);
        InfoPanel.setBorder(blackline);
        InfoPanel.setBackground(Color.black);
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


    public void addCardToPanel(Color colour, String text) {


        cardsOnCardPanelPanel[AmountOfCardsInCardPanel].setString(text);
        cardsOnCardPanelPanel[AmountOfCardsInCardPanel].topColorLabel.setBackground(colour);
        if (colour.equals(Color.white)) {
            cardsOnCardPanelPanel[AmountOfCardsInCardPanel].midColorLabel.setBackground(colour);
        } else {
            cardsOnCardPanelPanel[AmountOfCardsInCardPanel].midColorLabel.setBackground(colour.darker());
        }

        cardsOnCardPanelPanel[AmountOfCardsInCardPanel].setVisible(true);


        AmountOfCardsInCardPanel++;
    }




    private void initializeFacultyPricesAndNames() {
        facultyPrices[1] = 60;
        facultyNames[1] = "Mat";
        facultyColor[1] = new Color(0xc6a995);

        facultyPrices[3] = 60;
        facultyNames[3] = "Fiz";
        facultyColor[3] = new Color(0xc6a995);

        facultyPrices[4] = 200; //akademik nr1
        facultyNames[4] = "DS8";
        facultyColor[4] = Color.WHITE;


        facultyPrices[5] = 100;
        facultyNames[5] = "Inst. Fizyki Molekularnej";
        facultyColor[5] = new Color(0xd0474c);

        facultyPrices[7] = 120;
        facultyNames[7] = "Inst. Chemii Organicznej";
        facultyColor[7] = new Color(0xd0474c);

        facultyPrices[9] = 140;
        facultyNames[9] = "Inst. Materiałoznawstwa";
        facultyColor[9] = new Color(0xf7ee40);


        facultyPrices[11] = 160;
        facultyNames[11] = "Kat. Technologii Dziewiarskich";
        facultyColor[11] = new Color(0xf7ee40);

        facultyPrices[12] = 200; //akademik nr2
        facultyNames[12] = "DS4";
        facultyColor[12] = Color.WHITE;

        facultyPrices[14] = 180;
        facultyNames[14] = "Kat. Budownictwa Betonowego";
        facultyColor[14] = new Color(0xfb8ced);

        facultyPrices[15] = 200;
        facultyNames[15] = "Inst. Architektury";
        facultyColor[15] = new Color(0xfb8ced);

        facultyPrices[17] = 220;
        facultyNames[17] = "Inst. Obrabiarek";
        facultyColor[17] = new Color(0x6abbd6);

        facultyPrices[19] = 240;
        facultyNames[19] = "Kat. Wytrzymałości Materiałów";
        facultyColor[19] = new Color(0x6abbd6);

        facultyPrices[20] = 200; //akademik nr3
        facultyNames[20] = "DS7";
        facultyColor[20] = Color.WHITE;

        facultyPrices[21] = 260;
        facultyNames[21] = "DMCS";
        facultyColor[21] = new Color(0xec813a);

        facultyPrices[22] = 260;
        facultyNames[22] = "CTI";
        facultyColor[22] = new Color(0xec813a);

        facultyPrices[23] = 280;
        facultyNames[23] = "IMSI";
        facultyColor[23] = new Color(0xec813a);

        facultyPrices[25] = 300;
        facultyNames[25] = "Inst. Biotechnologii";
        facultyColor[25] = new Color(0x4daa58);

        facultyPrices[27] = 320;
        facultyNames[27] = "Inst. Fermentacji";
        facultyColor[27] = new Color(0x4daa58);

        facultyPrices[28] = 200; //akademik nr4
        facultyNames[28] = "DS1";
        facultyColor[28] = Color.WHITE;

        facultyPrices[29] = 350;
        facultyNames[29] = "Inst. Zarządzania";
        facultyColor[29] = Color.gray;

        facultyPrices[31] = 400;
        facultyNames[31] = "Inst. Marketingu";
        facultyColor[31] = Color.gray;
    }
}

