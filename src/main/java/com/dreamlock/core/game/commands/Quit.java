package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Quit implements ICommand{
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        outputMessages.add(new OutputMessage(5000, PrintStyle.ONLY_TITLE));
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        gameContext.setGameRunning(false);
        return outputMessages;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
       return null;
    }
}
