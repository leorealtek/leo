package leo.Game;

public class Entity {
    private final String name;
    private float health;
    private Weapon weapon;

    public Entity(String name, float health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public float getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public boolean attack(Entity entity) {
        if (this.getWeapon().getDurability() == 0) return false;
        entity.setHealth(entity.getHealth() - this.getWeapon().getDamage());
        this.getWeapon().setDurability(this.getWeapon().getDurability() - 1);
        return true;
    }

}
