package Scuola.Progettini.Scacchi.Util;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;

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
        isBianco = Character.isUpperCase(nome);
        this.mappa = mappa;
    }

    public void muovi(int x, int y) {
        Casella[][] possibili = mossePossibili();

        if (x < 0 || x >= 8 || y < 0 || y >= 8 || possibili[x][y] == null) {
            throw new MossaNonValidaException(
                "Il pezzo non può muoversi in (" + x + ", " + y + ")"
            );
        }

        mappa[riga][colonna].inserisciPezzo(null);
        mappa[x][y].inserisciPezzo(this);
        riga = x;
        colonna = y;
    }

    public abstract Casella[][] mossePossibili();

    public void mostraMossePossibili() {
        Casella[][] mossePossibili = mossePossibili();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (mossePossibili[i][j] != null) {
                    System.out.print("X ");
                } else if (mappa[i][j].getPezzoContenuto() != null) {
                    System.out.print(mappa[i][j].getPezzoContenuto().getNome() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public char getNome() {
        return nome;
    }

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