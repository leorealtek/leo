package leo.Game;

public class HealPotion extends Item{
    private final int quantityOfHealing;
    private final float speedOfHealing;

    public HealPotion(String nome, int quantityOfHealing, float speedOfHealing) {
        super(nome);
        this.quantityOfHealing = quantityOfHealing;
        this.speedOfHealing = speedOfHealing;
    }

    public int getQuantityOfHealing() {
        return quantityOfHealing;
    }

    public float getSpeedOfHealing() {
        return speedOfHealing;
    }

}
