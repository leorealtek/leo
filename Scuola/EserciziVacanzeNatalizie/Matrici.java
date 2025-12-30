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
        return matrice;
    }

    public static int[][] scambiaColonne(int[][] matrice, int idx1, int idx2) {
        
        return matrice;
    }

    public static void sommaRighe(int[][] matrice) {
        int sommaRiga = 0;
        
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[0].length; j++) {
                sommaRiga += matrice[i][j]; 
            }
            System.out.println(sommaRiga);
            sommaRiga = 0;
        }
    }

    public static void sommaColonne(int[][] matrice) {
        int sommaColonna = 0;
        
        for (int i = 0; i < matrice[0].length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                sommaColonna += matrice[j][i]; 
            }
            System.out.println(sommaColonna);
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
        int n = s.nextInt();
        int m = s.nextInt();
        int[][] matrice = creaMatrice(n, m);
        stampaMatrice(matrice);
        sommaRighe(matrice);
        sommaColonne(matrice);

    }
}