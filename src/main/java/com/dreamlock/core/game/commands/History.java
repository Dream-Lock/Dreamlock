package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();
        output.add(10002);

        List<String> history = gameContext.getHistory().getHistory();
        int id = 9100;
        if (history != null) {
            if (history.size() == 1 && history.get(0).equals("history")) {
                output.add(2005);
            }
            else {
                for (String command : history) {
                    if (!command.equals("history")) {
                        gameContext.registerMessage(command, id);
                        output.add(id);
                        id++;
                    }
                }
            }
        }
        else {
            output.add(2005);
        }
        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
