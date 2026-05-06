package Scuola.Progettini.Scacchi.Partite;

import Scuola.Progettini.Scacchi.Exception.*;
import Scuola.Progettini.Scacchi.Pezzi.*;
import Scuola.Progettini.Scacchi.Util.*;

import java.io.*;
import java.util.Scanner;

public class Partita extends PartitaAstratta {

    private int mosse;
    private int ultimaMossaPerPatta;

    public Partita() {
        super();
        inizializzaPartitaStandard();
    }

    public Partita(String percorsoFile) throws IOException{
        caricaMappaDaFile(percorsoFile);
    }

    public Partita(Casella[][] mappa, boolean attaccaBianco) {
        super(mappa, attaccaBianco);
    }

    private void inizializzaPartitaStandard() {
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
        mosse = 1;
    }

        private void impostaPedoneEnPassant(int riga, int colonna) {
        if (riga == -1 && colonna == -1) {
            pedoneEnPassant = null;
            return;
        }

        if (riga < 0 || riga >= 8 || colonna < 0 || colonna >= 8) {
            throw new FileNonValidoException("Coordinate EN_PASSANT fuori dalla scacchiera.");
        }

        Pezzo pezzo = mappa[riga][colonna].getPezzoContenuto();

        if (!(pezzo instanceof Pedone)) {
            throw new FileNonValidoException("EN_PASSANT indica una casella senza pedone.");
        }

        pedoneEnPassant = (Pedone) pezzo;
    }

    private boolean reHaMosso(boolean bianco) {
        int riga = bianco ? 7 : 0;
        Pezzo pezzo = mappa[riga][4].getPezzoContenuto();

        if (pezzo instanceof Re) {
            return ((Re) pezzo).haMosso();
        }

        return true;
    }

    private boolean torreHaMosso(boolean bianco, boolean latoLungo) {
        int riga = bianco ? 7 : 0;
        int colonna = latoLungo ? 0 : 7;

        Pezzo pezzo = mappa[riga][colonna].getPezzoContenuto();

        if (pezzo instanceof Torre) {
            return ((Torre) pezzo).haMosso();
        }

        return true;
    }

    private void impostaStatoArrocco(
            boolean reBiancoHaMosso,
            boolean torreBiancaLungaHaMosso,
            boolean torreBiancaCortaHaMosso,
            boolean reNeroHaMosso,
            boolean torreNeraLungaHaMosso,
            boolean torreNeraCortaHaMosso
    ) {
        impostaReHaMosso(true, reBiancoHaMosso);
        impostaTorreHaMosso(true, true, torreBiancaLungaHaMosso);
        impostaTorreHaMosso(true, false, torreBiancaCortaHaMosso);

        impostaReHaMosso(false, reNeroHaMosso);
        impostaTorreHaMosso(false, true, torreNeraLungaHaMosso);
        impostaTorreHaMosso(false, false, torreNeraCortaHaMosso);
    }

    private void impostaReHaMosso(boolean bianco, boolean haMosso) {
        int riga = bianco ? 7 : 0;
        Pezzo pezzo = mappa[riga][4].getPezzoContenuto();

        if (pezzo instanceof Re) {
            ((Re) pezzo).setHaMosso(haMosso);
        }
    }

    private void impostaTorreHaMosso(boolean bianco, boolean latoLungo, boolean haMosso) {
        int riga = bianco ? 7 : 0;
        int colonna = latoLungo ? 0 : 7;

        Pezzo pezzo = mappa[riga][colonna].getPezzoContenuto();

        if (pezzo instanceof Torre) {
            ((Torre) pezzo).setHaMosso(haMosso);
        }
    }

    private void validaRePresenti() {
        if (trovaRe(true) == null) {
            throw new FileNonValidoException("Nel file manca il Re bianco.");
        }

        if (trovaRe(false) == null) {
            throw new FileNonValidoException("Nel file manca il Re nero.");
        }
    }

