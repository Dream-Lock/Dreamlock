package com.dreamlock.core.story_parser.items;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.EquipmentSlot;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.states.IState;

import java.io.Serializable;
import java.util.Map;

public abstract class Item implements Serializable{
    protected Integer id;
    protected String name;
    protected String description;
    protected ItemType type;

    protected Map<ActionState,IState> states;
    protected Map<Stats, Object> stats;

    EquipmentSlot equipmentSlot;

    public OutputMessage doAction (ActionState actionState, IGameContext gameContext) {
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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

}
