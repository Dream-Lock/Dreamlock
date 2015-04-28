package com.dreamlock.game;

import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;

import java.util.Map;

public class GameContext implements IGameContext {
    private Room currentRoom;
    private Player player;

    public GameContext(Map<Integer, Room> rooms, Player player) {
        this.player = player;
        this.currentRoom = rooms.get(1);
    }

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
