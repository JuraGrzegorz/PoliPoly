import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CashPanel extends JPanel {

    public int playerCashValue;

    static JLabel playerCash;


    public CashPanel(int x,int y) throws IOException {
        Rectangle cashPanelRectangle = new Rectangle(x, y, 300, 50);
        setBounds(cashPanelRectangle);

        BufferedImage myPicture = ImageIO.read(new File("assets\\manymanymany.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setLayout(new BorderLayout());

        playerCashValue = 0;
        playerCash = new JLabel();
        playerCash.setText(String.format("%d P$", playerCashValue*2));
        playerCash.setFont(new Font("Calibri", Font.BOLD, 30));
        playerCash.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerCash.setForeground(Color.WHITE);
        playerCash.setHorizontalAlignment(JLabel.CENTER);

        int margin = 35;
        EmptyBorder marginBorder = new EmptyBorder(0, 0, 0, margin);
        playerCash.setBorder(marginBorder);

        playerCash.setVisible(true);
        playerCash.invalidate();
        playerCash.validate();
        playerCash.repaint();

        picLabel.add(playerCash, BorderLayout.EAST);

        add(picLabel);
        setBackground(Color.BLACK);

    }

}
