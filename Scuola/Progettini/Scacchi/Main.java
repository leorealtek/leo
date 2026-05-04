package Scuola.Progettini.Scacchi;

import Scuola.Progettini.Scacchi.Grafica.FramePartita;
import javax.swing.SwingUtilities;

public class Main {

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
