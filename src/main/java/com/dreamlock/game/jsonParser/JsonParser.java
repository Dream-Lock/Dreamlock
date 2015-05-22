package com.dreamlock.game.jsonParser;

import com.dreamlock.game.jsonParser.DTOs.*;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.jsonParser.items.ItemFactory;
import com.dreamlock.game.models.Door;
import com.dreamlock.game.models.Enemy;
import com.dreamlock.game.models.Room;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    private HashMap<Integer, Room> rooms = new HashMap<>();

    public JsonParser() {
    }

    /**
     * Parse the main story file
     * @param file      file name
     */
    public void parseWorld(String file) {
        String jsonString = read(file);
        Gson gson = new Gson();
        ItemFactory itemFactory = new ItemFactory();

        RoomDTO[] roomDTOs = gson.fromJson(jsonString, RoomDTO[].class); //read all rooms

        for (RoomDTO roomDTO : roomDTOs) {               // for every worldDTO
            Room room = new Room();
            String roomPath = roomDTO.getPath();           // get the path of room
            String jsonRoom = read(roomPath);               // read the file-room
            JsonElement roomElement = gson.fromJson(jsonRoom, JsonElement.class);      // store data
            JsonObject jsonRoomObj = roomElement.getAsJsonObject();
            room.setDescription(jsonRoomObj.get("description").getAsString());
            room.setTitle(jsonRoomObj.get("title").getAsString());
            room.setId(jsonRoomObj.get("id").getAsInt());

            List<Door> doors = new ArrayList<>();

            for (DoorDTO doorDTO : roomDTO.getDoors()) {
                String doorPath = doorDTO.getPath();
                String jsonDoor = read(doorPath);
                JsonElement doorElement = gson.fromJson(jsonDoor, JsonElement.class);
                JsonObject jsonDoorObject = doorElement.getAsJsonObject();

                String name = jsonDoorObject.get("name").getAsString();
                String direction = doorDTO.getDirection();
                Integer key = jsonDoorObject.get("key").getAsInt();
                Integer id = jsonDoorObject.get("id").getAsInt();


                Door door = new Door(name, direction, id, key);

                doors.add(door);
            }
            room.setDoors(doors);



            List<Item> items = new ArrayList<>();
            for (ItemDTO itemDTO : roomDTO.getItems()) {        // for every item, the room contain
                String itemPath = itemDTO.getPath();            // this is the path of file
                String jsonItem = read(itemPath);               // the string, file contains.
                JsonElement itemElement = gson.fromJson(jsonItem, JsonElement.class);       // parseWorld to JsonElement
                JsonObject jsonItemObj = itemElement.getAsJsonObject();                     // to JsonObject
                String type = jsonItemObj.get("type").getAsString();                        // and take the type

                if (type.equals("Container")) {                         //If the item is a container, that contains items, we must parse each individual item
                    List <Item> containerItems = new ArrayList<>();
                    JsonArray itemsPath = jsonItemObj.get("items").getAsJsonArray();        //get the path of each item, in a string

                    for (JsonElement path : itemsPath) {
                        String insideItemPath = path.getAsJsonObject().get("path").getAsString();  //get item's path
                        String containerItem = read(insideItemPath);                // get Item's fields in a string

                        JsonElement containerItemElement = gson.fromJson(containerItem, JsonElement.class); //build JsonElement from the String
                        JsonObject jsonObject = containerItemElement.getAsJsonObject();     //make it JsonObject
                        String containerItemType = jsonObject.get("type").getAsString();   //get the type

                        containerItems.add(itemFactory.createItem(containerItemType,containerItem)); // build the item through ItemFactory and store it
                                                                                                    //  in the list of the container's enclosed items :)
                    }
                    items.add(itemFactory.createItem(jsonItem, containerItems));
                }
                else {
                    items.add(itemFactory.createItem(type, jsonItem));                          // create the item and store it in list.
                }
            }
            room.setItems(items);

            List<Enemy> enemies = new ArrayList<>();
            for (EnemyDTO enemyDTO : roomDTO.getEnemies()){ //for every enemy the room contains
                String enemyPath = enemyDTO.getPath();  //path of the room
                String jsonEnemy = read(enemyPath); //the string the file contains

                JsonElement enemyElement = gson.fromJson(jsonEnemy, JsonElement.class); //parse to jsonElement
                JsonObject jsonEnemyObj = enemyElement.getAsJsonObject();  //parse to jsonObject
                Enemy enemy = new Enemy();

                enemy.setId(jsonEnemyObj.get("id").getAsInt());
                enemy.setName(jsonEnemyObj.get("name").getAsString());
                enemy.setDescription(jsonEnemyObj.get("description").getAsString());
                enemy.setHealth(jsonEnemyObj.get("health").getAsInt());
                enemy.setAttack(jsonEnemyObj.get("attack").getAsInt());
                enemy.setDefense(jsonEnemyObj.get("defense").getAsInt());
                enemies.add(enemy);
            }
            room.setEnemies(enemies);

            rooms.put(roomDTO.getId(), room);
        }

        /**
         * Initialize all room with no exits
         */
        rooms.put(0, new Room());
        for (RoomDTO roomDTO : roomDTOs) {
            rooms.get(roomDTO.getId()).setExits(new HashMap<String, Room>());
            rooms.get(roomDTO.getId()).setExit("north", rooms.get(0));
            rooms.get(roomDTO.getId()).setExit("east", rooms.get(0));
            rooms.get(roomDTO.getId()).setExit("south", rooms.get(0));
            rooms.get(roomDTO.getId()).setExit("west", rooms.get(0));
        }

        /**
         * Set room exits
         */
        for (RoomDTO roomDTO : roomDTOs) {
            for (ExitsDTO exitsDTO : roomDTO.getExits()) {

                if (exitsDTO.getNorth() != 0) {
                    rooms.get(roomDTO.getId()).setExit("north", rooms.get(exitsDTO.getNorth()));
                    rooms.get(exitsDTO.getNorth()).setExit("south", rooms.get(roomDTO.getId()));
                }
                if (exitsDTO.getEast() != 0) {
                    rooms.get(roomDTO.getId()).setExit("east", rooms.get(exitsDTO.getEast()));
                    rooms.get(exitsDTO.getEast()).setExit("west", rooms.get(roomDTO.getId()));
                }
                if (exitsDTO.getSouth() != 0) {
                    rooms.get(roomDTO.getId()).setExit("south", rooms.get(exitsDTO.getSouth()));
                    rooms.get(exitsDTO.getSouth()).setExit("north", rooms.get(roomDTO.getId()));
                }
                if (exitsDTO.getWest() != 0) {
                    rooms.get(roomDTO.getId()).setExit("west", rooms.get(exitsDTO.getWest()));
                    rooms.get(exitsDTO.getWest()).setExit("east", rooms.get(roomDTO.getId()));
                }
            }
        }

        /**
         * Checks if 2+ rooms have an exit to the same room on the same direction
         */
        Boolean isDuplicate = false;
        HashMap<String, Room> thisExits = new HashMap<>();
        HashMap<String, Room> nextExits = new HashMap<>();
        int i = 0, j;
        while ( i < roomDTOs.length-1 && !isDuplicate) {
            thisExits = rooms.get(roomDTOs[i].getId()).getExits();
            j = i+1;
            while (j < roomDTOs.length && !isDuplicate) {
                nextExits = rooms.get(roomDTOs[j].getId()).getExits();
                for (String direction : thisExits.keySet()) {
                    if (!thisExits.get(direction).getTitle().equals("wall")) {
                        if (thisExits.get(direction).getTitle().equals(nextExits.get(direction).getTitle())) {
                            isDuplicate = true;
                        }
                    }
                }
                j++;
            }
            i++;
        }

        if (isDuplicate) {
            System.out.println("\nThere are two or more rooms that have an exit to the same room on the same direction!");
            System.exit(0);
        }
    }

    /**
     * Read data from file (json)
     * @param file      file name
     * @return          json string from a file
     */
    public String read(String file) {
        // build json string from file
        String jsonStr = "";
        String temp ;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
            temp = in.readLine();
            while (temp != null) {
                jsonStr += temp;
                temp = in.readLine();
            }
            in.close();
        } catch (IOException e) {
            e.getMessage();
            System.out.println("Can not read!! " + file);
        }
        return jsonStr;
    }

    public String[] parseOpening(String file) {
        Gson gson = new Gson();
        String jsonOpening = read(file);
        OpeningDTO openingDTO = gson.fromJson(jsonOpening, OpeningDTO.class);

        return new String[]{openingDTO.getOpening(),openingDTO.getWelcome()};
    }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }
}