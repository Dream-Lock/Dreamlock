package com.dreamlock.game.combat;


import com.dreamlock.game.states.ICombatState;

import java.util.HashMap;
import java.util.Map;

public abstract class Combatant {
    protected Integer health;
    protected Integer attack;
    protected Integer defense;
    protected Boolean isAlive;

    protected Map<String,ICombatState> states;

    public Map<String, ICombatState> getStates() {
        return states;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
