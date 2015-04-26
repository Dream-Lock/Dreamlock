package com.dreamlock;

import com.dreamlock.game.GameContext;
import com.dreamlock.game.IGameContext;
import com.dreamlock.parsers.commandParser.Lexeme;
import com.dreamlock.parsers.commandParser.Parser;
import com.dreamlock.parsers.commandParser.TokenType;
import com.dreamlock.parsers.jsonParser.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Dreamlock {
    public static void main (String[] args) {

        JsonParser jsonParser = new JsonParser();
        String jsonOpening = jsonParser.parseOpening("/openings/dreamlock_opening.json");

//        OpeningDTO openingDTO = gson.fromJson(jsonOpening, OpeningDTO.class);
//        System.out.println(openingDTO.getOpening());
//
//        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//        String name = "";
//        do try {
//            name = buffer.readLine();
//            if (Objects.equals(name, "")) {
//                System.out.println("Hint: Type your name.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        while (Objects.equals(name, ""));
//        System.out.println("Ah, " + name + openingDTO.getWelcome());
//
//        // load views
//        jsonParser.parseWorld("/story.json");
//        Map<Integer, Room> rooms = jsonParser.getRooms();
//
//        // Setup player
//        Inventory inventory = new Inventory(20);
//        Player player = new Player(name, inventory);
//
//        final IGameContext gameContext = new GameContext(rooms, player);
//        gameContext.printRoomDescription();
//
//        HistoryController historyController = new HistoryController();
//
//        Boolean running = true;
//        Sentences sentences = Sentences.INSTANCE;               // take the sentence structures
//        while (running) {
//            try {
//                String line = buffer.readLine();
//                String input = line.toLowerCase();              // input line to lowerCase
//                String[] tokens = input.trim().split("\\s");           // split input to array
//                if ( tokens[0].isEmpty() && tokens.length < 2 ) {
//                    System.out.println("I beg your pardon?");
//                }
//                else {
//                    ISentence sentence = sentences.getSentence(tokens.length);     // match the typed sentence
//                    historyController.register(line);
//                    int repeats = historyController.getRepeats();
//                    if (repeats < 1) {
//                        sentence.parse(tokens, gameContext);                      // send all tokens
//                    } else {
//                        historyController.print(repeats);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                running = false;
//            }
//        }




        List<Lexeme> lexemes = new ArrayList<>();

        lexemes.add(new Lexeme(2, "go", TokenType.VERB));
        lexemes.add(new Lexeme(1, "east", TokenType.NOUN));

        Parser parser = new Parser();
        JsonObject output =  parser.parse(lexemes);
        CommandHandler commandHandler = new CommandHandler(output, new GameContext());
        commandHandler.handle();
    }
}
