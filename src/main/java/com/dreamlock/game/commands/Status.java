package com.dreamlock.game.commands;


import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Status implements ICommand{
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();

        System.out.println("Health: " + gameContext.getPlayer().getHealth());
        System.out.println("Attack: " + gameContext.getPlayer().getAttack());
        System.out.println("Defense: " + gameContext.getPlayer().getDefense());
        System.out.println("Stamina: " + gameContext.getPlayer().getStamina());
        System.out.println("Strength: " + gameContext.getPlayer().getStrength());
        System.out.println("Agility: " + gameContext.getPlayer().getAgility());

        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {

        return null;
    }
}
