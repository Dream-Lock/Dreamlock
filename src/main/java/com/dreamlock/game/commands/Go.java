package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Room;
import com.dreamlock.game.models.Word;

import java.util.Map;

public class Go implements ICommand {
    @Override
    public Integer execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public Integer execute(IGameContext gameContext, Map<Integer, Word> words) {
        String direction = words.get(1).getDescription();

        Room room = gameContext.getCurrentRoom().getExits().get(direction);
        // TODO
        if (!room.getDescription().equals("wall")) {
            gameContext.setCurrentRoom(room);
            return 1;
        }
        else {
            return 2;
        }
    }
}
