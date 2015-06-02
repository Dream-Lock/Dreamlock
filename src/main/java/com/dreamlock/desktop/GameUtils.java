package com.dreamlock.desktop;

import com.dreamlock.core.game.GameContext;
import com.dreamlock.core.game.models.Inventory;
import com.dreamlock.core.game.models.Player;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.story_parser.DesktopStoryParser;
import com.dreamlock.core.story_parser.IStoryParser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameUtils {

    public Player createNewPlayer () {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        IStoryParser desktopStoryParser = new DesktopStoryParser();
        String[] opening = desktopStoryParser.parseOpening("/openings/dreamlock_opening.json");

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

    public Map<Integer, Room> createNewStory () {
        IStoryParser desktopStoryParser = new DesktopStoryParser();
        String[] opening = desktopStoryParser.parseOpening("/openings/dreamlock_opening.json");
        desktopStoryParser.parseWorld("/story.json");

        System.out.println(opening[0] + "\n");
        return desktopStoryParser.getRooms();
    }

    public GameContext LoadStory() {
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

            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            try {
                boolean found = false;

                do {
                    System.out.print("Which character's game would you like to load? : ");
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

    public void GameLogo(String logo) {

        int width = 120;
        int height = 10;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Verdana", Font.BOLD, 13));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics.drawString(logo, 2, 10);

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
