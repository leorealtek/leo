package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Cavallo extends Pezzo {

    public Cavallo(char nome, int posX, int posY, Casella[][] mappa) {
        super(nome, posX, posY, mappa);
    }

    @Override
    protected Casella[][] mossePossibili() {
        Casella[][] casellePossibili = new Casella[8][8];

        int[][] mosse = {
            {-2, -1}, {-2, +1},
            {-1, -2}, {-1, +2},
            {+1, -2}, {+1, +2},
            {+2, -1}, {+2, +1}
        };

        for (int[] mossa : mosse) {
            int x = posX + mossa[0];
            int y = posY + mossa[1];

            if (x < 0 || x >= 8 || y < 0 || y >= 8) continue;

            Casella casella = mappa[x][y];

            if (casella.isVuota() || casella.getPezzoContenuto().isBianco() != this.isBianco) {
                casellePossibili[x][y] = casella;
            }
        }

        return casellePossibili;
    }
    
}