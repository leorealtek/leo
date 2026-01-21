public class Esercizio_457EEEI {

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

    public static int convertiDaBinarioADecimale(String numero) {
        int decimale = 0;
        int esponente = 0;
        for (int i = numero.length() - 1; i >= 0; i--) {
            decimale += Integer.parseInt(String.valueOf(numero.charAt(i))) * Math.pow(2, esponente++);
        }
        return decimale;
    }

    public static String conversioneDaEsadecimaleABinario(String esadecimale) {
        esadecimale = esadecimale.substring(2);
        String binario = "";
        String valori = "0123456789ABCDEF";

        for (int i = 0; i < esadecimale.length(); i++) {
            int indice = valori.indexOf(esadecimale.charAt(i));
            String numero = conversioneParteIntera(indice);
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
        String finito = "";
        if (esponente < 0) {
            finito = "0.";
            for (int i = 1; i < esponente; i++) {
                finito += '0';
            }
            finito += '1';
            finito += mantissa;
        }
        else if (esponente > 0) {
            finito = "1";
            for (int i = 0; i < esponente; i++) {
                finito += mantissa.charAt(i);
            }
            finito += '.';
            finito += mantissa.substring(esponente);
        }
        else {
            return "mamt";
        }
        return finito;
    }

    public static void main(String[] args) {
        String esadecimale = "0x3F000000";
        String binario = conversioneDaEsadecimaleABinario(esadecimale);
        String[] divisi = dividiNumero(binario);
        int esponente = convertiDaBinarioADecimale(divisi[1]) - 127;
        System.out.println(binario);
        System.out.println(spostaVirgola(divisi[2], esponente));

    }
}