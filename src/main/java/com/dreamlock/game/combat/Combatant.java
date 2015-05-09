package com.dreamlock.game.combat;


import com.dreamlock.game.states.ICombatState;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Combatant implements Serializable{
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;


    protected Map<String,ICombatState> states;

    public Map<String, ICombatState> getStates() {
        return states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
