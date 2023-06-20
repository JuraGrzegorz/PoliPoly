import javax.swing.*;
import java.awt.*;

public class CashPanel extends JPanel {

    public int playerCashValue;

    static JLabel playerCash;


    public CashPanel(int x,int y){
        Rectangle cashPanelRectangle = new Rectangle(x, y, 300, 50);
        setBounds(cashPanelRectangle);

        playerCashValue = 0;
        playerCash = new JLabel();
        playerCash.setText(String.format("%d P$", playerCashValue*2));
        playerCash.setFont(new Font("Calibri", Font.BOLD, 40));
        playerCash.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerCash.setVisible(true);
        playerCash.invalidate();
        playerCash.validate();
        playerCash.repaint();

        add(playerCash);
        setBackground(Color.yellow);
    }

}
