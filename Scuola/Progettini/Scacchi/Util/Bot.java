package Scuola.Progettini.Scacchi.Util;

import java.util.ArrayList;
import java.util.List;

import Scuola.Progettini.Scacchi.Partite.PartitaAstratta;
import Scuola.Progettini.Scacchi.Pezzi.Alfiere;
import Scuola.Progettini.Scacchi.Pezzi.Cavallo;
import Scuola.Progettini.Scacchi.Pezzi.Pedone;
import Scuola.Progettini.Scacchi.Pezzi.Re;
import Scuola.Progettini.Scacchi.Pezzi.Regina;
import Scuola.Progettini.Scacchi.Pezzi.Torre;

public class Bot {
    private int profondita;
    private Casella[][] mappa;
    private PartitaAstratta partita;

    public Bot(int profondita) {
        this.profondita = profondita;
    }

    private double valutaPosizione(Casella[][] scacchiera) {
        double pezziBianchi = 0;
        double pezziNeri = 0;

        for (int i = 0; i < scacchiera.length; i++) {
            for (int j = 0; j < scacchiera[i].length; j++) {
                Pezzo p = scacchiera[i][j].getPezzoContenuto();
                if (p == null) continue;
                if (p.isBianco()) pezziBianchi += p.getValore();
                else pezziNeri += p.getValore();
            }
        }

        return pezziBianchi - pezziNeri;
    }

    private double valutaPosizioneFinale(Casella[][] scacchiera, boolean toccaBianco) {
        Casella[][] mappaPrecedente = this.mappa;
        this.mappa = scacchiera;

        try {
            boolean sottoScacco = partita.isSottoScacco(toccaBianco);
            boolean haMosse = !creaTutteMosseDisponibili(scacchiera, toccaBianco).isEmpty();

            if (sottoScacco && !haMosse) {
                return toccaBianco ? -100000 : 100000;
            }

            if (!sottoScacco && !haMosse) {
                return 0;
            }

            return valutaPosizione(scacchiera);
        } finally {
            this.mappa = mappaPrecedente;
        }
    }

    private double minimax(Casella[][] scacchiera, int profondita, boolean toccaBianco) {
        List<Casella[][]> mosse = creaTutteMosseDisponibili(scacchiera, toccaBianco);

        if (profondita == 0 || mosse.isEmpty()) {
            return valutaPosizioneFinale(scacchiera, toccaBianco);
        }

        if (toccaBianco) {
            double maxPoint = Double.NEGATIVE_INFINITY;

            for (Casella[][] scacchieraAttuale : mosse) {
                double val = minimax(scacchieraAttuale, profondita - 1, false);
                maxPoint = Math.max(maxPoint, val);
            }

            return maxPoint;
        }

        double minPoint = Double.POSITIVE_INFINITY;

        for (Casella[][] scacchieraAttuale : mosse) {
            double val = minimax(scacchieraAttuale, profondita - 1, true);
            minPoint = Math.min(minPoint, val);
        }

        return minPoint;
    }

    public Casella[][] trovaPosizioneMigliore(boolean toccaBianco) {
        List<Casella[][]> mosse = creaTutteMosseDisponibili(mappa, toccaBianco);

        if (mosse.isEmpty()) {
            return mappa;
        }

        Casella[][] migliore = null;

        if (toccaBianco) {
            double valoreMigliore = Double.NEGATIVE_INFINITY;

            for (Casella[][] scacchiera : mosse) {
                double valore = minimax(scacchiera, profondita - 1, false);

                if (valore > valoreMigliore) {
                    valoreMigliore = valore;
                    migliore = scacchiera;
                }
            }
        } else {
            double valoreMigliore = Double.POSITIVE_INFINITY;

            for (Casella[][] scacchiera : mosse) {
                double valore = minimax(scacchiera, profondita - 1, true);

                if (valore < valoreMigliore) {
                    valoreMigliore = valore;
                    migliore = scacchiera;
                }
            }
        }

        return migliore;
    }

