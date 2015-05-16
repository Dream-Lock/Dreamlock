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

        output.add(10005);
        output.add(gameContext.getPlayer().getHealth());
        output.add(gameContext.getPlayer().getAttack());
        output.add(gameContext.getPlayer().getDefense());
        output.add(gameContext.getPlayer().getStamina());
        output.add(gameContext.getPlayer().getStrength());
        output.add(gameContext.getPlayer().getAgility());

        return output;

    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {

        return null;
    }
}
