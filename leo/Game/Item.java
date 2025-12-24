package leo.Game;

public class Item {
    private final String nome;
    private boolean utilized;

    public Item(String nome) {
        this.nome = nome;
        utilized = false;
    }

    public String getNome() {
        return nome;
    }

    public boolean isUtilized() {
        return utilized;
    }

    public void use(Entity entity) {
        this.utilized = true;
    }

}
