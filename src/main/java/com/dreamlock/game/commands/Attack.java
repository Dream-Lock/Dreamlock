package com.dreamlock.game.commands;


import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Enemy;
import com.dreamlock.game.models.Word;
import com.dreamlock.game.states.combatStates.CanAttackState;

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

        if(enemies.size() > 0)
            gameContext.getTurnBattle().LetTheBattleBegin(); // activate turn system

        for (Enemy enemy : enemies) {
            if (enemy.getName().toLowerCase().contains(word.getDescription())) {
                enemiesFound++;
                foundEnemy = enemy;
            }
        }

        output.add(10000);
        if (enemiesFound == 1) {
            if (foundEnemy.getHealth() > 0) {
                output.add(foundEnemy.getId());
                output.add(1301);
                output.add(foundEnemy.getStates().get("Attack").doAction(gameContext, gameContext.getPlayer() ,foundEnemy));
                if (!foundEnemy.isAlive()) {
                    output.add(10002);
                    output.add(foundEnemy.getId());
                    output.add(1307);
                }

            }else if (foundEnemy.getHealth() <= 0) {
                output.add(1306);
            }

            List<Integer> templist = gameContext.getTurnBattle().nextTurn(gameContext);
            while(templist != null){
                output.addAll(templist);
                templist = gameContext.getTurnBattle().nextTurn(gameContext);
            }

            return output;
        }

        output.add(1305);
        return output;
    }
}
