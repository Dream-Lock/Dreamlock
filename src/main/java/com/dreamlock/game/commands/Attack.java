package com.dreamlock.game.commands;


import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ActionState;
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

        if(enemies.size() > 0 && gameContext.getTurnBattle().enemiesAlive())
            gameContext.getTurnBattle().letTheBattleBegin(); // activate turn system

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
                output.add(foundEnemy.getStates().get(ActionState.ATTACK).doAction(gameContext, gameContext.getPlayer(), foundEnemy));
                output.add(1309);
                if (!foundEnemy.isAlive()) {
                    output.add(10002);
                    output.add(foundEnemy.getId());
                    output.add(1307);
                }

            }else if (foundEnemy.getHealth() <= 0) {
                output.add(1306);
            }
            if(gameContext.getTurnBattle().activeBattle()) {

                List<Integer> templist = gameContext.getTurnBattle().nextTurn(gameContext);
                while (gameContext.getTurnBattle().activeBattle() && templist != null) {
                    output.addAll(templist);
                    templist = gameContext.getTurnBattle().nextTurn(gameContext);
                }

                if (!gameContext.getTurnBattle().activeBattle()) {
                    output.add(10002);
                    output.add(1310);
                }
            }
            return output;
        }

        output.add(1305);
        return output;
    }
}
