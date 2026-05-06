package Scuola.Progettini.Scacchi.Grafica;

import Scuola.Progettini.Scacchi.Partite.Esercizio;
import java.awt.Color;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class FrameEsercizio extends FrameScacchiAstratto {

    private final Esercizio esercizio;
    private final boolean coloreCheDeveDareMatto;
    private String percorsoFile;

    public FrameEsercizio(String percorsoFile) throws IOException {
        this(new Esercizio(percorsoFile));
        this.percorsoFile = percorsoFile;
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

    @Override
    public void creaFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FrameEsercizio fe = new FrameEsercizio(percorsoFile);
                    fe.avviaFrame();
                } catch (IOException e) {
                    try {
                        FrameEsercizio fe = new FrameEsercizio(new Esercizio(percorsoFile));
                        fe.avviaFrame();
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }
        });
    }
}
