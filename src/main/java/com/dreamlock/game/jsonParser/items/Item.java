package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.EquipmentSlot;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.states.IState;

import java.io.Serializable;
import java.util.Map;

public abstract class Item implements Serializable{
    protected Integer id;
    protected String name;
    protected String description;
    protected String type;

    protected Map<ActionState,IState> states;
    protected Map<Stats, Object> stats;

    EquipmentSlot equipmentSlot;

    public int doAction (ActionState actionState, IGameContext gameContext) {
        return this.getStates().get(actionState).doAction(gameContext, this);
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }

    public void setEquipmentSlot(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public Map<Stats, Object> getStats() {
        return stats;
    }

    public Map<ActionState, IState> getStates() {
        return states;
    }

    public void setStats(Map<Stats, Object> stats) {
        this.stats = stats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
