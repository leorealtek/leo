package Scuola.EserciziAScuola.OOP;

public class ProdottoAlimentare extends Prodotto{
    private double calorie;
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
        return "ProdottoAlimentare [nome=" + nome + ", calorie=" + calorie + ", prezzo=" + prezzo + ", richiedeRefrigerazione=" + richiedeRefrigerazione + "]";
    }
}