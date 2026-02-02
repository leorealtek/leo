package Scuola.Progettini.Zukkaoh.Carte.Exodia;

import Scuola.Progettini.Zukkaoh.Carte.Carta;

public class ExodiaBraccio extends Carta {

    public static int contatoreIstanze = 0;
    
    public ExodiaBraccio() {
        contatoreIstanze++;
        this.puntiVita = 0;
        this.puntiAttacco = 0;
        this.puntiDifesa = 0;
        this.target = null;
        this.abilita = null;
        this.nome = "Exodia il Proibito (Braccio)";
    }

    @Override
    public String toString() {
        return "Carta [Exodia il Proibito (Braccio)]";
    }
}