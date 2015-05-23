package com.dreamlock.game;

import com.dreamlock.game.combat.TurnBattle;
import com.dreamlock.game.models.History;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;
import com.dreamlock.messageSystem.MessageHandler;

import java.io.Serializable;
import java.util.Map;

public class GameContext implements IGameContext, Serializable {
    private Room currentRoom;
    private Player player;
    private History history;
    private TurnBattle turnbattle;
    private MessageHandler messageHandler;
    boolean gameRunning;

    public GameContext(Map<Integer, Room> rooms, Player player) {
        this.player = player;
        this.currentRoom = rooms.get(1);
        this.history = new History();
        this.gameRunning = true;
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
        turnbattle = new TurnBattle(this, currentRoom);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public TurnBattle getTurnBattle() {
        return turnbattle;
    }

    @Override
    public boolean gameIsRunning() {
        return gameRunning;
    }

    @Override
    public void setGameRunning(boolean running) {
        this.gameRunning = running;
    }

    @Override
    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void registerMessage(String string, int id) {
        messageHandler.registerString(string, id);
    }
}
