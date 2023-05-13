import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final JFrame window;

    public MainWindow() {


        // Dodanie JLabel z logo
        ImageIcon logo = new ImageIcon("assets/Polipoly.png");
        JLabel logoLabel = new JLabel(logo);

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Tworzenie przycisków menuEnter
        JButton playButton = new JButton("Graj");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(Color.GREEN);
        playButton.setPreferredSize(new Dimension(300, 50));

        JButton creditsButton = new JButton("Autorzy");
        creditsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsButton.setForeground(Color.WHITE);
        creditsButton.setBackground(Color.GREEN);
        creditsButton.setPreferredSize(new Dimension(300, 50));

        JButton leaveButton = new JButton("Wyjdź");
        leaveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaveButton.setForeground(Color.WHITE);
        leaveButton.setBackground(Color.GREEN);
        leaveButton.setPreferredSize(new Dimension(300, 50));


        // Tworzenie przycisków menuPlay
        JButton hostButton = new JButton("Host");
        hostButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hostButton.setBackground(Color.GREEN);
        hostButton.setForeground(Color.WHITE);
        hostButton.setPreferredSize(new Dimension(300, 50));

        JButton joinButton = new JButton("Join");
        joinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinButton.setForeground(Color.WHITE);
        joinButton.setBackground(Color.GREEN);
        joinButton.setPreferredSize(new Dimension(300, 50));

        JButton localgameButton = new JButton("Gra Lokalna");
        localgameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        localgameButton.setForeground(Color.WHITE);
        localgameButton.setBackground(Color.GREEN);
        localgameButton.setPreferredSize(new Dimension(300, 50));

        JButton backButton = new JButton("Wróć");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.GREEN);
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
        menuPlay.add(localgameButton);
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
