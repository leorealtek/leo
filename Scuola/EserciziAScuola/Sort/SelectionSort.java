package Sort;

public class SelectionSort {
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int idxMin = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[idxMin]) {
                    idxMin = j;
                }
            }
            int temp = array[i];
            array[i] = array[idxMin];
            array[idxMin] = temp;
        }
    }
}