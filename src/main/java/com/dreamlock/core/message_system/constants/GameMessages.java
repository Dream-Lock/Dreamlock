package com.dreamlock.core.message_system.constants;

import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.Enemy;
import com.dreamlock.core.game.models.Player;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.message_system.messages.EffectMessage;
import com.dreamlock.core.message_system.messages.IMessage;
import com.dreamlock.core.message_system.messages.Message;
import com.dreamlock.core.story_parser.items.Consumable;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;

import java.util.HashMap;
import java.util.Map;

public class GameMessages {
    private Map<Integer,IMessage> gameMessages;

    public GameMessages(Player player, Map<Integer, Room> rooms) {
        gameMessages = new HashMap<>();

        for (int i = 1 ; i < rooms.size() ; i++ ) {
            Room room = rooms.get(i);
            gameMessages.put(room.getId(), new Message(room.getTitle(), room.getDescription()));

            for (Item item : room.getItems()) {
                try {
                    ItemType itemType = item.getType();
                    switch (itemType) {
                        case CONTAINER:
                            registerItem(item);
                            Container container = (Container) item;
                            for (Item containerItem : container.getItems()) {
                                registerItem(containerItem);
                            }
                            break;
                        case CONSUMABLE:
                            registerItem(item);
                            break;
                        default:
                            registerItem(item);
                            break;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for(Enemy enemy: room.getEnemies()){
                gameMessages.put(enemy.getId(), new Message(enemy.getName(), enemy.getDescription()));
            }

            for(Door door: room.getDoors()){
                gameMessages.put(door.getId(), new Message(door.getName(), door.getDescription()));
            }
        }
    }

    private void registerItem(Item item) {
        ItemType itemType = item.getType();
        if (itemType.equals(ItemType.CONSUMABLE)) {
            Consumable consumableItem = (Consumable) item;
            gameMessages.put(consumableItem.getId(),
                    new EffectMessage(consumableItem.getName(),
                            consumableItem.getDescription(), consumableItem.getEffect()));
        }
        else {
            gameMessages.put(item.getId(), new Message(item.getName(), item.getDescription()));
        }
    }

    public Map<Integer, IMessage> getGameMessages() {
        return gameMessages;
    }

    public void setGameMessages(Map<Integer, IMessage> gameMessages) {
        this.gameMessages = gameMessages;
    }
}
