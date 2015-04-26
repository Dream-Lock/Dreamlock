package com.dreamlock.parsers.commandParser;

import java.util.HashMap;
import java.util.Map;

public enum Rules {

    INSTANCE;

    Rules() {
        rules = new HashMap<>();

        rules.put("V1",true);

        rules.put("V2N1",true);
        rules.put("V2U1",true);
        rules.put("V2U1U1",true);

        rules.put("V3P1N2",true);

        rules.put("V4N3P1N2",true);

        rules.put("N3", true);
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
