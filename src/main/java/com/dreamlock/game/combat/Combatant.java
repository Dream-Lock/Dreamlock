package com.dreamlock.game.combat;


import com.dreamlock.game.jsonParser.items.Armor;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.jsonParser.items.Weapon;
import com.dreamlock.game.states.ICombatState;

import java.io.Serializable;
import java.util.Map;


public abstract class Combatant implements Serializable{
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected int stamina;
    protected int strength;
    protected int agility;
    protected Armor chest, head;
    protected Weapon main_hand;


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
        int stamina_mod=0, agility_mod=0,strength_mod=0,attack_mod=0,defense_mod=0;

        if(head!=null){
            stamina_mod+=  Integer.parseInt(head.getStats().get("stamina").toString());
            strength_mod+= Integer.parseInt(head.getStats().get("strength").toString());
            agility_mod+= Integer.parseInt(head.getStats().get("agility").toString());
            defense_mod+= Integer.parseInt(head.getStats().get("defense").toString());
        }

        if(chest!=null){
            stamina_mod+=  Integer.parseInt(head.getStats().get("stamina").toString());
            strength_mod+= Integer.parseInt(head.getStats().get("strength").toString());
            agility_mod+= Integer.parseInt(head.getStats().get("agility").toString());
            defense_mod+= Integer.parseInt(head.getStats().get("defense").toString());
        }

        if(main_hand!=null){
            stamina_mod+=  Integer.parseInt(head.getStats().get("stamina").toString());
            strength_mod+= Integer.parseInt(head.getStats().get("strength").toString());
            agility_mod+= Integer.parseInt(head.getStats().get("agility").toString());
            attack_mod+= Integer.parseInt(head.getStats().get("attack").toString());
        }

        this.setHealth(10 + getStamina() + stamina_mod);
        this.setAttack(1 + getStrength() + strength_mod + attack_mod);
        this.setDefense(1 + getAgility() + agility_mod + defense_mod);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
