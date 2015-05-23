package com.dreamlock.game.combat;


import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.models.Enemy;
import com.dreamlock.game.models.Room;

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

    public List<Integer> executeTurn(IGameContext gameContext){
        List<Integer> output = new ArrayList<>();

        if(currentChar != 0){
            characters.get(0).getStates().get(ActionState.ATTACK).doAction(gameContext, characters.get(currentChar) , characters.get(0));
            output.add(10002);
            output.add(((Enemy) (characters.get(currentChar))).getId());
            output.add(1302);
            output.add(gameContext.getPlayer().getHealth());
            output.add(1308);

            if (gameContext.getPlayer().getHealth() <= 0) {
                output.add(10002);
                output.add(1303);
                gameContext.setGameRunning(false);
            }
        }

        return output;
    }

    public List<Integer> nextTurn(IGameContext gameContext){
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
