package Scuola.EserciziAScuola.OOP;

public class Elettronico extends Prodotto implements Scontabile{
    private int garanziaMesi;

    public Elettronico(String nome, double prezzo, int garanziaMesi) {
        super(nome, prezzo);
        this.garanziaMesi = garanziaMesi;
    }

    @Override
    public double getPrezzoAttuale() {
        return prezzo;
    }

    @Override
    public void applicaSconto(int percentuale) {
        prezzo -= (prezzo*percentuale) / 100;
    }

    public int getGaranziaMesi() {
        return garanziaMesi;
    }

    @Override
    public String toString() {
        return "Elettronico [Nome: " + nome + " Garanzia mesi: " + garanziaMesi + " prezzo: " + prezzo + "]";
    }
    
}