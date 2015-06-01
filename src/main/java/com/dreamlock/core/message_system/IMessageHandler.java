package com.dreamlock.core.message_system;

import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.message_system.messages.IMessage;

import java.util.List;
import java.util.Map;

public interface IMessageHandler {
    void register(Map<Integer, IMessage> messages);

    void registerString(String string, int id);

    void print(List<OutputMessage> messageIds);
}
