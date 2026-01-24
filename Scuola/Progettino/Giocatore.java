package Scuola.Progettino;

import java.util.Random;

public class Giocatore {
    protected final String nome;
    protected Carta[] mazzo;
    protected Carta[] mano;
    protected Carta[] campo;
    protected int puntiVita;
    protected int carteDistrutte;
    protected int dannoInflitto;
    private int indiceMazzo = 5;
    private Random random = new Random();

    public Giocatore(String nome) {
        this.nome = nome;
        mazzo = creaMazzo();
        mano = new Carta[10];
        campo = new Carta[5];
        carteDistrutte = 0;
        dannoInflitto = 0;
        puntiVita = 3;
        
        for (int i = 0; i < 5; i++) {
            mano[i] = mazzo[i];
        }
    }

    private static Carta[] creaMazzo() {
        Carta[] mazzo = new Carta[50];
        for (int i = 0; i < mazzo.length; i++) {
            if (Math.random() < 0.05) {
                mazzo[i] = new ZuccanTech();
            } else {
                mazzo[i] = new Carta();
            }
        }
        return mazzo;
    }

    public void mostraMano() {
        System.out.println("Mano di " + nome + ":");
        for (int i = 0; i < mano.length; i++) {
            if (mano[i] != null) {
                String tipoCartaIcon = mano[i].isZuccanTech ? " [TECH]" : "";
                System.out.println("  " + mano[i] + tipoCartaIcon);
            }
        }
    }

    public void mostraCampo() {
        System.out.println("Campo di " + nome + ":");
        if (contaCarte(campo) == 0) {
            System.out.println("  (vuoto)");
        } else {
            for (int i = 0; i < campo.length; i++) {
                if (campo[i] != null) {
                    String tipoCartaIcon = campo[i].isZuccanTech ? " [TECH]" : "";
                    System.out.println("  " + campo[i] + tipoCartaIcon);
                }
            }
        }
    }

