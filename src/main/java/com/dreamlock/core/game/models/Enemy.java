package com.dreamlock.core.game.models;

import com.dreamlock.core.game.combat.Combatant;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.states.combatStates.CanAttackState;
import com.dreamlock.core.story_parser.DTOs.EnemyDTO;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;

public class Enemy extends Combatant implements Serializable {
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