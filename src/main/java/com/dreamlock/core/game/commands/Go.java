package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
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
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        String direction = words.get(Sequence.SECOND).getDescription();

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
            if (!nextRoom.getDescription().equals("wall") && !isLocked) {
                gameContext.setCurrentRoom(nextRoom);
                Look look = new Look();
                return look.execute(gameContext);
            }
            else {
                if (isLocked) {
                    outputMessages.add(new OutputMessage(roomDoor.getId(), PrintStyle.ONLY_DESCRIPTION_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(1152, PrintStyle.ONLY_TITLE));
                }
                else {
                    outputMessages.add(new OutputMessage(1001, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(words.get(Sequence.SECOND).getId(), PrintStyle.ONLY_TITLE));
                }
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                return outputMessages;
            }
        }else{
            outputMessages.add(new OutputMessage(1009, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }
    }
}
