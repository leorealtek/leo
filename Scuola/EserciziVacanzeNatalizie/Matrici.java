package Scuola.EserciziVacanzeNatalizie;

import java.util.Scanner;

public class Matrici {
    public static Scanner s = new Scanner(System.in);

    public static int[][] creaMatrice(int n, int m) {
        int[][] matrice = new int[n][m];
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = (int) (Math.random() * 100);
            }
        }
        return matrice;
    }

    public static int[][] traspostaMatrice(int[][] matrice) {
        int righe = matrice.length;
        int colonne = matrice[0].length;
        int[][] trasposta = new int[colonne][righe];
        
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                trasposta[j][i] = matrice[i][j];
            }
        }
        return trasposta;
    }

    public static int[][] scambiaRighe(int[][] matrice, int idx1, int idx2) {
        if (idx1 < 0 || idx1 >= matrice.length || idx2 < 0 || idx2 >= matrice.length) {
            System.out.println("Indici non validi per lo scambio delle righe!");
            return matrice;
        }
    
        int[] temp = matrice[idx1];
        matrice[idx1] = matrice[idx2];
        matrice[idx2] = temp;

        return matrice;
    }

    public static int[][] scambiaColonne(int[][] matrice, int idx1, int idx2) {
        if (idx1 < 0 || idx1 >= matrice[0].length || idx2 < 0 || idx2 >= matrice[0].length) {
            System.out.println("Indici non validi per lo scambio delle colonne!");
            return matrice;
        }
    
        for (int i = 0; i < matrice.length; i++) {
            int temp = matrice[i][idx1];
            matrice[i][idx1] = matrice[i][idx2];
            matrice[i][idx2] = temp;
        }

        return matrice;
    }

    public static void sommaRighe(int[][] matrice) {
        int sommaRiga = 0;
        
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[0].length; j++) {
                sommaRiga += matrice[i][j]; 
            }
            System.out.println("Riga: " + (i+1) + " Somma = " + sommaRiga);
            sommaRiga = 0;
        }
    }

    public static void sommaColonne(int[][] matrice) {
        int sommaColonna = 0;
        
        for (int i = 0; i < matrice[0].length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                sommaColonna += matrice[j][i]; 
            }
            System.out.println("Colonna: " + (i+1) + " Somma = " + sommaColonna);
            sommaColonna = 0;
        }
    }

    public static void stampaMatrice(int[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] < 10) System.out.print("0" + matrice[i][j] + " ");
                else System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        boolean continua = true;
        System.out.println("Dimmi le righe che avrà la matrice");
        int righe = s.nextInt();
        System.out.println("Dimmi le colonne che avrà la matrice");
        int colonne = s.nextInt();
        int[][] matrice = creaMatrice(righe, colonne);

        while(continua){
            System.out.println("0) Stampa matrice");
            System.out.println("1) Trasposta matrice");
            System.out.println("2) Scambia due righe della matrice (Dal prossimo comando verrà usatà questa come matrice di riferimento)");
            System.out.println("3) Scambia due colonne della matrice (Dal prossimo comando verrà usatà questa come matrice di riferimento)");
            System.out.println("4) Stampa la somma di tutte le righe della matrice");
            System.out.println("5) Stampa la somma di tutte le colonne della matrice");
            System.out.println("6) Esci");

            switch (s.nextInt()) {
                case 0:
                    stampaMatrice(matrice);
                    break;
                case 1:
                    stampaMatrice(traspostaMatrice(matrice));       
                    break;
                case 2:
                    System.out.print("Dimmi il primo indice della riga da scambiare: ");
                    int idxRig1 = s.nextInt();
                    System.out.print("Dimmi il secondo indice della riga da scambiare: ");
                    int idxRig2 = s.nextInt();
                    matrice = scambiaRighe(matrice, idxRig1, idxRig2);
                    stampaMatrice(matrice);           
                    break;
                case 3:
                    System.out.print("Dimmi il primo indice della colonna da scambiare: ");
                    int idxCol1 = s.nextInt();
                    System.out.print("Dimmi il secondo indice della colonna da scambiare: ");
                    int idxCol2 = s.nextInt();
                    matrice = scambiaColonne(matrice, idxCol1, idxCol2);
                    stampaMatrice(matrice);
                    break;
                case 4:
                    sommaRighe(matrice);            
                    break;
                case 5:
                    sommaColonne(matrice);            
                    break;
                case 6:
                    continua = false;
                    break;
                default:
                    System.out.println("Comando non valido");
                    break;
            }
        }
    }
}