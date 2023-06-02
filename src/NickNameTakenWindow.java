import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class NickNameTakenWindow {
    JPanel container;
    JPanel buttonPanel;
    JButton okButton;
    JTextArea promptLabel;
    String promptText;


    private final JFrame nickNameTakenWindow;

    public NickNameTakenWindow() {

        okButton = MenuWindow.standardButtonGenerate("OK");
        okButton.setPreferredSize(new Dimension(300, 40));

        promptText = "NIE UMIEM WYŚRODKOWAĆ";
        promptLabel = new JTextArea(promptText);
        promptLabel.setLineWrap(true);
        promptLabel.setWrapStyleWord(true);
        promptLabel.setEditable(false);
        promptLabel.setOpaque(false);
        promptLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        promptLabel.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        promptLabel.setPreferredSize(new Dimension(300, 50));

        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
        buttonPanel.setPreferredSize(new Dimension(200,50));
        buttonPanel.add(okButton);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.add(Box.createVerticalStrut(20));
        container.add(promptLabel);
        container.add(Box.createVerticalStrut(10));
        container.add(buttonPanel);
        container.add(Box.createVerticalStrut(10));

        nickNameTakenWindow = new JFrame();
        nickNameTakenWindow.setTitle("nickNameTakenWindow");
        nickNameTakenWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nickNameTakenWindow.setSize(300, 150);
        nickNameTakenWindow.setResizable(false);
        nickNameTakenWindow.setUndecorated(true);
        nickNameTakenWindow.setLocationRelativeTo(null);
        nickNameTakenWindow.add(container);


        okButton.addActionListener(back -> nickNameTakenWindow.setVisible(false));


    }

    public void show() {
        nickNameTakenWindow.setVisible(true);

    }
}
