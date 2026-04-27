package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Pedone extends Pezzo {

    private boolean primaMossa;

    public Pedone(char nome, int riga, int colonna, Casella[][] mappa) {
        super(nome, riga, colonna, mappa);
        primaMossa = true;
    }

    @Override
    public void muovi(int x, int y) {
        super.muovi(x, y);
        primaMossa = false;
    }

    @Override
    public Casella[][] mossePossibili() {
        Casella[][] casellePossibili = new Casella[8][8];

        int direzione = isBianco ? -1 : +1;
        int x1 = riga + direzione;
        if (x1 >= 0 && x1 < 8 && mappa[x1][colonna].isVuota()) {
            casellePossibili[x1][colonna] = mappa[x1][colonna];

            int x2 = riga + direzione * 2;
            if (primaMossa && x2 >= 0 && x2 < 8 && mappa[x2][colonna].isVuota()) {
                casellePossibili[x2][colonna] = mappa[x2][colonna];
            }
        }

        int[] colonneDiagonali = {colonna - 1, colonna + 1};
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