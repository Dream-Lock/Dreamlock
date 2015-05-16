package com.dreamlock.game.combat;


import com.dreamlock.game.jsonParser.items.Armor;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.jsonParser.items.Weapon;
import com.dreamlock.game.states.ICombatState;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public abstract class Combatant implements Serializable{
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected int stamina;
    protected int strength;
    protected int agility;
    protected Pair<String, Armor> chest, head;
    protected Pair<String, Weapon> main_hand;

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

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void calculateStats(){
        int stamina_mod = 0, agility_mod = 0,strength_mod = 0,attack_mod = 0,defense_mod = 0;

        if(head!=null){
            stamina_mod+=  Integer.parseInt(head.getValue().getStats().get("stamina").toString());
            strength_mod+= Integer.parseInt(head.getValue().getStats().get("strength").toString());
            agility_mod+= Integer.parseInt(head.getValue().getStats().get("agility").toString());
            defense_mod+= Integer.parseInt(head.getValue().getStats().get("defense").toString());
        }

        if(chest!=null){
            stamina_mod+=  Integer.parseInt(chest.getValue().getStats().get("stamina").toString());
            strength_mod+= Integer.parseInt(chest.getValue().getStats().get("strength").toString());
            agility_mod+= Integer.parseInt(chest.getValue().getStats().get("agility").toString());
            defense_mod+= Integer.parseInt(chest.getValue().getStats().get("defense").toString());
        }

        if(main_hand!=null){
            stamina_mod+=  Integer.parseInt(main_hand.getValue().getStats().get("stamina").toString());
            strength_mod+= Integer.parseInt(main_hand.getValue().getStats().get("strength").toString());
            agility_mod+= Integer.parseInt(main_hand.getValue().getStats().get("agility").toString());
            attack_mod+= Integer.parseInt(main_hand.getValue().getStats().get("attack").toString());
        }

        this.setStamina(1 + stamina_mod);
        this.setStrength(1 + strength_mod);
        this.setAgility(1 + agility_mod);

        this.setHealth(10 + getStamina());
        this.setAttack(1 + getStrength() + attack_mod);
        this.setDefense(1 + getAgility() + defense_mod);

    }

    public boolean isAlive() {
        return health > 0;
    }
}
