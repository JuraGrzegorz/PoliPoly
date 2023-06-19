import javax.swing.*;
import java.awt.*;

public class CardOnCardPanelPanel extends JPanel {


    public JPanel topColorLabel;
    public JPanel midColorLabel;
    JLabel label;
    public String text = "default";

    public void setString(String value) {
        this.text = value;
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setMaximumSize(new Dimension(100, 100));
        label.setPreferredSize(new Dimension(100, 100));
    }


    CardOnCardPanelPanel() {

        Color defaultColor = new Color(0xcf2929);


        topColorLabel = new JPanel();
        topColorLabel.setOpaque(true);
        topColorLabel.setBackground(defaultColor);
        topColorLabel.setPreferredSize(new Dimension(100, 30));

        midColorLabel = new JPanel();
        midColorLabel.setOpaque(true);
        midColorLabel.setBackground(defaultColor.darker());
        midColorLabel.setPreferredSize(new Dimension(100, 5));


        label = new JLabel();
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setMaximumSize(new Dimension(100, 100));
        label.setPreferredSize(new Dimension(100, 100));


        add(topColorLabel);
        add(midColorLabel);
        add(label, BorderLayout.CENTER);


    }


}
