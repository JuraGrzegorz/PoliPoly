import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;


public class MainWindow {
    private final JFrame window;
    private static final int BUTTONFONTSIZE = 16;

    private Server server;
    private Client client;
    boolean gameStarted;
    boolean stopHostingGame;

    private final NickNameTakenWindow alertWindow;

    public MainWindow() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Clip menuTheme = PlaySoundEffect.playMusicOnRepeat("assets\\sounds\\music\\menutheme.wav");
        gameStarted=false;
        stopHostingGame=false;
        MenuWindow menuWindow=new MenuWindow();
        alertWindow=new NickNameTakenWindow();
        Player player=new Player();

        JPanel container;
        // Tworzenie panelu Container
        container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon menuBackground = new ImageIcon("assets/tmp_bg.png");
                g.drawImage(menuBackground.getImage(), 0, 0, null);
            }
        };
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        menuWindow.logoLabel.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        container.add(menuWindow.logoLabel);
        container.add(menuWindow.mainMenu);
        container.add(menuWindow.menuHostGame, BorderLayout.NORTH);
        container.add(menuWindow.menuJoinGame);
        menuWindow.menuPlay.setVisible(false);
        menuWindow.menuHostGame.setVisible(false);
        menuWindow.menuJoinGame.setVisible(false);
        container.add(menuWindow.menuPlay);




        //akcje przycisków
        menuWindow.playButton.addActionListener(back -> {
            menuWindow.mainMenu.setVisible(false);
            menuWindow.menuPlay.setVisible(true);
        });

        menuWindow.enterJoinMenuButton.addActionListener(back -> {
            menuWindow.menuPlay.setVisible(false);
            menuWindow.menuJoinGame.setVisible(true);
        });

        menuWindow.joinGameButton.addActionListener(back -> {

            if(!player.playerConnected){
                try {
                    client=new Client();
                    client.ClientConnect(menuWindow.ipAddressGetTextField.getText(),8080);
                    client.SetCommunicationParameters(client.clientSocket);
                    player.PlayerConnect();
                    ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client,menuWindow,menuWindow.joinGameListButtons,alertWindow,player);
                    clientReadFromServer.start();

                    client.fromClient.println("setNickname:"+menuWindow.nickNameTextFieldJoinMenu.getText());

                } catch (IOException e) {

                }
            }else{
                alertWindow.setMessage("Jesteś juz w lobby!!");
                alertWindow.show();
            }
        });

        menuWindow.enterHostMenuButton.addActionListener(back -> {
            server=new Server();

            synchronized (this) {
                stopHostingGame=false;
            }

            try {
                server.openSocket(8080);
                server.serverSocketChannel.setSoTimeout(1000);

            } catch (IOException e) {
                alertWindow.setMessage("Server juz istnieje!");
                alertWindow.show();
                return;
            }
            menuWindow.menuPlay.setVisible(false);
            menuWindow.menuHostGame.setVisible(true);

            Thread ThreadWaitingForPlayers = new Thread(() -> {

                ServerMainThread serverMainThread=new ServerMainThread(server);
                serverMainThread.start();
                while (true){

                    synchronized (this) {
                        if(gameStarted || stopHostingGame){
                            break;
                        }
                    }
                    Socket tmp_clientSock = null;
                    try {
                        tmp_clientSock = server.serverSocketChannel.accept();
                        server.addSemaphore();
                        Communication tmp_Comm=server.listOfCommunication.get(server.listOfCommunication.size()-1);
                        ServerReadFromClient serverReadThread=new ServerReadFromClient(tmp_clientSock,tmp_Comm,server.syncJoiningPlayers);
                        serverReadThread.start();
                        ServerWriteTOClient serverWriteThread=new ServerWriteTOClient(tmp_clientSock,tmp_Comm);
                        serverWriteThread.start();

                    } catch (IOException e) {}
                }

            });
            ThreadWaitingForPlayers.start();

            while (true){
                try {
                    client=new Client();
                    client.ClientConnect("localhost",8080);
                    client.SetCommunicationParameters(client.clientSocket);

                    ClientReadFromServer clientReadFromServer=new ClientReadFromServer(client,menuWindow,menuWindow.hostGameListButtons,alertWindow,player);
                    clientReadFromServer.start();
                    client.fromClient.println("setNickname:"+menuWindow.nickNameTextFieldHostMenu.getText());

                    break;
                } catch (IOException e) {}
            }
        });

        menuWindow.backToMainMenuButton.addActionListener(back -> {
            menuWindow.mainMenu.setVisible(true);
            menuWindow.menuPlay.setVisible(false);
        });

        menuWindow.backFromJoinMenuButton.addActionListener(back -> {
            if(player.playerConnected){
                try{
                    client.fromClient.println("Quit");
                    player.PlayerDisconnect();
                }catch (NullPointerException error) {
                    System.err.println(error.getMessage());
                }
            }else{
                menuWindow.menuPlay.setVisible(true);
                menuWindow.menuJoinGame.setVisible(false);
            }
        });

        menuWindow.backFromHostMenuButton.addActionListener(back -> {
            synchronized (this) {
                stopHostingGame=true;
                try {
                    Thread.sleep(1000);
                    server.serverSocketChannel.close();
                    client.fromClient.println("Quit");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        menuWindow.changeNickNameHostButton.addActionListener(back -> {
            client.fromClient.println("changeNickname:"+menuWindow.nickNameTextFieldHostMenu.getText());
        });

        menuWindow.changeNickNameJoinButton.addActionListener(back -> {
            client.fromClient.println("changeNickname:"+menuWindow.nickNameTextFieldJoinMenu.getText());
        });




        menuWindow.startGameButton.addActionListener(back -> {
            synchronized (this) {
                gameStarted=true;
                PlaySoundEffect.stopMusicOnRepeat(menuTheme);
                try {
                    PlaySoundEffect.playMusicOnRepeat("assets\\sounds\\music\\gameplaytheme.wav");
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            client.fromClient.println("startGame");
        });

        menuWindow.leaveButton.addActionListener(leaveGame -> System.exit(0));

        window = new JFrame();
        window.setTitle("PoliPoly");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.add(container);

    }

    public void show() {
        window.setVisible(true);

    }

}
