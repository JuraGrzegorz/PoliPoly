
// deckID = 0 -> talia kart szansy
// deckID = 1 -> talia kart kasy spolecznej

import javax.swing.*;
import java.util.*;

public class DeckOfCards extends JPanel {
    Deque<String> cardsDeck;

    DeckOfCards(short deckID){
        cardsDeck = new ArrayDeque<>();
        if(deckID == 0){
            initializeDeck(deckID);
        }
        if(deckID == 1){
            initializeDeck(deckID);
        }
        else System.out.println("Invalid deckID while calling DeckOfCards constructor");
    }

    void initializeDeck(short deckID){
        if(deckID == 0){
            cardsDeck.add("Idź do Instytutu Obrabiarek. Jeśli przejdziesz przez start, pobierz 200 zł.");
            cardsDeck.add("Idź do najbliższego Akademika. Jeśli nie posiada on jeszcze Namiestnika " +
                    "Rady Akademickiej, możesz się nim stać za 200 zł. Jeśli to stanowisko jest już zajęte, zapłać Namiestnikowi odpowiednią kwotę.");
            cardsDeck.add("Idź do Centrum Językowego. Jeśli nie posiada ono jeszcze Dorywczego Korepetytora, możesz się nim stać za 50zł. Jeśli to " +
                    "stanowisko jest już zajęte, zapłać Korepetytorowi 25zł.");
            cardsDeck.add("Idź do V Domu Studenta. Nie przechodź przez START. Nie pobieraj 200 zł.");
            cardsDeck.add("Za swoje zasługi dla uczelni otrzymujesz uścisk dłoni Rektora (jest bezcenny).");
            cardsDeck.add("Cofnij się o trzy pola.");
            cardsDeck.add("Przejdź na Instytut Marketingu.");
        }
        if(deckID == 1){
            cardsDeck.add("Doktoranci i Profesorowie rządają wypłacenia premii. Zapłać 25 zł na Doktoranta " +
                    "i 100 zł na Profesora na każdym Wydziale, na którym jesteś Asystentem Dziekana.");
            cardsDeck.add("Tym razem nie zgubiłeś się w drodze powrotnej do domu po imprezie. Przejdź na START i pobierz 200 zł.");
            cardsDeck.add("Otrzymujesz prestiżową Nagrodę Dziekana. Pobierz 150 zł.");
            cardsDeck.add("Popełniłeś błąd w obliczeniach po zrzutce na shoty w klubie. Zapłać pozostałym graczom po 10 zł.");
            cardsDeck.add("Idź do V Domu Studenta. Nie przechodź przez START. Nie pobieraj 200 zł.");
            cardsDeck.add("Pomogłeś koledze pozbyć się Segmentation Faulta. Pobierz 50 zł.");
            cardsDeck.add("Zgubiłeś portfel na torowisku tramwajowym wracając z imprezy. Tracisz 100 zł.");
        }
    }

    void shuffleDeck(){
        List<String> cardsList = new ArrayList<>(cardsDeck);
        Collections.shuffle(cardsList);
        cardsDeck.clear();
        cardsDeck.addAll(cardsList);
    }

    String drawCard(){
        String topCard = cardsDeck.pop();
        cardsDeck.addLast(topCard);
        return topCard;
    }
}
