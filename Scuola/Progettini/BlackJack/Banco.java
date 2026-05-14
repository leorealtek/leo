package Scuola.Progettini.BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banco {
    private static final String[] SEMI = {"Cuori", "Quadri", "Fiori", "Picche"};
    private static final char[] VALORI = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K'};

    private List<Giocatore> giocatori;
    private Giocatore dealer;
    private List<Carta> mazzo;
    private int quantiMazzi;

    public Banco(Giocatore dealer, int quantiMazzi) {
        this.dealer = dealer;
        this.giocatori = new ArrayList<>();
        this.quantiMazzi = quantiMazzi <= 0 ? 1 : quantiMazzi;

        if (this.dealer != null) {
            this.dealer.setBanco(this);
        }

        creaMazzo(this.quantiMazzi);
    }

    public Banco(Giocatore dealer) {
        this(dealer, 1);
    }

    private void creaMazzo(int quantiMazzi) {
        mazzo = new ArrayList<>();

        for (int i = 0; i < quantiMazzi; i++) {
            for (String seme : SEMI) {
                for (char valore : VALORI) {
                    mazzo.add(new Carta(seme, valore));
                }
            }
        }

        Collections.shuffle(mazzo);
    }

    public Carta pescaCarta() {
        if (mazzo.isEmpty()) {
            creaMazzo(quantiMazzi);
        }

        return mazzo.remove(mazzo.size() - 1);
    }

    public void distribuisciCarteIniziali() {
        for (int i = 0; i < 2; i++) {
            for (Giocatore giocatore : giocatori) {
                giocatore.pescaCarta();
            }
            dealer.pescaCarta();
        }
    }

    public void turnoDealer() {
        while (dealer.getPunteggio() < 17) {
            dealer.pescaCarta();
        }
    }

    public String risultato(Giocatore giocatore) {
        int puntiGiocatore = giocatore.getPunteggio();
        int puntiDealer = dealer.getPunteggio();

        if (giocatore.haSballato()) {
            return giocatore.getNome() + " ha sballato e perde";
        }

        if (dealer.haSballato()) {
            return giocatore.getNome() + " vince perché il dealer ha sballato";
        }

        if (giocatore.haBlackjack() && !dealer.haBlackjack()) {
            return giocatore.getNome() + " ha fatto blackjack e vince";
        }

        if (!giocatore.haBlackjack() && dealer.haBlackjack()) {
            return giocatore.getNome() + " perde perché il dealer ha fatto blackjack";
        }

        if (puntiGiocatore > puntiDealer) {
            return giocatore.getNome() + " ha vinto";
        }

        if (puntiGiocatore < puntiDealer) {
            return giocatore.getNome() + " ha perso";
        }

        return giocatore.getNome() + " ha pareggiato";
    }

    public List<Integer> getPunteggiGiocatori() {
        List<Integer> punteggi = new ArrayList<>();

        for (Giocatore giocatore : giocatori) {
            punteggi.add(giocatore.getPunteggio());
        }

        return punteggi;
    }

    public void aggiungiGiocatore(Giocatore giocatore) {
        giocatore.setBanco(this);
        giocatori.add(giocatore);
    }

    public List<Giocatore> getGiocatori() {
        return giocatori;
    }

    public Giocatore getDealer() {
        return dealer;
    }

    public List<Carta> getMazzo() {
        return mazzo;
    }
}