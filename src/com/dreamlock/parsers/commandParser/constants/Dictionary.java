package com.dreamlock.parsers.commandParser.constants;

import com.dreamlock.parsers.commandParser.models.Lexeme;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Dictionary {

    INSTANCE;

    Dictionary() {
        dictionary = new HashMap<>();

        /** VERBS **/
        dictionary.put("look", new Lexeme(1, "look", TokenType.VERB));
        dictionary.put("quit", new Lexeme(1, "quit", TokenType.VERB));
        dictionary.put("exit", new Lexeme(1, "exit", TokenType.VERB));
        dictionary.put("help", new Lexeme(1, "help", TokenType.VERB));

        dictionary.put("go", new Lexeme(2, "go", TokenType.VERB));
        dictionary.put("walk", new Lexeme(2, "walk", TokenType.VERB));

        dictionary.put("examine", new Lexeme(3, "examine", TokenType.VERB));
        dictionary.put("take", new Lexeme(3, "take", TokenType.VERB));
        dictionary.put("drop", new Lexeme(3, "drop", TokenType.VERB));

        dictionary.put("open", new Lexeme(4, "open", TokenType.VERB));

        /** CONJUCTIONS **/
        dictionary.put("and", new Lexeme(1, "and", TokenType.CONJUNCTION));

        /** PREPOSITIONS **/
        dictionary.put("under", new Lexeme(1, "under", TokenType.PREPOSITION));
        dictionary.put("in", new Lexeme(2, "in", TokenType.PREPOSITION));
        dictionary.put("on", new Lexeme(3, "on", TokenType.PREPOSITION));
        dictionary.put("with", new Lexeme(4, "with", TokenType.PREPOSITION));

        /** NOUNS **/
        dictionary.put("west", new Lexeme(1, "west", TokenType.NOUN));
        dictionary.put("north", new Lexeme(1, "north", TokenType.NOUN));
        dictionary.put("east", new Lexeme(1, "east", TokenType.NOUN));
        dictionary.put("south", new Lexeme(1, "south", TokenType.NOUN));
    }

    private final Map<String, Lexeme> dictionary;

    public Lexeme getLexeme(String possibleLexeme) {
        if (dictionary.containsKey(possibleLexeme)) {
            return dictionary.get(possibleLexeme);
        }
        else {
            return new Lexeme(1, possibleLexeme, TokenType.UNKNOWN);
        }
    }

    public Set<String> getDefinedGrammarKeys() {
        return dictionary.keySet();
    }
}
