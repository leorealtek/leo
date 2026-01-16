package Sort;

import java.util.Random;
import java.util.Scanner;

public class Esercizio {
    private static Scanner s = new Scanner(System.in);

    public static int[] creaArray(int n, int min, int max) {
        int[] array = new int[n];
        Random generator = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = generator.nextInt(min, max);
        }
        return array;
    }

    public static void provaTempoEsecuzione() {
        int length = 10;
        for (int i = 0; i < 5; i++) {
            int[] array = creaArray(length, 0, 100);
            long startTime = System.nanoTime();
            BubbleSort.bubbleSort(array, true);
            long endTime = System.nanoTime();
            System.out.println("Elementi: " + length + " Tempo: " + (endTime - startTime) + " ns");
            length *= 10;
        }
    }

    public static void main(String[] args) {
        provaTempoEsecuzione();
    }
}