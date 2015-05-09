package com.dreamlock.game.combat;


import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Enemy;
import com.dreamlock.game.models.Room;

import java.util.ArrayList;
import java.util.List;

public class TurnBattle {
    private List<Combatant> characters;
    private int currentChar;
    private boolean inCombat;

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
            characters.get(0).getStates().get("Attack").doAction(gameContext, characters.get(currentChar) , characters.get(0));
            output.add(10002);
            output.add(((Enemy) (characters.get(currentChar))).getId());
            output.add(1302);
            output.add(gameContext.getPlayer().getHealth());
        }

        return output;
    }

    public List<Integer> nextTurn(IGameContext gameContext){
        if(currentChar != characters.size()-1) {
            currentChar++;
            while(!characters.get(currentChar).isAlive() && currentChar !=characters.size()-1)
                currentChar++;
            if (characters.get(currentChar).isAlive())
                return executeTurn(gameContext);
        }



        currentChar = 0;
        return null;
    }

    public void LetTheBattleBegin(){
        inCombat =true;
    }

    public boolean activeBattle(){
        return inCombat;
    }

}
