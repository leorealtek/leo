package Scuola.EserciziAScuola.OOP;

public class Abbigliamento extends Prodotto implements Scontabile {
    private double taglia;

    public Abbigliamento(String nome, double prezzo, double taglia) {
        super(nome, prezzo);
        this.taglia = taglia;
    }

    @Override
    public double getPrezzoAttuale() {
        return prezzo;
    }

    @Override
    public void applicaSconto(int percentuale) {
        prezzo -= (prezzo*percentuale) / 100;
    }

    public double getTaglia() {
        return taglia;
    }

    @Override
    public String toString() {
        return "Abbigliamento [nome=" + nome + ", taglia=" + taglia + ", prezzo=" + prezzo + "]";
    }
}