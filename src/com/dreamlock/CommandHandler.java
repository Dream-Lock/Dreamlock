package com.dreamlock;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;
import com.dreamlock.parsers.jsonParser.items.Item;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

public class CommandHandler {
    private JsonObject parsedJsonObject;
    private IGameContext gameContext;
    private List<Word> dictionary;

    public CommandHandler(JsonObject parsedJsonObject, IGameContext gameContext) {
        this.parsedJsonObject = parsedJsonObject;
        this.gameContext = gameContext;
//        buildGameDictionary();
    }

    private void buildGameDictionary() {
        dictionary = new ArrayList<>();
        List<Item> items = gameContext.getCurrentRoom().getItems();
        List<Item> inventoryItems = gameContext.getPlayer().getInventory().getItems();
        items.addAll(inventoryItems);

        for(Item item : items) {
            dictionary.add(new Word(item.getDescription(), "unknown"));
        }
    }

    public int handle() {
        int result = 0;
        JsonArray sentences = parsedJsonObject.getAsJsonArray("sentences");
        Word verb, noun, noun2, preposition;

        for (JsonElement sentence : sentences) {
            String rule = sentence.getAsJsonArray().get(sentence.getAsJsonArray().size() - 1).getAsJsonObject().get("rule").getAsString();

            Map<Integer, Word> words = new HashMap<>();
            int mapSize = sentence.getAsJsonArray().size() - 1;

            for (int i = 0 ; i < mapSize ; i++) {
                JsonElement jsonElement = sentence.getAsJsonArray().get(i);
                words.put(i + 1, new Word(jsonElement.getAsJsonObject().get("description").getAsString(),
                        jsonElement.getAsJsonObject().get("word").getAsString()));
            }

//            result = checkGameDictionary(words);

            if (result == 0) {
                switch (rule) {
                    case "V1":                                          // Syntax: Verb
                        verb = words.get(1);
                        result = execute(verb.getDescription());
                        break;
                    case "V2":                                          // Syntax: Verb, Direction(Noun)
                        verb = words.get(1);
                        noun = words.get(2);
                        result = execute(verb.getDescription(), noun.getDescription());
                        break;
                    case "V3":                                          // Syntax: Verb, Item(Noun)
                        verb = words.get(1);
                        noun = words.get(2);
                        result = execute(verb.getDescription(), noun.getDescription());
                        break;
                    case "V4":                                          // Syntax: Verb, Item(Noun), Preposition, Item(Noun)
                        verb = words.get(1);
                        noun = words.get(2);
                        preposition = words.get(3);
                        noun2 = words.get(4);
                        result = execute(verb.getDescription(), noun.getDescription(), preposition.getDescription(), noun2.getDescription());
                        break;
                    case "N3":                                          // Syntax: Noun(command)
                        noun = words.get(2);
                        result = execute(noun.getDescription());
                        break;
                    default:
                        result = -1;
                        break;
                }
            }
        }

        return result;
    }

    private int checkGameDictionary(Map<Integer, Word> words) {
        int result = 0;
        for (int i = 1 ; i <= words.size() ; i++) {
            Word word = words.get(i);
            if (word.getType().equals("unknown")) {
                for (Word gameWord : dictionary) {
                    if (!word.getDescription().equals(gameWord.getDescription())) {
                        result = -1;
                    }
                }
            }
        }

        return result;
    }

    private int execute(String verbDescription) {
        System.out.println(verbDescription);
        return 1;
    }

    private int execute(String verbDescription,String nounDescription) {
        System.out.println(verbDescription);
        return 1;
    }

    private int execute(String verbDescription, String prepositionDescription, String nounDescription) {
        System.out.println(verbDescription);
        return 1;
    }

    private int execute(String verbDescription, String nounDescription1, String prepositionDescription, String nounDescription2 ) {
        System.out.println(verbDescription);
        return 1;
    }
}