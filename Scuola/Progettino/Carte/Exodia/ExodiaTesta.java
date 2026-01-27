package Scuola.Progettino.Carte.Exodia;

import Scuola.Progettino.Carte.Carta;

public class ExodiaTesta extends Carta {

    public static int contatoreIstanze = 0;

    public ExodiaTesta() {
        contatoreIstanze++;
        this.puntiVita = 0;
        this.puntiAttacco = 0;
        this.puntiDifesa = 0;
        this.target = null;
        this.abilita = null;
        this.nome = "Exodia il Proibito (Testa)";
    }

    @Override
    public String toString() {
        return "Carta [Exodia il Proibito (Testa)]";
    }
}
