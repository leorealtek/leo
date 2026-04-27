package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Alfiere extends Pezzo {

    public Alfiere(char nome, int posX, int posY, Casella[][] mappa) {
        super(nome, posX, posY, mappa);
    }
    
    @Override
    protected Casella[][] mossePossibili() {
        Casella[][] casellePossibili = new Casella[8][8];

        int[][] direzioni = {
            {+1, +1}, {+1, -1}, {-1, +1}, {-1, -1}
        };

        for (int[] dir : direzioni) {
            int x = posX + dir[0];
            int y = posY + dir[1];

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
