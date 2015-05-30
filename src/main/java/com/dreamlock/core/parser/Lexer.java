package com.dreamlock.core.parser;

import com.dreamlock.core.game.constants.Commands;
import com.dreamlock.core.parser.constants.Dictionary;
import com.dreamlock.core.parser.models.Lexeme;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Lexer {
    private Dictionary dictionary;

    public Lexer() {
        dictionary = Dictionary.INSTANCE;
    }

    public ArrayList<Lexeme> tokenize(String line) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        String input = line.toLowerCase();              // input line to lowerCase
        String[] tempTokens = input.trim().split("\\s");    // split input to array
        String[] tokens;

        Boolean hasDoubleToken = false;

        if (tempTokens.length > 1) {
            Set<String> commands = Commands.INSTANCE.getDefinedCommands();
            for (String command : commands) {
                if ((tempTokens[0] + " " + tempTokens[1]).equals(command)) {
                    hasDoubleToken = true;
                }
            }
        }

        if (hasDoubleToken) {
            tokens = new String[tempTokens.length - 1];
            tokens[0] = tempTokens[0] + " " + tempTokens[1];
            System.arraycopy(tempTokens, 2, tokens, 1, tempTokens.length - 1 - 1);
        }
        else {
            tokens = tempTokens;
        }

        for (String token : tokens) {
            if (!Objects.equals(token, "")) {
                lexemes.add(dictionary.getLexeme(token));
            }
        }

        return lexemes;
    }
}


