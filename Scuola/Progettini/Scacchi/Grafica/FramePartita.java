package Scuola.Progettini.Scacchi.Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Scuola.Progettini.Scacchi.Exception.MossaNonValidaException;
import Scuola.Progettini.Scacchi.Partite.Partita;
import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;
import Scuola.Progettini.Scacchi.Pezzi.Re;
import Scuola.Progettini.Scacchi.Pezzi.Torre;

public class FramePartita extends JFrame implements MouseListener {

    private static final int DIMENSIONE_SCACCHIERA = 8;
    private static final int DIMENSIONE_CASELLA = 90;

    private static final Color COLORE_CHIARO = new Color(240, 217, 181);
    private static final Color COLORE_SCURO = new Color(181, 136, 99);
    private static final Color COLORE_SELEZIONE = new Color(246, 246, 105);
    private static final Color COLORE_MOSSA = new Color(120, 200, 120);
    private static final Color COLORE_CATTURA = new Color(220, 80, 80);
    private static final Color COLORE_ERRORE = new Color(220, 80, 80);
    private static final Color COLORE_ARROCCO = new Color(120, 170, 230);

    private final JLabel[][] labels;
    private final Partita partita;
    private final JLabel stato;

    private int rigaSelezionata = -1;
    private int colonnaSelezionata = -1;

    private boolean erroreVisibile = false;
    private boolean partitaFinita = false;
    private Timer timerErrore;

