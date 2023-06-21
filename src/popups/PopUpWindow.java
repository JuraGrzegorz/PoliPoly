import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PopUpWindow extends JFrame {

    private final JLabel titleLabel;
    protected JPanel buttonPanel;
    protected final JLabel promptLabel;

    public PopUpWindow() {
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(0x2dce98));
        titleBar.setPreferredSize(new Dimension(300, 20)); // Ustawienie preferowanej wielkości

        JPanel titleBarShadow = new JPanel();
        titleBarShadow.setBackground(new Color(0x2dce98).darker());
        titleBarShadow.setPreferredSize(new Dimension(300, 2)); // Ustawienie preferowanej wielkości

        // Tworzenie etykiety dla tekstu paska tytułowego
        titleLabel = new JLabel();
        titleLabel.setForeground(Color.WHITE); // Ustawienie koloru tekstu na biały
        titleBar.add(titleLabel); // Dodanie etykiety do panelu paska tytułowego

        promptLabel = new JLabel();
        promptLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        promptLabel.setMaximumSize(new Dimension(100, 70));

        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
        buttonPanel.setPreferredSize(new Dimension(70, 50));


        JPanel container = new JPanel(new GridBagLayout());
        container.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;


        gbc.weighty = 0;
        container.add(titleBar, gbc); // Dodanie panelu paska tytułowego

        gbc.gridy++;
        gbc.weighty = 0.1;
        container.add(titleBarShadow, gbc);

        gbc.gridy++;
        gbc.weighty = 0.5;
        container.add(Box.createVerticalGlue(), gbc);

        gbc.gridy++;
        gbc.weighty = 0.0;
        container.add(promptLabel, gbc);

        gbc.gridy++;
        gbc.weighty = 0.5;
        container.add(Box.createVerticalGlue(), gbc);

        gbc.gridy++;
        gbc.weighty = 0.0;
        container.add(buttonPanel, gbc);


        container.add(buttonPanel, gbc);
        setSize(300, 150);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        add(container);
    }

    public void setMessage(String message) {
        promptLabel.setText("<html><div style='text-align: center; width: 230px;'>" + message + "</div></html>");
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }


}
