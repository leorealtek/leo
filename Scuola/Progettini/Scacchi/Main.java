package Scuola.Progettini.Scacchi;

import Scuola.Progettini.Scacchi.Grafica.*;
import Scuola.Progettini.Scacchi.Util.Bot;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main {

    private static int mostraDialogPerScelta(String titolo, Object messaggio, String percorsoImmagine, String[] opzioni, String valoreIniziale) {
        int scelta = JOptionPane.showOptionDialog(
            null,
            messaggio,
            titolo,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon(percorsoImmagine),
            opzioni,
            valoreIniziale
        );
        return scelta;
    }


    private static boolean scegliConBot() {
        int sceltaBot = mostraDialogPerScelta(
            "Modalità partita",
            "Vuoi giocare contro il bot?",
            "Scuola/Progettini/Scacchi/Immagini/Pedone.png",
            new String[]{"2 giocatori", "Contro bot"},
            "2 giocatori"
        );

        if (sceltaBot < 0) {
            System.exit(0);
        }

        return sceltaBot == 1;
    }

    private static int scegliDifficoltaBot() {
        int sceltaBot = mostraDialogPerScelta(
            "Difficoltà bot",
            "Imposta difficoltà bot",
            "Scuola/Progettini/Scacchi/Immagini/Pedone.png",
            new String[]{"Facile", "Media", "Difficile"},
            "Media"
        );

        if (sceltaBot == 0) sceltaBot += 1;
        if (sceltaBot == 1 || sceltaBot == 2) sceltaBot += 2;

        if (sceltaBot < 0) {
            System.exit(0);
        }

        return sceltaBot;
    }

    private static ArrayList<File> trovaFile(String percorsoCartella) {
        ArrayList<File> files = new ArrayList<>();
        File cartella = new File(percorsoCartella);

        if (cartella.exists() && cartella.isDirectory()) {
            File[] elencoFile = cartella.listFiles();
            if (elencoFile != null) {
                for (File file : elencoFile) {
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        files.add(file);
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(
                null,
                "La cartella delle partite non esiste",
                "Errore",
                JOptionPane.ERROR_MESSAGE
            );

            System.exit(0);
        }

        if (files.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "Non ci sono partite salvate",
                "Errore",
                JOptionPane.ERROR_MESSAGE
            );

            System.exit(0);
        }
        return files;
    }

    public static void main(String[] args) throws IOException {
        int sceltaIniziale = mostraDialogPerScelta(
                                "Menù", 
                                "Scegli che modalita vuoi giocare",
                                "Scuola/Progettini/Scacchi/Immagini/Pedone.png",
                                new String[]{"Partita", "Esercizio"},
                                "Partita"
                            );

        if (sceltaIniziale == 0) {
            int sceltaPartita = mostraDialogPerScelta(
                                    "Partita", 
                                    "                    Crea partita",
                                    "Scuola/Progettini/Scacchi/Immagini/Pedone.png",
                                    new String[]{"Crea nuova partita", "Carica partita da file"},
                                    "Crea nuova partita"
                                );

            switch (sceltaPartita) {
                case 0:
                    {
                        boolean conBot = scegliConBot();
                        FramePartita fp = new FramePartita(conBot);
                        if (conBot) {
                            int difficoltaBot = scegliDifficoltaBot();
                            Bot bot = new Bot(difficoltaBot);
                            fp.getPartita().setBot(bot);
                        }       fp.avviaFrame();
                        break;
                    }
                case 1:
                    {
                        ArrayList<File> files = trovaFile("Scuola/Progettini/Scacchi/FilePartite");
                        String[] nomiFile = new String[files.size()];
                        for (int i = 0; i < nomiFile.length; i++) {
                            nomiFile[i] = files.get(i).getName().replace(".txt", "");
                        }       
                        JComboBox<String> tendinaPartite = new JComboBox<>(nomiFile);
                        int sceltaFile = mostraDialogPerScelta(
                                "Scegli partita",
                                tendinaPartite,
                                "Scuola/Progettini/Scacchi/Immagini/File.png",
                                null,
                                null
                        );
                        if (sceltaFile != JOptionPane.OK_OPTION) {
                            System.exit(0);
                        }       int indiceFile = tendinaPartite.getSelectedIndex();
                        boolean conBot = scegliConBot();
                        FramePartita fp = new FramePartita(files.get(indiceFile).getAbsolutePath(), conBot);
                        if (conBot) {
                            int difficoltaBot = scegliDifficoltaBot();
                            Bot bot = new Bot(difficoltaBot);
                            fp.getPartita().setBot(bot);
                        }       fp.avviaFrame();
                        break;
                    }
                default:
                    System.exit(0);
            }
        }

        if (sceltaIniziale == 1) {
            ArrayList<File> files = trovaFile("Scuola/Progettini/Scacchi/FileEsercizi");

            String[] scelteFile = new String[files.size()];

            for (int i = 0; i < scelteFile.length; i++) {
                scelteFile[i] = files.get(i).getName().replace(".txt", "");
            }

            JComboBox<String> tendinaEsercizi = new JComboBox<>(scelteFile);

            int sceltaFile = mostraDialogPerScelta(
                                "Scegli partita", 
                                tendinaEsercizi, 
                                "Scuola/Progettini/Scacchi/Immagini/File.png",
                                null,
                                null
                            );

            if (sceltaFile != JOptionPane.OK_OPTION) {
                System.exit(0);
            }

            int indiceFile = tendinaEsercizi.getSelectedIndex();

            FrameEsercizio fe = new FrameEsercizio(files.get(indiceFile).getAbsolutePath(), true);
            int difficoltaBot = scegliDifficoltaBot();
            Bot bot = new Bot(difficoltaBot);
            fe.getPartita().setBot(bot);
            fe.avviaFrame();
            
        }

        if (sceltaIniziale < 0) {
            System.exit(0);
        }
    }
}