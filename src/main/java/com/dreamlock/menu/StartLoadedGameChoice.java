package com.dreamlock.menu;

import com.dreamlock.GameUtils;
import com.dreamlock.HistoryController;
import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.JsonParser;
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

public class StartLoadedGameChoice implements IMenuChoice {
    @Override
    public void run() {
        JsonParser jsonParser = new JsonParser();
        String[] opening = jsonParser.parseOpening("/openings/dreamlock_opening.json");
        jsonParser.parseWorld("/story.json");
        GameUtils gameUtils = new GameUtils();

        final IGameContext gameContext = gameUtils.LoadStory();
        if (gameContext != null) {
            System.out.println(gameContext.getCurrentRoom().getDescription() + "\n");
            // Setup message handler
            GameMessages gameMessages = new GameMessages(gameContext.getPlayer(), jsonParser.getRooms());
            CommandMessages commandMessages = CommandMessages.INSTANCE;
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.register(gameMessages.getGameMessages());
            messageHandler.register(commandMessages.getCommandMessages());

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
}
