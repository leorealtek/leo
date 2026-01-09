package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Trapezio extends Quadrilatero{
    protected int lunghezzaBaseMaggiore;
    protected int lunghezzaBaseMinore;
    protected int altezza;
    
    public Trapezio(Punto a, int lunghezzaBaseMinore, int lunghezzaBaseMaggiore, int altezza, int offsetDaSinistra) {
        super(a, null, null, null);
        this.lunghezzaBaseMaggiore = lunghezzaBaseMaggiore;
        this.lunghezzaBaseMinore = lunghezzaBaseMinore;
        this.altezza = altezza;
        Punto[] punti = creaCostruttore(a, lunghezzaBaseMinore, lunghezzaBaseMaggiore, altezza, offsetDaSinistra);
        b = punti[0];
        c = punti[1];
        d = punti[2];
    }

    public Trapezio(Punto a, int lunghezzaBaseMinore, int lunghezzaBaseMaggiore, int altezza) {
        super(a, null, null, null);
        this.lunghezzaBaseMaggiore = lunghezzaBaseMaggiore;
        this.lunghezzaBaseMinore = lunghezzaBaseMinore;
        Punto[] punti = creaCostruttore(a, lunghezzaBaseMinore, lunghezzaBaseMaggiore, altezza, 0);
        b = punti[0];
        c = punti[1];
        d = punti[2];
    }

    public static Punto[] creaCostruttore(Punto a, int lunghezzaBaseMinore, int lunghezzaBaseMaggiore, int altezza, int offsetDaSinistra) {
        Punto[] punti = new Punto[3];
        punti[0] = new Punto(a.getX() + lunghezzaBaseMaggiore, a.getY());
        punti[1] = new Punto(punti[1].getX() - offsetDaSinistra, punti[1].getY() + altezza);
        punti[2] = new Punto(punti[2].getX() - lunghezzaBaseMinore, punti[2].getY());
        return punti;
    }

    public double getArea() {
        return ((lunghezzaBaseMaggiore + lunghezzaBaseMinore) * altezza) / 2;
    }

    public int getLunghezzaBaseMaggiore() {
        return lunghezzaBaseMaggiore;
    }

    public int getLunghezzaBaseMinore() {
        return lunghezzaBaseMinore;
    }

    public int getAltezza() {
        return altezza;
    }

}
