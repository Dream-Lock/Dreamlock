package com.dreamlock.game;

import com.dreamlock.game.models.History;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;

import java.io.Serializable;
import java.util.Map;

public class GameContext implements IGameContext, Serializable {
    private Room currentRoom;
    private Player player;
    private History history;

    public GameContext(Map<Integer, Room> rooms, Player player) {
        this.player = player;
        this.currentRoom = rooms.get(1);
        this.history = new History();
    }

    @Override
    public History getHistory() {
        return history;
    }

    @Override
    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public Room getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
}
