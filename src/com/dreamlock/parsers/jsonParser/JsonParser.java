package com.dreamlock.parsers.jsonParser;

import com.dreamlock.models.Room;
import com.dreamlock.parsers.jsonParser.DTOs.ExitsDTO;
import com.dreamlock.parsers.jsonParser.DTOs.ItemDTO;
import com.dreamlock.parsers.jsonParser.DTOs.RoomDTO;
import com.dreamlock.parsers.jsonParser.items.Item;
import com.dreamlock.parsers.jsonParser.items.ItemFactory;
import com.google.gson.Gson;
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
     * Parse the main file
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

            List<Item> items = new ArrayList<>();
            for (ItemDTO itemDTO : roomDTO.getItems()) {        // for every item, the room contain
                String itemPath = itemDTO.getPath();            // this is the path of file
                String jsonItem = read(itemPath);               // the string, file contains.
                JsonElement itemElement = gson.fromJson(jsonItem, JsonElement.class);       // parseWorld to JsonElement
                JsonObject jsonItemObj = itemElement.getAsJsonObject();                     // to JsonObject
                String type = jsonItemObj.get("type").getAsString();                        // and take the type
                items.add(itemFactory.createItem(type, jsonItem));                          // create the item and store it in list.
            }
            room.setItems(items);

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

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public String parseOpening(String s) {
        return null;
    }
}