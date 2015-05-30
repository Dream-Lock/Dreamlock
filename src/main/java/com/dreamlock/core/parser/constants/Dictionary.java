package com.dreamlock.core.parser.constants;

import com.dreamlock.core.parser.models.Lexeme;

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
        dictionary.put("stats", new Lexeme(1, "status", TokenType.VERB, 3000));
        dictionary.put("flee", new Lexeme(1, "flee", TokenType.VERB, 3000));
        dictionary.put("run", new Lexeme(1, "run", TokenType.VERB, 3000));
        dictionary.put("history", new Lexeme(1, "history", TokenType.VERB, 3000));

        dictionary.put("go", new Lexeme(2, "go", TokenType.VERB, 3000));
        dictionary.put("walk", new Lexeme(2, "walk", TokenType.VERB, 3000));

        dictionary.put("use", new Lexeme(3, "use", TokenType.VERB, 3000));
        dictionary.put("drink", new Lexeme(3, "drink", TokenType.VERB, 3000));
        dictionary.put("eat", new Lexeme(3, "eat", TokenType.VERB, 3000));
        dictionary.put("equip", new Lexeme(3, "equip", TokenType.VERB, 3000));
        dictionary.put("unequip", new Lexeme(3, "unequip", TokenType.VERB, 3000));
        dictionary.put("examine", new Lexeme(3, "examine", TokenType.VERB, 3000));
        dictionary.put("take", new Lexeme(3, "take", TokenType.VERB, 3000));
        dictionary.put("pick", new Lexeme(3, "pick", TokenType.VERB, 3000));
        dictionary.put("pick up", new Lexeme(3, "pick up", TokenType.VERB, 3000));
        dictionary.put("add", new Lexeme(3, "add", TokenType.VERB, 3000));
        dictionary.put("drop", new Lexeme(3, "drop", TokenType.VERB, 3000));
        dictionary.put("remove", new Lexeme(3, "remove", TokenType.VERB, 3000));
        dictionary.put("inspect", new Lexeme(3, "inspect", TokenType.VERB, 3000));
        dictionary.put("attack", new Lexeme(3, "attack", TokenType.VERB, 3000));

        dictionary.put("open", new Lexeme(4, "open", TokenType.VERB, 3000));

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
