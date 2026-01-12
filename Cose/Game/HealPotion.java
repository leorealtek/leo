package Game;

public class HealPotion extends Item{
    private final float quantityOfHealing;
    private final float secondsOfHealing;

    public HealPotion(String name, float quantityOfHealing, float secondsOfHealing) {
        super(name);
        this.quantityOfHealing = quantityOfHealing;
        this.secondsOfHealing = secondsOfHealing;
    }

    public float getQuantityOfHealing() {
        return quantityOfHealing;
    }

    public float getSecondsOfHealing() {
        return secondsOfHealing;
    }

    @Override
    public void use(Entity entity) {
        super.use(entity);
        entity.setHealth(entity.getHealth() + quantityOfHealing * secondsOfHealing);
    }

}
