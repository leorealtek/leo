package leo.Game;

public class Enemy extends Entity {
    public Enemy(String name, float health, Weapon weapon) {
        super(name, health, weapon);
    }

    @Override
    public String toString() {
        return "Nemico [Nome = " + getName() + ", Vita = " + getHealth() + ", Arma = " + getWeapon() + "]";
    }
    
}
