package com.dreamlock.core.message_system.constants;

import com.dreamlock.core.message_system.messages.IMessage;
import com.dreamlock.core.message_system.messages.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum CommandMessages {

    INSTANCE;

    CommandMessages() {
        commandMessages = new HashMap<>();

        commandMessages.put(0, new Message("", ""));
        // for message handling
//        commandMessages.put(10000, new Message("",""));     // print only title
//        commandMessages.put(10001, new Message("",""));     // print only description
//        commandMessages.put(10002, new Message("\n",""));   // title new line
//        commandMessages.put(10003, new Message("","\n"));   // description new line
//        commandMessages.put(10004, new Message("",""));     // print title and description without new line
//        commandMessages.put(10005, new Message("","",""));     //print stats
//        commandMessages.put(10006, new Message("","",""));     // print with effect
//        commandMessages.put(10007, new Message("","","\n"));     // effect new line

        // Go messages
        commandMessages.put(1001, new Message("I can not go to ", ""));
        commandMessages.put(1002, new Message("west", ""));
        commandMessages.put(1003, new Message("north", ""));
        commandMessages.put(1004, new Message("east", ""));
        commandMessages.put(1005, new Message("south", ""));
        commandMessages.put(1006, new Message("Door unlocked successfully!", ""));
        commandMessages.put(1007, new Message("You turn the handle of the door, but it seems that the door is locked!", ""));
        commandMessages.put(1008, new Message("Where do you want me to go?", ""));
        commandMessages.put(1009, new Message("You cannot flee your current battle - Stand and fight!", ""));

        // Examine messages
        commandMessages.put(1020, new Message("I can't find anything with that name!", ""));

        // Drop messages
        commandMessages.put(1040, new Message(" dropped successfully!", ""));
        commandMessages.put(1041, new Message(" can't be dropped!", ""));
        commandMessages.put(1042, new Message("There is no such item in your inventory", ""));
        commandMessages.put(1043, new Message("You do not wear such an item. ", ""));

        // Pick up messages
        commandMessages.put(1060, new Message(" -> Added to inventory!", ""));
        commandMessages.put(1061, new Message(" -> You can not take that!", ""));
        commandMessages.put(1062, new Message("There is no such item, in this room", ""));
        commandMessages.put(1063, new Message(" -> This object is of no value to me", ""));

        // Show inventory messages
        commandMessages.put(1080, new Message("You don't have any items in your inventory!", ""));
        commandMessages.put(1081, new Message("In your Inventory you have:", ""));

        // Help messages
        commandMessages.put(1100, new Message("Go <direction> -> ","Move around."));
        commandMessages.put(1101, new Message("Examine <item> -> ","With this command tou can examine an item."));
        commandMessages.put(1102, new Message("Drop <item> -> ","With this command you can drop items"));
        commandMessages.put(1103, new Message("Inspect <item> -> ","This lets you inspect equipment statistics."));
        commandMessages.put(1104, new Message("Look -> ","This will give you a full description of your location."));
        commandMessages.put(1105, new Message("Open <item> with <item>-> ","Open an item with something"));
        commandMessages.put(1106, new Message("Pick up <item> -> ","With this command you can pick up items"));
        commandMessages.put(1107, new Message("Quit -> ","This lets you stop."));
        commandMessages.put(1108, new Message("Save -> ","With this command you can save your game progress."));
        commandMessages.put(1109, new Message("Reload -> ","Reloads your character's most recent saved game."));
        commandMessages.put(1110, new Message("Inventory -> ","With this command you can view the items that are in your inventory. Alternate command: i."));
        commandMessages.put(1111, new Message("Attack -> ","This lets you attack opponents."));
        commandMessages.put(1112, new Message("Equip <item> -> ","This lets you equip items to your weapon and armor slots."));
        commandMessages.put(1113, new Message("Unequip <item> -> ","This lets you unequip items from your weapon and armor slots."));
        commandMessages.put(1114, new Message("Status -> ","This lets you view your character's status."));
        commandMessages.put(1115, new Message("Use -> ","This lets you use consumable items. Alternate command: drink <item>."));
        commandMessages.put(1116, new Message("Flee -> ","Try and flee from battle. Failure to do so will make you lose your turn and make enemies attack. Alternate command: run."));

        // Open messages
        commandMessages.put(1150, new Message(" opened successfully!", ""));
        commandMessages.put(1151, new Message(" can't be opened!!", ""));
        commandMessages.put(1152, new Message(" is locked and requires a certain key to open!!", ""));
        commandMessages.put(1153, new Message(" is already opened!!", ""));
        commandMessages.put(1154, new Message("In the chest you find: ", ""));
        commandMessages.put(1155, new Message("You can't open it with this item, 'cause you don't have it!", ""));
        commandMessages.put(1156, new Message("You need a different key for this kind of lock", ""));

        // Save messages
        commandMessages.put(1200, new Message("Game was saved successfully!!", ""));
        commandMessages.put(1201, new Message("Save was not successful! :-(", ""));
        commandMessages.put(1202, new Message("Please specify a name for the save file, ex. save <filename>", ""));

        // Load messages
        commandMessages.put(1210, new Message("Loading completed successfully!!", ""));
        commandMessages.put(1211, new Message("Could not load game!! :-(", ""));

        // Inspect messages
        commandMessages.put(1130, new Message(" has an attack rating of ", ""));
        commandMessages.put(1131, new Message(" has a defense rating of ", ""));
        commandMessages.put(1132, new Message(" cannot be equipped!", ""));
        commandMessages.put(1133, new Message("You only can inspect wearables!", ""));


        // Attack messages
        commandMessages.put(1301, new Message(" was attacked for ", ""));
        commandMessages.put(1302, new Message(" attacked you. Remaining health points: ", ""));
        commandMessages.put(1303, new Message(" You have been bested in combat. GAME OVER.", ""));
        commandMessages.put(1304, new Message(" cannot be attacked!", ""));
        commandMessages.put(1305, new Message(" There is no such enemy here.", ""));
        commandMessages.put(1306, new Message(" That enemy is already dead.", ""));
        commandMessages.put(1307, new Message(" has died.", ""));
        commandMessages.put(1308, new Message(".", ""));
        commandMessages.put(1309, new Message(" points of damage.", ""));
        commandMessages.put(1310, new Message("All your enemies are dead, the battle is over!.", ""));

        // Equip messages
        commandMessages.put(1401, new Message(" was successfully equipped.", ""));
        commandMessages.put(1402, new Message(" was unequipped.", ""));
        commandMessages.put(1403, new Message(" is not an item that can be equipped!", ""));

        // Drink/Eat messages
        commandMessages.put(1501, new Message(" was successfully used!", ""));
        commandMessages.put(1502, new Message(" is not an item that can be drunk!", ""));
        commandMessages.put(1503, new Message(" is not an item that can be eaten!", ""));

        // History messages
        commandMessages.put(1601, new Message("I already did that.", ""));
        commandMessages.put(1602, new Message("Please stop making me repeat myself!", ""));
        commandMessages.put(1603, new Message("Type 'qq'!", ""));
        commandMessages.put(1604, new Message("Never mind I will do it for you...", ""));

        // Flee messages
        commandMessages.put(1701, new Message("You successfully fled the battle! Retreat into another room quickly!", ""));
        commandMessages.put(1702, new Message("You've missed your chance, the enemy strikes at the opportunity", ""));
        commandMessages.put(1703, new Message("There is nothing to flee from...", ""));

        // Look messages
        commandMessages.put(1800, new Message("I also can see an item:", ""));
        commandMessages.put(1801, new Message("I also can see some items:", ""));
        commandMessages.put(1802, new Message("It seems there is an enemy:", ""));
        commandMessages.put(1803, new Message("It seems there are some enemies:", ""));
        commandMessages.put(1804, new Message(" There is a door: ", ""));
        commandMessages.put(1805, new Message(" I can see doors:", ""));

        // Use message
        commandMessages.put(1900, new Message(" is not an item that can be used!", ""));

        // General messages
        commandMessages.put(2001, new Message("There more than one items with that name!", ""));
        commandMessages.put(2002, new Message("There more than one doors with that name!", ""));
        commandMessages.put(2003, new Message(" is locked.", ""));
        commandMessages.put(2004, new Message(" is unlocked!", ""));
        commandMessages.put(2005, new Message("History is empty.", ""));

        // Symbols
        commandMessages.put(2067, new Message("", ""));
        commandMessages.put(2068, new Message(" ", " "));
        commandMessages.put(2069, new Message("<", ""));
        commandMessages.put(2070, new Message(">", ""));
        commandMessages.put(2071, new Message("!", ""));
        commandMessages.put(2072, new Message("@", ""));
        commandMessages.put(2073, new Message("#", ""));
        commandMessages.put(2074, new Message("$", ""));
        commandMessages.put(2075, new Message("%", ""));
        commandMessages.put(2076, new Message("^", ""));
        commandMessages.put(2077, new Message("&", ""));
        commandMessages.put(2078, new Message("*", ""));
        commandMessages.put(2079, new Message("(", ""));
        commandMessages.put(2080, new Message(")", ""));
        commandMessages.put(2081, new Message("[", ""));
        commandMessages.put(2082, new Message("]", ""));
        commandMessages.put(2083, new Message("{", ""));
        commandMessages.put(2084, new Message("}", ""));
        commandMessages.put(2085, new Message("|", ""));
        commandMessages.put(2086, new Message("_", ""));
        commandMessages.put(2087, new Message("-", ""));
        commandMessages.put(2088, new Message("+", ""));
        commandMessages.put(2089, new Message("*", ""));
        commandMessages.put(2090, new Message("/", ""));
        commandMessages.put(2091, new Message("=", ""));
        commandMessages.put(2092, new Message(":", ""));
        commandMessages.put(2093, new Message(";", ""));
        commandMessages.put(2094, new Message("?", ""));
        commandMessages.put(2095, new Message(".", ""));
        commandMessages.put(2096, new Message(",", ""));
        commandMessages.put(2097, new Message("\"", ""));
        commandMessages.put(2098, new Message("'", ""));
        commandMessages.put(2099, new Message("~", ""));


        // Error handler messages
        commandMessages.put(2101, new Message("I don't think I can do that", ""));
        commandMessages.put(2102, new Message("Unexpected state error", ""));
        commandMessages.put(2103, new Message("Shut your dirty mouth!", ""));
        commandMessages.put(2104, new Message("Watch your mouth! who do you think you are.", ""));
        commandMessages.put(2105, new Message("Hey, don't talk that way to me!", ""));
        commandMessages.put(2106, new Message("Excuse me, but could you please watch you language?\nThis is a children's game!", ""));
        commandMessages.put(2107, new Message("Profanity is strictly prohibited!", ""));

        // User replies
        commandMessages.put(2200, new Message("Hi", ""));
        commandMessages.put(2201, new Message("Why hello.", ""));

        commandMessages.put(2202, new Message("Just dandy, thanks.", ""));
        commandMessages.put(2203, new Message("I'm fine, thank you.", ""));
        commandMessages.put(2213, new Message("Very well, thanks.", ""));
        commandMessages.put(2214, new Message("I've been better.", ""));
        commandMessages.put(2215, new Message("I'm hanging in there.", ""));

        commandMessages.put(2204, new Message("That's none of your business!", ""));
        commandMessages.put(2205, new Message("She's pretty good I guess.", ""));

        commandMessages.put(2206, new Message("Your existence fears, do not concern me.", ""));

        commandMessages.put(2207, new Message("It was a task from Master Petal in order to save humanity", ""));

        commandMessages.put(2210, new Message("You should never ask a lady's age!", ""));
        commandMessages.put(2216, new Message("I will say that I am old enough to be your mamma!", ""));
        commandMessages.put(2217, new Message("Age is just a number and mine is unlisted.", ""));
        commandMessages.put(2218, new Message("Age doesn't matter unless you are cheese or wine.", ""));

        commandMessages.put(2211, new Message("My name is of no concern to you.", ""));
        commandMessages.put(2219, new Message("That's for me to know and for you to wonder about.", ""));
        commandMessages.put(2212, new Message("My name is Hestia", ""));

        commandMessages.put(2298, new Message("I do not understand your question", ""));
        commandMessages.put(2299, new Message("I beg your pardon", ""));

        // Status
        commandMessages.put(2500, new Message("Agility: ", ""));
        commandMessages.put(2501, new Message("Attack: ", ""));
        commandMessages.put(2502, new Message("Defense: ", ""));
        commandMessages.put(2503, new Message("Health: ", ""));
        commandMessages.put(2504, new Message("Stamina: ", ""));
        commandMessages.put(2505, new Message("Strength: ", ""));
        commandMessages.put(2506, new Message("Name: ", ""));

        // Quit messages
        commandMessages.put(5000, new Message("Quiting to Main Menu", ""));
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
