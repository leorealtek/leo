package Scuola.Progettini.GameOfLife.LangtonAnt;

public class Cella {
    public boolean ant = false;
    public boolean viva = false;

    public void cambiaStato() {
        viva = !viva;
    }

    public void cambiaAnt() {
        ant = !ant;
    }

}