package Scuola.Progettini.Scacchi.Partite;

import Scuola.Progettini.Scacchi.Pezzi.Alfiere;
import Scuola.Progettini.Scacchi.Pezzi.Cavallo;
import Scuola.Progettini.Scacchi.Pezzi.Pedone;
import Scuola.Progettini.Scacchi.Pezzi.Re;
import Scuola.Progettini.Scacchi.Pezzi.Regina;
import Scuola.Progettini.Scacchi.Pezzi.Torre;
import Scuola.Progettini.Scacchi.Util.Casella;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Partita extends PartitaAstratta {

    private int mosse;

    public Partita() {
        super();
        inizializzaPartitaStandard();
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

    public void caricaMappaDaFile(String percorsoFile) throws IOException {
        try (Scanner s = new Scanner(new File(percorsoFile))) {
            Casella[][] nuovaMappa = leggiScacchiera(s);

            if (!s.hasNextLine()) {
                throw new Scuola.Progettini.Scacchi.Exception.FileNonValidoException("Turno mancante nel file.");
            }

            this.mappa = nuovaMappa;
            this.attaccaBianco = leggiTurno(s.nextLine());
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
        }
    }

    private boolean pattaPerMosse() {
        int mossaIniziale = mosse;

        // TODO

        return false;
    }

    @Override
    protected void dopoMossa(boolean coloreCheHaMosso) {
        if (pattaPerMosse()) {
            terminaParita();
        }
        mosse++;
    }

    public int getMosse() {
        return mosse;
    }

}