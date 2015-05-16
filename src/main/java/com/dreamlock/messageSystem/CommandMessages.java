package com.dreamlock.messageSystem;

import com.dreamlock.game.models.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum CommandMessages {

    INSTANCE;

    CommandMessages() {
        commandMessages = new HashMap<>();

        commandMessages.put(0, new Message());
        // for message handling
        commandMessages.put(10000, new Message("",""));     // print only title
        commandMessages.put(10001, new Message("",""));     // print only description
        commandMessages.put(10002, new Message("\n",""));   // title new line
        commandMessages.put(10003, new Message("","\n"));   // description new line
        commandMessages.put(10004, new Message("",""));     // print title and description without new line
        // Go messages
        commandMessages.put(1001, new Message("I can not go to ", ""));
        commandMessages.put(1002, new Message("west", ""));
        commandMessages.put(1003, new Message("north", ""));
        commandMessages.put(1004, new Message("east", ""));
        commandMessages.put(1005, new Message("south", ""));
        // Examine messages
        commandMessages.put(1020, new Message("I can't find anything with that name!", ""));
        // Drop messages
        commandMessages.put(1040, new Message(" dropped successfully!",""));
        commandMessages.put(1041, new Message(" can't be dropped!",""));
        commandMessages.put(1042, new Message("There is no such item, in your inventory", ""));
        // Pick up messages
        commandMessages.put(1060, new Message(" -> Added to inventory!",""));
        commandMessages.put(1061, new Message(" -> You can not take that!",""));
        commandMessages.put(1062, new Message("There is no such item, in this room", ""));
        // show inventory messages
        commandMessages.put(1080, new Message("You don't have any items in your inventory!",""));
        commandMessages.put(1081, new Message("In your Inventory you have:",""));
        // help messages
        commandMessages.put(1100, new Message("Go <direction> -> ","Move around."));
        commandMessages.put(1101, new Message("Examine <item> -> ","With this command tou can examine an item."));
        commandMessages.put(1102, new Message("Drop <item> -> ","With this command you can drop items"));
        commandMessages.put(1103, new Message("Inspect <item> -> ","With this command you can inspect items in your inventory."));
        commandMessages.put(1104, new Message("Look -> ","This will give you a full description of your location."));
        commandMessages.put(1105, new Message("Open <item> -> ","Open an item"));
        commandMessages.put(1106, new Message("Pick up <item> -> ","With this command you can pick up items"));
        commandMessages.put(1107, new Message("Quit -> ","This lets you stop."));
        commandMessages.put(1108, new Message("Save <filename> -> ","With this command you can save your game progress."));
        commandMessages.put(1109, new Message("Reload <filename> -> ","Reloads your character's most recent saved game."));
        commandMessages.put(1110, new Message("Inventory -> ","With this command you can view the items that are in your inventory."));
        commandMessages.put(1111, new Message("Attack -> ","This lets you attack opponents."));
        commandMessages.put(1112, new Message("Inspect -> ","This lets you inspect equipment statistics."));
        commandMessages.put(1113, new Message("Equip -> ","This lets you equip items to your weapon and armor slots."));

        // open messages
        commandMessages.put(1120, new Message(" opened successfully!",""));
        commandMessages.put(1121, new Message(" can't be opened!!",""));
        commandMessages.put(1122, new Message(" is locked and requires a certain key to open!!",""));
        commandMessages.put(1123, new Message(" is already opened!!",""));
        commandMessages.put(1124, new Message("In the chest you find: ",""));
        //save messages
        commandMessages.put(1200, new Message("Game was saved successfully!!",""));
        commandMessages.put(1201, new Message("Save was not successful! :-(",""));
        commandMessages.put(1202, new Message("Please specify a name for the save file, ex. save <filename>",""));
        //load messages
        commandMessages.put(1210, new Message("Loading completed successfully!!",""));
        commandMessages.put(1211, new Message("Could not load game!! :-(",""));
        //inspect messages
        commandMessages.put(1130, new Message(" has an attack rating of ", ""));
        commandMessages.put(1131, new Message(" has a defense rating of ",""));
        commandMessages.put(1131, new Message(" cannot be equipped!",""));
        //attack messages
        commandMessages.put(1301, new Message(" was attacked for ",""));
        commandMessages.put(1302, new Message(" attacked you. Remaining health points: ",""));
        commandMessages.put(1303, new Message(" bested you! You have died.",""));
        commandMessages.put(1304, new Message(" cannot be attacked!",""));
        commandMessages.put(1305, new Message(" There is no such enemy here.",""));
        commandMessages.put(1306, new Message(" That enemy is already dead.",""));
        commandMessages.put(1307, new Message(" has died.",""));
        commandMessages.put(1308, new Message(".",""));
        commandMessages.put(1309, new Message(" points of damage.",""));
        //equip messages
        commandMessages.put(1401, new Message(" was successfully equipped.",""));
        commandMessages.put(1402, new Message(" was unequipped.",""));
        commandMessages.put(1403, new Message(" is not an item that could be equipped!",""));
        // general messages
        commandMessages.put(2001, new Message("There more than one items with that name!", ""));
        // error messages
        commandMessages.put(2101, new Message("I don't think I can do that", ""));
        //quit messages
        commandMessages.put(5000, new Message("Quiting to Main Menu", ""));
    }

    private final Map<Integer, Message> commandMessages;

    public Message getCommandMessage(Integer messageId) {
        return this.commandMessages.get(messageId);
    }

    public Map<Integer, Message> getCommandMessages() {
        return commandMessages;
    }

    public Set<Integer> getDefinedCommandMessages() {
        return this.commandMessages.keySet();
    }
}
