package Scuola.Progettini.Scacchi.Partite;

import Scuola.Progettini.Scacchi.Exception.FileNonValidoException;
import Scuola.Progettini.Scacchi.Util.Casella;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Esercizio extends PartitaAstratta {

    private boolean coloreCheDeveDareMatto;
    private int mosseRimanenti;

    public Esercizio(String percorsoFile) throws IOException {
        super();
        leggiMappaDaFile(percorsoFile);
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

    @Override
    protected void dopoMossa(boolean coloreCheHaMosso) {
        scalaMosseSeServe(coloreCheHaMosso);
    }

    private void scalaMosseSeServe(boolean coloreCheHaMosso) {
        if (coloreCheHaMosso == coloreCheDeveDareMatto) {
            mosseRimanenti--;
        }
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

    public boolean getColoreCheDeveDareMatto() {
        return coloreCheDeveDareMatto;
    }

    public int getMosseRimanenti() {
        return mosseRimanenti;
    }
}