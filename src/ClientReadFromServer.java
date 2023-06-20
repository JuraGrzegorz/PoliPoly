import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class ClientReadFromServer extends Thread{
    private final Client client;
    private final Player player;
    private final MenuWindow menuWindow;
    private final List<JButton> listButtons;
    private int countOfPlayer;
    private final OkConfirmPopUp alertWindow;
    GamingWindow gamingWindow;
    int playerCash;
    int[] playersPosition;
    int localPlayerNumber;
    DeckOfCards chanceDeck;
    DeckOfCards kasaspolDeck;
    int[] houseCount;
    public ClientReadFromServer(Client client, MenuWindow menuWindow, List<JButton> listButtons, OkConfirmPopUp alertWindow, Player player) {
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
        houseCount=new int[32];
        for(int i=0;i<32;i++){
            houseCount[i]=0;
        }
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
                    message=message.substring(("gameStarted:").length());
                    String[] tmp=message.split(":");
                    localPlayerNumber= Integer.parseInt(tmp[0]);
                    gamingWindow=new GamingWindow(client.fromClient);

                    gamingWindow.diceRollPanel.setVisible(false);
                    gamingWindow.buyPropertyButton.setVisible(false);
                    gamingWindow.endRound.setVisible(false);
                    CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                    gamingWindow.playerTurnPanel.setPlayername(tmp[1]);
                    client.fromClient.println("Starting");
                    for(int i=1;i<32;i++){
                        for(int j=0;j<4;j++){
                            showOrHide(gamingWindow.pawnPanel,i,j,0);
                        }
                    }
                    for(int i=0;i<17;i++){
                        for(int j=0;j<3;j++){
                            gamingWindow.housePanel[i][j].setVisible(false);
                        }
                    }
                    alertWindow.setMessage("Idź do V Domu Studenta. Nie przechodź przez START. Nie pobieraj 200P$.");
                    alertWindow.show();
                }

                if(message.startsWith("move:")){
                    int playerNumber;
                    int moveNumber;
                    message = message.substring(("move:").length());

                    String[] tmp=message.split(":");
                    playerNumber=Integer.parseInt(tmp[1]);
                    moveNumber=Integer.parseInt(tmp[0]);

                    if(Integer.parseInt(tmp[2])==-1 && playerCash>=gamingWindow.facultyPrices[playersPosition[playerNumber]]){
                        gamingWindow.buyPropertyButton.setVisible(true);
                    }
                    if(moveNumber==18){
                        playerCash-=100;
                        CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                    }
                    Random rand = new Random();
                    if(Integer.parseInt(tmp[2])==0){
                        if(moveNumber==2 || moveNumber==13){
                            playerCash-=rand.nextInt(5)*10;
                        }else{
                            if(houseCount[moveNumber]>0){
                                System.out.print(houseCount[moveNumber]+" \n");
                                playerCash-=(gamingWindow.facultyPrices[moveNumber]/2)*houseCount[moveNumber];
                            }else{
                                playerCash-=gamingWindow.facultyPrices[moveNumber]/5;
                            }
                        }
                        CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                        gamingWindow.buyPropertyButton.setVisible(false);
                    }
                    if(Integer.parseInt(tmp[2])==1){
                        if(moveNumber==2 || moveNumber==13){
                            playerCash+=rand.nextInt(5)*10;
                        }else{
                            if(houseCount[moveNumber]>0){
                                System.out.print(houseCount[moveNumber]+" \n");
                                playerCash+=(gamingWindow.facultyPrices[moveNumber]/2)*houseCount[moveNumber];
                            }else{
                                playerCash+=gamingWindow.facultyPrices[moveNumber]/5;
                            }
                        }
                        CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                    }
                    if(Integer.parseInt(tmp[2])==2){
                        if(playerCash>=gamingWindow.facultyPrices[moveNumber]/2 && houseCount[moveNumber]<3){
                            gamingWindow.buyPropertyButton.setVisible(true);
                            System.out.print("KUPUJ DOMKI");
                        }
                    }
                    if(playerNumber==localPlayerNumber){
                        gamingWindow.endRound.setVisible(true);
                    }
                    showOrHide(gamingWindow.pawnPanel,playersPosition[playerNumber],playerNumber,0);
                    showOrHide(gamingWindow.pawnPanel,moveNumber,playerNumber,1);

                    playersPosition[playerNumber]=moveNumber;

//                    Thread.sleep(50);
                    gamingWindow.diceRollPanel.setVisible(false);
                    PlaySoundEffect.playSound("assets\\sounds\\pawnjump.wav");

                }
                if(message.startsWith("message:")){
                    int val= Integer.parseInt(message.substring(("message:").length()));
                    switch (val){
                        case 0:
                            alertWindow.setMessage("Idź do Instytutu Obrabiarek. Jeśli przejdziesz przez start, pobierz 200P$.");
                            //idz do instytutu obrabiarek
                            break;
                        case 1:
                            alertWindow.setMessage("Idź do najbliższego Akademika. Jeśli nie posiada on jeszcze Namiestnika" +
                                    "Rady Akademickiej, możesz się nim stać za 200 P$. Jeśli to stanowisko jest już zajęte, zapłać Namiestnikowi odpowiednią kwotę.");
                            //idz do najblizszego akademika, mozesz kupic jesli wolny, zaplac jesli nalezy do gracza
                            break;
                        case 2:
                            alertWindow.setMessage("Idź do Centrum Językowego. Jeśli nie posiada ono jeszcze Dorywczego Korepetytora, możesz się nim stać za 50P$. Jeśli to" +
                                    "stanowisko jest już zajęte, zapłać Korepetytorowi 25P$.");
                            //idz do CJ, mozesz kupic jesli wolny, zaplac jesli nalezy do gracza
                            break;
                        case 3:
                            alertWindow.setMessage("Idź do V Domu Studenta. Nie przechodź przez START. Nie pobieraj 200P$.");
                            //idz do wiezienia, nie przechodz przez start, nie pobieraj 200zl
                            break;
                        case 4:
                            alertWindow.setMessage("Za swoje zasługi dla uczelni otrzymujesz uścisk dłoni Rektora (jest bezcenny).");
                            //karta zart, nic sie nie dzieje
                            break;
                        case 5:
                            alertWindow.setMessage("Cofnij się o trzy pola.");
                            //cofnij sie o 3 pola
                            break;
                        case 6:
                            alertWindow.setMessage("Przejdź na Instytut Marketingu.");
                            //przejdz do instytutu marketingu (ostatnia ulica przed startem)
                            break;
                        case 7:
                            alertWindow.setMessage("Doktoranci i Profesorowie rządają wypłacenia premii. Zapłać 25P$ na Doktoranta " +
                                    "i 100P$ na Profesora na każdym Wydziale, na którym jesteś Asystentem Dziekana.");
                            //plac 25zl za kazdy domek i 100zl za kazdy hotel
                            break;
                        case 8:
                            alertWindow.setMessage("Tym razem nie zgubiłeś się w drodze powrotnej do domu po imprezie. Przejdź na START i pobierz 200P$.");
                            playerCash+=200;
                            CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                            //przejdz na start i pobierz 200zl
                            break;
                        case 9:
                            alertWindow.setMessage("Otrzymujesz prestiżową Nagrodę Dziekana. Pobierz 150P$.");
                            playerCash+=150;
                            CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                            ////dostajesz 150zl
                            break;
                        case 10:
                            alertWindow.setMessage("Przejdź do Instytutu Marketingu");
                            //wracasz do Instytut Matematyki
                            break;
                        case 11:
                            alertWindow.setMessage("Idź do V Domu Studenta. Nie przechodź przez START. Nie pobieraj 200P$.");
                            //idz do wiezienia, nie przechodz przez start, nie pobieraj 200zl
                            break;
                        case 12:
                            alertWindow.setMessage("Pomogłeś koledze pozbyć się Segmentation Faulta. Pobierz 50P$.");
                            playerCash+=50;
                            CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                            //dostajesz 50zl
                            break;
                        case 13:
                            alertWindow.setMessage("Zgubiłeś portfel na torowisku tramwajowym wracając z imprezy. Tracisz 100P$.");
                            playerCash-=100;
                            CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                            //tracisz 100zl
                            break;
                    }
//                    TimeUnit.SECONDS.sleep(1);
                    alertWindow.show();
                }
                if(message.equals("Bought:")){
                    PlaySoundEffect.playSound("assets\\sounds\\kaching.wav");

                    playerCash-=gamingWindow.facultyPrices[playersPosition[localPlayerNumber]];
                    CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                    gamingWindow.buyPropertyButton.setVisible(false);
                    gamingWindow.endRound.setVisible(false);
                    CardsPanel.addCardToPanel(gamingWindow.facultyColor[playersPosition[localPlayerNumber]],gamingWindow.facultyNames[playersPosition[localPlayerNumber]]);
                }
                if(message.startsWith("setHouse:")){
                    gamingWindow.buyPropertyButton.setVisible(false);
                    gamingWindow.endRound.setVisible(false);
                    PlaySoundEffect.playSound("assets\\sounds\\kaching.wav");
                    int position= Integer.parseInt(message.substring(("setHouse:").length()));
                    if(playersPosition[localPlayerNumber]==position){
                        playerCash-=gamingWindow.facultyPrices[position]/2;
                    }
                    CashPanel.playerCash.setText(String.format("%d P$", playerCash));
                    setHouse(position,houseCount[position]);
                    houseCount[position]++;
                }
                if(message.equals("cash:")){
                    playerCash+=200;
                    CashPanel.playerCash.setText(String.format("%d P$", playerCash));
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
    void setHouse(int position,int count){
        if(position==1){
            gamingWindow.housePanel[14][count].setVisible(true);
        }
        if(position==3){
            gamingWindow.housePanel[8][count].setVisible(true);
        }
        if(position==5){
            gamingWindow.housePanel[5][count].setVisible(true);
        }
        if(position==7){
            gamingWindow.housePanel[1][count].setVisible(true);
        }
        if(position==9){
            gamingWindow.housePanel[0][count].setVisible(true);
        }
        if(position==11){
            gamingWindow.housePanel[4][count].setVisible(true);
        }
        if(position==14){
            gamingWindow.housePanel[11][count].setVisible(true);
        }
        if(position==15){
            gamingWindow.housePanel[13][count].setVisible(true);
        }
        if(position==17){
            gamingWindow.housePanel[3][count].setVisible(true);
        }
        if(position==19){
            gamingWindow.housePanel[7][count].setVisible(true);
        }
        if(position==21){
            gamingWindow.housePanel[10][count].setVisible(true);
        }
        if(position==22){
            gamingWindow.housePanel[12][count].setVisible(true);
        }
        if(position==23){
            gamingWindow.housePanel[16][count].setVisible(true);
        }
        if(position==25){
            gamingWindow.housePanel[15][count].setVisible(true);
        }
        if(position==27){
            gamingWindow.housePanel[9][count].setVisible(true);
        }
        if(position==29){
            gamingWindow.housePanel[6][count].setVisible(true);
        }
        if(position==31){
            gamingWindow.housePanel[2][count].setVisible(true);
        }
    }
}
