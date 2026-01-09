package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Rettangolo extends Quadrilatero{

    public Rettangolo(Punto a, int larghezza, int altezza) {
        super(a, a.trasla(Direzione.DESTRA, larghezza), a.trasla(Direzione.SU, altezza), a.trasla(Direzione.SINISTRA, larghezza));
    }

    public double getArea() {
        return a.distanza(b) * b.distanza(c);
    }

    @Override
    public String toString() {
        return "Rettangolo [" + super.toString() + "]";
    }
    
}
