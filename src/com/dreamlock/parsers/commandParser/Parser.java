package com.dreamlock.parsers.commandParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by Odin on 25/4/2015.
 */
public class Parser {
    public JsonObject parse(List<Lexeme> lexemes) {
        Rules rules = Rules.INSTANCE;

        StringBuilder stringBuilder = new StringBuilder();
        for (Lexeme lexeme : lexemes) {
            stringBuilder.append(lexeme.getTokenType().toString().charAt(0));
            stringBuilder.append(Integer.toString(lexeme.getId()));
        }

        if (rules.getRule(stringBuilder.toString())) {
            JsonObject rule = new JsonObject();
            JsonArray sentences = new JsonArray();
            JsonArray sentence = new JsonArray();

            for ( Lexeme lexeme : lexemes ) {
                JsonObject word = new JsonObject();
                word.addProperty("word", lexeme.getTokenType().toString().toLowerCase());
                word.addProperty("id", lexeme.getId());
                word.addProperty("description", lexeme.getTypedString());
                sentence.add(word);
            }

            rule.addProperty("rule", stringBuilder.toString().substring(0, 2));
            sentence.add(rule);

            sentences.add(sentence);

            JsonObject output = new JsonObject();
            output.add("sentences", sentences);
            return output;
        }
        else {
            JsonObject output = new JsonObject();
            output.addProperty("Error", "1");
            return output;
        }
    }
}
