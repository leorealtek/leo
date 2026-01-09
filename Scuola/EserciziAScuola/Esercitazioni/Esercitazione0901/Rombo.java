package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Rombo extends Quadrilatero {

    public Rombo(Punto puntoCentrale, int lunghezzaDiagonaleMinore, int lunghezzaDiagonaleMaggiore) {
        super(null, null, null, null);
    }

    public static Punto[] creaCostruttore(Punto puntoCentrale, int lunghezzaDiagonaleMinore, int lunghezzaDiagonaleMaggiore) {
        Punto[] punti = new Punto[4];
        punti[0] = new Punto(puntoCentrale.getX() - (int) (lunghezzaDiagonaleMinore / 2.0f), puntoCentrale.getY());
        return punti;
    }
    
}