package com.dreamlock.core.handlers;

import com.dreamlock.core.game.models.OutputMessage;

import java.util.List;

public interface IHandler {

    List<OutputMessage> handle();
}
