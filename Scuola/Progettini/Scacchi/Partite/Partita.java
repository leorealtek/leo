package Scuola.Progettini.Scacchi.Partite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Scuola.Progettini.Scacchi.Exception.FileNonValidoException;
import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Pezzi.Alfiere;
import Scuola.Progettini.Scacchi.Pezzi.Cavallo;
import Scuola.Progettini.Scacchi.Pezzi.Pedone;
import Scuola.Progettini.Scacchi.Pezzi.Re;
import Scuola.Progettini.Scacchi.Pezzi.Regina;
import Scuola.Progettini.Scacchi.Pezzi.Torre;
import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Partita {
    private Casella[][] mappa;
    private boolean attaccaBianco;

    public Partita() {
        mappa = new Casella[8][8];
        inizializzaCaselleVuote();

        mappa[0][0] = new Casella(new Torre('t', 0, 0, mappa));
        mappa[0][1] = new Casella(new Cavallo('c', 0, 1, mappa));
        mappa[0][2] = new Casella(new Alfiere('a', 0, 2, mappa));
        mappa[0][3] = new Casella(new Regina('d', 0, 3, mappa));
        mappa[0][4] = new Casella(new Re('r', 0, 4, mappa));
        mappa[0][5] = new Casella(new Alfiere('a', 0, 5, mappa));
        mappa[0][6] = new Casella(new Cavallo('c', 0, 6, mappa));
        mappa[0][7] = new Casella(new Torre('t', 0, 7, mappa));

        for (int i = 0; i < 8; i++) {
            mappa[1][i] = new Casella(new Pedone('p', 1, i, mappa));
            mappa[6][i] = new Casella(new Pedone('P', 6, i, mappa));
        }

        mappa[7][0] = new Casella(new Torre('T', 7, 0, mappa));
        mappa[7][1] = new Casella(new Cavallo('C', 7, 1, mappa));
        mappa[7][2] = new Casella(new Alfiere('A', 7, 2, mappa));
        mappa[7][3] = new Casella(new Regina('D', 7, 3, mappa));
        mappa[7][4] = new Casella(new Re('R', 7, 4, mappa));
        mappa[7][5] = new Casella(new Alfiere('A', 7, 5, mappa));
        mappa[7][6] = new Casella(new Cavallo('C', 7, 6, mappa));
        mappa[7][7] = new Casella(new Torre('T', 7, 7, mappa));

        attaccaBianco = true;
    }

    public Partita(Casella[][] mappa, boolean attaccaBianco) {
        if (mappa == null || mappa.length != 8) {
            throw new IllegalArgumentException("La mappa deve essere una matrice 8x8.");
        }

        for (int i = 0; i < 8; i++) {
            if (mappa[i] == null || mappa[i].length != 8) {
                throw new IllegalArgumentException("La mappa deve essere una matrice 8x8.");
            }
        }

        this.mappa = mappa;
        this.attaccaBianco = attaccaBianco;
    }

    private void inizializzaCaselleVuote() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mappa[i][j] = new Casella();
            }
        }
    }

    public void muoviPezzo(int rigaPartenza, int colonnaPartenza, int rigaArrivo, int colonnaArrivo) {
        validaCoordinate(rigaPartenza, colonnaPartenza);
        validaCoordinate(rigaArrivo, colonnaArrivo);

        Pezzo pezzo = mappa[rigaPartenza][colonnaPartenza].getPezzoContenuto();
        if (pezzo == null) {
            throw new MossaNonValidaException("Nessun pezzo nella casella di partenza.");
        }

        if (pezzo.isBianco() != attaccaBianco) {
            throw new MossaNonValidaException("Non è il turno di questo colore.");
        }

        Casella[][] possibili = pezzo.mossePossibili();
        if (possibili[rigaArrivo][colonnaArrivo] == null) {
            throw new MossaNonValidaException("Mossa non consentita per questo pezzo.");
        }

        Pezzo pezzoDestinazione = mappa[rigaArrivo][colonnaArrivo].getPezzoContenuto();
        if (pezzoDestinazione instanceof Re) {
            throw new MossaNonValidaException("Mossa non consentita: il Re non può essere catturato.");
        }

        if (lasciaReSottoScacco(pezzo, rigaPartenza, colonnaPartenza, rigaArrivo, colonnaArrivo)) {
            throw new MossaNonValidaException("Mossa non consentita: il Re resterebbe o finirebbe sotto scacco.");
        }

        pezzo.muovi(rigaArrivo, colonnaArrivo);
        attaccaBianco = !attaccaBianco;
    }

    public void arrocca(boolean latoLungo) {
        int rigaRe = attaccaBianco ? 7 : 0;
        Pezzo pezzo = mappa[rigaRe][4].getPezzoContenuto();
        if (!(pezzo instanceof Re) || pezzo.isBianco() != attaccaBianco) {
            throw new MossaNonValidaException("Non c'è un Re del colore di turno nella posizione iniziale.");
        }

        ((Re) pezzo).arrocca(latoLungo);
        attaccaBianco = !attaccaBianco;
    }

    private boolean lasciaReSottoScacco(Pezzo pezzo, int rigaPartenza, int colonnaPartenza, int rigaArrivo, int colonnaArrivo) {
        Pezzo catturato = mappa[rigaArrivo][colonnaArrivo].getPezzoContenuto();
        mappa[rigaArrivo][colonnaArrivo].inserisciPezzo(pezzo);
        mappa[rigaPartenza][colonnaPartenza].rimuoviPezzo();
        pezzo.aggiornaPosizione(rigaArrivo, colonnaArrivo);

        boolean sottoScacco = isSottoScacco(pezzo.isBianco());

        mappa[rigaPartenza][colonnaPartenza].inserisciPezzo(pezzo);
        mappa[rigaArrivo][colonnaArrivo].inserisciPezzo(catturato);
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

    private int[] trovaRe(boolean bianco) {
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
        boolean trovatoBianco = trovaRe(true) != null;
        boolean trovatoNero = trovaRe(false) != null;

        if (!trovatoBianco) return "Nero";
        if (!trovatoNero) return "Bianco";

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

    private boolean esisteAlmenoUnaMossaLegale(boolean bianco) {
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

        return esisteArroccoLegale(bianco);
    }

    private boolean esisteArroccoLegale(boolean bianco) {
        int rigaRe = bianco ? 7 : 0;
        if (!coordinateValide(rigaRe, 4)) return false;

        Pezzo pezzoRe = mappa[rigaRe][4].getPezzoContenuto();
        if (!(pezzoRe instanceof Re) || pezzoRe.isBianco() != bianco) return false;

        Re re = (Re) pezzoRe;
        if (re.haMosso() || isSottoScacco(bianco)) return false;

        return arroccoLegale(bianco, false) || arroccoLegale(bianco, true);
    }

    private boolean arroccoLegale(boolean bianco, boolean latoLungo) {
        int rigaRe = bianco ? 7 : 0;
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

    public void caricaMappaDaFile(String percorsoFile) throws IOException {
        try (Scanner s = new Scanner(new File(percorsoFile))) {
            Casella[][] nuovaMappa = leggiScacchiera(s);

            if (!s.hasNextLine()) {
                throw new FileNonValidoException("Turno mancante nel file.");
            }
            boolean nuovoTurno = leggiTurno(s.nextLine());

            this.mappa = nuovaMappa;
            this.attaccaBianco = nuovoTurno;
        }
    }

    private Casella[][] leggiScacchiera(Scanner s) {
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

    private Casella creaCasellaDaChar(char c, int riga, int colonna, Casella[][] mappa) {
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

    private boolean leggiTurno(String rigaTurno) {
        String turno = rigaTurno.trim().toUpperCase();
        if (turno.equals("BIANCO")) return true;
        if (turno.equals("NERO")) return false;
        throw new FileNonValidoException("Turno non valido: " + rigaTurno);
    }

    public void salvaSuFile(String percorsoFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(percorsoFile))) {
            for (int i = 0; i < mappa.length; i++) {
                for (int j = 0; j < mappa[i].length; j++) {
                    Casella casella = mappa[i][j];

                    if (casella != null && casella.getPezzoContenuto() != null) {
                        writer.print(casella.getPezzoContenuto().getNome());
                    } else {
                        writer.print(".");
                    }

                    if (j < mappa[i].length - 1) {
                        writer.print(" ");
                    }
                }
                writer.println();
            }

            writer.println(attaccaBianco ? "BIANCO" : "NERO");
        }
    }

    private void validaCoordinate(int riga, int colonna) {
        if (!coordinateValide(riga, colonna)) {
            throw new MossaNonValidaException("Coordinate non valide: (" + riga + ", " + colonna + ")");
        }
    }

    private boolean coordinateValide(int riga, int colonna) {
        return riga >= 0 && riga < 8 && colonna >= 0 && colonna < 8;
    }

    public Casella[][] getMappa() {
        return mappa;
    }

    public boolean attaccaBianco() {
        return attaccaBianco;
    }

    public boolean isAttaccaBianco() {
        return attaccaBianco;
    }
}
