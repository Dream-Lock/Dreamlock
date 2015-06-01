package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Enemy;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Look implements ICommand  {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        Room room = gameContext.getCurrentRoom();

        outputMessages.add(new OutputMessage(room.getId()));

        // Print doors
        Map<String,Room> exits = gameContext.getCurrentRoom().getExits();
        if (exits.size() == 1) {
            outputMessages.add(new OutputMessage(1804));
        }
        else {
            outputMessages.add(new OutputMessage(1805));
        }
        int id = 9200;
        for (Map.Entry<String,Room> door : exits.entrySet()) {
            if (!door.getValue().getTitle().equals("wall")) {
                gameContext.registerMessage("on your " + door.getKey(), id);
                outputMessages.add(new OutputMessage(id));
                id++;
            }
        }

        // Print items
        List<Item> items = gameContext.getCurrentRoom().getItems();
        int numberOfItems = 0;
        for (Item item : items) {
            if (!item.getType().equals(ItemType.MISC)) {
                numberOfItems++;
            }
        }
        if (numberOfItems == 1) {
            outputMessages.add(new OutputMessage(1800));
        }
        else if (numberOfItems > 1) {
            outputMessages.add(new OutputMessage(1801));
        }
        for (Item item : items) {
            if (!item.getType().equals(ItemType.MISC)) {
                outputMessages.add(new OutputMessage(item.getId()));
            }
        }

        // Print Enemies
        List<Enemy> enemies = gameContext.getCurrentRoom().getEnemies();
        Boolean isAlive = false;
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                isAlive = true;
            }
        }
        if (enemies.size() == 1 && isAlive) {
            outputMessages.add(new OutputMessage(1802));
        }
        else if (enemies.size() > 1 && isAlive) {
            outputMessages.add(new OutputMessage(1803));
        }
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                outputMessages.add(new OutputMessage(enemy.getId()));
            }
        }

        return outputMessages;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
