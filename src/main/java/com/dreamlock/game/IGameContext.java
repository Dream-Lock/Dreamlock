package com.dreamlock.game;

import com.dreamlock.game.models.History;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;

import java.io.Serializable;

public interface IGameContext extends Serializable {
    Room getCurrentRoom();

    void setCurrentRoom(Room room);

    History getHistory();

    void setHistory(History history);

    Player getPlayer();

    void setPlayer(Player player);
}
