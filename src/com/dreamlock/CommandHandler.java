package com.dreamlock;

import com.dreamlock.game.IGameContext;
import com.google.gson.JsonObject;

public class CommandHandler {
    private JsonObject parsedJsonObject;
    private IGameContext gameContext;

    public CommandHandler(JsonObject parsedJsonObject, IGameContext gameContext) {
        this.parsedJsonObject = parsedJsonObject;
        this.gameContext = gameContext;
    }

    public void handle() {

//        switch() {
//
//        }
    }
}
