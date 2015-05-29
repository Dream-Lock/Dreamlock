package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ItemType;
import com.dreamlock.game.jsonParser.items.Armor;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.jsonParser.items.Weapon;
import com.dreamlock.game.states.IState;

public class CanEquip implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        context.getPlayer().getInventory().removeItem(item);

        Item forInv = null;

        if(item.getType().equals(ItemType.ARMOR)){
            forInv = (Armor) context.getPlayer().getSlot(((Armor) item).getEquipmentSlot());

        }else if(item.getType().equals(ItemType.WEAPON)){
            forInv = (Weapon) context.getPlayer().getSlot(((Weapon) item).getEquipmentSlot());
        }

        if(forInv != null)
            context.getPlayer().getInventory().addItem(forInv);

        context.getPlayer().equipItem(item);
        context.getPlayer().calculateStats();

        return(1401);
    }

}