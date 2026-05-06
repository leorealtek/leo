package Scuola.Progettini.Scacchi.Partite;

import Scuola.Progettini.Scacchi.Exception.FileNonValidoException;
import Scuola.Progettini.Scacchi.Util.Casella;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Esercizio extends PartitaAstratta {

    private static final String[] PERCORSI_PREDEFINITI = {"Scuola/Progettini/Scacchi/FileEsercizi/Esercizio.txt"};

    private boolean coloreCheDeveDareMatto;
    private int mosseRimanenti;

    public Esercizio(String percorsoFile) throws IOException {
        super();
        leggiMappaDaFile(percorsoFile);
    }

    public Esercizio() throws IOException {
        super();
        leggiDaPercorsiPredefiniti();
    }

    public void leggiMappaDaFile(String percorsoFile) throws IOException {
        try (Scanner s = new Scanner(new File(percorsoFile))) {
            Casella[][] nuovaMappa = leggiScacchiera(s);

            if (!s.hasNextLine()) {
                throw new FileNonValidoException("Turno mancante nel file.");
            }

            attaccaBianco = leggiTurno(s.nextLine());
            coloreCheDeveDareMatto = attaccaBianco;

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

            mappa = nuovaMappa;
        }
    }

    private void leggiDaPercorsiPredefiniti() throws IOException {
    IOException ultimoErrore = null;

    for (String percorso : PERCORSI_PREDEFINITI) {
        try {
            leggiMappaDaFile(percorso);
            return;
        } catch (IOException e) {
            ultimoErrore = e;
        }
    }

    throw new IOException("Nessun percorso valido per Esercizio.txt", ultimoErrore);
}

    @Override
    protected void dopoMossa(boolean coloreCheHaMosso) {
        scalaMosseSeServe(coloreCheHaMosso);
    }

    private void scalaMosseSeServe(boolean coloreCheHaMosso) {
        if (coloreCheHaMosso == coloreCheDeveDareMatto) {
            mosseRimanenti--;
        }
    }

    public boolean getColoreCheDeveDareMatto() {
        return coloreCheDeveDareMatto;
    }

    public int getMosseRimanenti() {
        return mosseRimanenti;
    }
}