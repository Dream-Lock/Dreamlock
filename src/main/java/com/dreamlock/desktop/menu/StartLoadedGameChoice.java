package com.dreamlock.desktop.menu;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.handlers.CommandHandler;
import com.dreamlock.core.handlers.ErrorHandler;
import com.dreamlock.core.handlers.HistoryHandler;
import com.dreamlock.core.handlers.IHandler;

import com.dreamlock.core.message_system.IMessageHandler;
import com.dreamlock.core.message_system.StringMessageHandler;
import com.dreamlock.core.message_system.constants.CommandMessages;
import com.dreamlock.core.message_system.constants.GameMessages;
import com.dreamlock.core.parser.Lexer;
import com.dreamlock.core.parser.Parser;
import com.dreamlock.core.parser.models.Lexeme;
import com.dreamlock.core.story_parser.DesktopStoryParser;
import com.dreamlock.core.story_parser.IStoryParser;
import com.dreamlock.desktop.GameUtils;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StartLoadedGameChoice implements IMenuChoice {
    @Override
    public void run() {
        IStoryParser desktopStoryParser = new DesktopStoryParser();
        String[] opening = desktopStoryParser.parseOpening("/openings/dreamlock_opening.json");
        desktopStoryParser.parseWorld("/story.json");
        GameUtils gameUtils = new GameUtils();

        final IGameContext gameContext = gameUtils.LoadStory();
        if (gameContext != null) {
            System.out.println(gameContext.getCurrentRoom().getDescription() + "\n");
            // Setup message handler
            GameMessages gameMessages = new GameMessages(gameContext.getPlayer(), desktopStoryParser.getRooms());
            CommandMessages commandMessages = CommandMessages.INSTANCE;
            IMessageHandler messageHandler = new StringMessageHandler();
            messageHandler.register(gameMessages.getGameMessages());
            messageHandler.register(commandMessages.getCommandMessages());
            gameContext.setMessageHandler(messageHandler);

            Lexer lexer = new Lexer();
            Parser parser = new Parser();
            HistoryHandler historyHandler = new HistoryHandler(gameContext);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            while (gameContext.gameIsRunning()) {
                try {
                    String line = buffer.readLine();

                    ArrayList<Lexeme> lexemes = lexer.tokenize(line);
                    JsonObject output =  parser.parse(lexemes);

                    List<OutputMessage> messageIds;
                    IHandler handler;
                    if (!output.get("error").getAsBoolean()) {
                        historyHandler.register(line);
                        if (historyHandler.handle() != null) {
                            messageIds = historyHandler.handle();
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
                    messageHandler.register(gameMessages.getGameMessages());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
