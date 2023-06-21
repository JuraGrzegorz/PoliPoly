import javax.swing.*;
import java.awt.*;

public class CardOnCardPanelPanel extends JPanel {
    public JPanel topColorLabel;
    public JPanel midColorLabel;
    public JLabel nameLabel;
    public JLabel priceLabel;
    public String text = "default";

    public void setString(String value) {
        this.text = value;
        wrapText();
    }



    private void wrapText() {
        nameLabel.setText("<html><div style='text-align: center; width: " + nameLabel.getWidth() + "px;'>" + text + "</div><br><br></html>");
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

        nameLabel = new JLabel();
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(nameLabel);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setBorder(null);

        priceLabel = new JLabel("Opłata: ");
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setVerticalAlignment(SwingConstants.BOTTOM);


        setLayout(new BorderLayout());
        add(topColorLabel, BorderLayout.NORTH);
        add(midColorLabel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(priceLabel, BorderLayout.SOUTH);


    }

}
