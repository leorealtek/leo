package leo.Game;

import java.util.ArrayList;

public class Player extends Entity{
    private int age;
    private ArrayList<Item> inventory;

    public Player(String name, float health, Weapon weapon, int age, ArrayList<Item> inventory) {
        super(name, health, weapon);
        this.age = age;
        this.inventory = inventory;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void useItem(Item item) {
        if (inventory.contains(item) && !item.isUtilized()) {
            item.use(this);
        }
        else System.out.println("La pozione non è nel tuo inventario o è gia stata usata");
    }

    @Override
    public boolean attack(Entity entity) {
        super.attack(entity);
        for (Item item : inventory) {
            if (item instanceof StrenghtPotion && item.isUtilized()) {
                ((StrenghtPotion) item).decrementRemainingAttacks(this);
            }
        }
    return true;
    }

    @Override
    public String toString() {
        return "Giocatore [Età = " + age + ", Inventario = " + inventory + ", Nome = " + getName() + ", Vita = "
                + getHealth() + ", Arma = " + getWeapon() + "]";
    }
    
}