package Scuola.Progettini.Scacchi.Partite;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Scuola.Progettini.Scacchi.Exception.FileNonValidoException;
import Scuola.Progettini.Scacchi.Pezzi.Alfiere;
import Scuola.Progettini.Scacchi.Pezzi.Cavallo;
import Scuola.Progettini.Scacchi.Pezzi.Pedone;
import Scuola.Progettini.Scacchi.Pezzi.Re;
import Scuola.Progettini.Scacchi.Pezzi.Regina;
import Scuola.Progettini.Scacchi.Pezzi.Torre;
import Scuola.Progettini.Scacchi.Util.Casella;

public class Esercizio {
     
    private Casella[][] mappa;
    private boolean attaccaBianco;
    private int mosseRimanenti;

    public Esercizio(String percorsoFile) throws IOException {
        mappa = leggiMappaDaFile(percorsoFile);
    }

    public Casella[][] leggiMappaDaFile(String percorsoFile) throws IOException {
        try (Scanner s = new Scanner(new File(percorsoFile))) {
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

            if (!s.hasNextLine()) {
                throw new FileNonValidoException("Turno mancante nel file.");
            }
            attaccaBianco = leggiTurno(s.nextLine());

            if (!s.hasNextLine()) {
                throw new FileNonValidoException("Numero di mosse rimanenti mancante nel file.");
            }
            try {
                mosseRimanenti = Integer.parseInt(s.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new FileNonValidoException("Numero di mosse rimanenti non valido.");
            }

            if (mosseRimanenti < 0) {
                throw new FileNonValidoException("Il numero di mosse rimanenti non può essere negativo.");
            }

            return nuovaMappa;
        }
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

    public void stampaMappa() {
        for (Casella[] caselle : mappa) {
            for (Casella casella : caselle) {
                if (casella != null && casella.getPezzoContenuto() != null) {
                    System.out.print(casella.getPezzoContenuto().getNome() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public Casella[][] getMappa() {
        return mappa;
    }

    public boolean isAttaccaBianco() {
        return attaccaBianco;
    }

    public int getMosseRimanenti() {
        return mosseRimanenti;
    }
}
