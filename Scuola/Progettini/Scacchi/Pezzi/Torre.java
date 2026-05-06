package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.*;

public class Torre extends Pezzo {

    private boolean haMosso;

    public Torre(char nome, int riga, int colonna, Casella[][] mappa) {
        super(nome, riga, colonna, mappa);
        this.haMosso = !((isBianco && riga == 7 && (colonna == 0 || colonna == 7))
                      || (!isBianco && riga == 0 && (colonna == 0 || colonna == 7)));
    }

    @Override
    public void muovi(int x, int y) {
        super.muovi(x, y);
        haMosso = true;
    }
    
    public void muoviArrocco(int x, int y) {
        mappa[riga][colonna].rimuoviPezzo();
        aggiornaPosizione(x, y);
        haMosso = true;
        mappa[riga][colonna].inserisciPezzo(this);
    }

    @Override
    public Casella[][] mossePossibili() {
        Casella[][] casellePossibili = new Casella[8][8];

        int[][] direzioni = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        for (int[] dir : direzioni) {
            int x = riga + dir[0];
            int y = colonna + dir[1];

            while (coordinateValide(x, y)) {
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

    public boolean haMosso() {
        return haMosso;
    }

    public void setHaMosso(boolean haMosso) {
        this.haMosso = haMosso;
    }
}