package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.states.IState;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Armor;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.story_parser.items.Weapon;

public class CanEquip implements IState {
    @Override
    public OutputMessage doAction(IGameContext context) {
        return null;
    }

    @Override
    public OutputMessage doAction(IGameContext context, Item item) {
        context.getPlayer().getInventory().removeItem(item);

        Item forInv = null;

        if(item.getType().equals(ItemType.ARMOR)){
            forInv = (Armor) context.getPlayer().getSlot(((Armor) item).getEquipmentSlot());

        }
        else if(item.getType().equals(ItemType.WEAPON)){
            forInv = (Weapon) context.getPlayer().getSlot(((Weapon) item).getEquipmentSlot());
        }

        if(forInv != null)
            context.getPlayer().getInventory().addItem(forInv);

        context.getPlayer().equipItem(item);
        context.getPlayer().calculateStats();

        return new OutputMessage(1401, PrintStyle.ONLY_TITLE);
    }

}