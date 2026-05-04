package Scuola.Progettini.Scacchi.Grafica;

import Scuola.Progettini.Scacchi.Partite.Partita;
import java.awt.Color;
import javax.swing.SwingUtilities;

public class FramePartita extends FrameScacchiAstratto {

    private final Partita partitaNormale;

    public FramePartita() {
        this(new Partita());
    }

    private FramePartita(Partita partita) {
        super("Scacchi", partita);
        this.partitaNormale = partita;
    }

    @Override
    protected void controllaFine(boolean coloreCheHaMosso) {
        String risultato = partitaNormale.checkWin();

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

    public void usaFrame() {
        avviaFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FramePartita fp = new FramePartita();
                fp.usaFrame();
            }
        });
    }
}