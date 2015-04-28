package com.dreamlock.game;

import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;

public interface IGameContext {
    Room getCurrentRoom();

    void setCurrentRoom(String direction);

    Player getPlayer();
}