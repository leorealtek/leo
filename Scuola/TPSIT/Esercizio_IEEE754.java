import java.util.Scanner;

public class Esercizio_IEEE754 {

    public static final int LUNGHEZZA_MANTISSA = 23;
    public static final int LUNGHEZZA_ESPONENTE = 8;

    public static int parteIntera(float numero) {
        String parteIntera = "";
        int indice = 0;

        if (numero < 0) numero = numero * -1;
        
        String numeroString = String.valueOf(numero);
        

        for (int i = 0; i < numeroString.length(); i++) {
            if (numeroString.charAt(i) == '.') {
                indice = numeroString.length() - i;
                break;
            }
        }

        for (int i = 0; i < numeroString.length() - indice; i++) {
            parteIntera += numeroString.charAt(i);
        }
        return Integer.parseInt(parteIntera);
    }

    public static float parteDecimale(float numero) {
        String numeroString = String.valueOf(numero);
        String parteDecimale = "";
        int indice = 0;

        for (int i = 0; i < numeroString.length(); i++) {
            if (numeroString.charAt(i) == '.') {
                indice = i + 1;
                break;
            }
        }

        for (int i = indice; i < numeroString.length(); i++) {
            parteDecimale += numeroString.charAt(i);
        }

        float valoreFinale = Float.parseFloat(parteDecimale);
        valoreFinale = valoreFinale * (float) Math.pow(10, -parteDecimale.length());

        return valoreFinale;
    }

    public static String conversioneParteIntera(int numero){
        int resto;
        String binario = "";

        if (numero < 0) numero = numero * -1;

        while (numero > 0){
            resto = numero % 2;
            numero = (numero - resto) / 2;
            binario = resto + binario;
        }

        return binario;
    }

    public static String conversioneParteDecimale(float numero) {
        String finale = "";

        for (int i = 0; i < LUNGHEZZA_MANTISSA; i++) {
            numero *= 2;
            if (numero >= 1) {
                numero--;
                finale += "1";
            }
            else finale += "0";
        }
        return finale;
    }

    public static Object[] calcolaMantissaEdEsponente(String parteIntera, String parteDecimale) {
        Object[] oggetti = new Object[2];
        String finale = "";
        int esp = 127;

        if (parteIntera.equals("")) {
            for (int i = 0; i < parteDecimale.length(); i++) {
                if (parteDecimale.charAt(i) == '1') {
                    esp -= (i + 1); 
                    finale = parteDecimale.substring(i + 1, parteDecimale.length());
                    while (finale.length() < LUNGHEZZA_MANTISSA) {
                        finale += '0';
                    }
                    break;
                }   
            }
        }
        else {
            finale = parteIntera.substring(1) + parteDecimale.substring(0, parteDecimale.length() + 1 - parteIntera.length());
            esp += parteIntera.length() - 1;
        }

        String espBinario = conversioneParteIntera(esp);
        while (espBinario.length() < LUNGHEZZA_ESPONENTE) {
            espBinario = "0" + espBinario;
        }
        oggetti[0] = espBinario;
        oggetti[1] = finale;
        return oggetti;
    }

    public static String binarioFinale(String esponente, String mantissa, float num) {
        return (num < 0) ? "1" + esponente + mantissa : "0" + esponente + mantissa;
    }

    public static String convEsadecimale(String numeroFinale) {
        String[] valori = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String risultato = "";
    
        for (int i = 0; i < numeroFinale.length() / 4; i++) {
            String gruppo = numeroFinale.substring(i * 4, i * 4 + 4);
            int valore = 0;
        
            for (int j = 0; j < gruppo.length(); j++) {
                if (gruppo.charAt(j) == '1') {
                    valore += Math.pow(2, gruppo.length() - 1 - j);
                }
            }
            risultato += valori[valore];
        }
        return "0x" + risultato;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Dimmi il numero floating point da convertire in standard IEEE-754");
        float numero = s.nextFloat();
        s.close();
        int parteIntera = parteIntera(numero);
        float parteDecimale = parteDecimale(numero);
        String convParteInt = conversioneParteIntera(parteIntera);
        String convParteDec = conversioneParteDecimale(parteDecimale);
        Object[] notazione = calcolaMantissaEdEsponente(convParteInt, convParteDec);
        String esp = (String) notazione[0];
        String mantissa = (String) notazione[1];
        String numeroFinale = binarioFinale(esp, mantissa, numero);
        System.out.println(convEsadecimale(numeroFinale));

    }
}