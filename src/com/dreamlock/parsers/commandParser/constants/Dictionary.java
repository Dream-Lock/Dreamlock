package com.dreamlock.parsers.commandParser.constants;

import com.dreamlock.parsers.commandParser.models.Lexeme;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Dictionary {

    INSTANCE;

    Dictionary() {
        grammar = new HashMap<>();

        /** VERBS **/
        grammar.put("look", new Lexeme(1, "look", TokenType.VERB));
        grammar.put("quit", new Lexeme(1, "quit", TokenType.VERB));
        grammar.put("exit", new Lexeme(1, "exit", TokenType.VERB));
        grammar.put("help", new Lexeme(1, "help", TokenType.VERB));

        grammar.put("go", new Lexeme(2, "go", TokenType.VERB));
        grammar.put("walk", new Lexeme(2, "walk", TokenType.VERB));

        grammar.put("examine", new Lexeme(3, "examine", TokenType.VERB));
        grammar.put("take", new Lexeme(3, "take", TokenType.VERB));
        grammar.put("drop", new Lexeme(3, "drop", TokenType.VERB));

        grammar.put("open", new Lexeme(4, "open", TokenType.VERB));

        /** CONJUCTIONS **/
        grammar.put("and", new Lexeme(1, "and", TokenType.CONJUNCTION));

        /** PREPOSITIONS **/
        grammar.put("under", new Lexeme(1, "under", TokenType.PREPOSITION));
        grammar.put("in", new Lexeme(2, "in", TokenType.PREPOSITION));
        grammar.put("on", new Lexeme(3, "on", TokenType.PREPOSITION));

        /** NOUNS **/
        grammar.put("west", new Lexeme(1, "west", TokenType.NOUN));
        grammar.put("north", new Lexeme(1, "north", TokenType.NOUN));
        grammar.put("east", new Lexeme(1, "east", TokenType.NOUN));
        grammar.put("south", new Lexeme(1, "south", TokenType.NOUN));
    }

    private final Map<String, Lexeme> grammar;

    public Lexeme getLexeme(String possibleLexeme) {
        if (grammar.containsKey(possibleLexeme)) {
            return grammar.get(possibleLexeme);
        }
        else {
            return new Lexeme(1, possibleLexeme, TokenType.UNKNOWN);
        }
    }

    public Set<String> getDefinedGrammarKeys() {
        return grammar.keySet();
    }
}
