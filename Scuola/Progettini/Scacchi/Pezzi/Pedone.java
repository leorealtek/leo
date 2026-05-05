package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Pedone extends Pezzo {

    private boolean primaMossa;
    private boolean secondaMossa;

    public Pedone(char nome, int riga, int colonna, Casella[][] mappa) {
        super(nome, riga, colonna, mappa);
        this.primaMossa = (isBianco && riga == 6) || (!isBianco && riga == 1);
        this.secondaMossa = (isBianco && riga == 5 || riga == 4) || (!isBianco && riga == 2 || riga == 3);
    }

    @Override
    public void muovi(int x, int y) {
        int rigaPrecedente = riga;
        super.muovi(x, y);

        secondaMossa = Math.abs(x - rigaPrecedente) == 2;
        primaMossa = false;
    }

    @Override
    public Casella[][] mossePossibili() {
        Casella[][] casellePossibili = new Casella[8][8];

        int direzione = isBianco ? -1 : +1;
        int x1 = riga + direzione;
        if (coordinateValide(x1, colonna) && mappa[x1][colonna].isVuota()) {
            casellePossibili[x1][colonna] = mappa[x1][colonna];

            int x2 = riga + direzione * 2;
            if (primaMossa && coordinateValide(x2, colonna) && mappa[x2][colonna].isVuota()) {
                casellePossibili[x2][colonna] = mappa[x2][colonna];
            }
        }

        int[] colonneDiagonali = {colonna - 1, colonna + 1};
        for (int y : colonneDiagonali) {
            if (coordinateValide(x1, y)) {
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

    public boolean isSecondaMossa() {
        return secondaMossa;
    }

    public void setPrimaMossa(boolean primaMossa) {
        this.primaMossa = primaMossa;
    }

    public void setSecondaMossa(boolean secondaMossa) {
        this.secondaMossa = secondaMossa;
    }
}
