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
        commandMessages.put(1100, new Message("Go -> ","Move around."));
        commandMessages.put(1101, new Message("Examine -> ","With this command tou can examine an item."));
        commandMessages.put(1102, new Message("Drop -> ","With this command you can drop items"));
        commandMessages.put(1103, new Message("Inspect -> ","With this command you can inspect items in your inventory."));
        commandMessages.put(1104, new Message("Look -> ","This will give you a full description of your location."));
        commandMessages.put(1105, new Message("Open -> ","Open an item"));
        commandMessages.put(1106, new Message("Pick up -> ","With this command you can pick up items"));
        commandMessages.put(1107, new Message("Quit -> ","This lets you stop."));
        commandMessages.put(1108, new Message("Inventory -> ","With this command you can view the items that are in your inventory."));
        commandMessages.put(1109, new Message("Inspect -> ","With this command you can inspect an item's attributes before equipping it."));
        commandMessages.put(1110, new Message("Attack -> ","With this command you can attack an opponent."));
        // open messages
        commandMessages.put(1120, new Message(" opened successfully!",""));
        commandMessages.put(1121, new Message(" can't be opened!!",""));
        commandMessages.put(1122, new Message(" is locked and requires a certain key to open!!",""));
        commandMessages.put(1123, new Message(" is already opened!!",""));
        //inspect messages
        commandMessages.put(1130, new Message(" has an attack rating of ", ""));
        commandMessages.put(1131, new Message(" has a defense rating of ",""));
        commandMessages.put(1131, new Message(" cannot be equipped!",""));
        //attack messages
        commandMessages.put(1201, new Message(" was attacked for ",""));
        commandMessages.put(1202, new Message(" attacked you for ",""));
        commandMessages.put(1203, new Message(" bested you! You have died.",""));
        commandMessages.put(1204, new Message(" cannot be attacked!",""));
        commandMessages.put(1205, new Message(" There is no such enemy here.",""));
        commandMessages.put(1206, new Message(" That enemy is already dead.",""));
        // general messages
        commandMessages.put(2001, new Message("There more than one items with that name!", ""));

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
