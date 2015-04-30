package com.dreamlock;

import com.dreamlock.game.GameContext;
import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.JsonParser;
import com.dreamlock.game.models.Inventory;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;
import com.dreamlock.messageSystem.CommandMessages;
import com.dreamlock.messageSystem.GameMessages;
import com.dreamlock.messageSystem.MessageHandler;
import com.dreamlock.parser.Lexer;
import com.dreamlock.parser.Parser;
import com.dreamlock.parser.models.Lexeme;
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

        String[] opening = jsonParser.parseOpening("/openings/dreamlock_opening.json");
        System.out.println(opening[0] + "\n");

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String name = "";
        do try {
            name = buffer.readLine();
            if (Objects.equals(name, "")) {
                System.out.println("Hint: Type your name.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (Objects.equals(name, ""));
        System.out.println("Ah, " + name + opening[1] + "\n");

        jsonParser.parseWorld("/story.json");
        Map<Integer, Room> rooms = jsonParser.getRooms();

        // Setup player
        Player player = new Player(name, new Inventory(20));
        final IGameContext gameContext = new GameContext(rooms, player);

        System.out.println(gameContext.getCurrentRoom().getDescription() + "\n");

        // Setup message handler
        GameMessages gameMessages = new GameMessages(player, rooms);
        CommandMessages commandMessages = CommandMessages.INSTANCE;
        MessageHandler messageHandler = new MessageHandler();
        messageHandler.register(gameMessages.getGameMessages());
        messageHandler.register(commandMessages.getCommandMessages());

        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        Boolean running = true;
        while (running) {
            try {
                String line = buffer.readLine();

                ArrayList<Lexeme> lexemes = lexer.tokenize(line);
                JsonObject output =  parser.parse(lexemes);
                // TODO: error handling
                CommandHandler commandHandler = new CommandHandler(output, gameContext);
                List<Integer> messageIds = commandHandler.handle();
                messageHandler.register(new GameMessages(player,rooms).getGameMessages());
                messageHandler.print(messageIds);
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }
}
