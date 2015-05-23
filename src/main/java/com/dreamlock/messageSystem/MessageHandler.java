package com.dreamlock.messageSystem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHandler implements Serializable{
    private Map<Integer, IMessage> messages;

    public MessageHandler() {
        messages = new HashMap<>();
    }

    public void register(Map<Integer, IMessage> messages) {
        this.messages.putAll(messages);
    }

    public void registerString(String string, int id) {
        IMessage message = new NDMessage(string, "");
        this.messages.put(id, message);
    }

    public void print(List<Integer> messageIds) {
        if (messageIds.contains(10000)) {
            messageIds.add(10002);
            messageIds.add(10002);

            for (int i = 0 ; i < messageIds.size() ; i++) {
                int messageId = messageIds.get(i);

                if(asItself(messageIds,i,messageId)) {
                    System.out.print(messageId);
                }
                else {
                    System.out.print(messages.get(messageId).getName());
                }
            }
        }
        else if (messageIds.contains(10001)) {
            messageIds.add(10003);
            messageIds.add(10003);
            for (Integer messageId : messageIds) {
                System.out.print(messages.get(messageId).getDescription());
            }
        }
        else if (messageIds.contains(10004)) {
            messageIds.add(10003);
            messageIds.add(10003);
            for (Integer messageId : messageIds) {
                if (!messages.get(messageId).getName().equals("")) {
                    System.out.print(messages.get(messageId).getName());
                }
                if (!messages.get(messageId).getDescription().equals("")) {
                    System.out.print(messages.get(messageId).getDescription());
                }
            }
        }
        else if (messageIds.contains(10006)) {
            messageIds.add(10007);
            messageIds.add(10007);
            for (Integer messageId : messageIds) {
                if (!messages.get(messageId).getEffect().equals("")) {
                    System.out.print(messages.get(messageId).getEffect());
                }
            }
        }
        else if (messageIds.contains(10005)) { //TODO: allagh se message
            messageIds.add(10003);
            messageIds.add(10003);
            System.out.println("Health: " + messageIds.get(1));
            System.out.println("Attack: " + messageIds.get(2));
            System.out.println("Defense: " + messageIds.get(3));
            System.out.println("Stamina: " + messageIds.get(4));
            System.out.println("Strength: " + messageIds.get(5));
            System.out.println("Agility: " + messageIds.get(6));
            System.out.println();
        }
        else {
            messageIds.add(10003);
            messageIds.add(10003);
            for (Integer messageId : messageIds) {
                if (!messages.get(messageId).getName().equals("")) {
                    System.out.println(messages.get(messageId).getName());
                }
                if (!messages.get(messageId).getDescription().equals("")) {
                    System.out.print(messages.get(messageId).getDescription());
                }
            }
        }
    }

    private boolean asItself(List<Integer> messageIds, int i, int messageId){
        int next_message_id_idx = i+1;
        if(next_message_id_idx < messageIds.size() && messageIds.contains(1301) && messageIds.get(next_message_id_idx) == 1309)
            return true;
        else if( next_message_id_idx < messageIds.size() && messageIds.get(next_message_id_idx) == 1308)
            return true;

        return false;
    }
}
