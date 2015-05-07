package com.dreamlock.game.commands;


import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Enemy;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Attack implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();
        Integer enemiesFound = 0;
        Enemy foundEnemy = null;
        Word word = words.get(2);
        List<Enemy> enemies = gameContext.getCurrentRoom().getEnemies();

        for (Enemy enemy : enemies) {
            if (enemy.getName().toLowerCase().contains(word.getDescription())) {
                enemiesFound++;
                foundEnemy = enemy;
            }
        }

        output.add(10000);
        if (enemiesFound == 1) {
            if (foundEnemy.getHealth() > 0) {
                output.add((foundEnemy.getId()));
                //output.add(foundEnemy.doAction(gameContext, foundEnemy));
            }else if (foundEnemy.getHealth() <= 0) {
                output.add(1206);
            }
            return output;
        }

        output.add(1204);
        return output;
    }
}
