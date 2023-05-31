import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class MenuWindow {
    private static final JFrame window = new JFrame();
    private static final int BUTTONFONTSIZE = 16;

    JButton playButton;
    JButton creditsButton;
    JButton leaveButton;
    JButton enterHostMenuButton;
    JButton enterJoinMenuButton;
    JButton backToMainMenuButton;
    JButton backFromJoinMenuButton;

    JButton backFromHostMenuButton;

    JButton startGameButton;
    JButton joinGameButton;
    JLabel ip_info;
    JButton changeNickNameHostButton;
    JButton changeNickNameJoinButton;

    JPanel mainMenu;
    JPanel menuPlay;
    JPanel menuJoinGame;
    JPanel JoinPlayersPanel;
    JPanel hostPlayersPanel;


    private JButton standardButtonGenerate(String name) {
        JButton tmp = new JButton(name);
        tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
        tmp.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        tmp.setBackground(new Color(0x2dce98));
        tmp.setForeground(Color.white);
        tmp.setUI(new StyledButtonUI());
        tmp.setPreferredSize(new Dimension(300, 50));
        return tmp;
    }

    private JButton smallerButtonGenerate(String name) {
        JButton tmp = new JButton(name);
        tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
        tmp.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        tmp.setBackground(new Color(0x2dce98));
        tmp.setForeground(Color.white);
        tmp.setUI(new StyledSmallerButtonUI());
        tmp.setPreferredSize(new Dimension(70, 30));
        return tmp;
    }

    public MenuWindow() {

        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        // Dodanie JLabel z logo
        ImageIcon logo = new ImageIcon("assets/Polipoly.png");
        JLabel logoLabel = new JLabel(logo);

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Tworzenie przycisków mainMenu
        playButton = standardButtonGenerate("Graj");
        creditsButton = standardButtonGenerate("Autorzy");
        leaveButton = standardButtonGenerate("Wyjdź");

        // Tworzenie przycisków menuPlay
        enterHostMenuButton = standardButtonGenerate("Stwórz");
        enterJoinMenuButton = standardButtonGenerate("Dołącz");
        backToMainMenuButton = standardButtonGenerate("Wróć");
        backFromJoinMenuButton = standardButtonGenerate("Wróć");
        backFromHostMenuButton = standardButtonGenerate("Wróć");


        //Panel Główny
        mainMenu = new JPanel();
        mainMenu.setOpaque(false);
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
        mainMenu.add(Box.createVerticalGlue());
        mainMenu.add(playButton);
        mainMenu.add(Box.createVerticalStrut(10));
        mainMenu.add(creditsButton);
        mainMenu.add(Box.createVerticalStrut(10));
        mainMenu.add(leaveButton);
        mainMenu.add(Box.createVerticalGlue());

        //Panel Po wciśnięciu graj
        menuPlay = new JPanel();
        menuPlay.setOpaque(false);

        menuPlay.setLayout(new BoxLayout(menuPlay, BoxLayout.Y_AXIS));
        menuPlay.add(Box.createVerticalGlue());
        menuPlay.add(enterHostMenuButton);
        menuPlay.add(Box.createVerticalStrut(10));
        menuPlay.add(enterJoinMenuButton);
        menuPlay.add(Box.createVerticalStrut(10));
        menuPlay.add(backToMainMenuButton);
        menuPlay.add(Box.createVerticalGlue());


        startGameButton = standardButtonGenerate("Star Game");


        ip_info = new JLabel();
        ip_info.setText("ip : "+localhost.getHostAddress());
        ip_info.setFont(new Font("Calibri", Font.BOLD, 20));
        ip_info.setAlignmentX(Component.CENTER_ALIGNMENT);


        JTextField nickNameTextFieldHostMenu = new JTextField("Host");
        nickNameTextFieldHostMenu.setUI(new StyleTextFieldUI());
        nickNameTextFieldHostMenu.setMaximumSize(new Dimension(350, 50));


        changeNickNameHostButton = smallerButtonGenerate("Zmień!");
        changeNickNameJoinButton = smallerButtonGenerate("Zmień!");


        //Panel Po wciśnięciu host
        JPanel menuHostGame = new JPanel();
        menuHostGame.setOpaque(false);

        JPanel hostNickPanel = new JPanel();
        hostNickPanel.setOpaque(false);
        hostNickPanel.setPreferredSize(new Dimension(600, 5));
        hostPlayersPanel = new JPanel();
        hostPlayersPanel.setBackground(new Color(0, 0, 0, 128));
        JPanel hostBottomPanel = new JPanel();
        hostBottomPanel.setOpaque(false);

        menuHostGame.setLayout(new BoxLayout(menuHostGame, BoxLayout.Y_AXIS));
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(ip_info);
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(hostNickPanel);
        hostNickPanel.add(nickNameTextFieldHostMenu);
        nickNameTextFieldHostMenu.setPreferredSize(new Dimension(100, 30));
        hostNickPanel.add(changeNickNameHostButton);


        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(hostPlayersPanel);
        hostPlayersPanel.add(standardButtonGenerate("1"));
        hostPlayersPanel.add(standardButtonGenerate("2"));
        hostPlayersPanel.add(standardButtonGenerate("3"));
        hostPlayersPanel.add(standardButtonGenerate("4"));

        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(hostBottomPanel);
        hostBottomPanel.add(startGameButton);
        hostBottomPanel.add(backFromHostMenuButton);


        joinGameButton = standardButtonGenerate("Dołącz");


        JTextField ipAddressGetTextField = new JTextField("192.168.18.14");
        ipAddressGetTextField.setUI(new StyleTextFieldUI());
        ipAddressGetTextField.setMaximumSize(new Dimension(300, 50));

        JTextField nickNameTextFieldJoinMenu = new JTextField("Player");
        nickNameTextFieldJoinMenu.setUI(new StyleTextFieldUI());
        nickNameTextFieldJoinMenu.setPreferredSize(new Dimension(200, 50));


        menuJoinGame = new JPanel();
        menuJoinGame.setOpaque(false);

        JPanel joinNickPanel = new JPanel();
        joinNickPanel.setOpaque(false);
        joinNickPanel.setPreferredSize(new Dimension(600, 5));
        JoinPlayersPanel = new JPanel();
        JoinPlayersPanel.setBackground(new Color(0, 0, 0, 128));
        JPanel joinBottomPanel = new JPanel();
        joinBottomPanel.setOpaque(false);

        menuJoinGame.setLayout(new BoxLayout(menuJoinGame, BoxLayout.Y_AXIS));
        menuJoinGame.add(joinNickPanel);
        joinNickPanel.add(ipAddressGetTextField);
        joinNickPanel.add(nickNameTextFieldJoinMenu);
        nickNameTextFieldJoinMenu.setPreferredSize(new Dimension(100, 30));
        joinNickPanel.add(changeNickNameJoinButton);

        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(JoinPlayersPanel);
        JoinPlayersPanel.add(standardButtonGenerate("1"));
        JoinPlayersPanel.add(standardButtonGenerate("2"));
        JoinPlayersPanel.add(standardButtonGenerate("3"));
        JoinPlayersPanel.add(standardButtonGenerate("4"));

        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(joinBottomPanel);
        joinBottomPanel.add(joinGameButton);
        joinBottomPanel.add(backFromJoinMenuButton);


        // Tworzenie panelu Container
        /*JPanel container;
        container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon menuBackground = new ImageIcon("assets/tmp_bg.png");
                g.drawImage(menuBackground.getImage(), 0, 0, null);
            }
        };
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        container.add(logoLabel);
        container.add(mainMenu);
        container.add(menuHostGame, BorderLayout.NORTH);
        container.add(menuJoinGame);
        menuPlay.setVisible(false);
        menuHostGame.setVisible(false);
        menuJoinGame.setVisible(false);
        container.add(menuPlay);*/


        /*playButton.addActionListener(back -> {
            mainMenu.setVisible(false);
            menuPlay.setVisible(true);
        });

        enterJoinMenuButton.addActionListener(back -> {
            menuPlay.setVisible(false);
            menuJoinGame.setVisible(true);
        });

        enterHostMenuButton.addActionListener(back -> {
            menuPlay.setVisible(false);
            menuHostGame.setVisible(true);
        });

        backToMainMenuButton.addActionListener(back -> {
            mainMenu.setVisible(true);
            menuPlay.setVisible(false);
        });

        backFromJoinMenuButton.addActionListener(back -> {
            menuPlay.setVisible(true);
            menuJoinGame.setVisible(false);
        });

        backFromHostMenuButton.addActionListener(back -> {
            menuPlay.setVisible(true);
            menuHostGame.setVisible(false);
        });

        leaveButton.addActionListener(leaveGame -> System.exit(0));
*/

        /*window.setTitle("PoliPoly");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(container);*/

    }
}
