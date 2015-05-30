package com.dreamlock.core.parser.constants;

import java.util.HashMap;
import java.util.Map;

public enum Rules {

    INSTANCE;

    Rules() {
        rules = new HashMap<>();

        rules.put("V1",true);           // Syntax: Verb

        rules.put("V2N1",true);         // Syntax: Verb, Direction(Noun)

        rules.put("V3U1",true);         // Syntax: Verb, Item(Noun)

        rules.put("V4U1",true);         // Syntax: Verb, Item(Noun)

        rules.put("V4U1P4U1",true);     // Syntax: Verb, Item(Noun), Preposition, Item(Noun)

        rules.put("N3", true);          // Syntax: Noun(command)
    }

    private final Map<String, Boolean> rules;

    public Boolean getRule(String rule) {
        if (rules.containsKey(rule)) {
            return rules.get(rule);
        }
        else {
            return false;
        }
    }
}
