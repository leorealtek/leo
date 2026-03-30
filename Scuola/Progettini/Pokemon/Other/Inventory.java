package Scuola.Progettini.Pokemon.Other;

import java.util.ArrayList;
import java.util.List;

import Scuola.Progettini.Pokemon.Exceptions.FullStackException;
import Scuola.Progettini.Pokemon.Exceptions.UnsupportedActionException;
import Scuola.Progettini.Pokemon.Types.Consumables.Consumable;
import Scuola.Progettini.Pokemon.Types.HeldItems.HeldItem;
import Scuola.Progettini.Pokemon.Types.Item;

public class Inventory {

    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item newItem) throws FullStackException {

        for (Item existing : items) {
            if (existing.getClass().equals(newItem.getClass())) {
                int total = existing.getQuantity() + newItem.getQuantity();
                if (total > existing.getStack()) {
                    throw new FullStackException(newItem);
                }
                existing.addQuantity(newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public void useItem(Item item, Pokemon target) throws UnsupportedActionException {
        if (!(item instanceof Consumable)) {
            throw new UnsupportedActionException(
                "Can't use " + item.getName() + ": it's not a consumable item."
            );
        }
        Consumable c = (Consumable) item;
        c.use(target);

        if (item.getQuantity() <= 0) {
            items.remove(item);
        }
    }

    public void giveItemTo(Item item, Pokemon target) throws UnsupportedActionException {
        if (!(item instanceof HeldItem)) {
            throw new UnsupportedActionException(
                "Can't give " + item.getName() + ": it's not a held item."
            );
        }
        if (target.getHeldItem() != null) {
            throw new UnsupportedActionException(
                target.getName() + " already holds an item: " + target.getHeldItem()
            );
        }
        HeldItem h = (HeldItem) item;
        h.giveTo(target);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    public List<Consumable> getConsumables() {
        List<Consumable> result = new ArrayList<>();
        for (Item i : items) {
            if (i instanceof Consumable) result.add((Consumable) i);
        }
        return result;
    }

    public List<HeldItem> getHeldItems() {
        List<HeldItem> result = new ArrayList<>();
        for (Item i : items) {
            if (i instanceof HeldItem) result.add((HeldItem) i);
        }
        return result;
    }
}