package com.dreamlock;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryController {
    private IGameContext gameContext;

    public HistoryController(IGameContext gameContext) {
        this.gameContext = gameContext;
    }

    public void register(String command) {
        History history = gameContext.getHistory();
        history.setHistory(command);
        gameContext.setHistory(history);
    }

    public Integer getRepeats() {
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

    public List<Integer> handle() {

        List<String> history = gameContext.getHistory().getHistory();
        if(!history.get(history.size()-1).contains("go")) {
            Integer repeatedCommands = this.getRepeats();
            List<Integer> messageIds = new ArrayList<>();

            messageIds.add(10000); // only title
            switch (repeatedCommands) {
                case 1:
                    messageIds.add(1601);
                    break;
                case 2:
                    messageIds.add(1602);
                    break;
                case 3:
                    messageIds.add(1603);
                    break;
                case 4:
                    messageIds.add(1604);
                    gameContext.setGameRunning(false);
                    break;
                default:
                    return null;
            }
            return messageIds;
        }
        return null;
    }
}