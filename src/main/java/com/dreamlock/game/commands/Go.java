package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Door;
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

        Room nextRoom = gameContext.getCurrentRoom().getExits().get(direction);
        Room currentRoom = gameContext.getCurrentRoom();
        List<Door> doors = currentRoom.getDoors();
        Door roomDoor = null;
        boolean isLocked = false;
        for (Door door : doors) {
            if (door.getDirection().equals(direction)) {
                if (door.isLocked()) {
                    isLocked = true;
                    roomDoor = door;
                }
            }
        }
        if(gameContext.getTurnBattle() == null || !gameContext.getTurnBattle().activeBattle()) {
            if (!nextRoom.getDescription().equals("wall") && !isLocked) { //If wall

                gameContext.setCurrentRoom(nextRoom);
                output.add(nextRoom.getId());
                return output;

            }
            else {
                output.add(10000);                  // print only titles

                if (isLocked) {
                    output.add(roomDoor.getId());
                    output.add(1122);                   // can not go to
                } else {
                    output.add(1001);                   // can not go to
                    output.add(words.get(2).getId());
                }
                ;   // direction
                return output;
            }
        }else{
            output.add(10000);
            output.add(1009);
            return output;
        }
    }
}
