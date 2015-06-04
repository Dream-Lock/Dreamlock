package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Help implements ICommand  {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        for (int i = 1100 ; i < 1116 ; i++) {
            outputMessages.add(new OutputMessage(i, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            outputMessages.add(new OutputMessage(i, PrintStyle.ONLY_DESCRIPTION_IN_SAME_LINE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        }
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        return outputMessages;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        return null;
    }
}