    public void caricaMappaDaFile(String percorsoFile) throws IOException {
        try (Scanner s = new Scanner(new File(percorsoFile))) {
            Casella[][] nuovaMappa = leggiScacchiera(s);

            if (!s.hasNextLine()) {
                throw new FileNonValidoException("Turno mancante nel file.");
            }

            this.mappa = nuovaMappa;
            this.attaccaBianco = leggiTurno(s.nextLine());

            if (!s.hasNextLine()) {
                throw new FileNonValidoException("Mosse mancanti nel file.");
            }

            try {
                this.mosse = Integer.parseInt(s.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new FileNonValidoException("Numero mosse non valido.");
            }

            if (mosse < 1) {
                throw new FileNonValidoException("Il numero della mossa non può essere minore di 1.");
            }

            this.ultimaMossaPerPatta = 0;
            this.pedoneEnPassant = null;

            while (s.hasNextLine()) {
                String riga = s.nextLine().trim();

                if (riga.isBlank()) {
                    continue;
                }

                String[] parti = riga.split("\\s+");

                if (parti[0].equals("ULTIMA_MOSSA_PATTA")) {
                    if (parti.length != 2) {
                        throw new FileNonValidoException("Riga ULTIMA_MOSSA_PATTA non valida.");
                    }

                    try {
                        ultimaMossaPerPatta = Integer.parseInt(parti[1]);
                    } catch (NumberFormatException e) {
                        throw new FileNonValidoException("Valore ULTIMA_MOSSA_PATTA non valido.");
                    }
                }

                else if (parti[0].equals("EN_PASSANT")) {
                    if (parti.length != 3) {
                        throw new FileNonValidoException("Riga EN_PASSANT non valida.");
                    }

                    try {
                        int rigaPedone = Integer.parseInt(parti[1]);
                        int colonnaPedone = Integer.parseInt(parti[2]);
                        impostaPedoneEnPassant(rigaPedone, colonnaPedone);
                    } catch (NumberFormatException e) {
                        throw new FileNonValidoException("Coordinate EN_PASSANT non valide.");
                    }
                }

                else if (parti[0].equals("ARROCCO")) {
                    if (parti.length != 7) {
                        throw new FileNonValidoException("Riga ARROCCO non valida.");
                    }

                    impostaStatoArrocco(
                        Boolean.parseBoolean(parti[1]),
                        Boolean.parseBoolean(parti[2]),
                        Boolean.parseBoolean(parti[3]),
                        Boolean.parseBoolean(parti[4]),
                        Boolean.parseBoolean(parti[5]),
                        Boolean.parseBoolean(parti[6])
                    );
                }
            }

            validaRePresenti();
        }
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
            writer.println(mosse);

            writer.println("ULTIMA_MOSSA_PATTA " + ultimaMossaPerPatta);

            if (pedoneEnPassant == null) {
                writer.println("EN_PASSANT -1 -1");
            } else {
                writer.println("EN_PASSANT " + pedoneEnPassant.getRiga() + " " + pedoneEnPassant.getColonna());
            }

            writer.println(
                "ARROCCO "
                + reHaMosso(true) + " "
                + torreHaMosso(true, true) + " "
                + torreHaMosso(true, false) + " "
                + reHaMosso(false) + " "
                + torreHaMosso(false, true) + " "
                + torreHaMosso(false, false)
            );
        }
    }

    @Override
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

        if (pezzo instanceof Pedone || mappa[rigaArrivo][colonnaArrivo].getPezzoContenuto() != null) ultimaMossaPerPatta = mosse;
        pezzo.muovi(rigaArrivo, colonnaArrivo);

        pedoneEnPassant = null;
        if (pezzo instanceof Pedone pedone && distanzaRighe == 2) {
            pedoneEnPassant = pedone;
        }

        dopoMossa(coloreCheHaMosso);
        attaccaBianco = !attaccaBianco;
    }

    public boolean pattaPerMosse() {
        if (mosse - ultimaMossaPerPatta > 50) {
            System.out.println("patta");
            return true;
        }
        return false;
    }

    @Override
    protected void dopoMossa(boolean coloreCheHaMosso) {
        mosse++;
    }

    public int getMosse() {
        return mosse;
    }

}