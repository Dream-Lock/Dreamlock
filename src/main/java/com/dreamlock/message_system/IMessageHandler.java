package com.dreamlock.message_system;

import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.message_system.messages.IMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by tommy on 31/5/2015.
 */
public interface IMessageHandler {
    void register(Map<Integer, IMessage> messages);

    void registerString(String string, int id);

    void print(List<OutputMessage> messageIds);
}
