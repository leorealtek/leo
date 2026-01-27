package Scuola.Progettino.Utility;

import Scuola.Progettino.Carte.Carta;

public class Utility {
    
    public static void ordinaManoPerHP(Carta[] mano) {
        int n = mano.length;
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (mano[j] == null) continue;
                
                if (mano[j + 1] == null) {
                    Carta temp = mano[j];
                    mano[j] = mano[j + 1];
                    mano[j + 1] = temp;
                } else if (mano[j].getPuntiVita() > mano[j + 1].getPuntiVita()) {
                    Carta temp = mano[j];
                    mano[j] = mano[j + 1];
                    mano[j + 1] = temp;
                }
            }
        }
    }
    
    public static void ordinaCampoPerPuntiTotali(Carta[] campo) {
        int n = campo.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            
            for (int j = i + 1; j < n; j++) {
                if (campo[minIdx] == null) {
                    minIdx = j;
                } else if (campo[j] != null) {
                    if (campo[j].getPuntiTotali() < campo[minIdx].getPuntiTotali()) {
                        minIdx = j;
                    }
                }
            }
            
            if (minIdx != i) {
                Carta temp = campo[i];
                campo[i] = campo[minIdx];
                campo[minIdx] = temp;
            }
        }
    }
    
    public static void ordinaPerInsertionSort(Carta[] array, String tipo) {
        int n = array.length;
        
        for (int i = 1; i < n; i++) {
            Carta key = array[i];
            if (key == null) continue;
            
            int j = i - 1;
            
            while (j >= 0 && array[j] != null) {
                boolean shouldSwap = false;
                
                if (tipo.equals("HP")) {
                    shouldSwap = array[j].getPuntiVita() > key.getPuntiVita();
                } else if (tipo.equals("TOTALI")) {
                    shouldSwap = array[j].getPuntiTotali() > key.getPuntiTotali();
                }
                
                if (shouldSwap) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }
}