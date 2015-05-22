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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Dreamlock {
    public static void main (String[] args) {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        Boolean MainGameRunning = true;

        while (MainGameRunning) {
            createMainMenu();
            try {
                input = buffer.readLine();
                switch (input) {

                    case "1":  //NEW GAME
                        StartNewGame();
                        break;

                    case "2": //LOAD GAME
                        StartLoadedGame();
                        break;

                    case "3": //EXIT
                        MainGameRunning = false;
                        System.out.println("Goodbye! Farewell Traveler!!");
                        break;

                    default:
                        System.out.println("Not a valid choice!");
                        break;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void createMainMenu () {
        GameLogo();
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit\n");
        System.out.print(": ");
    }

    public static void StartNewGame () {
        Map<Integer,Room> rooms  = createNewStory();
        // Setup player
        Player player = createNewPlayer();
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
        HistoryController historyController = new HistoryController(gameContext);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while (gameContext.gameIsRunning()) {
            try {
                String line = buffer.readLine();

                ArrayList<Lexeme> lexemes = lexer.tokenize(line);
                JsonObject output =  parser.parse(lexemes);
                historyController.register(line);
                CommandHandler commandHandler = new CommandHandler(output, gameContext);
                List<Integer> messageIds = commandHandler.handle();
                messageHandler.print(messageIds);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void StartLoadedGame () {
        JsonParser jsonParser = new JsonParser();
        String[] opening = jsonParser.parseOpening("/openings/dreamlock_opening.json");
        jsonParser.parseWorld("/story.json");

        final IGameContext loadedGameContext = LoadGame();
        if (loadedGameContext != null) {
            System.out.println(loadedGameContext.getCurrentRoom().getDescription() + "\n");
            // Setup message handler
            GameMessages gameMessages = new GameMessages(loadedGameContext.getPlayer(), jsonParser.getRooms());
            CommandMessages commandMessages = CommandMessages.INSTANCE;
            MessageHandler messageHandler = new MessageHandler();
            messageHandler.register(gameMessages.getGameMessages());
            messageHandler.register(commandMessages.getCommandMessages());

            Lexer lexer = new Lexer();
            Parser parser = new Parser();
            HistoryController historyController = new HistoryController(loadedGameContext);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            while (loadedGameContext.gameIsRunning()) {
                try {
                    String line = buffer.readLine();

                    ArrayList<Lexeme> lexemes = lexer.tokenize(line);
                    JsonObject output = parser.parse(lexemes);
                    historyController.register(line);
                    CommandHandler commandHandler = new CommandHandler(output, loadedGameContext);
                    List<Integer> messageIds = commandHandler.handle();
                    messageHandler.print(messageIds);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Player createNewPlayer () {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        JsonParser jsonParser = new JsonParser();
        String[] opening = jsonParser.parseOpening("/openings/dreamlock_opening.json");

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
        return new Player(name,new Inventory(20));
    }

    public static Map<Integer, Room> createNewStory () {
        JsonParser jsonParser = new JsonParser();
        String[] opening = jsonParser.parseOpening("/openings/dreamlock_opening.json");
        jsonParser.parseWorld("/story.json");

        System.out.println(opening[0] + "\n");
        return jsonParser.getRooms();
    }

    public static IGameContext LoadGame () {
        File folder = new File("saves/");
        File[] listOfFiles = folder.listFiles();
        if (!folder.exists() || listOfFiles.length == 0) {
            System.out.println("There are no saved games to load from!!");
            try {
                System.out.println("Press Enter to continue...");
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        else {
                System.out.println("Saved Characters Profiles:");
                System.out.println("---------------------------");
                List<String> listFileNames = new ArrayList<>();
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile()) {
                        System.out.println(listOfFile.getName().replace(".dat", "") + "\t");
                        listFileNames.add(listOfFile.getName());
                    }
                }

                GameContext LoadedGameContext = null;
                System.out.print("Which character's game would you like to load? : ");
                BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
                try {
                    boolean found = false;

                    do {
                        String input = buffer.readLine();
                        for (String filename : listFileNames) {
                            if (filename.replace(".dat", "").toLowerCase().equals(input.toLowerCase())) {
                                found = true;
                                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("saves/" + filename));
                                try {
                                    LoadedGameContext = (GameContext) inputStream.readObject();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                inputStream.close();
                            }
                        }
                    } while (!found);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return LoadedGameContext;
        }
    }

    public static void GameLogo () {

        int width = 120;
        int height = 10;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Verdana", Font.BOLD, 13));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics.drawString("Dreamlock", 2, 10);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(sb);
        }
        System.out.println("");
    }
}