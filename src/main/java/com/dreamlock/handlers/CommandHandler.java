package com.dreamlock.handlers;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.commands.ICommand;
import com.dreamlock.game.constants.Commands;
import com.dreamlock.game.models.Word;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler implements IHandler {
    private JsonObject parsedJsonObject;
    private IGameContext gameContext;

    public CommandHandler(JsonObject parsedJsonObject, IGameContext gameContext) {
        this.parsedJsonObject = parsedJsonObject;
        this.gameContext = gameContext;
    }

    public List<Integer> handle() {
        List<Integer> messageIds = new ArrayList<>();


            JsonArray sentences = parsedJsonObject.getAsJsonArray("sentences");
            Word firstWord;

            for (JsonElement sentence : sentences) {
                String rule = sentence.getAsJsonArray().get(sentence.getAsJsonArray().size() - 1).getAsJsonObject().get("rule").getAsString();

                Map<Integer, Word> words = new HashMap<>();
                int mapSize = sentence.getAsJsonArray().size() - 1;

                for (int i = 0; i < mapSize; i++) {
                    JsonElement jsonElement = sentence.getAsJsonArray().get(i);
                    words.put(i + 1, new Word(jsonElement.getAsJsonObject().get("description").getAsString(),
                            jsonElement.getAsJsonObject().get("word").getAsString(),
                            jsonElement.getAsJsonObject().get("uniqueId").getAsInt()));
                }

                Commands commands = Commands.INSTANCE;
                firstWord = words.get(1);
                ICommand command = commands.getCommand(firstWord.getDescription());
                List<Integer> messageId;
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

        return messageIds;
    }
}