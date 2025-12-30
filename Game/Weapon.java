package leo.Game;

public class Weapon {
    private final String name;
    private int damage;
    private int durability;

    public Weapon(String name, int damage, int durability) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public String toString() {
        String durabilityStr = (durability == Integer.MAX_VALUE) ? "Infinity" : String.valueOf(durability);
        return "[Nome = " + name + ", Danno = " + damage + ", Durabilità = " + durabilityStr + "]";
    }

}
