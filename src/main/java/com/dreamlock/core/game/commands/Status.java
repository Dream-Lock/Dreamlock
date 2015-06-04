package com.dreamlock.core.game.commands;


import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Status implements ICommand{
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> output = new ArrayList<>();
        List <Integer> playerStats = gameContext.getPlayer().getPlayerStats();

        gameContext.registerMessage(gameContext.getPlayer().getName(),9999);
        output.add(new OutputMessage(2506, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
        output.add(new OutputMessage(9999, PrintStyle.ONLY_TITLE));
        output.add(new OutputMessage(0, PrintStyle.BREAK));

        int i=0;
        for (Integer integer : playerStats) {
            output.add(new OutputMessage(2500+i, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            output.add(new OutputMessage(integer, PrintStyle.NUMBER));
            output.add(new OutputMessage(0, PrintStyle.BREAK));
            i++;
        }
        output.add(new OutputMessage(0, PrintStyle.BREAK));

        return output;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        return null;
    }
}
