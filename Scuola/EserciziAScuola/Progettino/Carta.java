package Scuola.EserciziAScuola.Progettino;

import java.util.Random;

public class Carta {
    protected String nome;
    protected int puntiVita;
    protected int puntiAttacco;
    protected int puntiDifesa;
    protected TipoTarget target;

    private static String[] prefissi = {
        "Drago", "Lupo", "Tigre", "Serpente", "Aquila","Golem", "Spirito", "Ombra",
        "Fuoco", "Ghiaccio","Tuono", "Terra", "Vento", "Luce", "Oscuro"
        };

    private static String[] suffissi = {
        "Antico", "Furioso", "Mistico", "Selvaggio", "Celeste","Infernale","Glaciale",
        "Tonante","Sacro", "Maledetto","Supremo", "Eterno", "Fatale", "Divino", "Spettrale"
        };

    public Carta() {
        int[] statCarta = creaStatisticheCarta();
        nome = creaNomeCarta();
        puntiVita = statCarta[0];
        puntiAttacco = statCarta[1];
        puntiDifesa = statCarta[2];
        target = TipoTarget.generaTarget();
    }

    private static String creaNomeCarta() {
        Random generatoreNum = new Random();
        String nome = "";
        String prefissoNome = prefissi[generatoreNum.nextInt(0, prefissi.length - 1)];
        nome = prefissoNome + " ";
        String suffissoNome = suffissi[generatoreNum.nextInt(0, prefissi.length - 1)];
        nome += suffissoNome;
        return nome;
    }

    private static int[] creaStatisticheCarta() {
        Random generatoreNum = new Random();
        int maxPunti = 100;
        int[] statCarta = new int[3];
        int puntiVita = 0;
        int puntiAttacco = 0;
        int puntiDifesa = 0;

        if (maxPunti > 50) {
            puntiVita = generatoreNum.nextInt(0,50);
        }
        else {
            puntiVita = generatoreNum.nextInt(0,maxPunti);
        }

        maxPunti -= puntiVita;

        if (maxPunti > 50) {
            puntiAttacco = generatoreNum.nextInt(0,50);
        }
        else {
            puntiAttacco = generatoreNum.nextInt(0,maxPunti);
        }

        maxPunti -= puntiVita;

        if (maxPunti > 50) {
            puntiDifesa = generatoreNum.nextInt(0,50);
        }
        else {
            puntiDifesa = generatoreNum.nextInt(0,maxPunti);
        }

        statCarta[0] = puntiVita;
        statCarta[1] = puntiAttacco;
        statCarta[2] = puntiDifesa;
        return statCarta;
    }   

    @Override
    public String toString() {
        return "Carta [nome=" + nome + ", puntiVita=" + puntiVita + ", puntiAttacco=" + puntiAttacco + ", puntiDifesa="
                + puntiDifesa + ", target=" + target + "]";
    }

    public int getPuntiTotali() {
        return puntiVita + puntiAttacco + puntiDifesa;
    }

    public static void main(String[] args) {
        Carta c = new Carta();

        System.out.println(c);
    }
}