    public FramePartita() {
        super("Scacchi");

        this.partita = new Partita();
        this.labels = new JLabel[DIMENSIONE_SCACCHIERA][DIMENSIONE_SCACCHIERA];
        this.stato = new JLabel("Turno: Bianco", SwingConstants.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        stato.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        stato.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(stato, BorderLayout.NORTH);

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
        Casella[][] mappa = partita.getMappa();

        for (int riga = 0; riga < DIMENSIONE_SCACCHIERA; riga++) {
            for (int colonna = 0; colonna < DIMENSIONE_SCACCHIERA; colonna++) {
                JLabel label = labels[riga][colonna];
                label.setBackground(coloreBase(riga, colonna));
                label.setBorder(null);

                Pezzo pezzo = mappa[riga][colonna].getPezzoContenuto();
                label.setText(pezzo == null ? "" : simboloPezzo(pezzo.getNome()));
            }
        }

        if (rigaSelezionata != -1 && colonnaSelezionata != -1 && !partitaFinita) {
            labels[rigaSelezionata][colonnaSelezionata].setBackground(COLORE_SELEZIONE);
            evidenziaMossePossibili(rigaSelezionata, colonnaSelezionata);
        }

        if (!erroreVisibile && !partitaFinita) {
            stato.setForeground(Color.BLACK);
            stato.setText("Turno: " + (partita.isAttaccaBianco() ? "Bianco" : "Nero"));
        }
    }

    private void evidenziaMossePossibili(int riga, int colonna) {
        Pezzo pezzo = partita.getMappa()[riga][colonna].getPezzoContenuto();
        if (pezzo == null) return;

        Casella[][] mosse = pezzo.mossePossibili();
        Casella[][] mappa = partita.getMappa();

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
            int rigaRe = partita.isAttaccaBianco() ? 7 : 0;
            Casella[][] mappa = partita.getMappa();

            Pezzo pezzoRe = mappa[rigaRe][4].getPezzoContenuto();
            if (!(pezzoRe instanceof Re) || pezzoRe.isBianco() != partita.isAttaccaBianco()) {
                return false;
            }

            Re re = (Re) pezzoRe;
            if (re.haMosso() || partita.isSottoScacco(partita.isAttaccaBianco())) {
                return false;
            }

            int colonnaTorre = latoLungo ? 0 : 7;
            Pezzo pezzoTorre = mappa[rigaRe][colonnaTorre].getPezzoContenuto();
            if (!(pezzoTorre instanceof Torre) || pezzoTorre.isBianco() != partita.isAttaccaBianco()) {
                return false;
            }

            Torre torre = (Torre) pezzoTorre;
            if (torre.haMosso()) {
                return false;
            }

            int primaCasellaLibera = latoLungo ? 1 : 5;
            int ultimaCasellaLibera = latoLungo ? 3 : 6;

            for (int col = primaCasellaLibera; col <= ultimaCasellaLibera; col++) {
                if (!mappa[rigaRe][col].isVuota()) {
                    return false;
                }
            }

            int colonnaReDestinazione = latoLungo ? 2 : 6;
            int passo = latoLungo ? -1 : 1;

            for (int col = 4 + passo; col != colonnaReDestinazione + passo; col += passo) {
                if (partita.casellaAttaccata(rigaRe, col, !partita.isAttaccaBianco())) {
                    return false;
                }
            }

            return true;
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
        if (partitaFinita) return;

        Pezzo pezzo = partita.getMappa()[riga][colonna].getPezzoContenuto();

        if (pezzo == null) {
            deseleziona();
            return;
        }

        if (pezzo.isBianco() != partita.isAttaccaBianco()) {
            mostraErrore("Non è il turno di questo colore");
            deseleziona();
            return;
        }

        rigaSelezionata = riga;
        colonnaSelezionata = colonna;
        aggiornaGrafica();
    }

    private void muoviSuCasella(int rigaArrivo, int colonnaArrivo) {
        if (partitaFinita) return;

        try {
            Pezzo pezzo = partita.getMappa()[rigaSelezionata][colonnaSelezionata].getPezzoContenuto();

            if (isTentativoArrocco(pezzo, rigaArrivo, colonnaArrivo)) {
                boolean latoLungo = colonnaArrivo == 2;

                if (!puoArroccare(latoLungo)) {
                    throw new MossaNonValidaException("Arrocco non valido");
                }

                partita.arrocca(latoLungo);
            } else {
                partita.muoviPezzo(rigaSelezionata, colonnaSelezionata, rigaArrivo, colonnaArrivo);
            }

            deseleziona();
            controllaFinePartita();

        } catch (MossaNonValidaException | IllegalArgumentException ex) {
            mostraErrore("Mossa invalida");
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

    private void controllaFinePartita() {
        String vincitore = partita.checkWin();

        if (!"Nessuno".equals(vincitore)) {
            partitaFinita = true;
            rigaSelezionata = -1;
            colonnaSelezionata = -1;
            erroreVisibile = false;

            if (timerErrore != null && timerErrore.isRunning()) {
                timerErrore.stop();
            }

            aggiornaGrafica();

            stato.setForeground(Color.BLACK);
            stato.setText("Scacco matto! Vince il " + vincitore);

            bloccaScacchiera();

        return;
    }

        if (partita.isSottoScacco(partita.isAttaccaBianco())) {
            stato.setForeground(Color.RED);
            stato.setText("Scacco al " + (partita.isAttaccaBianco() ? "Bianco" : "Nero") + "!");
        }
    }

    private void deseleziona() {
        rigaSelezionata = -1;
        colonnaSelezionata = -1;
        aggiornaGrafica();
    }

    private void mostraErrore(String messaggio) {
        if (partitaFinita) return;

        if (timerErrore != null && timerErrore.isRunning()) {
            timerErrore.stop();
        }

        erroreVisibile = true;

        stato.setText(messaggio != null && !messaggio.isBlank() ? messaggio : "Mossa invalida");
        stato.setForeground(COLORE_ERRORE);

        timerErrore = new Timer(1500, e -> {
            erroreVisibile = false;

            if (!partitaFinita) {
                stato.setForeground(Color.BLACK);
                stato.setText("Turno: " + (partita.isAttaccaBianco() ? "Bianco" : "Nero"));
            }
        });

        timerErrore.setRepeats(false);
        timerErrore.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (partitaFinita) return;

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

        Pezzo pezzoCliccato = partita.getMappa()[riga][colonna].getPezzoContenuto();

        if (pezzoCliccato != null && pezzoCliccato.isBianco() == partita.isAttaccaBianco()) {
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

    @Override
    public void mouseEntered(MouseEvent e) {
        if (partitaFinita) return;

        JLabel label = (JLabel) e.getSource();
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (partitaFinita) return;
        aggiornaGrafica();
    }

    @Override 
    public void mousePressed(MouseEvent e) {}
    
    @Override 
    public void mouseReleased(MouseEvent e) {}

}