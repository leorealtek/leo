package Scuola.EserciziAScuola.Sort;

import java.util.Arrays;

public class ProvaVerifica {
    public static int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;

            if (array[mid] == target) return mid;
            else if (array[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

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

    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) return array;

        int[] arr1 = mergeSort(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] arr2 = mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length));

        return merge(arr1, arr2);
    }

    private static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int indiceArr1 = 0, indiceArr2 = 0, indiceResult = 0;

        while (indiceArr1 < arr1.length && indiceArr2 < arr2.length) {
            if(arr1[indiceArr1] > arr2[indiceArr2]) result[indiceResult++] = arr2[indiceArr2++];
            else result[indiceResult++] = arr1[indiceArr1++];
        }

        while (indiceArr1 < arr1.length) result[indiceResult++] = arr1[indiceArr1++];
        while (indiceArr2 < arr2.length) result[indiceResult++] = arr2[indiceArr2++];
        
        return result;
    }

    public static void main(String[] args) {
        int[] array = {2,5,676,2,1,24,6,8};
        array = mergeSort(array);
        System.out.println(Arrays.toString(array));
    }


}
