package BubbleSort;

import java.util.Arrays;
import java.util.Scanner;

public class Esercizio {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Dimmi il numero di studenti");
        int studenti = s.nextInt();
        float[] altezze = new float[studenti];
        for (int i = 0; i < altezze.length; i++) {
            System.out.println("Dimmi l'altezza del " + (i + 1) + " studente");
            altezze[i] = s.nextFloat();
        }
        altezze = BubbleSort.bubbleSort(altezze, true);
        System.out.println(Arrays.toString(altezze));
        System.out.println("Altezza dello studente più basso: " + altezze[0] + " cm\nAltezza dello studente più alto: " + altezze[altezze.length - 1] + " cm");
    }
}
