package com.dreamlock.game.commands;

import com.dreamlock.game.GameContext;
import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Odin on 6/5/2015.
 */
public class Load implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("save.dat"));
            GameContext LoadedGameContext = (GameContext)inputStream.readObject();

            gameContext.setCurrentRoom(LoadedGameContext.getCurrentRoom());
            gameContext.setPlayer(LoadedGameContext.getPlayer());
            gameContext.setHistory(LoadedGameContext.getHistory());

            inputStream.close();
            output.add(1210);
        } catch(Exception ex) {
            output.add(1211);
        }
        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
