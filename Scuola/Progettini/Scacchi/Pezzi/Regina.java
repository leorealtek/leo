package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Regina extends Pezzo {

    public Regina(char nome, int posX, int posY, Casella[][] mappa) {
        super(nome, posX, posY, mappa);
    }

    @Override
    protected void muovi(int x, int y) {
        Casella[][] possibili = mossePossibili();
    
        if (x < 0 || x >= 8 || y < 0 || y >= 8 || possibili[x][y] == null) {
            throw new MossaNonValidaException(
                "La regina non può muoversi in (" + x + ", " + y + ")"
            );
        }
    
        mappa[posX][posY].inserisciPezzo(null);
    
        mappa[x][y].inserisciPezzo(this);
    
        posX = x;
        posY = y;
    }
    

    @Override
    protected Casella[][] mossePossibili() {
        Casella[][] casellePossibili = new Casella[8][8];
        int[][] direzioni = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
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