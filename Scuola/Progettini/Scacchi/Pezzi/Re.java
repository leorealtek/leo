package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Re extends Pezzo {

    private boolean haMosso;

    public Re(char nome, int posX, int posY, Casella[][] mappa) {
        super(nome, posX, posY, mappa);
        haMosso = false;
    }

    @Override
    protected void muovi(int x, int y) {
        Casella[][] possibili = mossePossibili();

        if (x < 0 || x >= 8 || y < 0 || y >= 8 || possibili[x][y] == null) {
            throw new MossaNonValidaException(
                "Il re non può muoversi in (" + x + ", " + y + ")"
            );
        }

        mappa[posX][posY].inserisciPezzo(null);
        mappa[x][y].inserisciPezzo(this);
        posX = x;
        posY = y;
        haMosso = true;
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

            if (x < 0 || x >= 8 || y < 0 || y >= 8) continue;

            Casella casella = mappa[x][y];

            if (casella.isVuota()) {
                casellePossibili[x][y] = casella;
            } else if (casella.getPezzoContenuto().isBianco() != this.isBianco) {
                casellePossibili[x][y] = casella;
            }
        }

        return casellePossibili;
    }

    public void arrocca(boolean latoLungo) {
        if (haMosso) {
            throw new MossaNonValidaException(
                "Arrocco non consentito: il Re ha già mosso."
            );
        }
 
        int riga = this.posY;
        int colonnaTorre;
        int colonnaReDestinazione;
        int colonnaTorreDestinazione;
        int colonnaCheckInizio; 
        int colonnaCheckFine;  
 
        if (!latoLungo) {
            colonnaTorre             = 7;
            colonnaReDestinazione    = 6;
            colonnaTorreDestinazione = 5;
            colonnaCheckInizio       = 5;
            colonnaCheckFine         = 6;
        } 
        else {
            colonnaTorre             = 0;
            colonnaReDestinazione    = 2;
            colonnaTorreDestinazione = 3;
            colonnaCheckInizio       = 1;
            colonnaCheckFine         = 3;
        }
 
        Casella casellaTorre = mappa[colonnaTorre][riga];
        if (casellaTorre.isVuota()) {
            throw new MossaNonValidaException(
                "Arrocco non consentito: non c'è una Torre sul lato " + (latoLungo ? "lungo" : "corto") + "."
            );
        }

        Pezzo pezzoTorre = casellaTorre.getPezzoContenuto();
        if (!(pezzoTorre instanceof Torre)) {
            throw new MossaNonValidaException(
                "Arrocco non consentito: il pezzo sul lato " + (latoLungo ? "lungo" : "corto") + " non è una Torre."
            );
        }

        Torre torre = (Torre) pezzoTorre;
        if (torre.isBianco() != this.isBianco()) {
            throw new MossaNonValidaException(
                "Arrocco non consentito: la Torre appartiene al colore avversario."
            );
        }

        if (torre.haMosso()) {
            throw new MossaNonValidaException(
                "Arrocco non consentito: la Torre ha già mosso."
            );
        }
 
        for (int col = colonnaCheckInizio; col <= colonnaCheckFine; col++) {
            if (!mappa[col][riga].isVuota()) {
                throw new MossaNonValidaException(
                    "Arrocco non consentito: la casella " +
                    (char) ('a' + col) + (riga + 1) + " non è libera."
                );
            }
        }
 
        mappa[this.posX][riga].rimuoviPezzo();
        mappa[colonnaTorre][riga].rimuoviPezzo();
 
        this.posX  = colonnaReDestinazione;
        this.posY  = riga;
        this.haMosso = true;
        mappa[colonnaReDestinazione][riga].inserisciPezzo(this);
 
        torre.muovi(colonnaTorreDestinazione, riga);
        torre.setHaMosso(true);
        mappa[colonnaTorreDestinazione][riga].inserisciPezzo(torre);
    }

    public boolean haMosso() {
        return haMosso;
    }
    
}