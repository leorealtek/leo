package Scuola.Progettini.Pokemon.Other;

import Scuola.Progettini.Pokemon.Types.HeldItem;

public class Pokemon {
    protected final String name;
    protected int HP;
    protected final int HPmax;
    protected int attack; 
    protected int defense; 
    protected Status status;
    protected HeldItem heldItem;

    public Pokemon(String name, int HP, int HPmax, int attack, int defense) {
        this.name = name;
        this.HP = HP;
        this.HPmax = HPmax;
        this.attack = attack;
        this.defense = defense;
        status = Status.Normal;
        heldItem = null;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHPmax() {
        return HPmax;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public HeldItem getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(HeldItem heldItem) {
        this.heldItem = heldItem;
    }
    
}