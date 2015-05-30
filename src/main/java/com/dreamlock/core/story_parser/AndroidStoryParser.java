package com.dreamlock.core.story_parser;


public class AndroidStoryParser extends DesktopStoryParser implements IStoryParser {
    // TODO: create a specific constructor and class implementation for android parsing
    @Override
    public String read(String file) {
        return super.read(file);
    }

    @Override
    public void parseWorld(String file) {
        super.parseWorld(file);
    }

    @Override
    public String[] parseOpening(String file) {
        return super.parseOpening(file);
    }
}
