package BubbleSort;

public class BubbleSort {
    public static long[] bubbleSort(long[] array, boolean crescente) {
        long temp;
        boolean swapped;

        for (int i = 0; i < array.length; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (crescente) {
                    if (array[j] > array[j + 1]) {
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swapped = true;
                    }
                }
                else {
                    if (array[j] < array[j + 1]) {
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swapped = true;
                    }
                }
            }
            if (!swapped) break;
        }
        return array;
    }

    public static double[] bubbleSort(double[] array, boolean crescente) {
        double temp;
        boolean swapped;

        for (int i = 0; i < array.length; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (crescente) {
                    if (array[j] > array[j + 1]) {
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swapped = true;
                    }
                }
                else {
                    if (array[j] < array[j + 1]) {
                        temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swapped = true;
                    }
                }
            }
            if (!swapped) break;
        }
        return array;
    }

    public static float[] bubbleSort(float[] array, boolean crescente) {
        double[] array2 = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            array2[i] = (double) array[i];
        }
        array2 = bubbleSort(array2, crescente);

        for (int i = 0; i < array2.length; i++) {
            array[i] = (float) array2[i];
        }
        return array;
    }

    public static int[] bubbleSort(int[] array, boolean crescente) {
        long[] array2 = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            array2[i] = (long) array[i];
        }
        array2 = bubbleSort(array2, crescente);

        for (int i = 0; i < array2.length; i++) {
            array[i] = (int) array2[i];
        }
        return array;
    } 

}