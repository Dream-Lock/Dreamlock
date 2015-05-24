package com.dreamlock.messageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum CommandMessages {

    INSTANCE;

    CommandMessages() {
        commandMessages = new HashMap<>();

        commandMessages.put(0, new NDMessage());
        // for message handling
        commandMessages.put(10000, new NDMessage("",""));     // print only title
        commandMessages.put(10001, new NDMessage("",""));     // print only description
        commandMessages.put(10002, new NDMessage("\n",""));   // title new line
        commandMessages.put(10003, new NDMessage("","\n"));   // description new line
        commandMessages.put(10004, new NDMessage("",""));     // print title and description without new line
        commandMessages.put(10005, new NDMessage("",""));     //print stats
        commandMessages.put(10006, new NDEMessage("","",""));     // print with effect
        commandMessages.put(10007, new NDEMessage("","","\n"));     // effect new line
        // Go messages
        commandMessages.put(1001, new NDMessage("I can not go to ", ""));
        commandMessages.put(1002, new NDMessage("west", ""));
        commandMessages.put(1003, new NDMessage("north", ""));
        commandMessages.put(1004, new NDMessage("east", ""));
        commandMessages.put(1005, new NDMessage("south", ""));
        commandMessages.put(1006, new NDMessage("Door unlocked successfully!", ""));
        commandMessages.put(1007, new NDMessage("You turn the handle of the door, but it seems that the door is locked!", ""));
        commandMessages.put(1008, new NDMessage("Where do you want me to go?", ""));
        commandMessages.put(1009, new NDMessage("You cannot flee your current battle - Stand and fight!", ""));

        // Examine messages
        commandMessages.put(1020, new NDMessage("I can't find anything with that name!", ""));
        // Drop messages
        commandMessages.put(1040, new NDMessage(" dropped successfully!",""));
        commandMessages.put(1041, new NDMessage(" can't be dropped!",""));
        commandMessages.put(1042, new NDMessage("There is no such item in your inventory", ""));
        // Pick up messages
        commandMessages.put(1060, new NDMessage(" -> Added to inventory!",""));
        commandMessages.put(1061, new NDMessage(" -> You can not take that!",""));
        commandMessages.put(1062, new NDMessage("There is no such item, in this room", ""));
        // show inventory messages
        commandMessages.put(1080, new NDMessage("You don't have any items in your inventory!",""));
        commandMessages.put(1081, new NDMessage("In your Inventory you have:",""));
        // help messages
        commandMessages.put(1100, new NDMessage("Go <direction> -> ","Move around."));
        commandMessages.put(1101, new NDMessage("Examine <item> -> ","With this command tou can examine an item."));
        commandMessages.put(1102, new NDMessage("Drop <item> -> ","With this command you can drop items"));
        commandMessages.put(1103, new NDMessage("Inspect <item> -> ","This lets you inspect equipment statistics."));
        commandMessages.put(1104, new NDMessage("Look -> ","This will give you a full description of your location."));
        commandMessages.put(1105, new NDMessage("Open <item> with <item>-> ","Open an item with something"));
        commandMessages.put(1106, new NDMessage("Pick up <item> -> ","With this command you can pick up items"));
        commandMessages.put(1107, new NDMessage("Quit -> ","This lets you stop."));
        commandMessages.put(1108, new NDMessage("Save -> ","With this command you can save your game progress."));
        commandMessages.put(1109, new NDMessage("Reload -> ","Reloads your character's most recent saved game."));
        commandMessages.put(1110, new NDMessage("Inventory -> ","With this command you can view the items that are in your inventory. Alternate command: i."));
        commandMessages.put(1111, new NDMessage("Attack -> ","This lets you attack opponents."));
        commandMessages.put(1112, new NDMessage("Equip <item> -> ","This lets you equip items to your weapon and armor slots."));
        commandMessages.put(1113, new NDMessage("Unequip <item> -> ","This lets you unequip items from your weapon and armor slots."));
        commandMessages.put(1114, new NDMessage("Status -> ","This lets you view your character's status."));
        commandMessages.put(1114, new NDMessage("Use -> ","This lets you use consumable items. Alternate command: drink <item>."));
        commandMessages.put(1114, new NDMessage("Flee -> ","Try and flee from battle. Failure to do so will make you lose your turn and make enemies attack. Alternate command: run."));

        // open messages
        commandMessages.put(1120, new NDMessage(" opened successfully!",""));
        commandMessages.put(1121, new NDMessage(" can't be opened!!",""));
        commandMessages.put(1122, new NDMessage(" is locked and requires a certain key to open!!",""));
        commandMessages.put(1123, new NDMessage(" is already opened!!",""));
        commandMessages.put(1124, new NDMessage("In the chest you find: ",""));
        commandMessages.put(1125, new NDMessage("You can't open it with this item, 'cause you don't have it!",""));
        commandMessages.put(1126, new NDMessage("You need a different key for this kind of lock",""));
        //save messages
        commandMessages.put(1200, new NDMessage("Game was saved successfully!!",""));
        commandMessages.put(1201, new NDMessage("Save was not successful! :-(",""));
        commandMessages.put(1202, new NDMessage("Please specify a name for the save file, ex. save <filename>",""));
        //load messages
        commandMessages.put(1210, new NDMessage("Loading completed successfully!!",""));
        commandMessages.put(1211, new NDMessage("Could not load game!! :-(",""));
        //inspect messages
        commandMessages.put(1130, new NDMessage(" has an attack rating of ", ""));
        commandMessages.put(1131, new NDMessage(" has a defense rating of ",""));
        commandMessages.put(1132, new NDMessage(" cannot be equipped!",""));
        //attack messages
        commandMessages.put(1301, new NDMessage(" was attacked for ",""));
        commandMessages.put(1302, new NDMessage(" attacked you. Remaining health points: ",""));
        commandMessages.put(1303, new NDMessage(" You have been bested in combat. GAME OVER.",""));
        commandMessages.put(1304, new NDMessage(" cannot be attacked!",""));
        commandMessages.put(1305, new NDMessage(" There is no such enemy here.",""));
        commandMessages.put(1306, new NDMessage(" That enemy is already dead.",""));
        commandMessages.put(1307, new NDMessage(" has died.",""));
        commandMessages.put(1308, new NDMessage(".",""));
        commandMessages.put(1309, new NDMessage(" points of damage.",""));
        commandMessages.put(1310, new NDMessage("All your enemies are dead, the battle is over!.",""));
        //equip messages
        commandMessages.put(1401, new NDMessage(" was successfully equipped.",""));
        commandMessages.put(1402, new NDMessage(" was unequipped.",""));
        commandMessages.put(1403, new NDMessage(" is not an item that could be equipped!",""));
        //use messages
        commandMessages.put(1501, new NDMessage(" was successfully used!",""));
        //history messages
        commandMessages.put(1601, new NDMessage("I already did that.",""));
        commandMessages.put(1602, new NDMessage("Please stop making me repeat myself!",""));
        commandMessages.put(1603, new NDMessage("Type 'qq'!",""));
        commandMessages.put(1604, new NDMessage("Never mind I will do it for you...",""));
        //flee messages
        commandMessages.put(1701, new NDMessage("You successfully fled the battle! Retreat into another room quickly!",""));
        commandMessages.put(1702, new NDMessage("You've missed your chance, the enemy strikes at the opportunity",""));
        commandMessages.put(1703, new NDMessage("There is nothing to flee from...",""));
        //general messages
        commandMessages.put(2001, new NDMessage("There more than one items with that name!", ""));
        commandMessages.put(2002, new NDMessage("There more than one doors with that name!", ""));
        commandMessages.put(2003, new NDMessage(" is locked.", ""));
        commandMessages.put(2004, new NDMessage(" is unlocked!", ""));
        //error messages
        commandMessages.put(2101, new NDMessage("I don't think I can do that", ""));
        commandMessages.put(2102, new NDMessage("Unexpected state error", ""));
        commandMessages.put(2103, new NDMessage("Shut your dirty mouth!", ""));
        commandMessages.put(2101, new NDMessage("I don't think I can do that", ""));
        commandMessages.put(2102, new NDMessage("Unexpected state error", ""));
        //quit messages
        commandMessages.put(5000, new NDMessage("Quiting to Main Menu", ""));
    }

    private final Map<Integer, IMessage> commandMessages;

    public IMessage getCommandMessage(Integer messageId) {
        return this.commandMessages.get(messageId);
    }

    public Map<Integer, IMessage> getCommandMessages() {
        return commandMessages;
    }

    public Set<Integer> getDefinedCommandMessages() {
        return this.commandMessages.keySet();
    }
}
