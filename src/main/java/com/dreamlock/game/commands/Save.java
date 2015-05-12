package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Save implements ICommand{
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();
        try {
            String saveFileName = gameContext.getPlayer().getName();
            File file = new File("saves/"+saveFileName+".dat");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(gameContext);

            objectStream.close();
            fileStream.close();
            output.add(1200);

        } catch (Exception e) {
            output.add(1201);
        }
        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
