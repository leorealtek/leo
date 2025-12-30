package Scuola.EserciziVacanzeNatalizie;

public class CorsaMain {

    public static Corsa trovaCorsaMiglore(Corsa[] corse) {
        Corsa migliore = new Corsa(null, 0, 0);
        for (Corsa corsa : corse) {
            if (migliore.calcolaPunteggio() < corsa.calcolaPunteggio()) migliore = corsa;
        }
        return migliore;
    }

    public static float calcolaChilometriTotali(Corsa[] corse) {
        float distanza = 0;
        for (Corsa corsa : corse) {
            distanza += corsa.getDistanza();
        }
        return distanza;
    }

    public static float calcolaPunteggioMedio(Corsa[] corse) {
        float punteggio = 0;
        for (Corsa corsa : corse) {
            punteggio += corsa.calcolaPunteggio();
        }
        return punteggio / corse.length;
    }

    public static Corsa[] filtraUtente(Corsa[] corse, String utente) {
        int count = 0;

        for (Corsa corsa : corse) {
            if (corsa.getNomeAtleta().equals(utente)) count++;
        }

        Corsa[] corseUtente = new Corsa[count];
        count = 0;

        for (Corsa corsa : corse) {
            if (corsa.getNomeAtleta().equals(utente)) corseUtente[count++] = corsa;
        }
        return corseUtente;
    }

    public static String[] stampaUtenti(Corsa[] corse) {
        int count = 0;

        for (int i = 0; i < corse.length; i++) {
            boolean duplicato = false;

            for (int j = 0; j < i; j++) {
                if (corse[i].getNomeAtleta().equals(corse[j].getNomeAtleta())) {
                    duplicato = true;
                    break;
                }
            }
            if (!duplicato) count++;
        }
    
        String[] utentiUnici = new String[count];
        int index = 0;

        for (int i = 0; i < corse.length; i++) {
            boolean duplicato = false;

            for (int j = 0; j < i; j++) {
                if (corse[i].getNomeAtleta().equals(corse[j].getNomeAtleta())) {
                    duplicato = true;
                    break;
                }
            }
            if (!duplicato) utentiUnici[index++] = corse[i].getNomeAtleta();
        }
        return utentiUnici;
    }

    public static String[] statisticheUtenti(Corsa[] corse) {
        String[] utenti = stampaUtenti(corse);
        float chilometriUtente = 0;
        float punteggioMedioUtente = 0;
        String[] statisticheUtenti = new String[utenti.length];
        int count = 0;

        for (String utente : utenti) {
            Corsa[] corseUtente = filtraUtente(corse, utente);
            for (Corsa corsaUtente : corseUtente) {
                chilometriUtente += corsaUtente.getDistanza();
            }
            punteggioMedioUtente = calcolaPunteggioMedio(corseUtente);
            String statisticheUtente = "Utente: " + utente + " Chilometri totali: " + chilometriUtente + " Punteggio medio: " + punteggioMedioUtente;
            statisticheUtenti[count++] = statisticheUtente;
        }
        return statisticheUtenti;
    }

    public static String analizzaProgressi(Corsa[] corse, String nomeAtleta) {
        Corsa[] corseAtleta = filtraUtente(corse, nomeAtleta);
        int IDmaggiore = 0;

        for (Corsa corsa : corseAtleta) {
            if (IDmaggiore < corsa.getID()) IDmaggiore = corsa.getID();
        }
        return "";
    }

    public static void main(String[] args) {
        CorsaInMontagna cInMontagna = new CorsaInMontagna("Gino", 5, 20, 200);
        CorsaInMontagna cInMontagna2 = new CorsaInMontagna("Paperino", 10, 60, 800);
        CorsaSottoPioggia cSottoPioggia = new CorsaSottoPioggia("Paperino", 7, 25, 6);
        CorsaSottoPioggia cSottoPioggia2 = new CorsaSottoPioggia("Massimo", 1, 5, 4);
        Corsa corsa = new Corsa("Gino", 10, 50);
        Corsa corsa2 = new Corsa("Massimo", 3.5f, 18);

        Corsa[] corse = {cInMontagna, cInMontagna2, cSottoPioggia, cSottoPioggia2, corsa, corsa2};

        for (Corsa run : corse) {
            System.out.println(run);
        }

        System.out.println(trovaCorsaMiglore(corse));

        System.out.println(calcolaChilometriTotali(corse));

        System.out.println(calcolaPunteggioMedio(corse));

        Corsa[] corseFiltrate = filtraUtente(corse, "Massimo");
        for (Corsa run : corseFiltrate) {
            System.out.println(run);          
        }

        String[] utentiUnici = stampaUtenti(corse);
        for (String utente : utentiUnici) {
            System.out.println(utente);
        }

        String[] statisticheUtenti = statisticheUtenti(corse);
        for (String statisticheUtente : statisticheUtenti) {
            System.out.println(statisticheUtente);
        }

    }
}
