import javax.swing.*;
import java.awt.*;

public class PurchaseHousePopUp extends PurchasePopUp {


    public PurchaseHousePopUp(int houseAmount) {


        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        purchaseButton.setText("");

        ImageIcon doctorIcon = new ImageIcon("assets\\DoctorButton.png");
        ImageIcon rectorIcon = new ImageIcon("assets\\RectorButton.png");

        if(houseAmount>=3) {
            purchaseButton.setIcon(rectorIcon);
        }
        else{
            purchaseButton.setIcon(doctorIcon);
        }

        purchaseButton.setPreferredSize(new Dimension(40, 40));
    }


    public void setPurchaseMessage(String propertyName, int housePrice, int houseAmount) {

        if(houseAmount>=3){
            setMessage("Wygląda na to że twoja kadra na <i>" + propertyName + "</i> jest kompletna, czy chcesz więc zatrudnić PROdziekana za kwotę <i>" + housePrice + " P$</i>?" );
        }
        else{
            setMessage("Czy chcesz zatrudnić profesora na <i>" + propertyName + "</i> za kwotę <i>" + housePrice + " P$</i>?" );
        }


    }
}
