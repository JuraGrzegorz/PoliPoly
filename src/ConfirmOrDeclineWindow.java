import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConfirmOrDeclineWindow extends PopUpWindow {

    public JButton acceptButton;

    public ConfirmOrDeclineWindow() {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        acceptButton = MenuWindow.standardButtonGenerate("Ok");
        acceptButton.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(acceptButton);


        JButton declineButton = MenuWindow.standardButtonGenerate("Anuluj");
        declineButton.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(declineButton);

        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        buttonPanel.setPreferredSize(new Dimension(160, 50));

        declineButton.addActionListener(back -> setVisible(false));
    }





}
