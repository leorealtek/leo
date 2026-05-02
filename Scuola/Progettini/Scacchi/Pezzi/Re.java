package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Re extends Pezzo {

    private boolean haMosso;

    public Re(char nome, int riga, int colonna, Casella[][] mappa) {
        super(nome, riga, colonna, mappa);
        this.haMosso = !((isBianco && riga == 7 && colonna == 4)
                      || (!isBianco && riga == 0 && colonna == 4));
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

            if (!coordinateValide(x, y)) continue;

            Casella casella = mappa[x][y];

            if (casella.isVuota() || casella.getPezzoContenuto().isBianco() != this.isBianco) {
                casellePossibili[x][y] = casella;
            }
        }

        return casellePossibili;
    }

    public void arrocca(boolean latoLungo) {
        if (haMosso) {
            throw new MossaNonValidaException("Arrocco non consentito: il Re ha già mosso.");
        }

        int colonnaInizialeRe = 4;
        if (this.colonna != colonnaInizialeRe) {
            throw new MossaNonValidaException("Arrocco non consentito: il Re non è nella colonna iniziale.");
        }

        if (casellaAttaccata(riga, colonna, !isBianco)) {
            throw new MossaNonValidaException("Arrocco non consentito: il Re è sotto scacco.");
        }

        int colonnaTorre = latoLungo ? 0 : 7;
        int colonnaReDestinazione = latoLungo ? 2 : 6;
        int colonnaTorreDestinazione = latoLungo ? 3 : 5;
        int primaCasellaLibera = latoLungo ? 1 : 5;
        int ultimaCasellaLibera = latoLungo ? 3 : 6;

        Casella casellaTorre = mappa[riga][colonnaTorre];
        if (casellaTorre.isVuota()) {
            throw new MossaNonValidaException("Arrocco non consentito: non c'è una Torre sul lato " + (latoLungo ? "lungo" : "corto") + ".");
        }

        Pezzo pezzoTorre = casellaTorre.getPezzoContenuto();
        if (!(pezzoTorre instanceof Torre)) {
            throw new MossaNonValidaException("Arrocco non consentito: il pezzo sul lato " + (latoLungo ? "lungo" : "corto") + " non è una Torre.");
        }

        Torre torre = (Torre) pezzoTorre;
        if (torre.isBianco() != this.isBianco()) {
            throw new MossaNonValidaException("Arrocco non consentito: la Torre appartiene al colore avversario.");
        }

        if (torre.haMosso()) {
            throw new MossaNonValidaException("Arrocco non consentito: la Torre ha già mosso.");
        }

        for (int col = primaCasellaLibera; col <= ultimaCasellaLibera; col++) {
            if (!mappa[riga][col].isVuota()) {
                throw new MossaNonValidaException("Arrocco non consentito: una casella tra Re e Torre non è libera.");
            }
        }

        int passo = latoLungo ? -1 : 1;
        for (int col = colonna + passo; col != colonnaReDestinazione + passo; col += passo) {
            if (casellaAttaccata(riga, col, !isBianco)) {
                throw new MossaNonValidaException("Arrocco non consentito: il Re passerebbe su una casella sotto attacco.");
            }
        }

        mappa[riga][this.colonna].rimuoviPezzo();
        mappa[riga][colonnaTorre].rimuoviPezzo();

        aggiornaPosizione(riga, colonnaReDestinazione);
        this.haMosso = true;
        mappa[riga][colonnaReDestinazione].inserisciPezzo(this);

        torre.aggiornaPosizione(riga, colonnaTorreDestinazione);
        torre.setHaMosso(true);
        mappa[riga][colonnaTorreDestinazione].inserisciPezzo(torre);
    }

    private boolean casellaAttaccata(int rigaTarget, int colonnaTarget, boolean daBianco) {
        int direzionePedone = daBianco ? -1 : 1;
        int rigaPedone = rigaTarget - direzionePedone;
        int[] colonnePedone = {colonnaTarget - 1, colonnaTarget + 1};
        for (int col : colonnePedone) {
            if (coordinateValide(rigaPedone, col)) {
                Pezzo p = mappa[rigaPedone][col].getPezzoContenuto();
                if (p instanceof Pedone && p.isBianco() == daBianco) return true;
            }
        }

        int[][] mosseCavallo = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };
        for (int[] mossa : mosseCavallo) {
            int x = rigaTarget + mossa[0];
            int y = colonnaTarget + mossa[1];
            if (coordinateValide(x, y)) {
                Pezzo p = mappa[x][y].getPezzoContenuto();
                if (p instanceof Cavallo && p.isBianco() == daBianco) return true;
            }
        }

        int[][] direzioniRe = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };
        for (int[] dir : direzioniRe) {
            int x = rigaTarget + dir[0];
            int y = colonnaTarget + dir[1];
            if (coordinateValide(x, y)) {
                Pezzo p = mappa[x][y].getPezzoContenuto();
                if (p instanceof Re && p.isBianco() == daBianco) return true;
            }
        }

        int[][] direzioni = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };
        for (int[] dir : direzioni) {
            int x = rigaTarget + dir[0];
            int y = colonnaTarget + dir[1];
            while (coordinateValide(x, y)) {
                Pezzo p = mappa[x][y].getPezzoContenuto();
                if (p != null) {
                    if (p.isBianco() == daBianco) {
                        boolean lineaDritta = dir[0] == 0 || dir[1] == 0;
                        if ((lineaDritta && (p instanceof Torre || p instanceof Regina))
                            || (!lineaDritta && (p instanceof Alfiere || p instanceof Regina))) {
                            return true;
                        }
                    }
                    break;
                }
                x += dir[0];
                y += dir[1];
            }
        }

        return false;
    }

    public boolean haMosso() {
        return haMosso;
    }

    public void setHaMosso(boolean haMosso) {
        this.haMosso = haMosso;
    }
}
