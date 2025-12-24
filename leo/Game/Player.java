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

    public boolean useItem(Item item) {
        if (inventory.contains(item)) {
            item.use(this);
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return "Giocatore [Età = " + age + ", Inventario = " + inventory + ", Nome = " + getName() + ", Vita = "
                + getHealth() + ", Arma = " + getWeapon() + "]";
    }
    
}