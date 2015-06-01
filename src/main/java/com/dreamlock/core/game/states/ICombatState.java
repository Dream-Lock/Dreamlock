package com.dreamlock.core.game.states;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.combat.Combatant;
import com.dreamlock.core.game.models.OutputMessage;

import java.io.Serializable;


public interface ICombatState extends Serializable {
    OutputMessage doAction (IGameContext context);
    OutputMessage doAction (IGameContext context, Combatant current, Combatant enemy);
}
