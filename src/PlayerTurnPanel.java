import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PlayerTurnPanel extends JPanel {


    PlayerTurnPanel(int x, int y) throws IOException {
        Rectangle playerTurnPanelRectangle = new Rectangle(x, y,400, 50);
        setBounds(playerTurnPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\turagracz.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        add(picLabel);
        setBackground(Color.black);
    }
}
