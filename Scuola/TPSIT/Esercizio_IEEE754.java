import java.util.Scanner;

public class Esercizio_IEEE754 {

    private static final int LUNGHEZZA_MANTISSA = 23;
    private static final int LUNGHEZZA_ESPONENTE = 8;

    private static int parteIntera(float numero) {
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

    private static float parteDecimale(float numero) {
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

    private static String conversioneParteIntera(int numero){
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

    private static String conversioneParteDecimale(float numero) {
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

    private static String calcolaNumeroIEEE(String parteIntera, String parteDecimale, float num) {
        String mantissa = "";
        int esp = 127;

        if (parteIntera.equals("")) {
            for (int i = 0; i < parteDecimale.length(); i++) {
                if (parteDecimale.charAt(i) == '1') {
                    esp -= (i + 1); 
                    mantissa = parteDecimale.substring(i + 1, parteDecimale.length());
                    while (mantissa.length() < LUNGHEZZA_MANTISSA) {
                        mantissa += '0';
                    }
                    break;
                }   
            }
        }
        else {
            esp += parteIntera.length() - 1;
            
            if (esp > 254) {
                esp = 255;
                mantissa = "0".repeat(LUNGHEZZA_MANTISSA);
            } else {
                int lunghezzaDaPrendere = LUNGHEZZA_MANTISSA - (parteIntera.length() - 1);
                if (lunghezzaDaPrendere < 0) lunghezzaDaPrendere = 0;
                if (lunghezzaDaPrendere > parteDecimale.length()) lunghezzaDaPrendere = parteDecimale.length();
                
                mantissa = parteIntera.substring(1) + parteDecimale.substring(0, lunghezzaDaPrendere);
                
                while (mantissa.length() < LUNGHEZZA_MANTISSA) {
                    mantissa += '0';
                }
                if (mantissa.length() > LUNGHEZZA_MANTISSA) {
                    mantissa = mantissa.substring(0, LUNGHEZZA_MANTISSA);
                }
            }
        }

        String espBinario = conversioneParteIntera(esp);
        while (espBinario.length() < LUNGHEZZA_ESPONENTE) {
            espBinario = "0" + espBinario;
        }

        String finale = (num < 0) ? "1" + espBinario + mantissa : "0" + espBinario + mantissa;
        return finale;
    }

    private static String conversioneEsadecimale(String numeroFinale) {
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

    public static String convertiIEEE754(String numero) {
        if (numero.equalsIgnoreCase("+Infinity")) return "0x7F800000";
        if (numero.equalsIgnoreCase("-Infinity")) return "0xFF800000";
        if (numero.equalsIgnoreCase("NaN")) return "0xFF800001";
        
        float numeroFloat = Float.parseFloat(numero);
        
        if (numeroFloat == 0) {
            return (numero.startsWith("-")) ? "0x80000000" : "0x00000000";
        }
        
        int parteIntera = parteIntera(numeroFloat);
        float parteDecimale = parteDecimale(numeroFloat);
        String conversioneParteIntera = conversioneParteIntera(parteIntera);
        String conversioneParteDecimale = conversioneParteDecimale(parteDecimale);
        String numeroBinario = calcolaNumeroIEEE(conversioneParteIntera, conversioneParteDecimale, numeroFloat);
        String numeroInEsadecimale = conversioneEsadecimale(numeroBinario);
        return numeroInEsadecimale;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Dimmi il numero floating point da convertire in standard IEEE-754");
        String numeroString = s.nextLine();
        s.close();
        String numeroConIlPunto = "";
        for (int i = 0; i < numeroString.length(); i++) {
            if (numeroString.charAt(i) == ',') {
                numeroConIlPunto += '.';
            }
            else {
                numeroConIlPunto += numeroString.charAt(i);
            }
        }
        System.out.println(numeroConIlPunto + " in IEEE-754 è: " + convertiIEEE754(numeroConIlPunto));
    }
}