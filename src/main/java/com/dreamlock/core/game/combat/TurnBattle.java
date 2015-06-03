package com.dreamlock.core.game.combat;


import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.models.Enemy;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TurnBattle implements Serializable{
    private List<Combatant> characters;
    private int currentChar;
    protected boolean inCombat;

    public TurnBattle(IGameContext gameContext, Room room){
        characters = new ArrayList<>();
        inCombat = false;
        currentChar = 0; // player first
        characters.add(gameContext.getPlayer());
        characters.addAll(room.getEnemies());
    }

    public List<OutputMessage> executeTurn(IGameContext gameContext){
        List<OutputMessage> outputMessages = new ArrayList<>();

        if(currentChar != 0) {
            characters.get(0).getStates().get(ActionState.ATTACK).doAction(gameContext, characters.get(currentChar), characters.get(0));
            outputMessages.add(new OutputMessage(((Enemy) (characters.get(currentChar))).getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            outputMessages.add(new OutputMessage(1302, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            outputMessages.add(new OutputMessage(gameContext.getPlayer().getHealth(), PrintStyle.NUMBER));
            outputMessages.add(new OutputMessage(1308, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));

            if (gameContext.getPlayer().getHealth() <= 0) {
                outputMessages.add(new OutputMessage(1303, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                gameContext.setGameRunning(false);
            }
        }

        return outputMessages;
    }

    public List<OutputMessage> nextTurn(IGameContext gameContext) {
        if(enemiesAlive() && currentChar != characters.size()-1) {
            currentChar++;
            while(!characters.get(currentChar).isAlive() && currentChar !=characters.size()-1) {
                currentChar++;
            }
            if (characters.get(currentChar).isAlive()) {
                return executeTurn(gameContext);
            }
        }
        currentChar = 0;
        return null;
    }

    public void letTheBattleBegin(){
        inCombat = true;
    }

    public boolean activeBattle(){
        return inCombat;
    }

    public boolean enemiesAlive(){
        int deadCounter = 0;
        for(int i = 1; i<characters.size(); i++){
            if (!characters.get(i).isAlive()) {
                deadCounter++;
            }
        }

        if (deadCounter == characters.size()-1){
            inCombat = false;
            return false;
        }

        return true;
    }

    public boolean fledFromBattle() {
        inCombat = false;
        return true;
    }
}
