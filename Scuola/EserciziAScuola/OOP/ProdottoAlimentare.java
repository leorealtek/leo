package Scuola.EserciziAScuola.OOP;

public class ProdottoAlimentare extends Prodotto{
    private final double calorie;
    protected boolean richiedeRefrigerazione;
    
    public ProdottoAlimentare(String nome, double prezzo, double calorie, boolean richiedeRefrigerazione) {
        super(nome, prezzo);
        this.calorie = calorie;
        this.richiedeRefrigerazione = richiedeRefrigerazione;
    }

    public double getCalorie() {
        return calorie;
    }

    public boolean RichiedeRefrigerazione() {
        return richiedeRefrigerazione;
    }

    @Override
    public String toString() {
        return "ProdottoAlimentare [Nome: " + nome + " Calorie: " + calorie + " Prezzo: " + prezzo + " Richiede configurazione: " + richiedeRefrigerazione + "]";
    }
}