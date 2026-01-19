package Scuola.EserciziAScuola.Ricerca;

public class RicercaUova {
    public static int ricercaLineare(EggGame gioco, int numeroPiani) {
        for (int i = 0; i < numeroPiani; i++) {
            if (gioco.lanciaUovo(i).equals("SPLAT")) return i - 1;
        }
        return -1;
    }

    public static int ricercaBinaria(EggGame gioco, int numeroPiani) {
        int left = 0; 
        int right = numeroPiani;
        int mid;

        while (left < right) {
            mid = (left + right + 1) / 2;
            if (gioco.lanciaUovo(mid).equals("SALVO")) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    public static void confrotaRicherche() {
        int piani = 10;

        for (int i = 0; i < 5; i++) {
            EggGame game = new EggGame(piani);
            ricercaLineare(game, piani);
            int lanciLineari = game.getContatoreNlanci();
            game.resetContatore();
            ricercaBinaria(game, piani);
            int lanciBinari = game.getContatoreNlanci();
            System.out.println("Piani: " + piani + " Lineare: " + lanciLineari + " lanci Binaria: " + lanciBinari + " lanci");
            piani *= 10;
        }
    }
    public static void main(String[] args) {
        confrotaRicherche();
    }

}
