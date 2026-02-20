package Scuola.EserciziAScuola.Esercitazioni.Esercitazione2002;

import java.util.UUID;

public class Giocatore {
    private final String nome;
    private int partiteVinte;

    public Giocatore(int lunghezzaNome) {
        nome = UUID.randomUUID().toString().substring(0, lunghezzaNome);
        partiteVinte = 0;
    }

    public int generaNumeroCasuale() {
        return (int) (Math.random() * 10);
    }

    public String getNome() {
        return nome;
    }

    public int getPartiteVinte() {
        return partiteVinte;
    }

    public void setPartiteVinte(int partiteVinte) {
        this.partiteVinte = partiteVinte;
    }
    
    @Override
    public String toString() {
        return "Giocatore [Nome: " + nome + " Ha vinto: " + partiteVinte + " parite]";
    }
}