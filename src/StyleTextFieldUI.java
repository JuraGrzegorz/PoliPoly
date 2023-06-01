import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;


public class StyleTextFieldUI extends BasicTextFieldUI {

    @Override
    protected void installDefaults() {
        super.installDefaults();
        // Ustawienie półprzezroczystego tła dla pola tekstowego
        LookAndFeel.installProperty(getComponent(), "opaque", false);
        getComponent().setBackground(new Color(0, 0, 0, 128));
        getComponent().setFont(new Font("Calibri", Font.PLAIN, 18)); // Ustawienie czcionki
    }
}


