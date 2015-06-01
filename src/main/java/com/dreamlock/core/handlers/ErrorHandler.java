package com.dreamlock.core.handlers;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
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
    public List<OutputMessage> handle() {
        List<OutputMessage> messageIds = new ArrayList<>();

        Boolean hasForbiddenWords = parsedJsonObject.get("forbidden").getAsBoolean();
        Boolean hasQuestion = parsedJsonObject.get("has_question").getAsBoolean();
        messageIds.add(new OutputMessage(10000)); // only title

        if (hasForbiddenWords) {
            Random random = new Random();
            int randomNumber = random.nextInt(5);
            switch (randomNumber) {
                case 1:
                    messageIds.add(new OutputMessage(2107));
                    break;
                case 2:
                    messageIds.add(new OutputMessage(2106));
                    break;
                case 3:
                    messageIds.add(new OutputMessage(2105));
                    break;
                case 4:
                    messageIds.add(new OutputMessage(2104));
                    break;
                default:
                    messageIds.add(new OutputMessage(2103));
            }
            return messageIds;
        }

        if (hasQuestion) {
            String question = parsedJsonObject.get("question").getAsString();
//            List<String> possibleQuestions = UserQuestions.INSTANCE.getQuestions();

//            return questionsHandle(question, possibleQuestions);
        }


        Integer correctWords = parsedJsonObject.get("correctWords").getAsInt();

        if (correctWords == 0) {
            messageIds.add(new OutputMessage(2101));
        } else {
            String correctCommand = parsedJsonObject.get("correctCommand").getAsString();

            if (correctWords == 100) {
                if (correctCommand.equals("go")) {
                    messageIds.add(new OutputMessage(1008));
                } else {
                    gameContext.registerMessage("What do you want me to " + correctCommand, 9001);
                    messageIds.add(new OutputMessage(9001));
                }
            } else {
                gameContext.registerMessage("I understood as far as wanting me to " + correctCommand, 9001);
                messageIds.add(new OutputMessage(9001));
            }
        }
        return messageIds;
    }

    public List<OutputMessage> questionsHandle (String question , List<String> possibleQuestions) {
        List<OutputMessage> messageIds = new ArrayList<>();

        if (question.equals(possibleQuestions.get(6)) || question.equals(possibleQuestions.get(7)) ) {
            Random random = new Random();
            Integer randomInt = random.nextInt(2);
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2200));
                    break;
                default:
                    messageIds.add(new OutputMessage(2201));
            }
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(8)) || question.equals(possibleQuestions.get(16))) {
            Random random = new Random(2);
            Integer randomInt = random.nextInt();
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2202));
                    break;
                default:
                    messageIds.add(new OutputMessage(2203));
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
                    messageIds.add(new OutputMessage(9300));
                    break;
                default:
                    messageIds.add(new OutputMessage(2210));
            }

            return messageIds;
        }
        if (question.equals(possibleQuestions.get(10))) {
            Random random = new Random();
            Integer randomInt = random.nextInt(2);
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2205));
                    break;
                default:
                    messageIds.add(new OutputMessage(2204));
            }

            return messageIds;
        }
        if (question.equals(possibleQuestions.get(11))) {

            messageIds.add(new OutputMessage(2207));
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(12))) {
            String name = gameContext.getPlayer().getName();
            gameContext.registerMessage("You are '" + name + "'" , 9301);

            Random random = new Random();
            Integer randomInt = random.nextInt();
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2206));
                    break;
                default:
                    messageIds.add(new OutputMessage(9301));
            }

            return messageIds;
        }

        if (question.equals(possibleQuestions.get(13)) || question.equals(possibleQuestions.get(14)) || question.equals(possibleQuestions.get(15))) {
            Random random = new Random();
            Integer randomInt = random.nextInt();

            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2212));
                    break;
                default:
                    messageIds.add(new OutputMessage(2211));
            }

            return messageIds;
        }
        for (int i = 0; i < 6; i++) {
            if (question.contains(possibleQuestions.get(i))) {

                messageIds.add(new OutputMessage(2208));
                return messageIds;
            }
        }

        messageIds.add(new OutputMessage(2209));
        return messageIds;
    }
}
