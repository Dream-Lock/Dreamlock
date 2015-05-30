package com.dreamlock.core.game.constants;

import com.dreamlock.core.game.commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Commands {

    INSTANCE;

    Commands() {
        commands = new HashMap<>();
        commands.put("go", new Go());
        commands.put("walk", new Go());
        commands.put("look", new Look());
        commands.put("examine", new Examine());
        commands.put("quit", new Quit());
        commands.put("exit", new Quit());
        commands.put("qq", new Quit());
        commands.put("help", new Help());
        commands.put("take", new PickUp());
        commands.put("pick", new PickUp());
        commands.put("add", new PickUp());
        commands.put("pick up", new PickUp());
        commands.put("drop", new Drop());
        commands.put("remove", new Drop());
        commands.put("open", new Open());
        commands.put("inventory", new ShowInventory());
        commands.put("i", new ShowInventory());
        commands.put("inspect", new Inspect());
        commands.put("attack", new Attack());
        commands.put("save", new Save());
        commands.put("equip", new Equip());
        commands.put("status", new Status());
        commands.put("stats", new Status());
        commands.put("unequip", new Unequip());
        commands.put("reload", new Reload());
        commands.put("use", new Use());
        commands.put("drink", new Drink());
        commands.put("eat", new Eat());
        commands.put("flee", new Flee());
        commands.put("run", new Flee());
        commands.put("history", new History());
    }

    private final Map<String, ICommand> commands;

    public ICommand getCommand(String commandVerb) {
        return this.commands.get(commandVerb);
    }

    public Set<String> getDefinedCommands() {
        return this.commands.keySet();
    }
}