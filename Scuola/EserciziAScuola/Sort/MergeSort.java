package Scuola.EserciziAScuola.Sort;

public class MergeSort {
    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int[] arr1 = mergeSort(copiaArray(array, 0, array.length / 2 - 1));
        int[] arr2 = mergeSort(copiaArray(array, array.length / 2, array.length - 1));

        return merge(arr1, arr2);
    }

    private static int[] copiaArray(int[] array, int inizio, int fine) {
        int[] copia = new int[fine - inizio + 1];
        for (int i = 0; i < copia.length; i++) {
            copia[i] = array[inizio + i];
        }
        return copia;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }
}
