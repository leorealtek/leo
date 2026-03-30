package Scuola.Progettini.Pokemon.Other;

import Scuola.Progettini.Pokemon.Exceptions.NegativeAttackException;
import Scuola.Progettini.Pokemon.Exceptions.NegativeHpException;
import Scuola.Progettini.Pokemon.Exceptions.OverMaxHpException;
import Scuola.Progettini.Pokemon.Types.HeldItems.HeldItem;

public class Pokemon {
    protected final String name;
    protected int HP;
    protected final int HPmax;
    protected int attack;
    protected int defense;
    protected Status status;
    protected HeldItem heldItem;

    public Pokemon(String name, int HPmax, int attack, int defense) {
        this.name = name;
        this.HPmax = HPmax;
        HP = HPmax;
        this.attack = attack;
        this.defense = defense;
        status = Status.Normal;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if (HP < 0) throw new NegativeHpException(HP);
        if (HP > HPmax) throw new OverMaxHpException(HP, HPmax);
        this.HP = HP;
    }

    public int getHPmax() {
        return HPmax;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        if (attack < 0) throw new NegativeAttackException(attack);
        this.attack = attack;
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

    @Override
    public String toString() {
        return "Pokemon [Name: " + name + " HP: " + HP + " HP max: " + HPmax + " Attack: " + attack + " Defense: "
                + defense + " Status: " + status + " Held item: " + heldItem + "]";
    }
}