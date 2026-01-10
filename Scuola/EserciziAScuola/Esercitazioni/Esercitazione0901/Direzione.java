package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public enum Direzione {
    SU(0, 1),
    GIU(0, -1),
    DESTRA(1, 0),
    SINISTRA(-1, 0);

    private final int x;
    private final int y;

    private Direzione(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}