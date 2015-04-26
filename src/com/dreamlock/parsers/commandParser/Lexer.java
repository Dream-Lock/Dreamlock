package com.dreamlock.parsers.commandParser;

import com.dreamlock.parsers.commandParser.constants.Dictionary;
import com.dreamlock.parsers.commandParser.models.Lexeme;

import java.util.ArrayList;
import java.util.Objects;

public class Lexer {
    private Dictionary dictionary;

    public Lexer() {
        dictionary = Dictionary.INSTANCE;
    }

    public ArrayList<Lexeme> tokenize(String line) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        String input = line.toLowerCase();              // input line to lowerCase
        String[] tokens = input.trim().split("\\s");    // split input to array

        for (String token : tokens) {
            if (!Objects.equals(token, "")) {
                lexemes.add(dictionary.getLexeme(token));
            }
        }

        return lexemes;
    }
}
