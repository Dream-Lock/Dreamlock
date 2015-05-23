package com.dreamlock.menu;

import com.dreamlock.GameUtils;
import com.dreamlock.HistoryController;
import com.dreamlock.game.GameContext;
import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Player;
import com.dreamlock.game.models.Room;
import com.dreamlock.handlers.CommandHandler;
import com.dreamlock.handlers.ErrorHandler;
import com.dreamlock.handlers.IHandler;
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

public class StartNewGameChoice implements IMenuChoice {
    @Override
    public void run() {
        GameUtils gameUtils = new GameUtils();
        Map<Integer,Room> rooms  = gameUtils.createNewStory();
        // Setup player
        Player player = gameUtils.createNewPlayer();
        final IGameContext gameContext = new GameContext(rooms, player);

        System.out.println(gameContext.getCurrentRoom().getDescription() + "\n");

        // Setup message handler
        GameMessages gameMessages = new GameMessages(player, rooms);
        CommandMessages commandMessages = CommandMessages.INSTANCE;
        MessageHandler messageHandler = new MessageHandler();
        messageHandler.register(gameMessages.getGameMessages());
        messageHandler.register(commandMessages.getCommandMessages());
        gameContext.setMessageHandler(messageHandler);

        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        HistoryController historyController = new HistoryController(gameContext);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while (gameContext.gameIsRunning()) {
            try {
                String line = buffer.readLine();

                ArrayList<Lexeme> lexemes = lexer.tokenize(line);
                JsonObject output =  parser.parse(lexemes);

                List<Integer> messageIds = null;
                IHandler handler;
                if (!output.get("error").getAsBoolean()) {
                    historyController.register(line);
                    if (historyController.handle() != null) {
                        messageIds = historyController.handle();
                        messageHandler.print(messageIds);
                        continue;
                    }
                    handler = new CommandHandler(output, gameContext);
                }
                else {
                    handler = new ErrorHandler(output, gameContext);
                }

                messageIds = handler.handle();
                messageHandler.print(messageIds);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
