package com.dreamlock;

import com.dreamlock.game.GameContext;
import com.dreamlock.parser.Lexeme;
import com.dreamlock.parser.Parser;
import com.dreamlock.parser.TokenType;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Dreamlock {
    public static void main (String[] args)
    {

        List<Lexeme> lexemes = new ArrayList<>();
//        lexemes.add(new Lexeme("v4", 4, TokenType.VERB));
//        lexemes.add(new Lexeme("n3",3, TokenType.NOUN));
//        lexemes.add(new Lexeme("p1",1, TokenType.PREPOSITION));
//        lexemes.add(new Lexeme("n2",2, TokenType.NOUN));
        lexemes.add(new Lexeme("go",2,TokenType.VERB));
        lexemes.add(new Lexeme("east",1,TokenType.NOUN));

        Parser parser = new Parser();
        JsonObject output =  parser.parse(lexemes);
        CommandHandler commandHandler = new CommandHandler(output, new GameContext());
        commandHandler.handle();
    }


}
