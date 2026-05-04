package Scuola.Progettini.Scacchi.Grafica;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Partite.Esercizio;
import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class FrameEsercizio extends JFrame implements MouseListener {

    private static final int DIMENSIONE_SCACCHIERA = 8;
    private static final int DIMENSIONE_CASELLA = 100;

    private static final Color COLORE_CHIARO = new Color(240, 217, 181);
    private static final Color COLORE_SCURO = new Color(181, 136, 99);
    private static final Color COLORE_SELEZIONE = new Color(246, 246, 105);
    private static final Color COLORE_MOSSA = new Color(120, 200, 120);
    private static final Color COLORE_CATTURA = new Color(220, 80, 80);
    private static final Color COLORE_ERRORE = new Color(220, 80, 80);
    private static final Color COLORE_ARROCCO = new Color(120, 170, 230);

    private final JLabel[][] labels;
    private final Esercizio esercizio;
    private final JLabel stato;
    private final JLabel infoEsercizio;

    private final boolean coloreCheDeveDareMatto;
    private int mosseRimanenti;

    private int rigaSelezionata = -1;
    private int colonnaSelezionata = -1;

    private boolean erroreVisibile = false;
    private boolean esercizioFinito = false;
    private Timer timerErrore;

    public FrameEsercizio(String percorsoFile) throws IOException {
        super("Esercizio");

        this.esercizio = new Esercizio(percorsoFile);
        this.coloreCheDeveDareMatto = esercizio.getColoreCheDeveDareMatto();
        this.mosseRimanenti = esercizio.getMosseRimanenti();

        this.labels = new JLabel[DIMENSIONE_SCACCHIERA][DIMENSIONE_SCACCHIERA];
        this.stato = new JLabel("", SwingConstants.CENTER);
        this.infoEsercizio = new JLabel("", SwingConstants.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel pannelloAlto = new JPanel(new GridLayout(2, 1));
        pannelloAlto.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        stato.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        infoEsercizio.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        pannelloAlto.add(stato);
        pannelloAlto.add(infoEsercizio);
        add(pannelloAlto, BorderLayout.NORTH);

        JPanel scacchiera = new JPanel(new GridLayout(DIMENSIONE_SCACCHIERA, DIMENSIONE_SCACCHIERA));
        scacchiera.setPreferredSize(new Dimension(
                DIMENSIONE_CASELLA * DIMENSIONE_SCACCHIERA,
                DIMENSIONE_CASELLA * DIMENSIONE_SCACCHIERA
        ));

        createLabels(scacchiera);
        add(scacchiera, BorderLayout.CENTER);

        aggiornaGrafica();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createLabels(JPanel scacchiera) {
        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 48));
                label.setOpaque(true);
                label.putClientProperty("riga", riga);
                label.putClientProperty("colonna", colonna);
                label.addMouseListener(this);

                labels[riga][colonna] = label;
                scacchiera.add(label);
            }
        }
    }

    private void aggiornaGrafica() {
        Casella[][] mappa = esercizio.getMappa();

        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                JLabel label = labels[riga][colonna];
                label.setBackground(coloreBase(riga, colonna));
                label.setBorder(null);

                Pezzo pezzo = mappa[riga][colonna].getPezzoContenuto();
                label.setText(pezzo == null ? "" : simboloPezzo(pezzo.getNome()));
            }
        }

        if (rigaSelezionata != -1 && colonnaSelezionata != -1 && !esercizioFinito) {
            labels[rigaSelezionata][colonnaSelezionata].setBackground(COLORE_SELEZIONE);
            evidenziaMossePossibili(rigaSelezionata, colonnaSelezionata);
        }

        aggiornaInfoEsercizio();

        if (!erroreVisibile && !esercizioFinito) {
            stato.setForeground(Color.BLACK);
            stato.setText("Turno: " + nomeColore(esercizio.isAttaccaBianco()));
        }
    }

    private void aggiornaInfoEsercizio() {
        infoEsercizio.setForeground(Color.DARK_GRAY);
        infoEsercizio.setText(
                "Parte: " + nomeColore(coloreCheDeveDareMatto)
                + "  |  Mosse disponibili per dare matto: " + mosseRimanenti
        );
    }

    private void evidenziaMossePossibili(int riga, int colonna) {
        Pezzo pezzo = esercizio.getMappa()[riga][colonna].getPezzoContenuto();
        if (pezzo == null) return;

        Casella[][] mosse = pezzo.mossePossibili();
        Casella[][] mappa = esercizio.getMappa();

        for (int i = 0; i < DIMENSIONE_SCACCHIERA; i++) {
            for (int j = 0; j < DIMENSIONE_SCACCHIERA; j++) {
                if (mosse[i][j] != null) {
                    Pezzo pezzoDestinazione = mappa[i][j].getPezzoContenuto();

                    if (pezzoDestinazione != null && pezzoDestinazione.isBianco() != pezzo.isBianco()) {
                        labels[i][j].setBackground(COLORE_CATTURA);
                        labels[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    } else {
                        labels[i][j].setBackground(COLORE_MOSSA);
                        labels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    }
                }
            }
        }

        evidenziaArroccoSePossibile(pezzo);
    }

    private void evidenziaArroccoSePossibile(Pezzo pezzo) {
        if (Character.toUpperCase(pezzo.getNome()) != 'R') return;

        int rigaRe = pezzo.isBianco() ? 7 : 0;
        if (rigaSelezionata != rigaRe || colonnaSelezionata != 4) return;

        if (puoArroccare(false)) {
            labels[rigaRe][6].setBackground(COLORE_ARROCCO);
            labels[rigaRe][6].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        }

        if (puoArroccare(true)) {
            labels[rigaRe][2].setBackground(COLORE_ARROCCO);
            labels[rigaRe][2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        }
    }

    private boolean puoArroccare(boolean latoLungo) {
        try {
            return esercizio.puoArroccare(latoLungo);
        } catch (RuntimeException ex) {
            return false;
        }
    }

    private Color coloreBase(int riga, int colonna) {
        return (riga + colonna) % 2 == 0 ? COLORE_CHIARO : COLORE_SCURO;
    }

    private String simboloPezzo(char nome) {
        switch (nome) {
            case 'R': return "♔";
            case 'D': return "♕";
            case 'T': return "♖";
            case 'A': return "♗";
            case 'C': return "♘";
            case 'P': return "♙";
            case 'r': return "♚";
            case 'd': return "♛";
            case 't': return "♜";
            case 'a': return "♝";
            case 'c': return "♞";
            case 'p': return "♟";
            default: return String.valueOf(nome);
        }
    }

    private void selezionaCasella(int riga, int colonna) {
        if (esercizioFinito) return;

        Pezzo pezzo = esercizio.getMappa()[riga][colonna].getPezzoContenuto();

        if (pezzo == null) {
            deseleziona();
            return;
        }

        if (pezzo.isBianco() != esercizio.isAttaccaBianco()) {
            mostraErrore("Non è il turno di questo colore");
            deseleziona();
            return;
        }

        rigaSelezionata = riga;
        colonnaSelezionata = colonna;
        aggiornaGrafica();
    }

    private void muoviSuCasella(int rigaArrivo, int colonnaArrivo) {
        if (esercizioFinito) return;

        try {
            boolean coloreCheHaMosso = esercizio.isAttaccaBianco();
            Pezzo pezzo = esercizio.getMappa()[rigaSelezionata][colonnaSelezionata].getPezzoContenuto();

            if (isTentativoArrocco(pezzo, rigaArrivo, colonnaArrivo)) {
                boolean latoLungo = colonnaArrivo == 2;

                if (!puoArroccare(latoLungo)) {
                    throw new MossaNonValidaException("Arrocco non valido");
                }

                esercizio.arrocca(latoLungo);
            } else {
                esercizio.muoviPezzo(rigaSelezionata, colonnaSelezionata, rigaArrivo, colonnaArrivo);
            }

            mosseRimanenti = esercizio.getMosseRimanenti();

            deseleziona();
            controllaFineEsercizio(coloreCheHaMosso);

        } catch (MossaNonValidaException | IllegalArgumentException e) {
            mostraErrore(e.getMessage());
            deseleziona();
        }
    }

    private boolean isTentativoArrocco(Pezzo pezzo, int rigaArrivo, int colonnaArrivo) {
        if (pezzo == null) return false;
        if (Character.toUpperCase(pezzo.getNome()) != 'R') return false;

        return rigaArrivo == rigaSelezionata
                && colonnaSelezionata == 4
                && (colonnaArrivo == 2 || colonnaArrivo == 6);
    }

    private void controllaFineEsercizio(boolean coloreCheHaMosso) {
        String risultato = esercizio.checkWin();

        if (!"Nessuno".equals(risultato) && !risultato.startsWith("Patta")) {
            boolean vincitoreBianco = "Bianco".equals(risultato);
            boolean esercizioRiuscito = vincitoreBianco == coloreCheDeveDareMatto && mosseRimanenti >= 0;

            finisciEsercizio(
                    esercizioRiuscito
                    ? "Esercizio completato! Scacco matto: vince il " + risultato
                    : "Esercizio fallito: ha vinto il " + risultato
            );
            return;
        }

        if (risultato.startsWith("Patta")) {
            finisciEsercizio("Esercizio fallito: la partita è patta");
            return;
        }

        if (coloreCheHaMosso == coloreCheDeveDareMatto && mosseRimanenti == 0) {
            finisciEsercizio("Esercizio fallito: mosse finite, non hai dato scacco matto");
            return;
        }

        if (esercizio.isSottoScacco(esercizio.isAttaccaBianco())) {
            stato.setForeground(Color.RED);
            stato.setText("Scacco al " + nomeColore(esercizio.isAttaccaBianco()) + "!");
        }
    }

    private void finisciEsercizio(String messaggio) {
        esercizioFinito = true;
        rigaSelezionata = -1;
        colonnaSelezionata = -1;
        erroreVisibile = false;

        if (timerErrore != null && timerErrore.isRunning()) {
            timerErrore.stop();
        }

        aggiornaGrafica();
        stato.setForeground(Color.BLACK);
        stato.setText(messaggio);
        bloccaScacchiera();
    }

    private void deseleziona() {
        rigaSelezionata = -1;
        colonnaSelezionata = -1;
        aggiornaGrafica();
    }

    private void mostraErrore(String messaggio) {
        if (esercizioFinito) return;

        if (timerErrore != null && timerErrore.isRunning()) {
            timerErrore.stop();
        }

        erroreVisibile = true;

        stato.setText(messaggio != null && !messaggio.isBlank() ? messaggio : "Mossa invalida");
        stato.setForeground(COLORE_ERRORE);

        timerErrore = new Timer(1500, e -> {
            erroreVisibile = false;

            if (!esercizioFinito) {
                stato.setForeground(Color.BLACK);
                stato.setText("Turno: " + (esercizio.isAttaccaBianco() ? "Bianco" : "Nero"));
            }
        });

        timerErrore.setRepeats(false);
        timerErrore.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (esercizioFinito) return;

        JLabel label = (JLabel) e.getSource();
        int riga = (int) label.getClientProperty("riga");
        int colonna = (int) label.getClientProperty("colonna");

        if (rigaSelezionata == -1 || colonnaSelezionata == -1) {
            selezionaCasella(riga, colonna);
            return;
        }

        if (riga == rigaSelezionata && colonna == colonnaSelezionata) {
            deseleziona();
            return;
        }

        Pezzo pezzoCliccato = esercizio.getMappa()[riga][colonna].getPezzoContenuto();

        if (pezzoCliccato != null && pezzoCliccato.isBianco() == esercizio.isAttaccaBianco()) {
            selezionaCasella(riga, colonna);
            return;
        }

        muoviSuCasella(riga, colonna);
    }

    private void bloccaScacchiera() {
        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                labels[riga][colonna].removeMouseListener(this);
                labels[riga][colonna].setEnabled(false);
            }
        }
    }

    private String nomeColore(boolean bianco) {
        return bianco ? "Bianco" : "Nero";
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (esercizioFinito) return;

        JLabel label = (JLabel) e.getSource();
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (esercizioFinito) return;
        aggiornaGrafica();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        new FrameEsercizio("/home/leo/Scrivania/leo/Scuola/Progettini/Scacchi/Partite/FileEsercizi/Esercizio.txt");
                    } catch (IOException e) {
                        try {
                            new FrameEsercizio("C:/Users/leonardo.giacomin/Desktop/leo/Scuola/Progettini/Scacchi/Partite/FileEsercizi/Esercizio.txt");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}