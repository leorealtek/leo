package leo.Game;

public class Entity {
    private final String name;
    private int health;
    private Weapon weapon;

    public Entity(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void attack(Entity entity) {
        entity.setHealth(entity. getHealth() - this.getWeapon().getDamage());
    }

}
