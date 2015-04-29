package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

import java.util.List;
import java.util.Objects;

public class CanPickUp implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return 0;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {

        if (context.getCurrentRoom().getItems().contains(item)) {
            item.getStates().put("Pick Up",new CanNotPickUp());
            item.getStates().put("Drop",new CanDrop());
            context.getPlayer().getInventory().addItem(item);
            context.getCurrentRoom().getItems().remove(item);
            System.out.println("Added to inventory!!");
        }

        return null;
    }
}
