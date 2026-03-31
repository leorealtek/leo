package Scuola.Progettini.Pokemon.Other;

import java.util.*;
import Scuola.Progettini.Pokemon.Exceptions.UnsupportedActionException;
import Scuola.Progettini.Pokemon.Types.Item;
import Scuola.Progettini.Pokemon.Types.Consumables.Consumable;
import Scuola.Progettini.Pokemon.Types.HeldItems.HeldItem;

public class Inventory {

    private List<Item> items = new ArrayList<>();
    private List<Integer> quantities = new ArrayList<>();

    public void addItem(Item newItem, int amount) {
        int index = findIndex(newItem.getName());
        int maxStack = newItem.getStack();

        if (index == -1) {
            int toAdd = Math.min(maxStack, amount);
            items.add(newItem);
            quantities.add(toAdd);
        } else {
            int current = quantities.get(index);
            int spaceLeft = maxStack - current;
            int toAdd = Math.min(spaceLeft, amount);
            quantities.set(index, current + toAdd);
        }
    }

    public void useItem(String name, Pokemon target) throws UnsupportedActionException {
        int index = findIndex(name);

        if (index == -1 || quantities.get(index) <= 0) {
            throw new UnsupportedActionException("Item not found: " + name);
        }

        Item item = items.get(index);

        if (!(item instanceof Consumable)) {
            throw new UnsupportedActionException("Item is not consumable: " + name);
        }

        Consumable consumable = (Consumable) item;
        consumable.use(target);

        decreaseQuantity(index, 1);
    }

    public void giveHeldItem(String name, Pokemon target) throws UnsupportedActionException {
        int index = findIndex(name);

        if (index == -1 || quantities.get(index) <= 0) {
            throw new UnsupportedActionException("Item not found: " + name);
        }

        Item item = items.get(index);

        if (!(item instanceof HeldItem)) {
            throw new UnsupportedActionException("Item not holdable: " + name);
        }

        HeldItem held = (HeldItem) item;
        held.giveTo(target);

        decreaseQuantity(index, 1);
    }

    private void decreaseQuantity(int index, int amount) {
        int updated = quantities.get(index) - amount;

        if (updated <= 0) {
            items.remove(index);
            quantities.remove(index);
        } else {
            quantities.set(index, updated);
        }
    }

    private int findIndex(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public int getQuantity(String name) {
        int index = findIndex(name);
        return (index == -1) ? 0 : quantities.get(index);
    }

    public void printInventory() {
        System.out.println("--- Inventory ---");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getName() + " x" + quantities.get(i));
        }
    }
}