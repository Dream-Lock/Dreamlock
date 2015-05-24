package com.dreamlock.handlers;

import com.dreamlock.game.IGameContext;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ErrorHandler implements IHandler {
    private JsonObject parsedJsonObject;
    private IGameContext gameContext;

    public ErrorHandler(JsonObject parsedJsonObject, IGameContext gameContext) {
        this.parsedJsonObject = parsedJsonObject;
        this.gameContext = gameContext;
    }

    @Override
    public List<Integer> handle() {
        List<Integer> messageIds = new ArrayList<>();

        Boolean hasForbiddenWords = parsedJsonObject.get("forbidden").getAsBoolean();
        messageIds.add(10000); // only title

        if (hasForbiddenWords) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(5);
            switch (randomNumber) {
                case 1:
                    messageIds.add(2107);
                    break;
                case 2:
                    messageIds.add(2106);
                    break;
                case 3:
                    messageIds.add(2105);
                    break;
                case 4:
                    messageIds.add(2104);
                    break;
                default:
                    messageIds.add(2103);
            }
            return messageIds;
        }

        Integer correctWords = parsedJsonObject.get("correctWords").getAsInt();

        if (correctWords == 0) {
            messageIds.add(2101);
        } else {
            String correctCommand = parsedJsonObject.get("correctCommand").getAsString();

            if (correctWords == 100) {
                if (correctCommand.equals("go")) {
                    messageIds.add(1008);
                } else {
                    gameContext.registerMessage("What do you want me to " + correctCommand, 9001);
                    messageIds.add(9001);
                }
            } else {
                gameContext.registerMessage("I understood as far as wanting me to " + correctCommand, 9001);
                messageIds.add(9001);
            }
        }
        return messageIds;
    }
}
