import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ClientReadFromServer extends Thread{
    private final Client client;
    private final Player player;
    private final MenuWindow menuWindow;
    private final List<JButton> listButtons;
    private int countOfPlayer;
    private final NickNameTakenWindow alertWindow;
    GamingWindow gamingWindow;
    int playerCash;
    int[] playersPosition;
    int localPlayerNumber;
    DeckOfCards chanceDeck;
    DeckOfCards kasaspolDeck;
    public ClientReadFromServer(Client client, MenuWindow menuWindow, List<JButton> listButtons, NickNameTakenWindow alertWindow, Player player) {
        chanceDeck = new DeckOfCards((short)0);
        kasaspolDeck = new DeckOfCards((short)1);
        chanceDeck.shuffleDeck();
        kasaspolDeck.shuffleDeck();
        this.client=client;
        this.player=player;
        this.menuWindow=menuWindow;
        this.listButtons=listButtons;
        countOfPlayer=0;
        this.alertWindow=alertWindow;
        playersPosition=new int[4];
        for(int i=0;i<4;i++){
            playersPosition[i]=0;
        }
        playerCash=800;
    }

    public void run() {
        String message;
        while (true){
            try {
                message=client.intoClient.readLine();
                if(message.startsWith("JoiningLobby:")){
                    String[] tmp=message.split(":");
                    tmp=tmp[1].split(",");

                    for(String UsersNicks:tmp){
                        listButtons.get(countOfPlayer).setText(UsersNicks);
                        listButtons.get(countOfPlayer).setBackground(new Color(0x2dce98));
                        countOfPlayer++;
                    }
                }

                if(message.equals("serverFull")){
                    client.intoClient.close();
                    alertWindow.setMessage("Server pelen!!");
                    alertWindow.show();
                    player.PlayerDisconnect();
                    return;
                }

                if(message.startsWith("PlayerDisconnect:")){
                    String[] tmp=message.split(":");
                    for(int i=0;i<listButtons.size();i++){

                        if(listButtons.get(i).getText().equals(tmp[1])){
                            int j;
                            for(j=i;j<countOfPlayer-1;j++){
                                listButtons.get(j).setText(listButtons.get(j+1).getText());
                            }
                            listButtons.get(j).setText("");
                            listButtons.get(j).setBackground(new Color(0xD6D6D6));
                            countOfPlayer--;
                            break;
                        }
                    }
                }

                if(message.startsWith("ConfirmChangeNickname:")){
                    message = message.substring(("ConfirmChangeNickname:").length());
                    String[] tmp=message.split(":");
                    for(int i=0;i<listButtons.size();i++){
                        if(listButtons.get(i).getText().equals(tmp[0])){
                            listButtons.get(i).setText(tmp[1]);
                            break;
                        }
                    }
                }

                if(message.equals("nickNameTakenChanging")){
                    alertWindow.setMessage("Nick jest zajety!!");
                    alertWindow.show();

                }
                if(message.equals("nickNameTaken")){
                    alertWindow.setMessage("Nick jest zajety!!");
                    alertWindow.show();
                    player.PlayerDisconnect();
                }

                if(message.startsWith("ConfirmQuit")){
                    for(int i=0;i<listButtons.size();i++){
                        listButtons.get(i).setText("");
                        listButtons.get(i).setBackground(new Color(0xD6D6D6));
                    }
                    menuWindow.menuPlay.setVisible(true);
                    menuWindow.menuHostGame.setVisible(false);
                    menuWindow.menuJoinGame.setVisible(false);
                    return;
                }

                if(message.equals("ForceQuit")){
                    client.fromClient.println("Quit");
                    for(int i=0;i<listButtons.size();i++){
                        listButtons.get(i).setText("");
                        listButtons.get(i).setBackground(new Color(0xD6D6D6));
                    }

                    menuWindow.menuPlay.setVisible(true);
                    menuWindow.menuJoinGame.setVisible(false);

                    alertWindow.setMessage("Host odłaczony!!");
                    alertWindow.show();
                    player.PlayerDisconnect();
                    return;
                }

                if(message.startsWith("gameStarted:")){
                    localPlayerNumber= Integer.parseInt(message.substring(("gameStarted:").length()));
                    gamingWindow=new GamingWindow(client.fromClient);
                    gamingWindow.diceRollPanel.setVisible(false);
                    gamingWindow.buyPropertyButton.setVisible(false);
                    gamingWindow.endRound.setVisible(false);
                    gamingWindow.playerCash.setText(String.format("%d P$", playerCash));
                    client.fromClient.println("Starting");
//                    for(int i=1;i<32;i++){
//                        for(int j=0;j<4;j++){
//                            showOrHide(gamingWindow.pawnPanel,i,j,0);
//                        }
//                    }

                }

                if(message.startsWith("move:")){
                    int playerNumber;
                    int moveNumber;
                    message = message.substring(("move:").length());

                    String[] tmp=message.split(":");
                    playerNumber=Integer.parseInt(tmp[1]);
                    moveNumber=Integer.parseInt(tmp[0]);

                    if(Integer.parseInt(tmp[2])==-1 && playerCash>=gamingWindow.facultyPrices[playersPosition[playerNumber]]){
//                        localPlayerNumber=playerNumber;
                        gamingWindow.buyPropertyButton.setVisible(true);
                    }
                    if(Integer.parseInt(tmp[2])==0){
                        playerCash-=gamingWindow.facultyPrices[moveNumber]/5;
                        gamingWindow.playerCash.setText(String.format("%d P$", playerCash));
                    }
                    if(Integer.parseInt(tmp[2])==1){
                        playerCash+=gamingWindow.facultyPrices[moveNumber]/5;
                        gamingWindow.playerCash.setText(String.format("%d P$", playerCash));
                    }
                    if(playerNumber==localPlayerNumber){
                        gamingWindow.endRound.setVisible(true);
                    }
                    showOrHide(gamingWindow.pawnPanel,moveNumber,playerNumber,1);
                    showOrHide(gamingWindow.pawnPanel,playersPosition[playerNumber],playerNumber,0);
                    playersPosition[playerNumber]=moveNumber;

                    Thread.sleep(50);
                    gamingWindow.diceRollPanel.setVisible(false);
                    PlaySoundEffect.playSound("assets\\sounds\\pawnjump.wav");
                    // 6 - kasa
                    // 26 - kasa
                    // 10 - szansa
                    // 30 - szansa

                    checkForChanceOrKasa(playerNumber);

                }
                if(message.equals("Bought:")){
                    PlaySoundEffect.playSound("assets\\sounds\\kaching.wav");
                    playerCash-=gamingWindow.facultyPrices[playersPosition[localPlayerNumber]];
                    gamingWindow.playerCash.setText(String.format("%d P$", playerCash));
                    gamingWindow.buyPropertyButton.setVisible(false);
                    gamingWindow.endRound.setVisible(false);


                    gamingWindow.addCardToPanel(gamingWindow.facultyColor[playersPosition[localPlayerNumber]],gamingWindow.facultyNames[playersPosition[localPlayerNumber]]);



                }
                if(message.equals("cash:")){
                    playerCash+=400;
                    gamingWindow.playerCash.setText(String.format("%d P$", playerCash));
                }
                if(message.equals("yourTurn")){
                    gamingWindow.diceRollPanel.setVisible(true);

                }
                if(message.equals("next")){
                    gamingWindow.endRound.setVisible(false);
                    gamingWindow.buyPropertyButton.setVisible(false);
                }
            } catch (IOException | InterruptedException | UnsupportedAudioFileException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void showOrHide(JPanel[][] tab,int val,int element,int how) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int tmpVal;
        if(val>=1 && val<8){
            tmpVal=28;
            if(how==0){
                tab[tmpVal-val*2][element].setVisible(false);
            }else{
                tab[tmpVal-val*2][element].setVisible(true);
            }

        }
        if(val>=9 && val<16){
            tmpVal=val-9;
            tmpVal*=2;
            if(how==0){
                tab[tmpVal][element].setVisible(false);
            }else{
                tab[tmpVal][element].setVisible(true);
            }
        }
        if(val>=17 && val<24){
            tmpVal=(val-17);
            if(how==0){
                tab[val-2+tmpVal][element].setVisible(false);
            }else{
                tab[val-2+tmpVal][element].setVisible(true);
            }
        }
        if(val>=25 && val<32){
            tmpVal=13-(val-25)*2;

            if(how==0){
                tab[tmpVal][element].setVisible(false);
            }else{
                tab[tmpVal][element].setVisible(true);
            }
        }

        if(val==0 || val==8 || val==16 || val==24){
            if(val==0){
                if(how==0){
                    tab[29][element].setVisible(false);
                }else{
                    tab[29][element].setVisible(true);
                }
            }
            if(val==8){
                if(how==0){
                    tab[28][element].setVisible(false);
                }else{
                    tab[28][element].setVisible(true);
                }
            }
            if(val==16){
                if(how==0){
                    tab[30][element].setVisible(false);
                }else{
                    tab[30][element].setVisible(true);
                }
            }
            if(val==24){
                if(how==0){
                    tab[31][element].setVisible(false);
                }else{
                    tab[31][element].setVisible(true);
                }
            }
        }
    }

    //WYWOLANIE POD KONIEC if(message.startsWith("move:")){}
    private void checkForChanceOrKasa(int playerNumber) {
        if(playersPosition[playerNumber] == 10 || playersPosition[playerNumber] == 30){
            //wejscie na szanse
            String drawnCard = chanceDeck.drawCard();
            char drawnCardID = drawnCard.charAt(0);
            System.out.println("drawnCardID: " + drawnCardID + " zalosowana karta: " + drawnCard.replace(drawnCardID+".",""));
            switch(drawnCardID){
                case '1':
                    //idz do instytutu obrabiarek
                    break;
                case '2':
                    //idz do najblizszego akademika, mozesz kupic jesli wolny, zaplac jesli nalezy do gracza
                    break;
                case '3':
                    ////idz do CJ, mozesz kupic jesli wolny, zaplac jesli nalezy do gracza
                    break;
                case '4':
                    //idz do wiezienia, nie przechodz przez start, nie pobieraj 200zl
                    break;
                case '5':
                    //karta zart, nic sie nie dzieje
                    break;
                case '6':
                    //cofnij sie o 3 pola
                    break;
                case '7':
                    //przejdz do instytutu marketingu (ostatnia ulica przed startem)
                    break;
            }
        }
        if(playersPosition[playerNumber] == 6 || playersPosition[playerNumber] == 26){
            //wejscie na kase spoleczna
            String drawnCard = kasaspolDeck.drawCard();
            char drawnCardID = drawnCard.charAt(0);
            System.out.println("drawnCardID: " + drawnCardID + " zalosowana karta: " + drawnCard.replace(drawnCardID+".",""));
            switch(drawnCardID){
                case '1':
                    //plac 25zl za kazdy domek i 100zl za kazdy hotel
                    break;
                case '2':
                    //przejdz na start i pobierz 200zl
                    break;
                case '3':
                    ////dostajesz 150zl
                    break;
                case '4':
                    //placisz kazdemu graczowi po 10zl
                    break;
                case '5':
                    //idz do wiezienia, nie przechodz przez start, nie pobieraj 200zl
                    break;
                case '6':
                    //dostajesz 50zl
                    break;
                case '7':
                    //tracisz 100zl
                    break;
            }
        }
    }

}
