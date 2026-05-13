package Scuola.Progettini.BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {
    private List<Carta> carteInMano;
    private String nome;
    private double saldo;
    private Banco banco;

    public Giocatore(String nome) {
        this.carteInMano = new ArrayList<>();
        this.nome = nome;
        this.saldo = 100.0d;
    }

    public Giocatore(String nome, Banco banco) {
        this(nome);
        this.banco = banco;
    }

    public void pescaCarta() {
        if (banco == null) {
            throw new IllegalStateException("Il giocatore non è collegato a nessun banco");
        }

        carteInMano.add(banco.pescaCarta());
    }

    public int calcolaPunteggio() {
        int punteggio = 0;
        int assi = 0;

        for (Carta carta : carteInMano) {
            punteggio += carta.getPunti();

            if (carta.isAsso()) {
                assi++;
            }
        }

        while (punteggio > 21 && assi > 0) {
            punteggio -= 10;
            assi--;
        }

        return punteggio;
    }

    public boolean haSballato() {
        return calcolaPunteggio() > 21;
    }

    public boolean haBlackjack() {
        return carteInMano.size() == 2 && calcolaPunteggio() == 21;
    }

    public void svuotaMano() {
        carteInMano.clear();
    }

    public List<Carta> getCarteInMano() {
        return carteInMano;
    }

    public int getPunteggio() {
        return calcolaPunteggio();
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}