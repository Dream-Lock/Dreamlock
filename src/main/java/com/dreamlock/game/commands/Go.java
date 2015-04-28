package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Room;

/**
 * Created by tommy on 28/4/2015.
 */
public class Go implements ICommand {
    @Override
    public Integer execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public Integer execute(IGameContext gameContext, String[] words) {
        String direction = words[0];

        Room room = gameContext.getCurrentRoom().getExits().get(direction);
        // TODO
        if (!room.getDescription().equals("wall")) {
            gameContext.setCurrentRoom(room);
            return 1;
        }
        else {
            return -1;
        }
    }
}
