package com.dreamlock.core.game.states;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.combat.Combatant;

import java.io.Serializable;


public interface ICombatState extends Serializable {
    Integer doAction (IGameContext context);
    Integer doAction (IGameContext context, Combatant current, Combatant enemy);
}
