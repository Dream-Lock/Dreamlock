package com.dreamlock.game.states.combatStates;


import com.dreamlock.game.IGameContext;
import com.dreamlock.game.combat.Combatant;
import com.dreamlock.game.states.ICombatState;

public class CanAttackState implements ICombatState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Combatant combatant) {
        //enemy.setHealth(enemy.getHealth() - context.getPlayer().getAttack());
        combatant.setHealth(combatant.getHealth() - (combatant.getDefense() - context.getPlayer().getAttack()));
        context.getPlayer().setHealth(context.getPlayer().getHealth() - (combatant.getAttack() - context.getPlayer().getDefense()));
        return 1201;
    }

}
