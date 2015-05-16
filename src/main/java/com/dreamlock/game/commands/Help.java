package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Help implements ICommand  {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();
        output.add(10004);
        for (int i = 1100 ; i < 1114 ; i++) {
            output.add(i);
            output.add(10003);
        }
        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
