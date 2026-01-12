package BubbleSort;

public class BubbleSort {
    public static int[] bubbleSort(int[] array) {
        int temp;
        boolean swapped;

        for (int i = 0; i < array.length; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Se non ha cambiato nulla, ha finito di ordinare l'array e quindi si può fermare anche prima.
        }
        return array;
    }
}