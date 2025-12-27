package leo.Game;

public class StrenghtPotion extends Item {
    private final int attacksWithBoost;
    private final int quantityOfBoost;

    public StrenghtPotion(String name, int attacksWithBoost, int quantityOfBoost) {
        super(name);
        this.attacksWithBoost = attacksWithBoost;
        this.quantityOfBoost = quantityOfBoost;
    }

    public float getAttacksWithBoost() {
        return attacksWithBoost;
    }

    public int getQuantityOfBoost() {
        return quantityOfBoost;
    }

    @Override
    public void use(Entity entity) {
        super.use(entity);
        entity.getWeapon().setDamage(entity.getWeapon().getDamage() + quantityOfBoost);
        // da finire
    }

    



    

}