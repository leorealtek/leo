package Scuola.TPSIT;

import java.util.Scanner;

public class Esercizio_457EEEI {

    private static String convertiDecimaleInBinario(int numero){
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

    public static int convertiBinarioInDecimale(String binario) {
        int decimale = 0;
        int esponente = 0;
        for (int i = binario.length() - 1; i >= 0; i--) {
            decimale += Integer.parseInt(String.valueOf(binario.charAt(i))) * Math.pow(2, esponente++);
        }
        return decimale;
    }

    public static String convertiEsadecimaleInBinario(String esadecimale) {
        esadecimale = esadecimale.substring(2);
        String binario = "";
        String valori = "0123456789ABCDEF";

        for (int i = 0; i < esadecimale.length(); i++) {
            int indice = valori.indexOf(esadecimale.charAt(i));
            String numero = convertiDecimaleInBinario(indice);
            while (numero.length() < 4) {
                numero = "0" + numero;
            }
            binario += numero;
        }
        return binario;
    }

    public static String[] dividiNumero(String binario) {
        String[] diviso = new String[3];
        String segno =  String.valueOf(binario.charAt(0));
        String esponente = binario.substring(1,9);
        String mantissa = binario.substring(9);
        diviso[0] = segno;
        diviso[1] = esponente;
        diviso[2] = mantissa;
        return diviso;
    }

    public static String spostaVirgola(String mantissa, int esponente) {
        String risultato = "";
        if (esponente < 0) {
            risultato = "0.";
            for (int i = 1; i < -esponente; i++) {
                risultato += '0';
            }
            risultato += '1';
            risultato += mantissa;
        }
        else if (esponente > 0) {
            risultato = "1";
            for (int i = 0; i < esponente; i++) {
                risultato += mantissa.charAt(i);
            }
            risultato += '.';
            risultato += mantissa.substring(esponente);
        }
        return risultato;
    }

    public static String ottieniRisultato(String numero, String segno) {
        int indicePunto = 0;
        float parteDecimale = 0;
        int esponente = -1;

        for (int i = 0; i < numero.length(); i++) {
            if (numero.charAt(i) == '.') {
                indicePunto = i;
                break;
            }
        }

        int parteIntera = convertiBinarioInDecimale(numero.substring(0, indicePunto));
        String parteDecimaleBinaria = numero.substring(indicePunto + 1);
        for (int i = 0; i < parteDecimaleBinaria.length(); i++) {
            parteDecimale += Integer.parseInt(String.valueOf(parteDecimaleBinaria.charAt(i))) * Math.pow(2, esponente--);
        }
        float valore = parteIntera + parteDecimale;

        return (segno.charAt(0) == '0') ? String.valueOf(valore) : "-" + String.valueOf(valore);
    }

    public static String elaboraEsadecimale(String esadecimale) {
        if (esadecimale.equalsIgnoreCase("0x00000000")) return "0";
        String binario = convertiEsadecimaleInBinario(esadecimale);
        String[] diviso = dividiNumero(binario);
        int esponente = convertiBinarioInDecimale(diviso[1]) - 127;
        String mantissa = diviso[2];
        if (esponente == 128 && Integer.valueOf(mantissa) == 0) return (diviso[0].equalsIgnoreCase("0")) ? "+Infinito" : "-Infinito"; 
        if (esponente == 128 && Integer.valueOf(mantissa) != 0) return "NaN";
        if (esponente == -127 && Integer.valueOf(mantissa) != 0) return String.valueOf(convertiBinarioInDecimale(mantissa));
        String numero = spostaVirgola(mantissa, esponente);
        return ottieniRisultato(numero, diviso[0]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il numero esadecimale con '0x' prima dell'esadecimale: ");
        String esadecimale = scanner.nextLine().toUpperCase();
        scanner.close();
        
        System.out.println("Risultato: " + elaboraEsadecimale(esadecimale));
    }
}