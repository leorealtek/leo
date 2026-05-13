package Scuola.Progettini.BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private static final String[] SEMI = {"Cuori", "Quadri", "Fiori", "Picche"};
    private static final char[] VALORI = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K'};

    private List<Giocatore> giocatori;
    private Carta[] mazzo;

    public Banco(int quantiMazzi) {
        this.giocatori = new ArrayList<>();
        creaMazzo(quantiMazzi);
    }

    private void creaMazzo(int quantiMazzi) {
        if (quantiMazzi == 0) {
            this.mazzo = new Carta[52];
        }
        else {
            this.mazzo = new Carta[52 * quantiMazzi];
        }

        for (int i = 0; i < quantiMazzi; i++) {
            for (String seme : SEMI) {
                for (int j = 0; j < VALORI.length; j++) {
                    mazzo[j] = new Carta(seme, VALORI[j]);
                }
            }
        }
    }

    public Carta getCartaRandom() {
        int indiceCarta = (int) (Math.random() * (int) (mazzo.length / 52));
        Carta cartaRandom = mazzo[indiceCarta];
        mazzo[indiceCarta] = null;
        return cartaRandom;
    }

    public List<Integer> getPunteggiGiocatori() {
        List<Integer> punteggi = new ArrayList<>();
        for (Giocatore giocatore : giocatori) {
            int punteggioGiocatore = 0;
            for (Carta carta : giocatore.getCarteInMano()) {
                punteggioGiocatore += carta.getValore();
            }
            punteggi.add(punteggioGiocatore);
        }
        return punteggi;
    }

    public void aggiungiGiocatore(Giocatore giocatore) {
        this.giocatori.add(giocatore);
    }

    public List<Giocatore> getGiocatori() {
        return giocatori;
    }

    public Carta[] getMazzo() {
        return mazzo;
    }

}