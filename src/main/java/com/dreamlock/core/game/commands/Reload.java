package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.GameContext;
import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reload implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        try {
            String saveFileName = gameContext.getPlayer().getName();
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("saves/"+saveFileName+".dat"));
            GameContext LoadedGameContext = (GameContext)inputStream.readObject();

            gameContext.setPlayer(LoadedGameContext.getPlayer());
            gameContext.setCurrentRoom(LoadedGameContext.getCurrentRoom());
            gameContext.setHistory(LoadedGameContext.getHistory());

            inputStream.close();
            outputMessages.add(new OutputMessage(1210, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        }
        catch(Exception ex) {
            outputMessages.add(new OutputMessage(1211, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        }
        return outputMessages;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        return null;
    }
}
