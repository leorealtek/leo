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
        this.b = punti[1];
        this.c = punti[2];
        this.d = punti[3];
        this.angolo = angolo;
        this.lato1 = lunghezza1;
        this.lato2 = lunghezza2;
        this.lato3 = lunghezza1;
        this.lato4 = lunghezza2;
    }

    public static Punto[] creaCostruttore(Punto a, int lunghezza1, int lunghezza2, int angolo) {
        Punto[] punti = new Punto[3];
        int theta = (int) Math.toRadians(angolo);
        int x1 = a.getX();
        int y1 = a.getY();
        int x2 = x1 + lunghezza1;
        int y2 = y1;
        int x3 = x1 + lunghezza1 + lunghezza2 * (int) Math.cos(theta);
        int y3 = y1 + lunghezza2 * (int) Math.sin(theta); 
        int x4 = x1 + lunghezza2 * (int) Math.cos(theta);
        int y4 = y1 + lunghezza2 * (int) Math.sin(theta);

        punti[0] = new Punto(x2, y2);
        punti[1] = new Punto(x3, y3);
        punti[2] = new Punto(x4, y4);

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