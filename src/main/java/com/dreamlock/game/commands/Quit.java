package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.util.List;
import java.util.Map;

public class Quit implements ICommand{
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        System.out.println("GAME OVER!"); //TODO
        System.exit(0);
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
       return null;
    }
}
