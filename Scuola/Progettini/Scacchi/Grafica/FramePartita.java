package Scuola.Progettini.Scacchi.Grafica;

import Scuola.Progettini.Scacchi.Partite.Partita;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class FramePartita extends FrameScacchiAstratto {

    private final Partita partitaNormale;
    private JButton salvaPartita;

    public FramePartita(boolean conBot) {
        this(new Partita(), conBot);
    }

    public FramePartita(String percorsoFile, boolean conBot) throws IOException {
        this(new Partita(percorsoFile), conBot);
    }

    private FramePartita(Partita partita, boolean conBot) {
        super("Scacchi", partita, conBot);
        this.partitaNormale = partita;
        this.salvaPartita = new JButton("Salva partita");
        aggiungiPulsanteSalvaPartita();
    }

    private void aggiungiPulsanteSalvaPartita() {
        salvaPartita = new JButton("Salva partita");
        salvaPartita.setFocusable(false);

        salvaPartita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File cartella = new File("Scuola/Progettini/Scacchi/FilePartite");

                    if (!cartella.exists()) {
                        cartella.mkdirs();
                    }

                    int numero = 1;
                    File fileDaSalvare;

                    do {
                        fileDaSalvare = new File(cartella, "Partita" + numero + ".txt");
                        numero++;
                    } while (fileDaSalvare.exists());

                    partitaNormale.salvaSuFile(fileDaSalvare.getPath());

                    JOptionPane.showMessageDialog(
                        FramePartita.this,
                        "Partita salvata in:\n" + fileDaSalvare.getAbsolutePath(),
                        "Salvataggio completato",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    String[] scelte = {"Continua", "Esci"};

                    int scelta = JOptionPane.showOptionDialog(
                        null,
                        "Vuoi continuare a giocare?",
                        "Salvataggio partita",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("Scuola/Progettini/Scacchi/Immagini/Scelta.jpg"),
                        scelte,
                        scelte[0]
                    );

                    if (scelta <= 0) return;
                    if (scelta == 1) {
                        System.exit(0);
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                        FramePartita.this,
                        "Errore durante il salvataggio:\n" + ex.getMessage(),
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        JPanel pannelloAlto = new JPanel(new BorderLayout());
        pannelloAlto.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel pannelloTesti = new JPanel(new GridLayout(2, 1));
        pannelloTesti.add(stato);
        pannelloTesti.add(info);

        JPanel spazioSinistra = new JPanel();
        spazioSinistra.setPreferredSize(salvaPartita.getPreferredSize());

        pannelloAlto.add(spazioSinistra, BorderLayout.WEST);
        pannelloAlto.add(pannelloTesti, BorderLayout.CENTER);
        pannelloAlto.add(salvaPartita, BorderLayout.EAST);

        getContentPane().remove(0);
        add(pannelloAlto, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    @Override
    protected void controllaFine(boolean coloreCheHaMosso) {
        String risultato = partitaNormale.checkWin();

        if (partitaNormale.pattaPerMosse()) {
            finisciConMessaggio("Patta per la regola delle 50 mosse");
        }

        if (risultato.equals("Nessuno")) {
            if (partitaNormale.isSottoScacco(partitaNormale.isAttaccaBianco())) {
                stato.setForeground(Color.RED);
                stato.setText("Scacco al " + nomeColore(partitaNormale.isAttaccaBianco()) + "!");
            }
            return;
        }

        if (risultato.equals("Patta BIANCO")) {
            finisciConMessaggio("Patta per stallo, il bianco non può muoversi");
        } else if (risultato.equals("Patta NERO")) {
            finisciConMessaggio("Patta per stallo, il nero non può muoversi");
        } else {
            finisciConMessaggio("Scacco matto! Vince il " + risultato);
        }
    }

    @Override
    protected void aggiornaInfoExtra() {
        if (info == null) return;

        info.setForeground(Color.DARK_GRAY);
        info.setText("Mossa: " + partitaNormale.getMosse());
    }

}