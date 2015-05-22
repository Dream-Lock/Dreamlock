package com.dreamlock;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.History;

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
}