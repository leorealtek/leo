package Scuola.Progettini.BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void checkWin(List<Giocatore> giocatori, Giocatore dealer, Banco banco) {
        for (Giocatore giocatore : giocatori) {
            System.out.println(banco.risultato(giocatore));
        }
    }

    public static void main(String[] args) {
        Giocatore dealer = new Giocatore("Dealer");
        Banco banco = new Banco(dealer);

        ArrayList<Giocatore> giocatori = new ArrayList<>(List.of(
                new Giocatore("Pippo"),
                new Giocatore("Gianfranco"),
                new Giocatore("Matteo")
        ));

        for (Giocatore giocatore : giocatori) {
            banco.aggiungiGiocatore(giocatore);
        }

        banco.distribuisciCarteIniziali();
        banco.turnoDealer();

        for (Giocatore giocatore : banco.getGiocatori()) {
            System.out.println("\n" + giocatore.getNome() + " - punti: " + giocatore.getPunteggio());

            for (Carta carta : giocatore.getCarteInMano()) {
                System.out.println(carta);
            }
        }

        System.out.println("\n" + dealer.getNome() + " - punti: " + dealer.getPunteggio());

        for (Carta carta : dealer.getCarteInMano()) {
            System.out.println(carta);
        }

        System.out.println();

        checkWin(banco.getGiocatori(), dealer, banco);
    }
}