package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Room;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Look implements ICommand  {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();
        Room room = gameContext.getCurrentRoom();

        output.add(10003);
        output.add(room.getId());
        //output.add(10000);
        for (Item item : gameContext.getCurrentRoom().getItems()) {
            output.add(10002);
            output.add(item.getId());
        }
        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
