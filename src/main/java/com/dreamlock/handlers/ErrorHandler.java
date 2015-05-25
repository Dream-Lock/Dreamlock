package com.dreamlock.handlers;

import com.dreamlock.game.IGameContext;
import com.dreamlock.messageSystem.UserQuestions;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ErrorHandler implements IHandler {
    private JsonObject parsedJsonObject;
    private IGameContext gameContext;

    public ErrorHandler(JsonObject parsedJsonObject, IGameContext gameContext) {
        this.parsedJsonObject = parsedJsonObject;
        this.gameContext = gameContext;
    }

    @Override
    public List<Integer> handle() {
        List<Integer> messageIds = new ArrayList<>();

        Boolean hasForbiddenWords = parsedJsonObject.get("forbidden").getAsBoolean();
        Boolean hasQuestion = parsedJsonObject.get("has_question").getAsBoolean();
        messageIds.add(10000); // only title

        if (hasForbiddenWords) {
            Random random = new Random();
            int randomNumber = random.nextInt(5);
            switch (randomNumber) {
                case 1:
                    messageIds.add(2107);
                    break;
                case 2:
                    messageIds.add(2106);
                    break;
                case 3:
                    messageIds.add(2105);
                    break;
                case 4:
                    messageIds.add(2104);
                    break;
                default:
                    messageIds.add(2103);
            }
            return messageIds;
        }

        if (hasQuestion) {
            String question = parsedJsonObject.get("question").getAsString();
            List<String> possibleQuestions = UserQuestions.INSTANCE.getQuestions();

            return questionsHandle(question, possibleQuestions);
        }


        Integer correctWords = parsedJsonObject.get("correctWords").getAsInt();

        if (correctWords == 0) {
            messageIds.add(2101);
        } else {
            String correctCommand = parsedJsonObject.get("correctCommand").getAsString();

            if (correctWords == 100) {
                if (correctCommand.equals("go")) {
                    messageIds.add(1008);
                } else {
                    gameContext.registerMessage("What do you want me to " + correctCommand, 9001);
                    messageIds.add(9001);
                }
            } else {
                gameContext.registerMessage("I understood as far as wanting me to " + correctCommand, 9001);
                messageIds.add(9001);
            }
        }
        return messageIds;
    }

    public List<Integer> questionsHandle (String question , List<String> possibleQuestions) {
        List<Integer> messageIds = new ArrayList<>();

        if (question.equals(possibleQuestions.get(6)) || question.equals(possibleQuestions.get(7)) ) {
            Random random = new Random();
            Integer randomInt = random.nextInt(2);
            switch (randomInt) {
                case 1:
                    messageIds.add(2200);
                    break;
                default:
                    messageIds.add(2201);
            }
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(8)) || question.equals(possibleQuestions.get(16))) {
            Random random = new Random(2);
            Integer randomInt = random.nextInt();
            switch (randomInt) {
                case 1:
                    messageIds.add(2202);
                    break;
                default:
                    messageIds.add(2203);
            }

            return messageIds;
        }
        if (question.equals(possibleQuestions.get(9))) {

            Random random = new Random();
            Integer randomInt = random.nextInt(2);
            Integer age = random.nextInt(100);
            gameContext.registerMessage("I am " + age + " years old" , 9300);
            switch (randomInt) {
                case 1:
                    messageIds.add(9300);
                    break;
                default:
                    messageIds.add(2210);
            }

            return messageIds;
        }
        if (question.equals(possibleQuestions.get(10))) {
            Random random = new Random();
            Integer randomInt = random.nextInt(2);
            switch (randomInt) {
                case 1:
                    messageIds.add(2205);
                    break;
                default:
                    messageIds.add(2204);
            }

            return messageIds;
        }
        if (question.equals(possibleQuestions.get(11))) {

            messageIds.add(2207);
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(12))) {
            String name = gameContext.getPlayer().getName();
            gameContext.registerMessage("You are '" + name + "'" , 9301);

            Random random = new Random();
            Integer randomInt = random.nextInt();
            switch (randomInt) {
                case 1:
                    messageIds.add(2206);
                    break;
                default:
                    messageIds.add(9301);
            }

            return messageIds;
        }

        if (question.equals(possibleQuestions.get(13)) || question.equals(possibleQuestions.get(14)) || question.equals(possibleQuestions.get(15))) {
            Random random = new Random();
            Integer randomInt = random.nextInt();

            switch (randomInt) {
                case 1:
                    messageIds.add(2212);
                    break;
                default:
                    messageIds.add(2211);
            }

            return messageIds;
        }
        for (int i = 0; i < 6; i++) {
            if (question.contains(possibleQuestions.get(i))) {

                messageIds.add(2208);
                return messageIds;
            }
        }

        messageIds.add(2209);
        return messageIds;
    }
}
