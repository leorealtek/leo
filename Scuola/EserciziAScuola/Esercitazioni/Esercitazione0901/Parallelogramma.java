package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Parallelogramma extends Quadrilatero{
    protected int angolo;
    protected int lato1;
    protected int lato2;
    protected int lato3;
    protected int lato4;
    
    public Parallelogramma(Punto a, int lunghezza1, int lunghezza2, int angolo) {
        super(a,null,null,null);
        Punto[] punti = creaCostruttore(a, lunghezza1, lunghezza2, angolo);
        this.b = punti[0];
        this.c = punti[1];
        this.d = punti[2];
        this.angolo = angolo;
        this.lato1 = lunghezza1;
        this.lato2 = lunghezza2;
        this.lato3 = lunghezza1;
        this.lato4 = lunghezza2;
    }

    public static Punto[] creaCostruttore(Punto a, int lunghezza1, int lunghezza2, int angolo) {
        Punto[] punti = new Punto[3];
        punti[0] = new Punto(a.getX() + lunghezza1, a.getY());
        punti[1] = new Punto(a.getX() + lunghezza1 + (int)(lunghezza2 * Math.cos(Math.toRadians(angolo))), a.getY() + (int)(lunghezza2 * Math.sin(Math.toRadians(angolo))));
        punti[2] = new Punto(a.getX() + (int)(lunghezza2 * Math.cos(Math.toRadians(angolo))), a.getY() + (int)(lunghezza2 * Math.sin(Math.toRadians(angolo))));
        return punti;
    }

    public int getAngolo() {
        return angolo;
    }

    public int getLato1() {
        return lato1;
    }

    public int getLato2() {
        return lato2;
    }

    public int getLato3() {
        return lato3;
    }

    public int getLato4() {
        return lato4;
    }

}