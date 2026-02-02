package Scuola.Progettini.Zukkaoh.Enum;

public enum Abilita {
    VELENO("Danno +5, Difesa Avversario = 0"),
    SCUDO("Danno ricevuto -50%"),
    VAMPIRO("Recupera PV pari al 100% del danno inflitto"),
    CRITICO("Possibilità del 25% di infliggere il doppio del danno"),
    EVASIONE("Possibilità del 30% di evitare l'attacco");

    private final String descrizione;

    Abilita(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public static Abilita getRandomAbilita() {
        Abilita[] abilitaValues = Abilita.values();
        int randomIndex = (int) (Math.random() * abilitaValues.length);
        return abilitaValues[randomIndex];
    }
}
