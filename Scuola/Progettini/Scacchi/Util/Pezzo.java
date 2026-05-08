package Scuola.Progettini.Scacchi.Util;

public abstract class Pezzo {
    protected char nome;
    protected int riga;
    protected int colonna;
    protected boolean isBianco;
    protected Casella[][] mappa;

    public Pezzo(char nome, int riga, int colonna, Casella[][] mappa) {
        this.nome = nome;
        this.riga = riga;
        this.colonna = colonna;
        this.isBianco = Character.isUpperCase(nome);
        this.mappa = mappa;
    }

    public void muovi(int x, int y) {
        mappa[x][y].inserisciPezzo(this);
        mappa[riga][colonna].rimuoviPezzo();
        aggiornaPosizione(x, y);
    }

    protected boolean coordinateValide(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public void aggiornaPosizione(int x, int y) {
        if (!coordinateValide(x, y)) {
            throw new IllegalArgumentException("Coordinate non valide: (" + x + ", " + y + ")");
        }
        this.riga = x;
        this.colonna = y;
    }

    public abstract Casella[][] mossePossibili();

    public char getNome() {
        return nome;
    }

    public abstract double getValore();

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    public boolean isBianco() {
        return isBianco;
    }
}
