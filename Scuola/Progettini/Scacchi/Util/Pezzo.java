package Scuola.Progettini.Scacchi.Util;

public abstract class Pezzo {
    protected char nome;
    protected int posX;
    protected int posY;
    protected boolean isBianco;
    protected Casella[][] mappa;

    public Pezzo(char nome, int posX, int posY, Casella[][] mappa) {
        this.nome = nome;
        this.posX = posX;
        this.posY = posY;
        isBianco = Character.isUpperCase(nome);
        this.mappa = mappa;
    }

    protected abstract void muovi(int x, int y);
    protected abstract Casella[][] mossePossibili();

    public char getNome() {
        return nome;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isBianco() {
        return isBianco;
    }

    
    
}