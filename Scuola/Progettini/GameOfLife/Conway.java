package Scuola.Progettini.GameOfLife;

import java.util.Scanner;

public class Conway {
    private static char[][] mappa;
    private static final Scanner kbd = new Scanner(System.in);
    private static int comando;

    public static char[][] creaMappa(){
        char[][] mappa = new char[30][30];
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                mappa[i][j] = '.';
            }
        }
        return mappa;
    }

    public static void stampaMappa(char[][] mappa) {
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                System.out.print(mappa[i][j] + " ");
            }
            System.err.println("");
        }
    }

    public static boolean stampaCelleVive(char[][] mappa){
        int indice = 1;
        System.out.println("Celle vive:");
        for (int col = 0; col < mappa.length; col++) {
            for (int riga = 0; riga < mappa[col].length; riga++) {
                if (mappa[col][riga] == 'V') {
                    System.out.println(indice + ". Riga: " + riga + " colonna: " + col);
                    indice++;
                }
            }
        }
        if (indice == 1) {
            System.out.println("non ci sono celle vive");
            return false;
        }
        return true;
    }
    
    public static void mettiVita(char[][] mappa, int col , int riga){
        try {
            mappa[col][riga] = 'V';
        } catch (Exception e) {
            System.out.println("Coordinate invalide");
        }
    }

    public static void togliVita(char[][] mappa, int col , int riga){
        try {
            mappa[col][riga] = '.';
        } catch (Exception e) {
            System.out.println("Coordinate invalide");
        }
    }

    public static int contaViciniVivi(char[][] mappa , int col , int riga){
        int viciniVIvi = 0;
        boolean esiste;
        char contenutoCella = ' ';
        for (int k = col-1; k <= col+1; k++) {
            for (int m = riga-1; m <= riga + 1; m++) {
                esiste = true;
                try {
                    contenutoCella = mappa[k][m];
                } catch (Exception e) {
                    esiste = false;
                }
                if (esiste) {
                    if (contenutoCella == 'V') {
                        viciniVIvi++;
                    } 
                }
            }
        }
        return viciniVIvi;
    }

    public static void muore(char[][] mappa , int col , int riga){
        int viciniVIvi = contaViciniVivi(mappa, col, riga);
        if (viciniVIvi < 2) {
            mappa[col][riga] = '.';
        }
        else if (viciniVIvi > 3) {
            mappa[col][riga] = '.';
        }
    }

    public static void nasce(char[][] mappa , int col , int riga){
        int viciniVIvi = contaViciniVivi(mappa, col, riga);
        if (viciniVIvi == 3){
            mappa[col][riga] = 'V';
        }
    }

    public static void round(char[][] mappa){
        for (int col = 0; col < mappa.length; col++) {
            for (int riga = 0; riga < mappa[col].length; riga++) {
                if (mappa[col][riga] == 'V') {
                    muore(mappa, col, riga);
                }
                else{
                    nasce(mappa, col, riga);
                }
            }
        }
    }
    public static void main(String[] args) {
        mappa = creaMappa();
        int riga;
        int colonna;
        int round;
        
        System.out.println("\n----- Benvenuto al Conway game of life -----\n"); 
        System.out.println("1. Aggiungi casella viva");
        System.out.println("2. Rimuovi casella viva");
        System.out.println("3. inizia la partita");
        System.out.print("\nComando:");
        comando = kbd.nextInt();

        while (comando != 3) {
            if (comando < 1 || comando > 3) {
                System.out.println("errore comando invalido");
            }
            else if (comando == 1){
                System.out.print("inserisci la colonna della cella viva (0-29):");
                colonna = kbd.nextInt();
                String boh = kbd.nextLine();
                System.out.println(boh);
                System.out.print("inserisci la riga della cella viva (0-29):");
                riga = kbd.nextInt();
                mettiVita(mappa, colonna, riga);
            }
            else {
                if (stampaCelleVive(mappa)){
                    System.out.print("inserisci la colonna della cella viva da rimuovere (0-29):");
                    colonna = kbd.nextInt();
                    String boh = kbd.nextLine();
                    System.out.println(boh);
                    System.out.print("inserisci la riga della cella viva da rimuovere (0-29):");
                    riga = kbd.nextInt();
                    togliVita(mappa, colonna, riga);
                }
            }
            System.out.print("inserire nuovo comando:");
            comando= kbd.nextInt();
        }
        System.out.print("Inserisci numero di round da eseguire:");
        round = kbd.nextInt();
        System.out.println("\n------- mappa iniziale -------\n");
        stampaMappa(mappa);
        for (int i = 1; i <= round; i++) {
            System.out.println("\n------- round numero " + i + " -------\n");
            round(mappa);
            stampaMappa(mappa);
        }
    }
}
