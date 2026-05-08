package Scuola.Progettini.Scacchi.Partite;

import Scuola.Progettini.Scacchi.Exception.*;
import Scuola.Progettini.Scacchi.Pezzi.*;
import Scuola.Progettini.Scacchi.Util.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public abstract class PartitaAstratta {

    protected Casella[][] mappa;
    protected boolean attaccaBianco;
    protected Pedone pedoneEnPassant;
    protected ArrayList<Casella[][]> tutteMossePossibili = new ArrayList<>();

    public PartitaAstratta() {
        mappa = new Casella[8][8];
        inizializzaCaselleVuote();
        attaccaBianco = true;
        pedoneEnPassant = null;
    }

    public PartitaAstratta(Casella[][] mappa, boolean attaccaBianco) {
        validaMappa(mappa);
        this.mappa = mappa;
        this.attaccaBianco = attaccaBianco;
        this.pedoneEnPassant = null;
    }

    protected void inizializzaCaselleVuote() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mappa[i][j] = new Casella();
            }
        }
    }

    protected void validaMappa(Casella[][] mappa) {
        if (mappa == null || mappa.length != 8) {
            throw new IllegalArgumentException("La mappa deve essere una matrice 8x8.");
        }

        for (int i = 0; i < 8; i++) {
            if (mappa[i] == null || mappa[i].length != 8) {
                throw new IllegalArgumentException("La mappa deve essere una matrice 8x8.");
            }
        }
    }

    public Pezzo promuoviPedone(Pedone p) {
        if (p.getRiga() == 0 || p.getRiga() == 7) {
            String[] pezziDisponibili = {"Torre", "Cavallo", "Alfiere", "Donna"};
            Pezzo[] pezzi = {
                new Torre((p.isBianco()) ? 'T' : 't', p.getRiga(), p.getColonna(), mappa),
                new Cavallo((p.isBianco()) ? 'C' : 'c', p.getRiga(), p.getColonna(), mappa),
                new Alfiere((p.isBianco()) ? 'A' : 'a', p.getRiga(), p.getColonna(), mappa),
                new Regina((p.isBianco()) ? 'D' : 'd', p.getRiga(), p.getColonna(), mappa),
            };

            int scelta = JOptionPane.showOptionDialog(
                null,
                "Scegli che in che pezzo vuoi promuovere il pedone",
                "Menù di scelta",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                pezziDisponibili,
                pezziDisponibili[3]
            );

            if (scelta < 0) {
                return pezzi[3];
            }

            return pezzi[scelta];
        }
        return null;
    }

    public void muoviPezzo(int rigaPartenza, int colonnaPartenza, int rigaArrivo, int colonnaArrivo) {
        validaCoordinate(rigaPartenza, colonnaPartenza);
        validaCoordinate(rigaArrivo, colonnaArrivo);

        Pezzo pezzo = mappa[rigaPartenza][colonnaPartenza].getPezzoContenuto();
        if (pezzo == null) {
            throw new MossaNonValidaException("Nessun pezzo selezionato.");
        }

        if (pezzo.isBianco() != attaccaBianco) {
            throw new MossaNonValidaException("Non è il turno di questo colore.");
        }

        Casella[][] possibili = pezzo.mossePossibili();
        boolean enPassant = isEnPassantValido(pezzo, rigaPartenza, colonnaPartenza, rigaArrivo, colonnaArrivo);

        if (possibili[rigaArrivo][colonnaArrivo] == null && !enPassant) {
            throw new MossaNonValidaException("Mossa non consentita per questo pezzo.");
        }

        if (lasciaReSottoScacco(pezzo, rigaPartenza, colonnaPartenza, rigaArrivo, colonnaArrivo)) {
            throw new MossaNonValidaException("Mossa non consentita: il Re resterebbe o finirebbe sotto scacco.");
        }

        boolean coloreCheHaMosso = attaccaBianco;
        int distanzaRighe = Math.abs(rigaArrivo - rigaPartenza);

        if (enPassant) {
            mappa[rigaPartenza][colonnaArrivo].rimuoviPezzo();
        }

        pezzo.muovi(rigaArrivo, colonnaArrivo);

        pedoneEnPassant = null;
        if (pezzo instanceof Pedone pedone && distanzaRighe == 2) {
            pedoneEnPassant = pedone;
        }

        dopoMossa(coloreCheHaMosso);
        attaccaBianco = !attaccaBianco;
    }

    public boolean isEnPassantValido(Pezzo pezzo, int rigaPartenza, int colonnaPartenza, int rigaArrivo, int colonnaArrivo) {
        if (!(pezzo instanceof Pedone)) return false;
        if (pedoneEnPassant == null) return false;
        if (pedoneEnPassant.isBianco() == pezzo.isBianco()) return false;
        if (!mappa[rigaArrivo][colonnaArrivo].isVuota()) return false;

        int direzione = pezzo.isBianco() ? -1 : 1;
        return rigaArrivo == rigaPartenza + direzione
                && Math.abs(colonnaArrivo - colonnaPartenza) == 1
                && pedoneEnPassant.getRiga() == rigaPartenza
                && pedoneEnPassant.getColonna() == colonnaArrivo;
    }

    public void arrocca(boolean latoLungo) {
        int rigaRe = attaccaBianco ? 7 : 0;
        Pezzo pezzo = mappa[rigaRe][4].getPezzoContenuto();

        if (!(pezzo instanceof Re) || pezzo.isBianco() != attaccaBianco) {
            throw new MossaNonValidaException("Non c'è un Re del colore di turno nella posizione iniziale.");
        }

        if (!puoArroccare(latoLungo)) {
            throw new MossaNonValidaException("Arrocco non valido.");
        }

        int colonnaTorre = latoLungo ? 0 : 7;
        int colonnaReDestinazione = latoLungo ? 2 : 6;
        int colonnaTorreDestinazione = latoLungo ? 3 : 5;

        Re re = (Re) pezzo;
        Torre torre = (Torre) mappa[rigaRe][colonnaTorre].getPezzoContenuto();
        boolean coloreCheHaMosso = attaccaBianco;

        mappa[rigaRe][4].rimuoviPezzo();
        mappa[rigaRe][colonnaTorre].rimuoviPezzo();

        re.aggiornaPosizione(rigaRe, colonnaReDestinazione);
        re.setHaMosso(true);
        mappa[rigaRe][colonnaReDestinazione].inserisciPezzo(re);

        torre.aggiornaPosizione(rigaRe, colonnaTorreDestinazione);
        torre.setHaMosso(true);
        mappa[rigaRe][colonnaTorreDestinazione].inserisciPezzo(torre);

        dopoMossa(coloreCheHaMosso);
        attaccaBianco = !attaccaBianco;
    }

    protected abstract void dopoMossa(boolean coloreCheHaMosso);

    public boolean lasciaReSottoScacco(Pezzo pezzo, int rigaPartenza, int colonnaPartenza, int rigaArrivo, int colonnaArrivo) {
        Pezzo catturato = mappa[rigaArrivo][colonnaArrivo].getPezzoContenuto();
        boolean enPassant = isEnPassantValido(pezzo, rigaPartenza, colonnaPartenza, rigaArrivo, colonnaArrivo);
        Pezzo catturatoEnPassant = null;

        if (enPassant) {
            catturatoEnPassant = mappa[rigaPartenza][colonnaArrivo].getPezzoContenuto();
            mappa[rigaPartenza][colonnaArrivo].rimuoviPezzo();
        }

        mappa[rigaArrivo][colonnaArrivo].inserisciPezzo(pezzo);
        mappa[rigaPartenza][colonnaPartenza].rimuoviPezzo();
        pezzo.aggiornaPosizione(rigaArrivo, colonnaArrivo);

        boolean sottoScacco = isSottoScacco(pezzo.isBianco());

        mappa[rigaPartenza][colonnaPartenza].inserisciPezzo(pezzo);
        mappa[rigaArrivo][colonnaArrivo].inserisciPezzo(catturato);
        if (enPassant) {
            mappa[rigaPartenza][colonnaArrivo].inserisciPezzo(catturatoEnPassant);
        }
        pezzo.aggiornaPosizione(rigaPartenza, colonnaPartenza);

        return sottoScacco;
    }

    public boolean isSottoScacco(boolean reBianco) {
        int[] posizioneRe = trovaRe(reBianco);
        if (posizioneRe == null) {
            return true;
        }
        return casellaAttaccata(posizioneRe[0], posizioneRe[1], !reBianco);
    }

    public boolean casellaAttaccata(int rigaTarget, int colonnaTarget, boolean daBianco) {
        validaCoordinate(rigaTarget, colonnaTarget);

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

    protected int[] trovaRe(boolean bianco) {
        char nomeRe = bianco ? 'R' : 'r';
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pezzo pezzo = mappa[i][j].getPezzoContenuto();
                if (pezzo != null && pezzo.getNome() == nomeRe) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    public String checkWin() {
        boolean coloreDiTurno = attaccaBianco;
        boolean sottoScacco = isSottoScacco(coloreDiTurno);
        boolean haMosseLegali = esisteAlmenoUnaMossaLegale(coloreDiTurno);

        if (sottoScacco && !haMosseLegali) {
            return coloreDiTurno ? "Nero" : "Bianco";
        }

        if (!sottoScacco && !haMosseLegali) {
            return "Patta " + (coloreDiTurno ? "BIANCO" : "NERO");
        }

        return "Nessuno";
    }

    protected boolean esisteAlmenoUnaMossaLegale(boolean bianco) {
        for (int rigaPartenza = 0; rigaPartenza < 8; rigaPartenza++) {
            for (int colonnaPartenza = 0; colonnaPartenza < 8; colonnaPartenza++) {

                Pezzo pezzo = mappa[rigaPartenza][colonnaPartenza].getPezzoContenuto();

                if (pezzo == null || pezzo.isBianco() != bianco) {
                    continue;
                }

                Casella[][] mosse = pezzo.mossePossibili();

                for (int rigaArrivo = 0; rigaArrivo < 8; rigaArrivo++) {
                    for (int colonnaArrivo = 0; colonnaArrivo < 8; colonnaArrivo++) {

                        if (mosse[rigaArrivo][colonnaArrivo] == null) {
                            continue;
                        }

                        Pezzo pezzoDestinazione = mappa[rigaArrivo][colonnaArrivo].getPezzoContenuto();
                        if (pezzoDestinazione instanceof Re) {
                            continue;
                        }

                        if (!lasciaReSottoScacco(
                                pezzo,
                                rigaPartenza,
                                colonnaPartenza,
                                rigaArrivo,
                                colonnaArrivo
                        )) {
                            return true;
                        }
                    }
                }
            }
        }

        if (esisteEnPassantLegale(bianco)) {
            return true;
        }

        return esisteArroccoLegale(bianco);
    }

    private boolean esisteEnPassantLegale(boolean bianco) {
        if (pedoneEnPassant == null || pedoneEnPassant.isBianco() == bianco) return false;

        int rigaPedoneAvversario = pedoneEnPassant.getRiga();
        int colonnaPedoneAvversario = pedoneEnPassant.getColonna();
        int direzione = bianco ? -1 : 1;
        int rigaArrivo = rigaPedoneAvversario + direzione;

        for (int colonnaPartenza : new int[] {colonnaPedoneAvversario - 1, colonnaPedoneAvversario + 1}) {
            if (!coordinateValide(rigaPedoneAvversario, colonnaPartenza)) continue;

            Pezzo pezzo = mappa[rigaPedoneAvversario][colonnaPartenza].getPezzoContenuto();
            if (pezzo instanceof Pedone && pezzo.isBianco() == bianco
                    && isEnPassantValido(pezzo, rigaPedoneAvversario, colonnaPartenza, rigaArrivo, colonnaPedoneAvversario)
                    && !lasciaReSottoScacco(pezzo, rigaPedoneAvversario, colonnaPartenza, rigaArrivo, colonnaPedoneAvversario)) {
                return true;
            }
        }

        return false;
    }

    private boolean esisteArroccoLegale(boolean bianco) {
        return arroccoLegale(bianco, false) || arroccoLegale(bianco, true);
    }

    private boolean arroccoLegale(boolean bianco, boolean latoLungo) {
        int rigaRe = bianco ? 7 : 0;
        if (!coordinateValide(rigaRe, 4)) return false;

        Pezzo pezzoRe = mappa[rigaRe][4].getPezzoContenuto();
        if (!(pezzoRe instanceof Re) || pezzoRe.isBianco() != bianco) return false;

        Re re = (Re) pezzoRe;
        if (re.haMosso() || isSottoScacco(bianco)) return false;

        int colonnaTorre = latoLungo ? 0 : 7;
        int primaCasellaLibera = latoLungo ? 1 : 5;
        int ultimaCasellaLibera = latoLungo ? 3 : 6;
        int colonnaReDestinazione = latoLungo ? 2 : 6;
        int passo = latoLungo ? -1 : 1;

        Pezzo pezzoTorre = mappa[rigaRe][colonnaTorre].getPezzoContenuto();
        if (!(pezzoTorre instanceof Torre) || pezzoTorre.isBianco() != bianco) return false;

        Torre torre = (Torre) pezzoTorre;
        if (torre.haMosso()) return false;

        for (int col = primaCasellaLibera; col <= ultimaCasellaLibera; col++) {
            if (!mappa[rigaRe][col].isVuota()) return false;
        }

        for (int col = 4 + passo; col != colonnaReDestinazione + passo; col += passo) {
            if (casellaAttaccata(rigaRe, col, !bianco)) return false;
        }

        return true;
    }

    public boolean puoArroccare(boolean latoLungo) {
        return arroccoLegale(attaccaBianco, latoLungo);
    }

    protected Casella[][] leggiScacchiera(Scanner s) {
        Casella[][] nuovaMappa = new Casella[8][8];
        for (int i = 0; i < 8; i++) {
            if (!s.hasNextLine()) {
                throw new FileNonValidoException("Il file deve contenere 8 righe di scacchiera.");
            }

            String riga = s.nextLine().replace(" ", "").trim();
            if (riga.length() != 8) {
                throw new FileNonValidoException("Ogni riga della scacchiera deve contenere esattamente 8 caselle.");
            }

            for (int j = 0; j < 8; j++) {
                nuovaMappa[i][j] = creaCasellaDaChar(riga.charAt(j), i, j, nuovaMappa);
            }
        }
        return nuovaMappa;
    }

    protected Casella creaCasellaDaChar(char c, int riga, int colonna, Casella[][] mappa) {
        switch (c) {
            case 't': case 'T': return new Casella(new Torre(c, riga, colonna, mappa));
            case 'c': case 'C': return new Casella(new Cavallo(c, riga, colonna, mappa));
            case 'a': case 'A': return new Casella(new Alfiere(c, riga, colonna, mappa));
            case 'd': case 'D': return new Casella(new Regina(c, riga, colonna, mappa));
            case 'r': case 'R': return new Casella(new Re(c, riga, colonna, mappa));
            case 'p': case 'P': return new Casella(new Pedone(c, riga, colonna, mappa));
            case '.': return new Casella();
            default: throw new FileNonValidoException("Carattere non valido nella scacchiera: " + c);
        }
    }

    protected boolean leggiTurno(String rigaTurno) {
        String turno = rigaTurno.trim().toUpperCase();
        if (turno.equals("BIANCO")) return true;
        if (turno.equals("NERO")) return false;
        throw new FileNonValidoException("Turno non valido: " + rigaTurno);
    }

    protected void validaCoordinate(int riga, int colonna) {
        if (!coordinateValide(riga, colonna)) {
            throw new MossaNonValidaException("Coordinate non valide: (" + riga + ", " + colonna + ")");
        }
    }

    protected boolean coordinateValide(int riga, int colonna) {
        return riga >= 0 && riga < 8 && colonna >= 0 && colonna < 8;
    }

    private double valutaPosizione(Casella[][] scacchiera) {
        double pezziBianchi = 0;
        double pezziNeri = 0;

        for (int i = 0; i < scacchiera.length; i++) {
            for (int j = 0; j < scacchiera[i].length; j++) {
                Pezzo p = scacchiera[i][j].getPezzoContenuto();
                if (p == null) continue;
                if (p.isBianco()) pezziBianchi += p.getValore();
                else pezziNeri += p.getValore();
            }
        }

        return pezziBianchi - pezziNeri;
    }

    private double minimax(Casella[][] scacchiera, int profondita, boolean toccaBianco) {
        if (profondita == 0) return valutaPosizione(scacchiera);

        if (toccaBianco) {
            double maxPoint = Integer.MIN_VALUE;
            for (Casella[][] scacchieraAttuale : tutteMossePossibili) {
                double val = minimax(scacchieraAttuale, profondita - 1, false);
                maxPoint = Math.max(val, maxPoint);
            }
            return maxPoint;
        }
        else{
            double minPoint = Integer.MAX_VALUE;
            for (Casella[][] scacchieraAttuale : tutteMossePossibili) {
                double val = minimax(scacchieraAttuale, profondita - 1, true);
                minPoint = Math.min(val, minPoint);
            }
            return minPoint;
        }
    }

    public Casella[][] trovaPosizioneMigliore(boolean toccaBianco) {
        creaTutteMosseDisponibli(toccaBianco);
        double valMigliore = minimax(mappa, 2, toccaBianco);
        for (Casella[][] scacchiera : tutteMossePossibili) {
            if (valutaPosizione(scacchiera) == valMigliore) {
                return scacchiera;
            }
        }
        return null;
    }

    private void creaTutteMosseDisponibli(boolean toccaBianco) {
        tutteMossePossibili.clear();
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                Pezzo p = mappa[i][j].getPezzoContenuto();
                if (p != null) {
                    if (p.isBianco() == toccaBianco) {
                        tutteMossePossibili.add(p.mossePossibili());
                    }
                }
            }
        }
    }

    public Casella[][] getMappa() {
        return mappa;
    }

    public boolean isAttaccaBianco() {
        return attaccaBianco;
    }
}