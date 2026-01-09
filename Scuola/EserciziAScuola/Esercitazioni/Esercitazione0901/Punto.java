package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Punto {
    protected int x;
    protected int y;

    public Punto(int  x, int  y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Punto trasla(Direzione direzione, int distanza) {
        if (direzione.equals(Direzione.SU)) y += distanza;
        if (direzione.equals(Direzione.GIU)) y -= distanza;
        if (direzione.equals(Direzione.DESTRA)) x += distanza;
        if (direzione.equals(Direzione.SINISTRA)) x -= distanza;
        return this;
    }

    @Override
    public String toString() {
        return "Punto [Coordinata x: " + x + " Coordinata y:" + y + "]";
    }

    public double distanza(Punto p) {
        return Math.sqrt(Math.pow(this.x - p.getX(), 2) + Math.pow(this.y - p.getY(), 2));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Punto other = (Punto) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

}