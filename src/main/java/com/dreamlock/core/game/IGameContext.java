package com.dreamlock.core.game;

import com.dreamlock.core.game.combat.TurnBattle;
import com.dreamlock.core.game.models.History;
import com.dreamlock.core.game.models.Player;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.message_system.IMessageHandler;

public interface IGameContext {
    Room getCurrentRoom();

    void setCurrentRoom(Room room);

    History getHistory();

    void setHistory(History history);

    Player getPlayer();
    
    void setPlayer(Player player);

    TurnBattle getTurnBattle();

    boolean gameIsRunning ();

    void setGameRunning (boolean running);

    void setMessageHandler(IMessageHandler messageHandler);

    void registerMessage(String s, int id);
}
