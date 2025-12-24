package leo.Game;

public class Player extends Entity{
    private int age;
    private Item[] inventory;

    public Player(String name, int health, Weapon weapon, int age, Item[] inventory) {
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

    public void regenerate(Item item) {
        //da fare
    }

    public Item[] getInventory() {
        return inventory;
    }

    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }

    public boolean useItem(Item item) {
        if (inventory.contains(item)) {
            item.setUtilized(true);
            return true;
        }
        else return false;
    }

}
