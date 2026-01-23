package Scuola.Progettino;

public class Giocatore {
    protected final String nome;
    protected Carta[] mazzo;
    protected Carta[] mano;
    protected Carta[] campo;
    protected int puntiVita;
    private int indiceMazzo = 5;

    public Giocatore(String nome) {
        this.nome = nome;
        mazzo = creaMazzo();
        mano = new Carta[10];
        campo = new Carta[5];
        puntiVita = 3;
        
        for (int i = 0; i < 5; i++) {
            mano[i] = mazzo[i];
        }
    }

    private static Carta[] creaMazzo() {
        Carta[] mazzo = new Carta[50];
        for (int i = 0; i < mazzo.length; i++) {
            mazzo[i] = new Carta();
        }
        return mazzo;
    }

    public void mostraMano() {
        System.out.println("Mano di " + nome + ":");
        for (int i = 0; i < mano.length; i++) {
            if (mano[i] != null) {
                System.out.println("  " + mano[i]);
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
                    System.out.println("  " + campo[i]);
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
        System.out.println(nome + " ha pescato: " + cartaPescata.getNome());
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
            System.out.println(nome + " evoca: " + cartaDaEvocare.getNome() + " (HP: " + cartaDaEvocare.getPuntiVita() + ")");
        }
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

    private void attacca(Giocatore avversario, Carta cartaAttaccante) {
        Carta bersaglio = avversario.trovaCartaPerStatistica(cartaAttaccante.target);
        
        if (bersaglio == null) {
            System.out.println("  " + cartaAttaccante.getNome() + " effettua un ATTACCO DIRETTO!");
            avversario.puntiVita--;
            System.out.println("  " + avversario.nome + " subisce un attacco diretto! Punti vita: " + avversario.puntiVita + "/3");
        } else {
            int danno = Math.max(1, cartaAttaccante.getPuntiAttacco() - bersaglio.getPuntiDifesa());
            bersaglio.setPuntiVita(bersaglio.getPuntiVita() - danno);
            
            System.out.println("  " + cartaAttaccante.getNome() + " (ATK:" + cartaAttaccante.getPuntiAttacco() + 
                             ") attacca " + bersaglio.getNome() + " (DEF:" + bersaglio.getPuntiDifesa() + ")");
            System.out.println("  Danno inflitto: " + danno + " | HP rimanenti: " + bersaglio.getPuntiVita());
            
            if (bersaglio.getPuntiVita() <= 0) {
                System.out.println("  >>> " + bersaglio.getNome() + " è stato DISTRUTTO! <<<");
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
}