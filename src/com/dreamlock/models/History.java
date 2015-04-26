package com.dreamlock.models;

import java.util.ArrayList;
import java.util.List;

public class History {
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
