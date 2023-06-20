import javax.swing.*;
import java.awt.*;

public class RulesPopup extends PopUpWindow {

    JButton acceptButton;

    public RulesPopup(){
        acceptButton = MenuWindow.standardButtonGenerate("OK");
        acceptButton.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(acceptButton);
        acceptButton.addActionListener(back -> setVisible(false));
        setBounds(560, 240, 800, 600);
        promptLabel.setMinimumSize(new Dimension(600, 400));
    }
    public void setMessage(String message) {
        promptLabel.setText("<html><div style='text-align: center; width: 600px;'>" + message + "</div></html>");
    }

}
