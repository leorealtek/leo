package leo.Game;

public class HealPotion extends Item{
    private final int quantityOfHealing;
    private final float speedOfHealing;
    private final float secondsOfHealing;

    public HealPotion(String name, int quantityOfHealing, float speedOfHealing, float secondsOfHealing) {
        super(name);
        this.quantityOfHealing = quantityOfHealing;
        this.speedOfHealing = speedOfHealing;
        this.secondsOfHealing = secondsOfHealing;
    }

    public int getQuantityOfHealing() {
        return quantityOfHealing;
    }

    public float getSpeedOfHealing() {
        return speedOfHealing;
    }

    public float getSecondsOfHealing() {
        return secondsOfHealing;
    }

    @Override
    public void use(Entity entity) {
        super.use(entity);
        try {
            Thread.sleep((long)speedOfHealing);
        } catch (Exception e) {}
        entity.setHealth(entity.getHealth() + quantityOfHealing * secondsOfHealing);
    }

}
