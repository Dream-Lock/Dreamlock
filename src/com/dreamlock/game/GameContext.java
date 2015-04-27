package com.dreamlock.game;

import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;

public class GameContext implements IGameContext {
    private Room currentRoom;
    private Player player;

    @Override
    public Room getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public void setCurrentRoom(String direction) {
        currentRoom = currentRoom.getExits().get(direction);
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
