package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;

public class Quit implements ICommand{
    @Override
    public Integer execute(IGameContext gameContext) {
        System.out.println("GAME OVER!"); //TODO
        System.exit(0);
        return null;
    }

    @Override
    public Integer execute(IGameContext gameContext, String[] words) {
       return null;
    }
}
