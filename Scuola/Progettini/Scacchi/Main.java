package Scuola.Progettini.Scacchi;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.File;

import Scuola.Progettini.Scacchi.Grafica.*;
import Scuola.Progettini.Scacchi.Partite.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] scelte = {"Partita", "Esercizio"};

        int sceltaIniziale = JOptionPane.showOptionDialog(
            null,
            "Inizio partita",
            "Menù di scelta",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon("Scuola/Progettini/Scacchi/Immagini/Scelta.jpg"),
            scelte,
            scelte[0]
        );

        if (sceltaIniziale == 0) {

            String[] sceltePartita = {"Crea nuova partita", "Carica partita da file"};

            int sceltaSeCaricare = JOptionPane.showOptionDialog(
                null,
                "Crea partita",
                "Menù di scelta",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("Scuola/Progettini/Scacchi/Immagini/Scelta.jpg"),
                sceltePartita,
                scelte[0]
            );

            if (sceltaSeCaricare == 0) {
                FramePartita fp = new FramePartita();
                fp.creaFrame();
            }

            if (sceltaSeCaricare == 1) {
                ArrayList<File> files = new ArrayList<>();

                File cartella = new File("Scuola/Progettini/Scacchi/FilePartite");

                if (cartella.exists() && cartella.isDirectory()) {
                    File[] elencoFile = cartella.listFiles();

                    if (elencoFile != null) {
                        for (File file : elencoFile) {
                            if (file.isFile() && file.getName().endsWith(".txt")) {
                                files.add(file);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "La cartella delle partite non esiste",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                    );
                }

                String[] scelteFile = new String[files.size()];
                for (int i = 0; i < scelteFile.length; i++) {
                    scelteFile[i] = files.get(i).getName();
                }

                int sceltaFile = JOptionPane.showOptionDialog(
                    null,
                    "Crea partita",
                    "Menù di scelta",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("Scuola/Progettini/Scacchi/Immagini/Scelta.jpg"),
                    scelteFile,
                    null
                );
                FramePartita fp = new FramePartita(files.get(sceltaFile).getAbsolutePath());
                fp.creaFrame();
            }
        }
        if (sceltaIniziale == 1) {
            ArrayList<File> files = new ArrayList<>();
            File cartella = new File("Scuola/Progettini/Scacchi/FileEsercizi");
            if (cartella.exists() && cartella.isDirectory()) {
                File[] elencoFile = cartella.listFiles();
                if (elencoFile != null) {
                    for (File file : elencoFile) {
                        if (file.isFile() && file.getName().endsWith(".txt")) {
                            files.add(file);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "La cartella delle partite non esiste",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            String[] scelteFile = new String[files.size()];
            for (int i = 0; i < scelteFile.length; i++) {
               scelteFile[i] = files.get(i).getName();
            }
            
            int sceltaFile = JOptionPane.showOptionDialog(
                null,
                "Crea partita",
                "Menù di scelta",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,   
                new ImageIcon("Scuola/Progettini/Scacchi/Immagini/Scelta.jpg"),
                scelteFile,
                null
            );
            FrameEsercizio fe = new FrameEsercizio(files.get(sceltaFile).getAbsolutePath());
            fe.creaFrame();
        }
    }
}