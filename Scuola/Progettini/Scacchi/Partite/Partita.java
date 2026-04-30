package Scuola.Progettini.Scacchi.Partite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Scuola.Progettini.Scacchi.Exception.FileNonValidoException;
import Scuola.Progettini.Scacchi.Pezzi.Alfiere;
import Scuola.Progettini.Scacchi.Pezzi.Cavallo;
import Scuola.Progettini.Scacchi.Pezzi.Pedone;
import Scuola.Progettini.Scacchi.Pezzi.Re;
import Scuola.Progettini.Scacchi.Pezzi.Regina;
import Scuola.Progettini.Scacchi.Pezzi.Torre;
import Scuola.Progettini.Scacchi.Util.Casella;

public class Partita {
    private Casella[][] mappa;
    private boolean attaccaBianco;

    public Partita() {
        mappa = new Casella[8][8];
        mappa[0][0] = new Casella(new Torre('t', 0, 0, mappa));
        mappa[0][1] = new Casella(new Cavallo('c', 0, 1, mappa));
        mappa[0][2] = new Casella(new Alfiere('a', 0, 2, mappa));
        mappa[0][3] = new Casella(new Regina('d', 0, 3, mappa));
        mappa[0][4] = new Casella(new Re('r', 0, 4, mappa));
        mappa[0][5] = new Casella(new Alfiere('a', 0, 5, mappa));
        mappa[0][6] = new Casella(new Cavallo('c', 0, 6, mappa));
        mappa[0][7] = new Casella(new Torre('t', 0, 7, mappa));
        for (int i = 0; i < mappa.length; i++) {
            mappa[1][i] = new Casella(new Pedone('p', 1, i, mappa));
            mappa[6][i] = new Casella(new Pedone('P', 6, i, mappa));
            for (int j = 2; j < 6; j++) {
                mappa[j][i] = new Casella();
            }
        }
        mappa[7][0] = new Casella(new Torre('T', 7, 0, mappa));
        mappa[7][1] = new Casella(new Cavallo('C', 7, 1, mappa));
        mappa[7][2] = new Casella(new Alfiere('A', 7, 2, mappa));
        mappa[7][3] = new Casella(new Re('R', 7, 3, mappa));
        mappa[7][4] = new Casella(new Regina('D', 7, 4, mappa));
        mappa[7][5] = new Casella(new Alfiere('A', 7, 5, mappa));
        mappa[7][6] = new Casella(new Cavallo('C', 7, 6, mappa));
        mappa[7][7] = new Casella(new Torre('T', 7, 7, mappa));
    }

    public void stampaMappa() {
        for (Casella[] caselle : mappa) {
            for (Casella casella : caselle) {
                if (casella.getPezzoContenuto() != null) {
                    System.out.print(casella.getPezzoContenuto().getNome() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public String checkWin() {
        boolean trovatoBianco = false, trovatoNero = false;
        for (Casella[] caselle : mappa) {
            for (Casella casella : caselle) {
                if (casella == null) continue;
                if (casella.getPezzoContenuto() != null && casella.getPezzoContenuto().getNome() == 'r') {
                    trovatoNero = true;
                }
                if (casella.getPezzoContenuto() != null && casella.getPezzoContenuto().getNome() == 'R') {
                    trovatoBianco = true;
                }
            }
        }

        if (!trovatoBianco) {
            return "Nero";
        }
        if (!trovatoNero) {
            return "Bianco";
        }

        return "Nessuno";
    }

    public void caricaMappaDaFile(String percorsoFile) throws IOException {
        Scanner s = new Scanner(new File(percorsoFile));
        Casella[][] mappa = new Casella[8][8];
        for (int i = 0; i < 8; i++) {
            int indice = 0;
            String riga = s.nextLine();
            for (char c : riga.toCharArray()) {
                switch (c) {
                    case 't':
                        mappa[i][indice] = new Casella(new Torre(c, i, indice++, mappa));                            
                        break;
                    case 'c':
                        mappa[i][indice] = new Casella(new Cavallo(c, i, indice++, mappa));
                        break;
                    case 'a':
                        mappa[i][indice] = new Casella(new Alfiere(c, i, indice++, mappa));
                        break;
                    case 'd':
                        mappa[i][indice] = new Casella(new Regina(c, i, indice++, mappa));
                        break;
                    case 'r':
                        mappa[i][indice] = new Casella(new Re(c, i, indice++, mappa));
                        break;
                    case 'p':
                        mappa[i][indice] = new Casella(new Pedone(c, i, indice++, mappa));
                        break;
                    case 'T':
                        mappa[i][indice] = new Casella(new Torre(c, i, indice++, mappa));                            
                        break;
                    case 'C':
                        mappa[i][indice] = new Casella(new Cavallo(c, i, indice++, mappa));
                        break;
                    case 'A':
                        mappa[i][indice] = new Casella(new Alfiere(c, i, indice++, mappa));
                        break;
                    case 'D':
                        mappa[i][indice] = new Casella(new Regina(c, i, indice++, mappa));
                        break;
                    case 'R':
                        mappa[i][indice] = new Casella(new Re(c, i, indice++, mappa));
                        break;
                    case 'P':
                        mappa[i][indice] = new Casella(new Pedone(c, i, indice++, mappa));
                        break;
                    case '.':
                        mappa[i][indice++] = new Casella();
                        break;
                    case ' ':
                        continue;
                    default:
                        throw new FileNonValidoException();
                }
            }
        }
        attaccaBianco = (s.nextLine().toUpperCase().equals("BIANCO")) ? true : false;
        s.close();
        this.mappa = mappa;
    }

    public void salvaSuFile(String percorsoFile) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(percorsoFile));

        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                Casella casella = mappa[i][j];

                if (casella != null && casella.getPezzoContenuto() != null) {
                    writer.print(casella.getPezzoContenuto().getNome());
                } else {
                    writer.print(".");
                }

                if (j < mappa[i].length - 1) {
                    writer.print(" ");
                }
            }
            writer.println();
        }

        writer.println(attaccaBianco ? "BIANCO" : "NERO");

        writer.close();
    }

    public Casella[][] getMappa() {
        return mappa;
    }

    public boolean attaccaBianco() {
        return attaccaBianco;
    }
}