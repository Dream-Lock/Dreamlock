package com.dreamlock.game;

import com.dreamlock.game.combat.TurnBattle;
import com.dreamlock.game.models.History;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;
import com.dreamlock.messageSystem.MessageHandler;

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

    void setMessageHandler(MessageHandler messageHandler);

    void registerMessage(String s, int id);
}
