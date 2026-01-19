package Scuola.EserciziAScuola.Ricerca;

public class Ricerca {

    public static int ricercaLineare(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (i == target) return i;
        }
        return -1;
    }

    public static int ricercaBinaria(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (array[mid] == target) return mid;
            else if (array[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}