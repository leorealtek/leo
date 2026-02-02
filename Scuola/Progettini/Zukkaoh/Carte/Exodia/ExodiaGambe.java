package Scuola.Progettini.Zukkaoh.Carte.Exodia;

import Scuola.Progettini.Zukkaoh.Carte.Carta;

public class ExodiaGambe extends Carta {

    public static int contatoreIstanze = 0;

    public ExodiaGambe() {
        contatoreIstanze++;
        this.puntiVita = 0;
        this.puntiAttacco = 0;
        this.puntiDifesa = 0;
        this.target = null;
        this.abilita = null;
        this.nome = "Exodia il Proibito (Gambe)";
    }

    @Override
    public String toString() {
        return "Carta [Exodia il Proibito (Gambe)]";
    }
    
}
