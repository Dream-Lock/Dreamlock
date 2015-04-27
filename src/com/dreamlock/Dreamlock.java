package com.dreamlock;

import com.dreamlock.game.GameContext;
import com.dreamlock.parsers.commandParser.models.Lexeme;
import com.dreamlock.parsers.commandParser.Lexer;
import com.dreamlock.parsers.commandParser.Parser;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dreamlock {
    public static void main (String[] args) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        Boolean running = true;
        while (running) {
            try {
                String line = buffer.readLine();

                ArrayList<Lexeme> lexemes = lexer.tokenize(line);
                JsonObject output =  parser.parse(lexemes);
                // TODO: error handling
                CommandHandler commandHandler = new CommandHandler(output, new GameContext());
                commandHandler.handle();

            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }


    }
}
