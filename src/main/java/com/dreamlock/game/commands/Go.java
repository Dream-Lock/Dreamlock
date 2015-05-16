package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
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

        if (!room.getDescription().equals("wall")) { //If wall

            if (room.isLocked()) { //if it is a room, and it is locked

                //Search for the correct key in inventory
                List <Item> inventory = gameContext.getPlayer().getInventory().getItems();
                boolean found = false;
                for (Item tempItem : inventory) {
                    if (tempItem.getId().equals(room.getRequires())) {
                        found = true;
                    }
                }
                if (found) {                 //if found
                    room.setLocked(false);      //transition
                    room.setRequires(0);
                    gameContext.setCurrentRoom(room);
                    output.add(1006);
                    output.add(room.getId());
                    return output;
                }
                else {              //Print a message indicating tha the door is locked
                    output.add(10000);
                    output.add(1007);
                    return output;
                }
            }
            else {
                gameContext.setCurrentRoom(room);
                output.add(room.getId());
                return output;
            }
        }
        else {
            output.add(10000);                  // print only titles
            output.add(1001);                   // can not go to
            output.add(words.get(2).getId());   // direction
            return output;
        }
    }
}
