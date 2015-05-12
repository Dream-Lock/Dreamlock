package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.states.IState;

import java.io.Serializable;
import java.util.Map;

public abstract class Item implements Serializable{
    protected Integer id;
    protected String name;
    protected String description;
    protected String type;

    protected Map<String,IState> states;
    protected Map<String, Object> stats;

    public int doActionState (String state, IGameContext gameContext) {
        return states.get(state).doAction(gameContext, this);
    }

    public Map<String, Object> getStats() {
        return stats;
    }

    public Map<String, IState> getStates() {
        return states;
    }

    public void setStats(Map<String, Object> stats) {
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
