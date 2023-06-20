import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InfoPanel extends JPanel {

    public InfoPanel(int x, int y) throws IOException {
       // Rectangle InfoPanelRectangle = new Rectangle(25 + PLAYER_SECTION_OFFSET_X, 100 - PLAYER_SECTION_OFFSET_Y, 725, 220);

        Rectangle InfoPanelRectangle = new Rectangle(x, y, 725, 220);
        setBounds(InfoPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\info.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        add(picLabel);
        setBackground(Color.black);
    }
}
