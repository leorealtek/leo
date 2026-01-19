package Scuola.Sort;

public class CocktailSort {
    public static int[] cocktailSort (int[] array) {
        boolean swapped = true;
        int start = 0;
        int end = array.length - 1;
        int temp;
    
        while (swapped) {
            swapped = false;
            for (int i = start; i < end; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
            end--;
            swapped = false;

            for (int i = end; i > start; i--) {
                if (array[i] < array[i - 1]) {
                    temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    swapped = true;
                }
            }
            start++;
        }
        return array;
    }
}