    private static int trovaPrimoSpazioVuoto(Carta[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void pescaCarta() {
        if (indiceMazzo >= mazzo.length) {
            System.out.println(nome + " - Mazzo esaurito!");
            return;
        }
        
        int posLibera = trovaPrimoSpazioVuoto(mano);
        
        if (posLibera == -1) {
            System.out.println(nome + " - Mano piena! Non puoi pescare altre carte.");
            return;
        }
        
        Carta cartaPescata = mazzo[indiceMazzo];
        indiceMazzo++;
        mano[posLibera] = cartaPescata;
        
        String tipoMsg = cartaPescata.isZuccanTech ? " ZUCCAN-TECH" : "";
        System.out.println(nome + " ha pescato: " + cartaPescata.getNome() + tipoMsg);
    }

    public void evocaCarta() {
        if (contaCarte(campo) >= 5) {
            System.out.println(nome + " - Campo pieno! Non può evocare.");
            return;
        }
        
        if (contaCarte(mano) == 0) {
            System.out.println(nome + " - Mano vuota! Non può evocare.");
            return;
        }
        
        Carta cartaDaEvocare = null;
        int indiceCarta = -1;
        
        for (int i = 0; i < mano.length; i++) {
            if (mano[i] != null) {
                if (cartaDaEvocare == null || mano[i].getPuntiVita() > cartaDaEvocare.getPuntiVita()) {
                    cartaDaEvocare = mano[i];
                    indiceCarta = i;
                }
            }
        }
        
        if (cartaDaEvocare != null) {
            int posLibera = trovaPrimoSpazioVuoto(campo);
            campo[posLibera] = cartaDaEvocare;
            mano[indiceCarta] = null;
            
            String tipoMsg = cartaDaEvocare.isZuccanTech ? " ZUCCAN-TECH" : "";
            System.out.println(nome + " evoca: " + cartaDaEvocare.getNome() + 
                              " (HP: " + cartaDaEvocare.getPuntiVita() + ")" + tipoMsg);
            
            if (cartaDaEvocare instanceof ZuccanTech) {
                ZuccanTech zuccanTech = (ZuccanTech) cartaDaEvocare;
                attivaEffettoZuccanTech(zuccanTech);
            }
        }
    }

    private void attivaEffettoZuccanTech(ZuccanTech zuccanTech) {
        EffettoTech effetto = zuccanTech.getEffettoTech();
        
        if (effetto == null) {
            System.out.println("  >>> Questa ZuccanTech non ha effetti speciali <<<");
            return;
        }
        
        if (effetto.isUsato()) {
            System.out.println("  >>> Effetto già utilizzato <<<");
            return;
        }
        
        System.out.println("  >>> ATTIVAZIONE EFFETTO ZUCCAN-TECH <<<");
        
        switch (effetto) {
            case PESCA_CARTA -> effetto.usaEffettoPescaCarta(this);
            case SCUDO -> effetto.usaEffettoScudo(zuccanTech);
            case CAMBIA_STATISTICHE -> effetto.usaEffettoCambiaStatistiche(zuccanTech);
        }
        
        effetto.setUsato(true);
    }

    public void faseBattaglia(Giocatore avversario) {
        if (contaCarte(campo) == 0) {
            System.out.println(nome + " non ha carte in campo per attaccare!");
            return;
        }
        
        Utility.ordinaCampoPerPuntiTotali(campo);
        
        System.out.println("\n--- FASE BATTAGLIA di " + nome + " ---");
        
        for (int i = 0; i < campo.length; i++) {
            if (campo[i] != null) {
                attacca(avversario, campo[i]);
                
                if (avversario.isSconfitto()) {
                    return;
                }
            }
        }
    }

    public Carta pescaCartaConAbilita(Abilita abilita) {
        if (indiceMazzo >= mazzo.length) {
            System.out.println(nome + " - Mazzo esaurito! Non puoi pescare altre carte.");
            return null;
        }
        
        int posLibera = trovaPrimoSpazioVuoto(mano);
        
        if (posLibera == -1) {
            System.out.println(nome + " - Mano piena! Non puoi pescare altre carte.");
            return null;
        }
        
        Carta cartaDaPescare = trovaCartaPerAbilita(abilita);
        if (cartaDaPescare != null) {
            mano[posLibera] = cartaDaPescare;
            System.out.println(nome + " pesca: " + cartaDaPescare.getNome() + " con abilità " + abilita);
            return cartaDaPescare;
        }
        
        System.out.println("Nessuna carta con l'abilità " + abilita + " trovata nel mazzo.");
        return null;
    }

    private Carta trovaCartaPerAbilita(Abilita abilitaDaCercare) {
        for (int i = indiceMazzo; i < mazzo.length; i++) {
            if (mazzo[i] != null && mazzo[i].getAbilita() == abilitaDaCercare) {
                Carta cartaTrovata = mazzo[i];
                mazzo[i] = mazzo[indiceMazzo];
                mazzo[indiceMazzo] = cartaTrovata;
                indiceMazzo++;
                return cartaTrovata;
            }
        }
        return null;
    }

    private void attacca(Giocatore avversario, Carta cartaAttaccante) {
        Carta bersaglio = avversario.trovaCartaPerStatistica(cartaAttaccante.target);
        
        if (bersaglio == null) {
            System.out.println("  " + cartaAttaccante.getNome() + " effettua un ATTACCO DIRETTO!");
            avversario.puntiVita--;
            System.out.println("  " + avversario.nome + " subisce un attacco diretto! Punti vita: " + avversario.puntiVita + "/3");
        } else {
            if (bersaglio.getAbilita() == Abilita.EVASIONE && random.nextInt(100) < 30) {
                System.out.println("  " + cartaAttaccante.getNome() + " attacca " + bersaglio.getNome());
                System.out.println("  >>> ABILITÀ EVASIONE attivata! " + bersaglio.getNome() + " schiva l'attacco! <<<");
                return;
            }
            
            int attacco = cartaAttaccante.getPuntiAttacco();
            int difesa = bersaglio.getPuntiDifesa();
            
            if (cartaAttaccante.getAbilita() == Abilita.VELENO) {
                System.out.println("  >>> ABILITÀ VELENO attivata! <<<");
                attacco += 5;
                difesa = 0;
            }
            
            boolean criticoAttivato = false;
            if (cartaAttaccante.getAbilita() == Abilita.CRITICO && random.nextInt(100) < 25) {
                System.out.println("  >>> ABILITÀ CRITICO attivata! <<<");
                attacco *= 2;
                criticoAttivato = true;
            }
            
            int danno = Math.max(1, attacco - difesa);
            
            if (bersaglio.getAbilita() == Abilita.SCUDO) {
                System.out.println("  >>> ABILITÀ SCUDO attivata! <<<");
                danno = danno / 2;
                if (danno < 1) danno = 1;
            }
            
            if (bersaglio instanceof ZuccanTech) {
                ZuccanTech zuccanTech = (ZuccanTech) bersaglio;
                EffettoTech effetto = zuccanTech.getEffettoTech();
                
                if (effetto == EffettoTech.SCUDO && effetto.isScudoAttivo()) {
                    danno = effetto.applicaScudo(danno);
                }
            }
            
            bersaglio.setPuntiVita(bersaglio.getPuntiVita() - danno);
            
            System.out.println("  " + cartaAttaccante.getNome() + " (ATK:" + cartaAttaccante.getPuntiAttacco() + 
                            (criticoAttivato ? " x2" : "") + ") attacca " + bersaglio.getNome() + " (DEF:" + bersaglio.getPuntiDifesa() + ")");
            dannoInflitto += danno;
            System.out.println("  Danno inflitto: " + danno + " | HP rimanenti: " + bersaglio.getPuntiVita());
            
            if (cartaAttaccante.getAbilita() == Abilita.VAMPIRO) {
                int curaVampiro = danno;
                cartaAttaccante.setPuntiVita(cartaAttaccante.getPuntiVita() + curaVampiro);
                System.out.println("  >>> ABILITÀ VAMPIRO attivata! " + cartaAttaccante.getNome() + 
                                 " recupera " + curaVampiro + " HP (Totale: " + cartaAttaccante.getPuntiVita() + ") <<<");
            }
            
            if (bersaglio.getPuntiVita() <= 0) {
                System.out.println("  >>> " + bersaglio.getNome() + " è stato DISTRUTTO! <<<");
                carteDistrutte++;
                avversario.rimuoviCarta(bersaglio);
            }
        }
    }

    private void rimuoviCarta(Carta carta) {
        for (int i = 0; i < campo.length; i++) {
            if (campo[i] == carta) {
                campo[i] = null;
                break;
            }
        }
    }

    public Carta trovaCartaPerStatistica(TipoTarget tipoTarget) {
        Carta cartaTrovata = null;
        
        switch (tipoTarget) {
            case ATK_FORTE:
                for (Carta carta : campo) {
                    if (carta != null) {
                        if (cartaTrovata == null || carta.getPuntiAttacco() > cartaTrovata.getPuntiAttacco()) {
                            cartaTrovata = carta;
                        }
                    }
                }
                break;
            case ATK_DEBOLE:
                for (Carta carta : campo) {
                    if (carta != null) {
                        if (cartaTrovata == null || carta.getPuntiAttacco() < cartaTrovata.getPuntiAttacco()) {
                            cartaTrovata = carta;
                        }
                    }
                }
                break;
            case DEF_FORTE:
                for (Carta carta : campo) {
                    if (carta != null) {
                        if (cartaTrovata == null || carta.getPuntiDifesa() > cartaTrovata.getPuntiDifesa()) {
                            cartaTrovata = carta;
                        }
                    }
                }
                break;
            case DEF_DEBOLE:
                for (Carta carta : campo) {
                    if (carta != null) {
                        if (cartaTrovata == null || carta.getPuntiDifesa() < cartaTrovata.getPuntiDifesa()) {
                            cartaTrovata = carta;
                        }
                    }
                }
                break;
            case HP_ALTO:
                for (Carta carta : campo) {
                    if (carta != null) {
                        if (cartaTrovata == null || carta.getPuntiVita() > cartaTrovata.getPuntiVita()) {
                            cartaTrovata = carta;
                        }
                    }
                }
                break;
            case HP_BASSO:
                for (Carta carta : campo) {
                    if (carta != null) {
                        if (cartaTrovata == null || carta.getPuntiVita() < cartaTrovata.getPuntiVita()) {
                            cartaTrovata = carta;
                        }
                    }
                }
                break;
        }
        
        return cartaTrovata;
    }

    public boolean isSconfitto() {
        return puntiVita <= 0;
    }

    public boolean haPerso() {
        return indiceMazzo >= mazzo.length && contaCarte(campo) == 0 && contaCarte(mano) == 0;
    }

    private int contaCarte(Carta[] array) {
        int count = 0;
        for (Carta c : array) {
            if (c != null) count++;
        }
        return count;
    }

    public Carta[] getMano() {
        return mano;
    }

    public Carta[] getCampo() {
        return campo;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public String getNome() {
        return nome;
    }

    public int getCarteDistrutte() {
        return carteDistrutte;
    }

    public int getDannoInflitto() {
        return dannoInflitto;
    }

    public int getIndiceMazzo() {
        return indiceMazzo;
    }

    public void setIndiceMazzo(int i) {
        indiceMazzo = i;
    }
}