    private Pezzo copiaPezzo(Pezzo p, Casella[][] nuovaMappa) {
        char nome = p.getNome();
        int riga = p.getRiga();
        int colonna = p.getColonna();

        if (p instanceof Pedone vecchioPedone) {
            Pedone nuovoPedone = new Pedone(nome, riga, colonna, nuovaMappa);
            nuovoPedone.setPrimaMossa(vecchioPedone.isPrimaMossa());
            nuovoPedone.setSecondaMossa(vecchioPedone.isSecondaMossa());
            return nuovoPedone;
        }

        if (p instanceof Torre vecchiaTorre) {
            Torre nuovaTorre = new Torre(nome, riga, colonna, nuovaMappa);
            nuovaTorre.setHaMosso(vecchiaTorre.haMosso());
            return nuovaTorre;
        }

        if (p instanceof Re vecchioRe) {
            Re nuovoRe = new Re(nome, riga, colonna, nuovaMappa);
            nuovoRe.setHaMosso(vecchioRe.haMosso());
            return nuovoRe;
        }

        if (p instanceof Cavallo) {
            return new Cavallo(nome, riga, colonna, nuovaMappa);
        }

        if (p instanceof Alfiere) {
            return new Alfiere(nome, riga, colonna, nuovaMappa);
        }

        if (p instanceof Regina) {
            return new Regina(nome, riga, colonna, nuovaMappa);
        }

        throw new IllegalArgumentException("Pezzo non valido: " + nome);
    }

    private Casella[][] copiaMappa(Casella[][] originale) {
        Casella[][] copia = new Casella[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pezzo p = originale[i][j].getPezzoContenuto();

                if (p == null) {
                    copia[i][j] = new Casella();
                } else {
                    copia[i][j] = new Casella(copiaPezzo(p, copia));
                }
            }
        }

        return copia;
    }

    private void promuoviPedoneAutomaticamente(Casella[][] scacchiera, int riga, int colonna) {
        Pezzo pezzo = scacchiera[riga][colonna].getPezzoContenuto();

        if (!(pezzo instanceof Pedone)) {
            return;
        }

        if (riga != 0 && riga != 7) {
            return;
        }

        char nomeRegina = pezzo.isBianco() ? 'D' : 'd';
        scacchiera[riga][colonna].inserisciPezzo(new Regina(nomeRegina, riga, colonna, scacchiera));
    }

    private List<Casella[][]> creaTutteMosseDisponibili(Casella[][] scacchiera, boolean toccaBianco) {
        List<Casella[][]> mosseDisponibili = new ArrayList<>();

        Casella[][] mappaPrecedente = this.mappa;
        this.mappa = scacchiera;

        try {
            for (int rigaPartenza = 0; rigaPartenza < 8; rigaPartenza++) {
                for (int colonnaPartenza = 0; colonnaPartenza < 8; colonnaPartenza++) {

                    Pezzo p = scacchiera[rigaPartenza][colonnaPartenza].getPezzoContenuto();

                    if (p == null || p.isBianco() != toccaBianco) {
                        continue;
                    }

                    Casella[][] mosse = p.mossePossibili();

                    for (int rigaArrivo = 0; rigaArrivo < 8; rigaArrivo++) {
                        for (int colonnaArrivo = 0; colonnaArrivo < 8; colonnaArrivo++) {

                            boolean enPassant = partita.isEnPassantValido(p, rigaPartenza, colonnaPartenza, rigaArrivo, colonnaArrivo);

                            if (mosse[rigaArrivo][colonnaArrivo] == null && !enPassant) {
                                continue;
                            }

                            Pezzo pezzoDestinazione = scacchiera[rigaArrivo][colonnaArrivo].getPezzoContenuto();

                            if (pezzoDestinazione instanceof Re) {
                                continue;
                            }

                            if (partita.lasciaReSottoScacco(
                                    p,
                                    rigaPartenza,
                                    colonnaPartenza,
                                    rigaArrivo,
                                    colonnaArrivo
                            )) {
                                continue;
                            }

                            Casella[][] nuovaScacchiera = copiaMappa(scacchiera);
                            Pezzo pezzoCopiato = nuovaScacchiera[rigaPartenza][colonnaPartenza].getPezzoContenuto();

                            if (enPassant) {
                                nuovaScacchiera[rigaPartenza][colonnaArrivo].rimuoviPezzo();
                            }

                            pezzoCopiato.muovi(rigaArrivo, colonnaArrivo);
                            promuoviPedoneAutomaticamente(nuovaScacchiera, rigaArrivo, colonnaArrivo);

                            mosseDisponibili.add(nuovaScacchiera);
                        }
                    }
                }
            }
        } finally {
            this.mappa = mappaPrecedente;
        }

        return mosseDisponibili;
    }

    public void setProfonditaBot(int profondita) {
        if (profondita < 1) {
            throw new IllegalArgumentException("La profondità del bot deve essere almeno 1.");
        }

        this.profondita = profondita;
    }
}