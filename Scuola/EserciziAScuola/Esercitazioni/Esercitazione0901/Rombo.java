package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Rombo extends Quadrilatero {
    protected int lunghezzaDiagonaleMaggiore;
    protected int lunghezzaDiagonaleMinore;

    public Rombo(Punto puntoCentrale, int lunghezzaDiagonaleMinore, int lunghezzaDiagonaleMaggiore) {
        super(null, null, null, null);
        Punto[] punti = creaCostruttore(puntoCentrale, lunghezzaDiagonaleMinore, lunghezzaDiagonaleMaggiore);
        a = punti[0];
        b = punti[1];
        c = punti[2];
        d = punti[3];
        this.lunghezzaDiagonaleMaggiore = lunghezzaDiagonaleMaggiore;
        this.lunghezzaDiagonaleMinore = lunghezzaDiagonaleMinore;
    }

    public Rombo(Punto puntoCentrale, int lunghezzaLato, double angolo) {
        super(null, null, null, null);
        Punto[] punti = creaCostruttore(puntoCentrale, lunghezzaLato, angolo);
        a = punti[0];
        b = punti[1];
        c = punti[2];
        d = punti[3];
        this.lunghezzaDiagonaleMinore = (int)(2 * lunghezzaLato * Math.sin(Math.toRadians(angolo) / 2));
        this.lunghezzaDiagonaleMaggiore = (int)(2 * lunghezzaLato * Math.cos(Math.toRadians(angolo) / 2));
    }

    public static Punto[] creaCostruttore(Punto puntoCentrale, int lunghezzaDiagonaleMinore, int lunghezzaDiagonaleMaggiore) {
        Punto[] punti = new Punto[4];
        punti[0] = new Punto(puntoCentrale.getX() - (int) (lunghezzaDiagonaleMinore / 2.0f), puntoCentrale.getY());
        punti[1] = new Punto(puntoCentrale.getX(), puntoCentrale.getY() - (int) (lunghezzaDiagonaleMaggiore / 2.0f));
        punti[2] = new Punto(puntoCentrale.getX() + (int) (lunghezzaDiagonaleMinore / 2.0f), puntoCentrale.getY());
        punti[3] = new Punto(puntoCentrale.getX(), puntoCentrale.getY() + (int) (lunghezzaDiagonaleMaggiore / 2.0f));
        return punti;
    }

    public static Punto[] creaCostruttore(Punto puntoCentrale, int lunghezzaLato, double angolo) {
        double angoloRad = Math.toRadians(angolo);
        int diagonaleMinore = (int)(2 * lunghezzaLato * Math.sin(angoloRad / 2));
        int diagonaleMaggiore = (int)(2 * lunghezzaLato * Math.cos(angoloRad / 2));
        return creaCostruttore(puntoCentrale, diagonaleMinore, diagonaleMaggiore);
    }
    
}