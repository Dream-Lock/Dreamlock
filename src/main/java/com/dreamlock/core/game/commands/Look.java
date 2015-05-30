package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Enemy;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Look implements ICommand  {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();
        Room room = gameContext.getCurrentRoom();


        //output.add(10000);
        //output.add(10003);
        output.add(room.getId());

        // Print doors
        Map<String,Room> exits = gameContext.getCurrentRoom().getExits();
        if (exits.size() == 1) {
            //output.add(10000);
            output.add(1804);
        }
        else {
            //output.add(10002);
            output.add(1805);
        }
        int id = 9200;
        for (Map.Entry<String,Room> door : exits.entrySet()) {
            if (!door.getValue().getTitle().equals("wall")) {
                gameContext.registerMessage("on your " + door.getKey(), id);
                output.add(id);
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
            output.add(10002);
            output.add(1800);
        }
        else if (numberOfItems > 1) {
            output.add(10002);
            output.add(1801);
        }
        for (Item item : items) {
            output.add(10002);
            if (!item.getType().equals(ItemType.MISC)) {
                output.add(item.getId());
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
            output.add(10002);
            output.add(1802);
        }
        else if (enemies.size() > 1 && isAlive) {
            output.add(10002);
            output.add(1803);
        }
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                output.add(enemy.getId());
            }
        }

        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
