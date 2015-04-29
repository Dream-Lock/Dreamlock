package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;
import java.util.List;
import java.util.Map;

public class ShowInventory implements ICommand{
    @Override
    public Integer execute(IGameContext gameContext) {
        List<Item> inventory = gameContext.getPlayer().getInventory().getItems();
        if (inventory.isEmpty()) {
            System.out.println("You don't have any items in your inventory!");
        }
        else {
            System.out.println("In your Inventory you have:");
            for(Item item: inventory) {
                System.out.println(item.getName().toUpperCase()+" -> " + item.getDescription());
            }
        }
        return 1;
    }

    @Override
    public Integer execute(IGameContext gameContext, Map<Integer, Word> words) {
       return null;
    }
}
