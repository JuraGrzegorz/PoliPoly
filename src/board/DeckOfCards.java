
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
    }

    void initializeDeck(short deckID){
        if(deckID == 0){
            cardsDeck.add("1. Idź do Instytutu Obrabiarek. Jeśli przejdziesz przez start, pobierz 200P$.");
            cardsDeck.add("2. Idź do najbliższego Akademika. Jeśli nie posiada on jeszcze Namiestnika " +
                    "Rady Akademickiej, możesz się nim stać za 200 P$. Jeśli to stanowisko jest już zajęte, zapłać Namiestnikowi odpowiednią kwotę.");
            cardsDeck.add("3. Idź do Centrum Językowego. Jeśli nie posiada ono jeszcze Dorywczego Korepetytora, możesz się nim stać za 50P$. Jeśli to " +
                    "stanowisko jest już zajęte, zapłać Korepetytorowi 25P$.");
            cardsDeck.add("4. Idź do V Domu Studenta. Nie przechodź przez START. Nie pobieraj 200P$.");
            cardsDeck.add("5. Za swoje zasługi dla uczelni otrzymujesz uścisk dłoni Rektora (jest bezcenny).");
            cardsDeck.add("6. Cofnij się o trzy pola.");
            cardsDeck.add("7. Przejdź na Instytut Marketingu.");
        }
        if(deckID == 1){
            cardsDeck.add("1. Doktoranci i Profesorowie rządają wypłacenia premii. Zapłać 25P$ na Doktoranta " +
                    "i 100P$ na Profesora na każdym Wydziale, na którym jesteś Asystentem Dziekana.");
            cardsDeck.add("2. Tym razem nie zgubiłeś się w drodze powrotnej do domu po imprezie. Przejdź na START i pobierz 200P$.");
            cardsDeck.add("3. Otrzymujesz prestiżową Nagrodę Dziekana. Pobierz 150P$.");
            cardsDeck.add("4. Popełniłeś błąd w obliczeniach po zrzutce na shoty w klubie. Zapłać pozostałym graczom po 10P$.");
            cardsDeck.add("5. Idź do V Domu Studenta. Nie przechodź przez START. Nie pobieraj 200P$.");
            cardsDeck.add("6. Pomogłeś koledze pozbyć się Segmentation Faulta. Pobierz 50P$.");
            cardsDeck.add("7. Zgubiłeś portfel na torowisku tramwajowym wracając z imprezy. Tracisz 100P$.");
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
