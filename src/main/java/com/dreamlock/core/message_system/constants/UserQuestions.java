package com.dreamlock.core.message_system.constants;

import java.util.ArrayList;
import java.util.List;

public enum UserQuestions {

    INSTANCE;

    UserQuestions() {
        questions = new ArrayList<>();

        // 0
        questions.add("how");
        // 1
        questions.add("what");
        // 2
        questions.add("where");
        // 3
        questions.add("when");
        // 4
        questions.add("why");
        // 5
        questions.add("which");
        // 6
        questions.add("hello");
        // 7
        questions.add("hi");
        // 8
        questions.add("how are you");
        // 9
        questions.add("how old are you");
        // 10
        questions.add("how is your mother");
        // 11
        questions.add("why was dreamlock created");
        // 12
        questions.add("who am i");
        // 13
        questions.add("what is your name");
        // 14
        questions.add("what s your name");
        // 15
        questions.add("whats your name");
        // 16
        questions.add("hi how are you");
    }

    private final List<String> questions;

    public List<String> getQuestions() {
        return questions;
    }
}
