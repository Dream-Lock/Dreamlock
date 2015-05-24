package com.dreamlock.messageSystem;

import com.dreamlock.game.jsonParser.items.Consumable;
import com.dreamlock.game.jsonParser.items.Container;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Door;
import com.dreamlock.game.models.Enemy;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;

import java.util.HashMap;
import java.util.Map;

public class GameMessages {
    private Map<Integer,IMessage> gameMessages;

    public GameMessages(Player player, Map<Integer, Room> rooms) {
        gameMessages = new HashMap<>();
        for (int i = 1 ; i < rooms.size() ; i++ ) {
            Room room = rooms.get(i);
            gameMessages.put(room.getId(), new NDMessage(room.getTitle(), room.getDescription()));
            for (Item item : room.getItems()) {
                try {
                    if (item.getType().equals("Container")) {
                        Container container = (Container) item;
                        for (Item containerItem : container.getItems()) {
                            if (item.getType().equals("Consumable")) {
                                Consumable consumableItem = (Consumable) containerItem;
                                gameMessages.put(consumableItem.getId(), new NDEMessage(consumableItem.getName(), consumableItem.getDescription(), consumableItem.getEffect()));
                            } else {
                                gameMessages.put(containerItem.getId(), new NDMessage(containerItem.getName(), containerItem.getDescription()));
                            }
                        }
                    }
                    if (item.getType().equals("Consumable")) {
                        Consumable consumableItem = (Consumable) item;
                        gameMessages.put(consumableItem.getId(), new NDEMessage(consumableItem.getName(), consumableItem.getDescription(), consumableItem.getEffect()));
                    } else {
                        gameMessages.put(item.getId(), new NDMessage(item.getName(), item.getDescription()));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for(Enemy enemy: room.getEnemies()){
                gameMessages.put(enemy.getId(), new NDMessage(enemy.getName(), enemy.getDescription()));

            }
            for(Door door: room.getDoors()){
                gameMessages.put(door.getId(), new NDMessage(door.getName(), door.getDescription()));

            }
            gameMessages.put(player.getId(), new NDSMessage(player.getName(), "", player.getPlayerStatsMap()));
        }
    }

    public Map<Integer, IMessage> getGameMessages() {
        return gameMessages;
    }

    public void setGameMessages(Map<Integer, IMessage> gameMessages) {
        this.gameMessages = gameMessages;
    }
}
