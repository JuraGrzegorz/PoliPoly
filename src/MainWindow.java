import javax.swing.*;
import java.awt.*;



public class MainWindow {
    private final JFrame window;
    private static final int BUTTONFONTSIZE = 16;

    public MainWindow() {


        // Dodanie JLabel z logo
        ImageIcon logo = new ImageIcon("assets/Polipoly.png");
        JLabel logoLabel = new JLabel(logo);

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Tworzenie przycisków menuEnter
        JButton playButton = new JButton("Graj");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        playButton.setBackground(new Color(0x2dce98));
        playButton.setForeground(Color.white);
        playButton.setUI(new StyledButtonUI());
        playButton.setPreferredSize(new Dimension(300, 50));

        JButton creditsButton = new JButton("Autorzy");
        creditsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        creditsButton.setBackground(new Color(0x2dce98));
        creditsButton.setForeground(Color.white);
        creditsButton.setUI(new StyledButtonUI());
        creditsButton.setPreferredSize(new Dimension(300, 50));

        JButton leaveButton = new JButton("Wyjdź");
        leaveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaveButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        leaveButton.setBackground(new Color(0x2dce98));
        leaveButton.setForeground(Color.white);
        leaveButton.setUI(new StyledButtonUI());
        leaveButton.setPreferredSize(new Dimension(300, 50));


        // Tworzenie przycisków menuPlay
        JButton hostButton = new JButton("Host");
        hostButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hostButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        hostButton.setBackground(new Color(0x2dce98));
        hostButton.setForeground(Color.white);
        hostButton.setUI(new StyledButtonUI());
        hostButton.setPreferredSize(new Dimension(300, 50));

        JButton joinButton = new JButton("Join");
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        joinButton.setBackground(new Color(0x2dce98));
        joinButton.setForeground(Color.white);
        joinButton.setUI(new StyledButtonUI());
        joinButton.setPreferredSize(new Dimension(300, 50));


        JButton backButton = new JButton("Wróć");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        backButton.setBackground(new Color(0x2dce98));
        backButton.setForeground(Color.white);
        backButton.setUI(new StyledButtonUI());
        backButton.setPreferredSize(new Dimension(300, 50));


        //Panel Główny
        JPanel menuEnter = new JPanel();
        menuEnter.setOpaque(false);

        menuEnter.setLayout(new BoxLayout(menuEnter, BoxLayout.Y_AXIS));
        menuEnter.add(Box.createVerticalGlue());
        menuEnter.add(playButton);
        menuEnter.add(Box.createVerticalStrut(10));
        menuEnter.add(creditsButton);
        menuEnter.add(Box.createVerticalStrut(10));
        menuEnter.add(leaveButton);
        menuEnter.add(Box.createVerticalGlue());

        //Panel Po wciśnięciu graj
        JPanel menuPlay = new JPanel();
        menuPlay.setOpaque(false);

        menuPlay.setLayout(new BoxLayout(menuPlay, BoxLayout.Y_AXIS));
        menuPlay.add(Box.createVerticalGlue());
        menuPlay.add(hostButton);
        menuPlay.add(Box.createVerticalStrut(10));
        menuPlay.add(joinButton);
        menuPlay.add(Box.createVerticalStrut(10));
        menuPlay.add(backButton);
        menuPlay.add(Box.createVerticalGlue());
        


        // Tworzenie panelu Container
        JPanel container;
        container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon menuBackground = new ImageIcon("assets/tmp_bg.png");
                g.drawImage(menuBackground.getImage(), 0, 0, null);
            }
        };
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
        container.add(logoLabel);
        container.add(menuEnter);
        menuPlay.setVisible(false);
        container.add(menuPlay);




        //akcje przycisków
        playButton.addActionListener(back -> {
            menuEnter.setVisible(false);
            menuPlay.setVisible(true);
        });

        backButton.addActionListener(back -> {
            menuPlay.setVisible(false);
            menuEnter.setVisible(true);
        });

        leaveButton.addActionListener(leaveGame -> System.exit(0));





        window = new JFrame();
        window.setTitle("PoliPoly");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 500);
        window.setLocationRelativeTo(null);
        window.add(container);


    }

    public void show() {
        window.setVisible(true);


    }

}
