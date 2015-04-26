package com.dreamlock;

import com.dreamlock.game.IGameContext;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CommandHandler {
    private JsonObject parsedJsonObject;
    private IGameContext gameContext;

    public CommandHandler(JsonObject parsedJsonObject, IGameContext gameContext) {
        this.parsedJsonObject = parsedJsonObject;
        this.gameContext = gameContext;
    }

    public void handle() {
        JsonArray sentences = parsedJsonObject.getAsJsonArray("sentences");

        for (JsonElement sentence : sentences) {
            String rule = sentence.getAsJsonObject().getAsJsonPrimitive("rule").getAsString();

            JsonObject verb = sentence.getAsJsonObject().getAsJsonObject("verb");
//            JsonArray noun = sentence.getAsJsonObject().getAsJsonArray("noun");
//            JsonObject preposition = sentence.getAsJsonObject().getAsJsonObject("preposition");

            switch(rule) {
                case "V1":
                    execute(verb.get("description").getAsString());
                    break;
                case "V2":
//                    execute(verb.get("description").getAsString(), noun.get("description").getAsString());
                    break;
                case "V3":
//                    execute(verb.get("description").getAsString(), preposition.get("description").getAsString(), noun.get("description").getAsString());
                    break;
                case "V4":
                    break;
                case "N3":
                    break;
                default:
                    break;
            }
        }
    }

    private void execute(String verbDescription) {
        System.out.println(verbDescription);
    }

    private void execute(String verbDescription,String nounDescription) {
        System.out.println(verbDescription);
    }

    private void execute(String verbDescription, String prepositionDescription, String nounDescription) {
        System.out.println(verbDescription);
    }
}
