package Scuola.Progettini.Scacchi.Partite;

import Scuola.Progettini.Scacchi.Util.Casella;

public class Esercizio {
     
    private Casella[][] mappa;
    private boolean attaccaBianco;
    private int mosseTotali;
    private String percorsoFile;

    public Esercizio(String percorsoFile) {
        this.percorsoFile = percorsoFile;
    }

    public Casella[][] getMappa() {
        return mappa;
    }

    public boolean isAttaccaBianco() {
        return attaccaBianco;
    }

    public int getMosseTotali() {
        return mosseTotali;
    }

    public String getPercorsoFile() {
        return percorsoFile;
    }
}