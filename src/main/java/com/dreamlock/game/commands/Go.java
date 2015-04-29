package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Room;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Go implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();
        String direction = words.get(2).getDescription();
        Room room = gameContext.getCurrentRoom().getExits().get(direction);
        if (!room.getDescription().equals("wall")) {
            gameContext.setCurrentRoom(room);
            output.add(room.getId());
            return output;
        }
        else {
            output.add(1000);
            output.add(words.get(2).getId());
            return output;
        }
    }
}
