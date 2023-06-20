import javax.swing.*;
import java.awt.*;

public class CardOnCardPanelPanel extends JPanel {
    public JPanel topColorLabel;
    public JPanel midColorLabel;
    public JLabel label;
    public String text = "default";

    public void setString(String value) {
        this.text = value;
        wrapText();
    }

    private void wrapText() {
        label.setText("<html><div style='text-align: center; width: " + label.getWidth() + "px;'>" + text + "</div></html>");
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
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setBorder(null);


        setLayout(new BorderLayout());
        add(topColorLabel, BorderLayout.NORTH);
        add(midColorLabel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

}
