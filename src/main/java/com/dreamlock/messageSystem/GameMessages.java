package com.dreamlock.messageSystem;

import com.dreamlock.game.jsonParser.items.Container;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.*;

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
                if (item.getType().equals("Container")) {
                    Container container = (Container) item;
                    for (Item containerItem : container.getItems()) {
                        gameMessages.put(containerItem.getId(), new Message(containerItem.getName(), containerItem.getDescription()));
                    }
                }
                gameMessages.put(item.getId(), new Message(item.getName(), item.getDescription()));
            }
            for(Enemy enemy: room.getEnemies()){
                gameMessages.put(enemy.getId(), new Message(enemy.getName(), enemy.getDescription()));

            }
            for(Door door: room.getDoors()){
                gameMessages.put(door.getId(), new Message(door.getName(), door.getDescription()));

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
