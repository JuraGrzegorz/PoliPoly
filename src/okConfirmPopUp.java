import javax.swing.*;
import java.awt.*;

public class okConfirmPopUp extends  PopUpWindow{

    JButton acceptButton;

    public okConfirmPopUp() {
        acceptButton = MenuWindow.standardButtonGenerate("OK");
        acceptButton.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(acceptButton);
        acceptButton.addActionListener(back -> setVisible(false));


    }
}
