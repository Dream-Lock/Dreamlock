package com.dreamlock.core.message_system;


import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.message_system.messages.EffectMessage;
import com.dreamlock.core.message_system.messages.IMessage;
import com.dreamlock.core.message_system.messages.Message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringMessageHandler implements Serializable, IMessageHandler {
    private Map<Integer, IMessage> messages;

    public StringMessageHandler() {
        messages = new HashMap<>();
    }

    @Override
    public void register(Map<Integer, IMessage> messages) {
        this.messages.putAll(messages);
    }

    @Override
    public void registerString(String string, int id) {
        IMessage message = new Message(string, "");
        this.messages.put(id, message);
    }

    @Override
    public void print(List<OutputMessage> messageIds) {

        for (OutputMessage outputMessage : messageIds) {
            PrintStyle printStyle = null;
            IMessage message = messages.get(outputMessage.getId());
            try {
                printStyle = message.getPrintStyle();
            }
            catch (Exception e) {
                printStyle = outputMessage.getPrintStyle();
            }

            if (printStyle.equals(PrintStyle.DEFAULT) || outputMessage.getPrintStyle().equals(PrintStyle.NUMBER)) {
                printStyle = outputMessage.getPrintStyle();
            } else {
                printStyle = message.getPrintStyle();
            }

            switch (printStyle) {
                case ONLY_TITLE:
                    System.out.println(message.getName());
                    break;
                case ONLY_TITLE_IN_SAME_LINE:
                    System.out.print(message.getName());
                    break;
                case ONLY_DESCRIPTION:
                    System.out.println(message.getDescription());
                    break;
                case ONLY_DESCRIPTION_IN_SAME_LINE:
                    System.out.print(message.getDescription());
                    break;
                case TITLE_DESCRIPTION:
                    System.out.println(message.getName());
                    System.out.println(message.getDescription());
                    break;
                case ONLY_EFFECT:
                    System.out.println(((EffectMessage) message).getEffect());
                    break;
                case BREAK:
                    System.out.println("");
                    break;
                case NUMBER:
                    System.out.print(outputMessage.getId());
                case EMPTY:
                    break;
                case ARROW:
                    System.out.print(" --> ");
                    break;
                default:
                    System.out.println("Print ERROR!!! PLEASE CHANGE DEFAULT PRINTING STYLE!!!");
                    break;
            }
        }
    }
}
