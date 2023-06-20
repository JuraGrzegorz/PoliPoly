import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PurchaseWindow extends PopUpWindow {


    public JButton purchaseButton;
    public JButton endTourButton;

    public PurchaseWindow() {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        purchaseButton = MenuWindow.standardButtonGenerate("Zakup");
        purchaseButton.setPreferredSize(new Dimension(80, 40));
        buttonPanel.add(purchaseButton);


        endTourButton = MenuWindow.standardButtonGenerate("Zakończ Ture");
        endTourButton.setPreferredSize(new Dimension(120, 40));
        buttonPanel.add(endTourButton);

        buttonPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        buttonPanel.setPreferredSize(new Dimension(200, 50));

        setSize(300, 200);

    }

    public void notEnoughFunds(){
        purchaseButton.setBackground(new Color(0xD6D6D6));
        purchaseButton.setEnabled(false);
    }


    public void setPurchaseMessage(String propertyName, int propertyPrice ) {

        setMessage("Czy chcesz zakupić <i>" + propertyName + "</i> za kwotę <i>" + propertyPrice + " P$</i>?" );
    }
}


