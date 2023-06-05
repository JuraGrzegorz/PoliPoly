import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

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

    Border blackline = BorderFactory.createLineBorder(Color.black);

    GamingWindow(){
        JFrame windowFrame = new JFrame();
        JPanel gameContents = new JPanel();
        gameContents.setPreferredSize(new Dimension(1080,1080));
        windowFrame.getContentPane().add(gameContents);
        windowFrame.pack();

        windowFrame.setSize(WINDOW_SIDE_LENGTH, WINDOW_SIDE_HEIGHT);
        windowFrame.setTitle("Polipoly");
        windowFrame.setLayout(null);
        windowFrame.setVisible(true);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setResizable(false);

        gameContents.setBackground(Color.white);
        gameContents.setVisible(true);
        gameContents.setLayout(null);

        JPanel[] verticalFieldsArray = new JPanel[14];
        JPanel[] horizontalFieldsArray = new JPanel[14];
        JPanel[] squareFieldsArray = new JPanel[4];


        paintVerticalRectangularFields(windowFrame, verticalFieldsArray);
        paintCornerFields(windowFrame, squareFieldsArray);
        paintHorizontalRectangularFields(windowFrame, horizontalFieldsArray);

        windowFrame.add(gameContents);

    }

    private void paintHorizontalRectangularFields(JFrame windowFrame, JPanel[] horizontalFieldsArray) {
        int rectangularFieldOffset = SQUARE_FIELDS_SIDE_LENGTH;
        for(int i=0; i<(RECTANGULAR_FIELDS_AMOUNT/2)-1; i+=2){
            horizontalFieldsArray[i] = new JPanel();
            horizontalFieldsArray[i+1] = new JPanel();

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

            horizontalFieldsArray[i].setBackground(Color.cyan);
            horizontalFieldsArray[i+1].setBackground(Color.cyan);

            horizontalFieldsArray[i].setVisible(true);
            horizontalFieldsArray[i+1].setVisible(true);

            horizontalFieldsArray[i].setBorder(blackline);
            horizontalFieldsArray[i+1].setBorder(blackline);

            windowFrame.add(horizontalFieldsArray[i]);
            windowFrame.add(horizontalFieldsArray[i+1]);
            rectangularFieldOffset += RECTANGULAR_FIELDS_SHORTER_SIDE_LENGTH;
        }
    }


    private void paintVerticalRectangularFields(JFrame windowFrame, JPanel[] verticalFieldsArray) {
        int rectangularFieldOffset = SQUARE_FIELDS_SIDE_LENGTH;
        for(int i=0; i<(RECTANGULAR_FIELDS_AMOUNT/2)-1; i+=2){
            verticalFieldsArray[i] = new JPanel();
            verticalFieldsArray[i+1] = new JPanel();

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

            verticalFieldsArray[i].setBackground(Color.cyan);
            verticalFieldsArray[i+1].setBackground(Color.cyan);

            verticalFieldsArray[i].setVisible(true);
            verticalFieldsArray[i+1].setVisible(true);

            verticalFieldsArray[i].setBorder(blackline);
            verticalFieldsArray[i+1].setBorder(blackline);

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
}
