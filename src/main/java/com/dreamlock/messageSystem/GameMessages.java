package com.dreamlock.messageSystem;

import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Message;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;

import java.util.HashMap;
import java.util.Map;

public class GameMessages {
    private Map<Integer,Message> gameMessages;

    public GameMessages(Player gameContext, Map<Integer, Room> rooms) {
        gameMessages = new HashMap<>();
        for (int i = 1 ; i < rooms.size() ; i++ ) {
            Room room = rooms.get(i);
            gameMessages.put(room.getId(), new Message(room.getTitle(), room.getDescription()));
            for (Item item : room.getItems()) {
                gameMessages.put(item.getId(), new Message(item.getName(), item.getDescription()));
            }
        }
    }

    public Map<Integer, Message> getGameMessages() {
        return gameMessages;
    }

    public void setGameMessages(Map<Integer, Message> gameMessages) {
        this.gameMessages = gameMessages;
    }
}
