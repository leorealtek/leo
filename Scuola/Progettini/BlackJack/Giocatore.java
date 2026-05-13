package Scuola.Progettini.BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Giocatore{
    private List<Carta> carteInMano;
    private int punteggio;
    private String nome;
    private double saldo;
    private Banco banco;
   
    public Giocatore(String nome) {
        this.carteInMano = new ArrayList<>();        
        this.punteggio = 0;
        this.nome = nome;
        this.saldo = 100.0d;
    }

    public void pescaCarta() {
        carteInMano.add(banco.getCartaRandom());
    }

    public List<Carta> getCarteInMano() {
        return carteInMano;
    }

    public int getPunteggio() {
        return punteggio;
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

    public void setCarteInMano(List<Carta> carteInMano) {
        this.carteInMano = carteInMano;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}