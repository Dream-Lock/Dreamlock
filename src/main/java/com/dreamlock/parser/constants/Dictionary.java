package com.dreamlock.parser.constants;

import com.dreamlock.parser.models.Lexeme;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Dictionary {

    INSTANCE;

    Dictionary() {
        dictionary = new HashMap<>();

        /** VERBS **/
        dictionary.put("look", new Lexeme(1, "look", TokenType.VERB, 3000));
        dictionary.put("quit", new Lexeme(1, "quit", TokenType.VERB, 3000));
        dictionary.put("qq", new Lexeme(1, "qq", TokenType.VERB, 3000));
        dictionary.put("exit", new Lexeme(1, "exit", TokenType.VERB, 3000));
        dictionary.put("help", new Lexeme(1, "help", TokenType.VERB, 3000));
        dictionary.put("inventory", new Lexeme(1, "inventory", TokenType.VERB, 3000));
        dictionary.put("i", new Lexeme(1, "i", TokenType.VERB, 3000));
        dictionary.put("save", new Lexeme(1, "save", TokenType.VERB, 3000));
        dictionary.put("reload", new Lexeme(1, "reload", TokenType.VERB, 3000));
        dictionary.put("status", new Lexeme(1, "status", TokenType.VERB, 3000));

        dictionary.put("go", new Lexeme(2, "go", TokenType.VERB, 3000));
        dictionary.put("walk", new Lexeme(2, "walk", TokenType.VERB, 3000));

        dictionary.put("equip", new Lexeme(3, "equip", TokenType.VERB, 3000));
        dictionary.put("examine", new Lexeme(3, "examine", TokenType.VERB, 3000));
        dictionary.put("take", new Lexeme(3, "take", TokenType.VERB, 3000));
        dictionary.put("drop", new Lexeme(3, "drop", TokenType.VERB, 3000));
        dictionary.put("inspect", new Lexeme(3, "inspect", TokenType.VERB, 3000));
        dictionary.put("attack", new Lexeme(3, "attack", TokenType.VERB, 3000));


        dictionary.put("open", new Lexeme(3, "open", TokenType.VERB, 3000));

        /** CONJUCTIONS **/
        dictionary.put("and", new Lexeme(1, "and", TokenType.CONJUNCTION, 3000));

        /** PREPOSITIONS **/
        dictionary.put("under", new Lexeme(1, "under", TokenType.PREPOSITION, 3000));
        dictionary.put("in", new Lexeme(2, "in", TokenType.PREPOSITION, 3000));
        dictionary.put("on", new Lexeme(3, "on", TokenType.PREPOSITION, 3000));
        dictionary.put("with", new Lexeme(4, "with", TokenType.PREPOSITION, 3000));

        /** NOUNS **/
        dictionary.put("west", new Lexeme(1, "west", TokenType.NOUN, 1002));
        dictionary.put("north", new Lexeme(1, "north", TokenType.NOUN, 1003));
        dictionary.put("east", new Lexeme(1, "east", TokenType.NOUN, 1004));
        dictionary.put("south", new Lexeme(1, "south", TokenType.NOUN, 1005));
    }

    private final Map<String, Lexeme> dictionary;

    public Lexeme getLexeme(String possibleLexeme) {
        if (dictionary.containsKey(possibleLexeme)) {
            return dictionary.get(possibleLexeme);
        }
        else {
            return new Lexeme(1, possibleLexeme, TokenType.UNKNOWN, 0);
        }
    }

    public Set<String> getDefinedGrammarKeys() {
        return dictionary.keySet();
    }
}
