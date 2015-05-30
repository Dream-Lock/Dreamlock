package com.dreamlock.core.story_parser;

import com.dreamlock.core.game.models.Room;

import java.util.HashMap;

public interface IStoryParser {

    String read(String file);

    void parseWorld(String file);

    HashMap<Integer, Room> getRooms();

    String[] parseOpening(String file);
}
