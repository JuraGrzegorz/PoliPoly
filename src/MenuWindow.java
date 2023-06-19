import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class MenuWindow {
    private static final int BUTTONFONTSIZE = 16;

    JButton playButton;
    JButton creditsButton;
    JButton leaveButton;
    JButton enterHostMenuButton;
    JButton enterJoinMenuButton;
    JButton backToMainMenuButton;
    JButton backFromJoinMenuButton;

    JPanel menuHostGame;
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
    JLabel logoLabel;
    JTextField nickNameTextFieldHostMenu;
    JTextField ipAddressGetTextField;
    JTextField nickNameTextFieldJoinMenu;
    List<JButton> joinGameListButtons;
    List<JButton> hostGameListButtons;
    static JButton standardButtonGenerate(String name) {
        JButton tmp = new JButton(name);
        tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
        tmp.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        tmp.setBackground(new Color(0x2dce98));
        tmp.setForeground(Color.white);
        tmp.setUI(new StyledButtonUI());
        tmp.setPreferredSize(new Dimension(300, 50));
        return tmp;
    }

    static JButton grayButtonGenerate(String name) {
        JButton tmp = new JButton(name);
        tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
        tmp.setFont(new Font("Calibri", Font.PLAIN, BUTTONFONTSIZE));
        tmp.setBackground(new Color(0xd3d3d3));
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
        joinGameListButtons =new ArrayList<>();
        hostGameListButtons=new ArrayList<>();
        hostGameListButtons.add(grayButtonGenerate(""));
        hostGameListButtons.add(grayButtonGenerate(""));
        hostGameListButtons.add(grayButtonGenerate(""));
        hostGameListButtons.add(grayButtonGenerate(""));
        joinGameListButtons.add(grayButtonGenerate(""));
        joinGameListButtons.add(grayButtonGenerate(""));
        joinGameListButtons.add(grayButtonGenerate(""));
        joinGameListButtons.add(grayButtonGenerate(""));


        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        // Dodanie JLabel z logo
        ImageIcon logo = new ImageIcon("assets/Polipoly.png");
        logoLabel = new JLabel(logo);

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


        startGameButton = standardButtonGenerate("Rozpocznij");


        ip_info = new JLabel();
        ip_info.setText("ip : "+localhost.getHostAddress());
        ip_info.setFont(new Font("Calibri", Font.BOLD, 20));
        ip_info.setAlignmentX(Component.CENTER_ALIGNMENT);


        nickNameTextFieldHostMenu = new JTextField("Host");
        nickNameTextFieldHostMenu.setUI(new StyleTextFieldUI());
        nickNameTextFieldHostMenu.setMaximumSize(new Dimension(350, 50));


        changeNickNameHostButton = smallerButtonGenerate("Zmień!");
        changeNickNameJoinButton = smallerButtonGenerate("Zmień!");


        //Panel Po wciśnięciu host
        menuHostGame = new JPanel();
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
        hostPlayersPanel.add(hostGameListButtons.get(0));
        hostPlayersPanel.add(hostGameListButtons.get(1));
        hostPlayersPanel.add(hostGameListButtons.get(2));
        hostPlayersPanel.add(hostGameListButtons.get(3));
        menuHostGame.add(Box.createVerticalStrut(10));
        menuHostGame.add(hostBottomPanel);
        hostBottomPanel.add(startGameButton);
        hostBottomPanel.add(backFromHostMenuButton);


        joinGameButton = standardButtonGenerate("Dołącz");


        ipAddressGetTextField = new JTextField("192.168.18.14");
        ipAddressGetTextField.setUI(new StyleTextFieldUI());
        ipAddressGetTextField.setMaximumSize(new Dimension(300, 50));

        nickNameTextFieldJoinMenu = new JTextField("Server.Player");
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
        JoinPlayersPanel.add(joinGameListButtons.get(0));
        JoinPlayersPanel.add(joinGameListButtons.get(1));
        JoinPlayersPanel.add(joinGameListButtons.get(2));
        JoinPlayersPanel.add(joinGameListButtons.get(3));

        menuJoinGame.add(Box.createVerticalStrut(10));
        menuJoinGame.add(joinBottomPanel);
        joinBottomPanel.add(joinGameButton);
        joinBottomPanel.add(backFromJoinMenuButton);
    }
}
