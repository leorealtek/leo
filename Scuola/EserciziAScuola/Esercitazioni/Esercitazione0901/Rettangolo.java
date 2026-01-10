package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Rettangolo extends Quadrilatero{
    protected int larghezza;
    protected int altezza;

    public Rettangolo(Punto a, int larghezza, int altezza) {
        super(a, null, null, null);
        Punto[] punti = creaCostruttore(a, larghezza, altezza);
        b = punti[0];
        c = punti[1];
        d = punti[2];
        this.larghezza = larghezza;
        this.altezza = altezza;
    }

    public static Punto[] creaCostruttore(Punto a, int larghezza, int altezza) {
        Punto[] punti = new Punto[3];
        punti[0] = new Punto(a.getX() + larghezza, a.getY());
        punti[1] = new Punto(a.getX(), a.getY() + altezza);
        punti[2] = new Punto(punti[1].getX() + larghezza, punti[1].getY());
        return punti;
    }

    public double getArea() {
        return larghezza * altezza;
    }

    @Override
    public String toString() {
        return "Rettangolo [" + super.toString() + "]";
    }
    
}
