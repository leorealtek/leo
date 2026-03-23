package Scuola.EserciziAScuola.OOP;

public class Digitale extends Prodotto implements Scontabile, Spedibile{
    @Override
    public double getPrezzoAttuale() {
        return prezzo;
    }

    @Override
    public void applicaSconto(int percentuale) {
        prezzo -= (prezzo*percentuale) / 100;
    }
}
