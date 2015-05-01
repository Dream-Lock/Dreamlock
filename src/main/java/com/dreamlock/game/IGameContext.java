package com.dreamlock.game;

import com.dreamlock.game.models.History;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;

public interface IGameContext {
    Room getCurrentRoom();

    void setCurrentRoom(Room room);

    History getHistory();

    void setHistory(History history);

    Player getPlayer();
}
