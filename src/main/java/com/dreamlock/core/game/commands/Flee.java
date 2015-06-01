package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Flee implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        if (gameContext.getTurnBattle() != null && gameContext.getTurnBattle().activeBattle()) {

            Random rand = new Random();
            int randomNumber = rand.nextInt(10);

            if (randomNumber > 3) {

                gameContext.getTurnBattle().fledFromBattle();
                outputMessages.add(new OutputMessage(1701));
            }
            else {
                outputMessages.add(new OutputMessage(1702));
                if(gameContext.getTurnBattle().activeBattle()) {

                    List<OutputMessage> templist = gameContext.getTurnBattle().nextTurn(gameContext);
                    while (gameContext.getTurnBattle().activeBattle() && templist != null) {
                        outputMessages.addAll(templist);
                        templist = gameContext.getTurnBattle().nextTurn(gameContext);
                    }
                }
            }
        }
        else {
            outputMessages.add(new OutputMessage(1703));
        }

        return outputMessages;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}