package Game;

public class StrenghtPotion extends Item {
    private final int attacksWithBoost;
    private final int quantityOfBoost;
    private int remainingAttacks;

    public StrenghtPotion(String name, int attacksWithBoost, int quantityOfBoost) {
        super(name);
        this.attacksWithBoost = attacksWithBoost;
        this.quantityOfBoost = quantityOfBoost;
        this.remainingAttacks = 0;
    }

    public int getAttacksWithBoost() {
        return attacksWithBoost;
    }

    public int getQuantityOfBoost() {
        return quantityOfBoost;
    }

    public int getRemainingAttacks() {
        return remainingAttacks;
    }

    @Override
    public void use(Entity entity) {
        super.use(entity);
        entity.getWeapon().setDamage(entity.getWeapon().getDamage() + quantityOfBoost);
        this.remainingAttacks = attacksWithBoost;
    }

    public void decrementRemainingAttacks(Entity entity) {
        if (remainingAttacks > 0) {
            remainingAttacks--;
            if (remainingAttacks == 0) {
                entity.getWeapon().setDamage(entity.getWeapon().getDamage() - quantityOfBoost);
            }
        }
    }
}