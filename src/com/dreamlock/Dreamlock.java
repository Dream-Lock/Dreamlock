package com.dreamlock;

import com.dreamlock.parser.Lexeme;
import com.dreamlock.parser.Parser;
import com.dreamlock.parser.TokenType;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Dreamlock {
    public static void main (String[] args)
    {

        List<Lexeme> lexemes = new ArrayList<Lexeme>();
        lexemes.add(new Lexeme("take",2, TokenType.VERB));
        lexemes.add(new Lexeme("axe",1,TokenType.UNKNOWN));

        Parser parser = new Parser();
        JsonObject output =  parser.parse(lexemes);

    }


}
