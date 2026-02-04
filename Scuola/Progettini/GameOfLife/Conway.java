package Scuola.Progettini.GameOfLife;

import java.util.Scanner;

public class Conway {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final Scanner kbd = new Scanner(System.in);

    private final int larghezza;
    private char[][] mappa;

    public Conway(int larghezza) {
        this.larghezza = larghezza;
        this.mappa = creaMappa();
    }

    private char[][] creaMappa(){
        char[][] mappa = new char[larghezza][larghezza];
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                mappa[i][j] = '.';
            }
        }
        return mappa;
    }

    public void stampaMappa() {
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                if (mappa[i][j] == 'V') {
                    System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_RED_BACKGROUND + " " + ANSI_RESET);
                }
            }
            System.out.println();
        }
    }

    public void stampaCelleVive(){
        int indice = 1;
        System.out.println("Celle vive:");
        for (int riga = 0; riga < mappa.length; riga++) {
            for (int col = 0; col < mappa[riga].length; col++) {
                if (mappa[riga][col] == 'V') {
                    System.out.println(indice + ". Riga: " + riga + " colonna: " + col);
                    indice++;
                }
            }
        }
        if (indice == 1) {
            System.out.println("non ci sono celle vive");
        }
    }
    
    public void mettiVita(int riga, int col){
        try {
            mappa[riga][col] = 'V';
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Coordinate invalide");
        }
    }

    public void togliVita(int riga, int col){
        try {
            mappa[riga][col] = '.';
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Coordinate invalide");
        }
    }

    public int contaViciniVivi(int riga, int col){
        int viciniVivi = 0;
        boolean esiste;
        char contenutoCella = ' ';
        for (int k = riga-1; k <= riga+1; k++) {
            for (int m = col-1; m <= col + 1; m++) {
                if (k == riga && m == col) {
                    continue;
                }
                
                esiste = true;
                try {
                    contenutoCella = mappa[k][m];
                } catch (Exception e) {
                    esiste = false;
                }
                if (esiste) {
                    if (contenutoCella == 'V') {
                        viciniVivi++;
                    } 
                }
            }
        }
        return viciniVivi;
    }

    public void round(){
        char[][] nuovaMappa = new char[larghezza][larghezza];
        
        for (int riga = 0; riga < mappa.length; riga++) {
            for (int col = 0; col < mappa[riga].length; col++) {
                int viciniVivi = contaViciniVivi(riga, col);
                
                if (mappa[riga][col] == 'V') {
                    if (viciniVivi == 2 || viciniVivi == 3) {
                        nuovaMappa[riga][col] = 'V';
                    } else {
                        nuovaMappa[riga][col] = '.';
                    }
                } else {
                    if (viciniVivi == 3) {
                        nuovaMappa[riga][col] = 'V';
                    } else {
                        nuovaMappa[riga][col] = '.';
                    }
                }
            }
        }
        
        mappa = nuovaMappa;
    }
    
    public static void main(String[] args) {
        int riga, colonna, round, comando;
        
        System.out.println("\n----- Benvenuto al Conway game of life -----\n"); 
        System.out.print("Inserisci la dimensione della mappa per iniziare: ");
        int larghezza = kbd.nextInt();
        Conway game = new Conway(larghezza);
        System.out.println("\nComandi disponibili:");
        System.out.println("1. Aggiungi casella viva");
        System.out.println("2. Rimuovi casella viva");
        System.out.println("3. Inizia la partita");
        System.out.print("\nComando: ");
        comando = kbd.nextInt();

        while (comando != 3) {
            if (comando < 1 || comando > 3) {
                System.out.println("Errore comando invalido");
            }
            else if (comando == 1){
                System.out.print("Inserisci la riga della cella viva (0-" + (larghezza-1) + "): ");
                riga = kbd.nextInt();
                System.out.print("Inserisci la colonna della cella viva (0-" + (larghezza-1) + "): ");
                colonna = kbd.nextInt();
                game.mettiVita(riga, colonna);
            }
            else {
                System.out.print("Inserisci la riga della cella viva da rimuovere (0-" + (larghezza-1) + "): ");
                riga = kbd.nextInt();
                System.out.print("Inserisci la colonna della cella viva da rimuovere (0-" + (larghezza-1) + "): ");
                colonna = kbd.nextInt();
                if (game.mappa[riga][colonna] != 'V') {
                    System.out.println("Errore: la cella selezionata non e' viva");
                    continue;
                }
                game.togliVita(riga, colonna);
            }
            System.out.print("Inserire nuovo comando: ");
            comando = kbd.nextInt();
        }
        System.out.print("Inserisci numero di round da eseguire: ");
        round = kbd.nextInt(); 
        for (int i = 0; i <= round; i++) {
            System.out.println("\n------- Mappa al round " + i + " -------\n");
            game.round();
            game.stampaMappa();
        }
        kbd.close();
    }
}