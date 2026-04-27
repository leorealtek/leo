package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Re extends Pezzo {

    private boolean haMosso;

    public Re(char nome, int riga, int colonna, Casella[][] mappa) {
        super(nome, riga, colonna, mappa);
        haMosso = false;
    }

    @Override
    public void muovi(int x, int y) {
        super.muovi(x, y);
        haMosso = true;
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

        int riga = this.riga;

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
        } else {
            colonnaTorre             = 0;
            colonnaReDestinazione    = 2;
            colonnaTorreDestinazione = 3;
            colonnaCheckInizio       = 1;
            colonnaCheckFine         = 3;
        }

        Casella casellaTorre = mappa[riga][colonnaTorre];
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
            if (!mappa[riga][col].isVuota()) {
                throw new MossaNonValidaException(
                    "Arrocco non consentito: la casella " +
                    (char) ('a' + col) + (riga + 1) + " non è libera."
                );
            }
        }

        mappa[riga][this.colonna].rimuoviPezzo();
        mappa[riga][colonnaTorre].rimuoviPezzo();

        this.riga = riga;
        this.colonna = colonnaReDestinazione;
        this.haMosso = true;
        mappa[riga][colonnaReDestinazione].inserisciPezzo(this);

        torre.muoviArrocco(riga, colonnaTorreDestinazione);
        mappa[riga][colonnaTorreDestinazione].inserisciPezzo(torre);
    }

    public boolean haMosso() {
        return haMosso;
    }
}