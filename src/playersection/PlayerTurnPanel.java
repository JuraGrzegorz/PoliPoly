import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class PlayerTurnPanel extends JPanel {

    private final JLabel textLabel;

    PlayerTurnPanel(int x, int y) throws IOException {
        Rectangle playerTurnPanelRectangle = new Rectangle(x, y, 400, 50);
        setBounds(playerTurnPanelRectangle);
        BufferedImage myPicture = ImageIO.read(new File("assets\\turagracz.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setLayout(new BorderLayout());

        textLabel = new JLabel("default");
        textLabel.setForeground(Color.WHITE);
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        textLabel.setFont(new Font("Calibri", Font.BOLD, 30));

        picLabel.add(textLabel, BorderLayout.CENTER);

        add(picLabel);
        setBackground(Color.BLACK);
    }

    public void setPlayername(String message) {
        textLabel.setText(message);
    }


}
