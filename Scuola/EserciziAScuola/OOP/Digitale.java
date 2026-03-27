package Scuola.EserciziAScuola.OOP;

public class Digitale extends Prodotto implements Scontabile, Spedibile{

    public Digitale(String nome, double prezzo) {
        super(nome, prezzo);
    }

    @Override
    public double getPrezzoAttuale() {
        return prezzo;
    }

    @Override
    public void applicaSconto(int percentuale) {
        prezzo -= (prezzo*percentuale) / 100;
    }
    

    @Override
    public double calcolaSpese() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Digitale [Nome: " + nome + " Prezzo: " + prezzo + "]";
    }

    @Override
    public String corriere() {
        return "Download diretto";
    }
}
