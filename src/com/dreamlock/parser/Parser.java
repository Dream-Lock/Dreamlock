package com.dreamlock.parser;

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
            JsonObject tokenType = new JsonObject();
            JsonArray sentences = new JsonArray();

            for ( Lexeme lexeme : lexemes ) {
                JsonObject properties = new JsonObject();
                properties.addProperty("id", lexeme.getId());
                properties.addProperty("description", lexeme.getDescription());
                tokenType.add(lexeme.getTokenType().toString().toLowerCase(), properties);
            }
            tokenType.addProperty("rule",stringBuilder.toString().substring(0,2));
            sentences.add(tokenType);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("sentences", sentences);
            return jsonObject;
        }
        else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("Error","1");
            return jsonObject;
        }
    }
}
