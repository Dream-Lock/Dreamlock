package com.dreamlock.game.states;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.combat.Combatant;

import java.io.Serializable;


public interface ICombatState extends Serializable {
    Integer doAction (IGameContext context);
    Integer doAction (IGameContext context, Combatant current, Combatant enemy);
}
