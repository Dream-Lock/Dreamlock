package com.dreamlock.parser;

import com.dreamlock.parser.constants.Rules;
import com.dreamlock.parser.constants.TokenType;
import com.dreamlock.parser.models.Lexeme;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public JsonObject parse(List<Lexeme> lexemes) {
        Rules rules = Rules.INSTANCE;

        lexemes = connectUnknownLexemes(lexemes);

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
                word.addProperty("uniqueId", lexeme.getUniqueId());
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

    private List<Lexeme> connectUnknownLexemes(List<Lexeme> lexemes) {
        List<Lexeme> filteredLexemes = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        Boolean flag = false;

        for (int i = 0 ; i < lexemes.size() ; i++) {
            Lexeme lexeme = lexemes.get(i);

            if (lexeme.getTokenType().toString().equals("UNKNOWN")) {
                stringBuilder.append(lexeme.getTypedString());
                flag = true;
                if (i == (lexemes.size()-1)) {
                    filteredLexemes.add(new Lexeme(1, stringBuilder.toString(), TokenType.UNKNOWN, 0));
                }
                else {
                    stringBuilder.append(" ");
                }
            }
            else {
                if (flag) {
                    stringBuilder.setLength(stringBuilder.length() - 1);
                    filteredLexemes.add(new Lexeme(1, stringBuilder.toString(), TokenType.UNKNOWN, 0));
                    stringBuilder = new StringBuilder();
                    filteredLexemes.add(lexeme);
                }
                else {
                    filteredLexemes.add(lexeme);
                }
                flag = false;
            }
        }

        return filteredLexemes;
    }
}
