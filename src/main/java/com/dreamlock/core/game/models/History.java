package com.dreamlock.core.game.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class History implements Serializable {
    private List<String> history;

    public History() {
        history = new ArrayList<>();
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(String command) {
        history.add(command);
    }
}
