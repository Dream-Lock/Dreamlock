package com.dreamlock.core.game.combat;


import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.models.Enemy;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Room;

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
        List<OutputMessage> output = new ArrayList<>();

        if(currentChar != 0){
            characters.get(0).getStates().get(ActionState.ATTACK).doAction(gameContext, characters.get(currentChar) , characters.get(0));
            output.add(new OutputMessage(10002));
            output.add(new OutputMessage(((Enemy) (characters.get(currentChar))).getId()));
            output.add(new OutputMessage(1302));
            output.add(new OutputMessage(gameContext.getPlayer().getHealth()));
            output.add(new OutputMessage(1308));

            if (gameContext.getPlayer().getHealth() <= 0) {
                output.add(new OutputMessage(10002));
                output.add(new OutputMessage(1303));
                gameContext.setGameRunning(false);
            }
        }

        return output;
    }

    public List<OutputMessage> nextTurn(IGameContext gameContext){
        if(enemiesAlive() && currentChar != characters.size()-1) {
            currentChar++;
            while(!characters.get(currentChar).isAlive() && currentChar !=characters.size()-1)
                currentChar++;
            if (characters.get(currentChar).isAlive())
                return executeTurn(gameContext);
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
            if (!characters.get(i).isAlive())
                deadCounter++;
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
