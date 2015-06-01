package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        List<String> history = gameContext.getHistory().getHistory();
        int id = 9100;
        if (history != null) {
            if (history.size() == 1 && history.get(0).equals("history")) {
                outputMessages.add(new OutputMessage(2005));
            }
            else {
                for (String command : history) {
                    if (!command.equals("history")) {
                        gameContext.registerMessage(command, id);
                        outputMessages.add(new OutputMessage(id));
                        id++;
                    }
                }
            }
        }
        else {
            outputMessages.add(new OutputMessage(2005));
        }
        return outputMessages;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
