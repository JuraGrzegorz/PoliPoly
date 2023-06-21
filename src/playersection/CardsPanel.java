import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardsPanel extends JPanel {

    public static int AmountOfCardsInCardPanel = 0;
    static CardOnCardPanelPanel[] cardsOnCardPanelPanel;


    public CardsPanel(JFrame windowFrame, int x, int y) throws IOException {
        //  Rectangle cardsPanelRectangle = new Rectangle(CARD_PANEL_X, 340 - PLAYER_SECTION_OFFSET_Y, 725, 614);
        Rectangle cardsPanelRectangle = new Rectangle(x, y, 725, 614);
        setBounds(cardsPanelRectangle);
        BufferedImage backgroundImage = ImageIO.read(new File("assets\\karciochy.png"));
        Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        add(backgroundLabel);
        setOpaque(false); // Ustawienie przezroczystości tła panelu


        cardsOnCardPanelPanel = new CardOnCardPanelPanel[15];
        fillCardsPanel(windowFrame, x, y, cardsOnCardPanelPanel);


    }

    public void fillCardsPanel(JFrame windowFrame, int x, int y, CardOnCardPanelPanel[] cardsOnCardPanelPanel) {

        int cardX = x + 18;
        int cardY = y + 22;
        int xOffset = 0; //co 45
        int yOffset = 0; //co 36

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

    public static void addCardToPanel(Color colour, String text) {


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
}