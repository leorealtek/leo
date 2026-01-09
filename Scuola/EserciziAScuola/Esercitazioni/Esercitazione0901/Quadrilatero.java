package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Quadrilatero {
    protected Punto a;
    protected Punto b;
    protected Punto c;
    protected Punto d;

    public Quadrilatero(Punto a, Punto b, Punto c, Punto d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void trasla(Direzione direzione, int distanza) {
        a.trasla(direzione, distanza);
        b.trasla(direzione, distanza);
        c.trasla(direzione, distanza);
        d.trasla(direzione, distanza);
    }

    public double getPerimetro() {
        return a.distanza(b) + b.distanza(c) + c.distanza(d) + d.distanza(a);
    }

    public Punto getA() {
        return a;
    }

    public Punto getB() {
        return b;
    }

    public Punto getC() {
        return c;
    }

    public Punto getD() {
        return d;
    }

    @Override
    public String toString() {
        return "Quadrilatero [A: " + a + " B: " + b + " C: " + c + " D: " + d + "]";
    }
}