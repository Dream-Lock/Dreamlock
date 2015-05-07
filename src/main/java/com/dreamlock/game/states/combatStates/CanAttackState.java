package com.dreamlock.game.states.combatStates;


import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Enemy;
import com.dreamlock.game.states.ICombatState;

public class CanAttackState implements ICombatState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Enemy enemy) {
        enemy.setHealth(enemy.getHealth() - (enemy.getDefense() - context.getPlayer().getAttack()));
        context.getPlayer().setHealth(context.getPlayer().getHealth() - (enemy.getAttack() - context.getPlayer().getDefense()));
        return 1201;
    }

}
