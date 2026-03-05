package Scuola.EserciziAScuola.Sort;

import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int idxMin = i;
            for (int j = i; j < array.length; j++) {
                if (array[idxMin] > array[j]) idxMin = j;
            }
            int temp = array[idxMin];
            array[idxMin] = array[i];
            array[i] = temp;
        }
    }
}