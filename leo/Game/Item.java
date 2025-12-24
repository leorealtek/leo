package leo.Game;

public class Item {
    private final String name;
    private boolean utilized;

    public Item(String name) {
        this.name = name;
        utilized = false;
    }

    public String getName() {
        return name;
    }

    public boolean isUtilized() {
        return utilized;
    }

    public void use(Entity entity) {
        this.utilized = true;
    }

    @Override
    public String toString() {
        return "Oggetto [Nome=" + name + ", Usato = " + utilized + "]";
    }
    
}
