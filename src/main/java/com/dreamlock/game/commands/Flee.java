package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Flee implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();

        if (gameContext.getTurnBattle() != null && gameContext.getTurnBattle().activeBattle()) {

            Random rand = new Random();
            int randomNumber = rand.nextInt(10);
            output.add(10000);
            if (randomNumber > 3) {

                gameContext.getTurnBattle().fledFromBattle();
                output.add(1701);
            }
            else {
                output.add(1702);
                if(gameContext.getTurnBattle().activeBattle()) {

                    List<Integer> templist = gameContext.getTurnBattle().nextTurn(gameContext);
                    while (gameContext.getTurnBattle().activeBattle() && templist != null) {
                        output.addAll(templist);
                        templist = gameContext.getTurnBattle().nextTurn(gameContext);
                    }
                }
            }
        }
        else {
            output.add(1703);
        }

        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}