package com.dreamlock.game.combat;


import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.jsonParser.items.Armor;
import com.dreamlock.game.jsonParser.items.Weapon;
import com.dreamlock.game.states.ICombatState;
import org.apache.commons.lang3.tuple.Pair;

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
    protected int maxHealth;
    protected Pair<String, Armor> chest, head;
    protected Pair<String, Weapon> main_hand;

    protected Map<ActionState,ICombatState> states;

    public Map<ActionState, ICombatState> getStates() {
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void calculateStats(){
        int stamina_mod = 0, agility_mod = 0,strength_mod = 0,attack_mod = 0,defense_mod = 0;

        if(head!=null){
            stamina_mod+=  Integer.parseInt(head.getValue().getStats().get(Stats.STAMINA).toString());
            strength_mod+= Integer.parseInt(head.getValue().getStats().get(Stats.STRENGTH).toString());
            agility_mod+= Integer.parseInt(head.getValue().getStats().get(Stats.AGILITY).toString());
            defense_mod+= Integer.parseInt(head.getValue().getStats().get(Stats.DEFENSE).toString());
        }

        if(chest!=null){
            stamina_mod+=  Integer.parseInt(chest.getValue().getStats().get(Stats.STAMINA).toString());
            strength_mod+= Integer.parseInt(chest.getValue().getStats().get(Stats.STRENGTH).toString());
            agility_mod+= Integer.parseInt(chest.getValue().getStats().get(Stats.AGILITY).toString());
            defense_mod+= Integer.parseInt(chest.getValue().getStats().get(Stats.DEFENSE).toString());
        }

        if(main_hand!=null){
            stamina_mod+=  Integer.parseInt(main_hand.getValue().getStats().get(Stats.STAMINA).toString());
            strength_mod+= Integer.parseInt(main_hand.getValue().getStats().get(Stats.STRENGTH).toString());
            agility_mod+= Integer.parseInt(main_hand.getValue().getStats().get(Stats.AGILITY).toString());
            attack_mod+= Integer.parseInt(main_hand.getValue().getStats().get(Stats.ATTACK).toString());
        }

        this.setStamina(stamina + stamina_mod);
        this.setStrength(strength + strength_mod);
        this.setAgility(agility + agility_mod);

        this.setHealth(health + getStamina());
        this.setMaxHealth(this.health);
        this.setAttack(attack + getStrength() + attack_mod);
        this.setDefense(attack + getAgility() + defense_mod);

    }

    public boolean isAlive() {
        return health > 0;
    }
}
