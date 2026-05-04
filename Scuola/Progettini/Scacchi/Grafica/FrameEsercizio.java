package Scuola.Progettini.Scacchi.Grafica;

import Scuola.Progettini.Scacchi.Partite.Esercizio;
import java.awt.Color;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class FrameEsercizio extends FrameScacchiAstratto {

    private static final String PERCORSO_LINUX = "/home/leo/Scrivania/leo/Scuola/Progettini/Scacchi/Partite/FileEsercizi/Esercizio.txt";
    private static final String PERCORSO_WINDOWS_1 = "C:/Users/leonardo.giacomin/Desktop/leo/Scuola/Progettini/Scacchi/Partite/FileEsercizi/Esercizio.txt";
    private static final String PERCORSO_WINDOWS_2 = "C:/Users/leogi/Desktop/leo/Scuola/Progettini/Scacchi/Partite/FileEsercizi/Esercizio.txt";

    private final Esercizio esercizio;
    private final boolean coloreCheDeveDareMatto;

    public FrameEsercizio(String percorsoFile) throws IOException {
        this(new Esercizio(percorsoFile));
    }

    private FrameEsercizio(Esercizio esercizio) {
        super("Esercizio", esercizio);
        this.esercizio = esercizio;
        this.coloreCheDeveDareMatto = esercizio.getColoreCheDeveDareMatto();
    }

    @Override
    protected void aggiornaInfoExtra() {
        if (info == null) return;

        info.setForeground(Color.DARK_GRAY);
        info.setText(
                "Parte: " + nomeColore(coloreCheDeveDareMatto)
                + "  |  Mosse disponibili per dare matto: " + esercizio.getMosseRimanenti()
        );
    }

    @Override
    protected void controllaFine(boolean coloreCheHaMosso) {
        String risultato = esercizio.checkWin();

        if (!"Nessuno".equals(risultato) && !risultato.startsWith("Patta")) {
            boolean vincitoreBianco = "Bianco".equals(risultato);
            boolean esercizioRiuscito = vincitoreBianco == coloreCheDeveDareMatto && esercizio.getMosseRimanenti() >= 0;

            finisciConMessaggio(
                    esercizioRiuscito
                    ? "Esercizio completato! Scacco matto: vince il " + risultato
                    : "Esercizio fallito: ha vinto il " + risultato
            );
            return;
        }

        if (risultato.startsWith("Patta")) {
            finisciConMessaggio("Esercizio fallito: la partita è patta");
            return;
        }

        if (coloreCheHaMosso == coloreCheDeveDareMatto && esercizio.getMosseRimanenti() == 0) {
            finisciConMessaggio("Esercizio fallito: mosse finite, non hai dato scacco matto");
            return;
        }

        if (esercizio.isSottoScacco(esercizio.isAttaccaBianco())) {
            stato.setForeground(Color.RED);
            stato.setText("Scacco al " + nomeColore(esercizio.isAttaccaBianco()) + "!");
        }
    }

    public void usaFrame() {
        avviaFrame();
    }

    private static FrameEsercizio creaFrameDaPercorsiPredefiniti() throws IOException {
        try {
            return new FrameEsercizio(PERCORSO_LINUX);
        } catch (IOException e) {
            try {
                return new FrameEsercizio(PERCORSO_WINDOWS_1);
            } catch (IOException ee) {
                return new FrameEsercizio(PERCORSO_WINDOWS_2);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FrameEsercizio fe = creaFrameDaPercorsiPredefiniti();
                    fe.usaFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
