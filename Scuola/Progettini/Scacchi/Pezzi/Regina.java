package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.*;

public class Regina extends Pezzo {

    public Regina(char nome, int riga, int colonna, Casella[][] mappa) {
        super(nome, riga, colonna, mappa);
    }

    @Override
    public Casella[][] mossePossibili() {
        Casella[][] casellePossibili = new Casella[8][8];
        int[][] direzioni = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] dir : direzioni) {
            int x = riga + dir[0];
            int y = colonna + dir[1];

            while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                Casella casella = mappa[x][y];

                if (casella.isVuota()) {
                    casellePossibili[x][y] = casella;
                } else {
                    if (casella.getPezzoContenuto().isBianco() != this.isBianco) {
                        casellePossibili[x][y] = casella;
                    }
                    break;
                }

                x += dir[0];
                y += dir[1];
            }
        }

        return casellePossibili;
    }
}