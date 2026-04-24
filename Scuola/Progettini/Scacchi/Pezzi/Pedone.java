package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Pedone extends Pezzo {

    private boolean primaMossa;

    public Pedone(char nome, int posX, int posY, Casella[][] mappa) {
        super(nome, posX, posY, mappa);
        primaMossa = true;
    }

    @Override
    protected void muovi(int x, int y) {
        Casella[][] possibili = mossePossibili();

        if (x < 0 || x >= 8 || y < 0 || y >= 8 || possibili[x][y] == null) {
            throw new MossaNonValidaException(
                "Il pedone non può muoversi in (" + x + ", " + y + ")"
            );
        }

        mappa[posX][posY].inserisciPezzo(null);
        mappa[x][y].inserisciPezzo(this);
        posX = x;
        posY = y;
        primaMossa = false;
    }

    @Override
    protected Casella[][] mossePossibili() {
        Casella[][] casellePossibili = new Casella[8][8];

        int direzione = isBianco ? 1 : -1;

        int x1 = posX + direzione;
        if (x1 >= 0 && x1 < 8 && mappa[x1][posY].isVuota()) {
            casellePossibili[x1][posY] = mappa[x1][posY];

            int x2 = posX + direzione * 2;
            if (primaMossa && x2 >= 0 && x2 < 8 && mappa[x2][posY].isVuota()) {
                casellePossibili[x2][posY] = mappa[x2][posY];
            }
        }

        int[] colonneDiagonali = {posY - 1, posY + 1};
        for (int y : colonneDiagonali) {
            if (x1 >= 0 && x1 < 8 && y >= 0 && y < 8) {
                Casella casella = mappa[x1][y];
                if (!casella.isVuota() && casella.getPezzoContenuto().isBianco() != this.isBianco) {
                    casellePossibili[x1][y] = casella;
                }
            }
        }

        return casellePossibili;
    }

    public boolean isPrimaMossa() {
        return primaMossa;
    }
    
}