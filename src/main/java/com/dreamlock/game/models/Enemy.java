package com.dreamlock.game.models;

import com.dreamlock.game.combat.Combatant;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.jsonParser.DTOs.EnemyDTO;
import com.dreamlock.game.states.combatStates.CanAttackState;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;

public class Enemy extends Combatant implements Serializable{
    private int id;
    private String description;

    public Enemy(){
        states = new HashMap<>();
        states.put(ActionState.ATTACK, new CanAttackState());
    }

    public Enemy (String jsonEnemy) {
        Gson gson = new Gson();
        EnemyDTO enemyDTO = gson.fromJson(jsonEnemy, EnemyDTO.class);
        id = enemyDTO.getId();
        name = enemyDTO.getName();
        description = enemyDTO.getDescription();
        health = enemyDTO.getHealth();
        attack = enemyDTO.getAttack();
        defense = enemyDTO.getDefense();

        states = new HashMap<>();
        states.put(ActionState.ATTACK, new CanAttackState());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}