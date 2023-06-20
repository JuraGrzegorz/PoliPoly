import javax.swing.*;
import java.awt.*;

public class OkConfirmPopUp extends  PopUpWindow{

    JButton acceptButton;

    public OkConfirmPopUp() {
        acceptButton = MenuWindow.standardButtonGenerate("OK");
        acceptButton.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(acceptButton);
        acceptButton.addActionListener(back -> setVisible(false));


    }
}
