package com.dreamlock.core.message_system.constants;

import java.io.Serializable;

public enum PrintStyle implements Serializable {
    ONLY_TITLE, ONLY_DESCRIPTION, ONLY_EFFECT, TITLE_DESCRIPTION, TITLE_DESCRIPTION_EFFECT,
    ONLY_TITLE_IN_SAME_LINE, ONLY_DESCRIPTION_IN_SAME_LINE,
    BREAK, EMPTY, ARROW,
    NUMBER,
    DEFAULT
}
