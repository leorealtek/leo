package Scuola.Progettini.Scacchi.Util;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;

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

    public void muovi(int x, int y)  {
        Casella[][] possibili = mossePossibili();

        if (x < 0 || x >= 8 || y < 0 || y >= 8 || possibili[x][y] == null) {
            throw new MossaNonValidaException(
                "Il pezzo non può muoversi in (" + x + ", " + y + ")"
            );
        }

        mappa[posX][posY].inserisciPezzo(null);
        mappa[x][y].inserisciPezzo(this);
        posX = x;
        posY = y;
    }

    protected abstract Casella[][] mossePossibili();

    public void mostraMossePossibili() {
        Casella[][] mossePossibili = mossePossibili();
        for (Casella[] caselle : mossePossibili) {
            for (Casella casella : caselle) {
                if (casella != null) {
                    System.out.print(casella.getPezzoContenuto().getNome() + " ");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

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