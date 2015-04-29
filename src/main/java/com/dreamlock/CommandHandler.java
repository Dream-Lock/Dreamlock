package com.dreamlock;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.commands.ICommand;
import com.dreamlock.game.constants.Commands;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;
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
    private List<Word> dictionary;

    public CommandHandler(JsonObject parsedJsonObject, IGameContext gameContext) {
        this.parsedJsonObject = parsedJsonObject;
        this.gameContext = gameContext;
        buildGameDictionary();
    }

    private void buildGameDictionary() {
        dictionary = new ArrayList<>();
        List<Item> items = gameContext.getCurrentRoom().getItems();
        List<Item> inventoryItems = gameContext.getPlayer().getInventory().getItems();
        items.addAll(inventoryItems);

        for(Item item : items) {
            dictionary.add(new Word(item.getName().toLowerCase(), "unknown", item.getId()));
        }
    }

    public List<Integer> handle() {
        List<Integer> messageIds = new ArrayList<>();

        JsonArray sentences = parsedJsonObject.getAsJsonArray("sentences");
        Word firstWord;

        for (JsonElement sentence : sentences) {
            String rule = sentence.getAsJsonArray().get(sentence.getAsJsonArray().size() - 1).getAsJsonObject().get("rule").getAsString();

            Map<Integer, Word> words = new HashMap<>();
            int mapSize = sentence.getAsJsonArray().size() - 1;

            for (int i = 0 ; i < mapSize ; i++) {
                JsonElement jsonElement = sentence.getAsJsonArray().get(i);
                words.put(i + 1, new Word(jsonElement.getAsJsonObject().get("description").getAsString(),
                        jsonElement.getAsJsonObject().get("word").getAsString(),
                        jsonElement.getAsJsonObject().get("uniqueId").getAsInt() ));
            }

            messageIds.add(checkGameDictionary(words));

            if (messageIds.get(messageIds.size()-1) == 0) {
                Commands commands = Commands.INSTANCE;
                firstWord = words.get(1);
                ICommand command = commands.getCommand(firstWord.getDescription());
                List<Integer> messageId = new ArrayList<>();
                switch (rule) {
                    case "V1":                                          // Syntax: Verb
                        messageId = command.execute(gameContext);
                        messageIds.addAll(messageId);
                        break;
                    case "V2":                                          // Syntax: Verb, Direction(Noun)
                        messageId = command.execute(gameContext, words);
                        messageIds.addAll(messageId);
                        break;
                    case "V3":                                          // Syntax: Verb, Item(Noun)
                        messageId = command.execute(gameContext, words);
                        messageIds.addAll(messageId);
                        break;
                    case "V4":                                          // Syntax: Verb, Item(Noun), Preposition, Item(Noun)
                        messageId = command.execute(gameContext, words);
                        messageIds.addAll(messageId);
                        break;
                    case "N3":                                          // Syntax: Noun(command)
                        messageId = command.execute(gameContext);
                        messageIds.addAll(messageId);
                        break;
                    default:
                        Integer errorMessageId = -1;
                        messageIds.add(errorMessageId);
                        break;
                }
            }
        }

        return messageIds;
    }

    private int checkGameDictionary(Map<Integer, Word> words) {
        int result = 0;
        boolean found = true;
        for (int i = 1 ; i <= words.size() ; i++) {
            Word word = words.get(i);
            if (word.getType().equals("unknown")) {
                found = false;
                for (Word gameWord : dictionary) {
                    if (word.getDescription().equals(gameWord.getDescription().toLowerCase())) {
                        found  = true;
                    }
                }
            }
        }

        if (found) return result;
        else return -1;
    }
}