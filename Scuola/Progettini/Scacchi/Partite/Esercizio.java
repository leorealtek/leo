package Scuola.Progettini.Scacchi.Partite;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Scuola.Progettini.Scacchi.Pezzi.Alfiere;
import Scuola.Progettini.Scacchi.Pezzi.Cavallo;
import Scuola.Progettini.Scacchi.Pezzi.Pedone;
import Scuola.Progettini.Scacchi.Pezzi.Re;
import Scuola.Progettini.Scacchi.Pezzi.Regina;
import Scuola.Progettini.Scacchi.Pezzi.Torre;
import Scuola.Progettini.Scacchi.Util.Casella;

public class Esercizio {
     
    private Casella[][] mappa;
    private boolean attaccaBianco;
    private int mosseTotali;
    private String percorsoFile;

    public Esercizio(String percorsoFile) throws IOException {
        this.percorsoFile = percorsoFile;
        creaMappa();
    }

    public void creaMappa() throws IOException {
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
                        throw new IllegalArgumentException("File non valido");
                }
            }
        }
        attaccaBianco = (s.nextLine().toUpperCase().equals("BIANCO")) ? true : false;
        mosseTotali = Integer.parseInt(s.nextLine().trim());
        s.close();
        this.mappa = mappa;
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

    public Casella[][] getMappa() {
        return mappa;
    }

    public boolean isAttaccaBianco() {
        return attaccaBianco;
    }

    public int getMosseTotali() {
        return mosseTotali;
    }

    public String getPercorsoFile() {
        return percorsoFile;
    }
}