package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Go implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();
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
                outputMessages.add(new OutputMessage(nextRoom.getId(), PrintStyle.TITLE_DESCRIPTION));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                return outputMessages;

            }
            else {

                if (isLocked) {
                    outputMessages.add(new OutputMessage(roomDoor.getId(), PrintStyle.ONLY_DESCRIPTION_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(1122, PrintStyle.ONLY_TITLE));                   // can not go to
                } else {
                    outputMessages.add(new OutputMessage(1001, PrintStyle.ONLY_TITLE_IN_SAME_LINE));                   // can not go to
                    outputMessages.add(new OutputMessage(words.get(2).getId(), PrintStyle.ONLY_TITLE));
                }
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                ;   // direction
                return outputMessages;
            }
        }else{
            outputMessages.add(new OutputMessage(1009, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }
    }
}
