package com.dreamlock.core.game.combat;


import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.EquipmentSlot;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.states.ICombatState;
import com.dreamlock.core.story_parser.items.Armor;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.story_parser.items.Weapon;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.util.*;


public abstract class Combatant implements Serializable{
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected int stamina;
    protected int strength;
    protected int agility;
    protected int maxHealth;
    protected Pair<String, Armor> chest, head, hands, legs, feet;
    protected Pair<String, Weapon> main_hand, off_hand;

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

    public void calculateStats() {
        //initialize stats
        this.setHealth(10);
        this.setAttack(1);
        this.setDefense(1);
        this.setStamina(1);
        this.setStrength(1);
        this.setAgility(1);

        int stamina_mod = 0, agility_mod = 0,strength_mod = 0,attack_mod = 0,defense_mod = 0;

        for(EquipmentSlot slot : EquipmentSlot.values()) {
            if(!isEmptySlot(slot)){
                stamina_mod+=  Integer.parseInt(getSlot(slot).getStats().get(Stats.STAMINA).toString());
                strength_mod+= Integer.parseInt(getSlot(slot).getStats().get(Stats.STRENGTH).toString());
                agility_mod+= Integer.parseInt(getSlot(slot).getStats().get(Stats.AGILITY).toString());
                if (getSlot(slot).getType().equals("armor")) {
                    defense_mod+= Integer.parseInt(getSlot(slot).getStats().get(Stats.DEFENSE).toString());
                }
                else {
                    attack_mod+= Integer.parseInt(getSlot(slot).getStats().get(Stats.ATTACK).toString());
                }

            }
        }

        this.setStamina(stamina + stamina_mod);
        if (this.getStamina() < 0) {
            this.setStamina(1);
        }
        this.setStrength(strength + strength_mod);
        if (this.getStrength() < 0) {
            this.setStrength(1);
        }
        this.setAgility(agility + agility_mod);
        if (this.getAgility() < 0) {
            this.setAgility(1);
        }

        this.setHealth(health + getStamina());
        this.setMaxHealth(this.health);
        this.setAttack(attack + getStrength() + attack_mod);
        this.setDefense(defense + getAgility() + defense_mod);

    }

    public boolean isAlive() {
        return health > 0;
    }

    public List<Integer> getPlayerStats (){
        List<Integer> playerStats = new ArrayList<>();

        playerStats.add(this.agility);
        playerStats.add(this.attack);
        playerStats.add(this.defense);
        playerStats.add(this.health);
        playerStats.add(this.stamina);
        playerStats.add(this.strength);

        return playerStats;
    }

    public Item getSlot(EquipmentSlot slot){
        switch(slot){
            case HEAD:
                if(head != null)
                    return head.getValue();
                else
                    return null;
            case CHEST:
                if(chest != null)
                    return chest.getValue();
                else
                    return null;
            case HANDS:
                if(hands != null)
                    return hands.getValue();
                else
                    return null;
            case LEGS:
                if(legs != null)
                    return legs.getValue();
                else
                    return null;
            case FEET:
                if(feet != null)
                    return feet.getValue();
                else
                    return null;
            case MAIN_HAND:
                if(main_hand != null)
                    return main_hand.getValue();
                else
                    return null;
            case OFF_HAND:
                if(off_hand != null)
                    return off_hand.getValue();
                else
                    return null;
            default:
                return null;
        }
    }

    public boolean isEmptySlot(EquipmentSlot equipmentSlot){
        Item item = getSlot(equipmentSlot);
        if (item == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
