package com.dreamlock.core.handlers;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.HistoryExceptions;
import com.dreamlock.core.game.models.History;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler implements IHandler {
    private IGameContext gameContext;

    public HistoryHandler(IGameContext gameContext) {
        this.gameContext = gameContext;
    }

    public void register(String command) {
        History history = gameContext.getHistory();
        history.setHistory(command);
        gameContext.setHistory(history);
    }

    @Override
    public List<OutputMessage> handle() {
        List<String> history = gameContext.getHistory().getHistory();
        Boolean hasException = false;
        for (HistoryExceptions exception : HistoryExceptions.values()) {
            if (history.get(history.size()-1).contains(exception.toString())) {
                hasException = true;
            }
        }

        if(!hasException) {
            Integer repeatedCommands = this.getRepeats();
            List<OutputMessage> messageIds = new ArrayList<>();

            switch (repeatedCommands) {
                case 1:
                    messageIds.add(new OutputMessage(1601, PrintStyle.ONLY_TITLE));
                    break;
                case 2:
                    messageIds.add(new OutputMessage(1602, PrintStyle.ONLY_TITLE));
                    break;
                case 3:
                    messageIds.add(new OutputMessage(1603, PrintStyle.ONLY_TITLE));
                    break;
                case 4:
                    messageIds.add(new OutputMessage(1604, PrintStyle.ONLY_TITLE));
                    gameContext.setGameRunning(false);
                    break;
                default:
                    return null;
            }
            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }
        return null;
    }

    private Integer getRepeats() {
        List<String> commandList = gameContext.getHistory().getHistory();
        int listSize = commandList.size();
        int counter = 0;

        if (listSize < 2) {
            return counter;
        }

        if (listSize >= 2) {
            int i = 2;
            while (i <= listSize && commandList.get(listSize-1).equals(commandList.get(listSize - i))) {
                counter ++;
                i ++;
            }
        }
        return counter;
    }
}