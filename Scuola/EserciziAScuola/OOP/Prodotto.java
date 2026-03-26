package Scuola.EserciziAScuola.OOP;

public abstract class Prodotto {
    protected String nome;
    protected double prezzo;

    public Prodotto(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public void applicaIVA(double aliquota) {
        prezzo += prezzo * aliquota;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }
        
    @Override
    public abstract String toString();

}