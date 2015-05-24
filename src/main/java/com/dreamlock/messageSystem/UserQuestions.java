package com.dreamlock.messageSystem;

import java.util.ArrayList;
import java.util.List;

public enum UserQuestions {

    INSTANCE;

    UserQuestions() {
        questions = new ArrayList<>();

        questions.add("hello");
    }

    private final List<String> questions;

    public List<String> getQuestions() {
        return questions;
    }
}
