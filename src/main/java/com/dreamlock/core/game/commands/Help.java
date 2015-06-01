package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Help implements ICommand  {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> output = new ArrayList<>();
        for (int i = 1100 ; i < 1114 ; i++) {
            output.add(new OutputMessage(i));
        }
        return output;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
