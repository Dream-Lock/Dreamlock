package com.dreamlock;

import com.dreamlock.game.IGameContext;
import com.dreamlock.models.Word;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler {
    private JsonObject parsedJsonObject;
    private IGameContext gameContext;

    public CommandHandler(JsonObject parsedJsonObject, IGameContext gameContext) {
        this.parsedJsonObject = parsedJsonObject;
        this.gameContext = gameContext;
    }

    public void handle() {
        JsonArray sentences = parsedJsonObject.getAsJsonArray("sentences");
        Word verb, noun, noun2,preposition;

        for (JsonElement sentence : sentences) {

            String rule = sentence.getAsJsonArray().get(sentence.getAsJsonArray().size()-1).getAsJsonObject().get("rule").getAsString();

            Map<Integer,Word> words = new HashMap<>();
            for (int i=0; i<sentence.getAsJsonArray().size()-1; i++) {
                JsonElement jsonElement = sentence.getAsJsonArray().get(i);
                words.put(i+1, new Word(jsonElement.getAsJsonObject().get("description").getAsString(),
                                        jsonElement.getAsJsonObject().get("word").getAsString()) );
            }
                switch(rule) {
                    case "V1":
                        verb = words.get(1);
                        execute(verb.getDescription());
                        break;
                    case "V2":
                        verb = words.get(1);
                        noun = words.get(2);
                        execute(verb.getDescription(), noun.getDescription());
                        break;
                    case "V3":
                        verb = words.get(1);
                        noun = words.get(2);
                        preposition = words.get(3);
                        execute(verb.getDescription(),preposition.getDescription(), noun.getDescription());
                        break;
                    case "V4":
                        verb = words.get(1);
                        noun = words.get(2);
                        preposition = words.get(3);
                        noun2 = words.get(4);

                        execute(verb.getDescription(),noun.getDescription(), preposition.getDescription(), noun2.getDescription());
                        break;
                    case "N3":
                        noun = words.get(2);
                        execute(noun.getDescription());
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

    private void execute(String verbDescription, String nounDescription1, String prepositionDescription, String nounDescription2 ) {
        System.out.println(verbDescription);
    }
}
