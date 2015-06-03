package com.dreamlock.core.handlers;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.message_system.constants.UserQuestions;
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

        if (hasForbiddenWords) {
            Random random = new Random();
            int randomNumber = random.nextInt(5);
            switch (randomNumber) {
                case 1:
                    messageIds.add(new OutputMessage(2107, PrintStyle.ONLY_TITLE));
                    break;
                case 2:
                    messageIds.add(new OutputMessage(2106, PrintStyle.ONLY_TITLE));
                    break;
                case 3:
                    messageIds.add(new OutputMessage(2105, PrintStyle.ONLY_TITLE));
                    break;
                case 4:
                    messageIds.add(new OutputMessage(2104, PrintStyle.ONLY_TITLE));
                    break;
                default:
                    messageIds.add(new OutputMessage(2103, PrintStyle.ONLY_TITLE));
            }
            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }

        if (hasQuestion) {
            String question = parsedJsonObject.get("question").getAsString();
            List<String> possibleQuestions = UserQuestions.INSTANCE.getQuestions();

            return questionsHandle(question, possibleQuestions);
        }


        Integer correctWords = parsedJsonObject.get("correctWords").getAsInt();

        if (correctWords == 0) {
            if (!parsedJsonObject.get("empty").getAsBoolean()) {
                messageIds.add(new OutputMessage(2101, PrintStyle.ONLY_TITLE));
                messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            }
            else {
                messageIds.add(new OutputMessage(2067, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            }

        }
        else {
            String correctCommand = parsedJsonObject.get("correctCommand").getAsString();

            if (correctWords == 100) {
                if (correctCommand.equals("go")) {
                    messageIds.add(new OutputMessage(1008, PrintStyle.ONLY_TITLE));
                } else {
                    gameContext.registerMessage("What do you want me to " + correctCommand, 9001);
                    messageIds.add(new OutputMessage(9001, PrintStyle.ONLY_TITLE));
                }
            } else {
                gameContext.registerMessage("I understood as far as wanting me to " + correctCommand, 9001);
                messageIds.add(new OutputMessage(9001, PrintStyle.ONLY_TITLE));
            }
            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
        }
        return messageIds;
    }

    public List<OutputMessage> questionsHandle (String question , List<String> possibleQuestions) {
        List<OutputMessage> messageIds = new ArrayList<>();
        Random random = new Random();

        if (question.equals(possibleQuestions.get(6)) || question.equals(possibleQuestions.get(7)) ) {
            Integer randomInt = random.nextInt(2);
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2200, PrintStyle.ONLY_TITLE));
                    break;
                default:
                    messageIds.add(new OutputMessage(2201, PrintStyle.ONLY_TITLE));
            }
            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(8)) || question.equals(possibleQuestions.get(16))) {
            Integer randomInt = random.nextInt(5);
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2202, PrintStyle.ONLY_TITLE));
                    break;
                case 2:
                    messageIds.add(new OutputMessage(2213, PrintStyle.ONLY_TITLE));
                    break;
                case 3:
                    messageIds.add(new OutputMessage(2214, PrintStyle.ONLY_TITLE));
                    break;
                case 4:
                    messageIds.add(new OutputMessage(2215, PrintStyle.ONLY_TITLE));
                    break;
                default:
                    messageIds.add(new OutputMessage(2203,PrintStyle.ONLY_TITLE));
            }

            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(9))) {
            Integer randomInt = random.nextInt(5);
            Integer age = random.nextInt(100);
            gameContext.registerMessage("I am " + age + " years old" , 9300);
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(9300, PrintStyle.ONLY_TITLE));
                    break;
                case 2:
                    messageIds.add(new OutputMessage(2216, PrintStyle.ONLY_TITLE));
                    break;
                case 3:
                    messageIds.add(new OutputMessage(2217, PrintStyle.ONLY_TITLE));
                    break;
                case 4:
                    messageIds.add(new OutputMessage(2218, PrintStyle.ONLY_TITLE));
                    break;
                default:
                    messageIds.add(new OutputMessage(2210, PrintStyle.ONLY_TITLE));
            }

            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(10))) {
            Integer randomInt = random.nextInt(2);
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2205, PrintStyle.ONLY_TITLE));
                    break;
                default:
                    messageIds.add(new OutputMessage(2204, PrintStyle.ONLY_TITLE));
            }

            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(11))) {

            messageIds.add(new OutputMessage(2207, PrintStyle.ONLY_TITLE));
            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }
        if (question.equals(possibleQuestions.get(12))) {
            String name = gameContext.getPlayer().getName();
            gameContext.registerMessage("You are '" + name + "'" , 9301);

            Integer randomInt = random.nextInt(2);
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2206, PrintStyle.ONLY_TITLE));
                    break;
                default:
                    messageIds.add(new OutputMessage(9301, PrintStyle.ONLY_TITLE));
            }
            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }

        if (question.equals(possibleQuestions.get(13)) || question.equals(possibleQuestions.get(14)) || question.equals(possibleQuestions.get(15))) {
            Integer randomInt = random.nextInt(3);
            switch (randomInt) {
                case 1:
                    messageIds.add(new OutputMessage(2212, PrintStyle.ONLY_TITLE));
                    break;
                case 2:
                    messageIds.add(new OutputMessage(2219, PrintStyle.ONLY_TITLE));
                    break;
                default:
                    messageIds.add(new OutputMessage(2211, PrintStyle.ONLY_TITLE));
            }
            messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
            return messageIds;
        }
        for (int i = 0; i < 6; i++) {
            if (question.contains(possibleQuestions.get(i))) {
                messageIds.add(new OutputMessage(2298, PrintStyle.ONLY_TITLE));
                messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
                return messageIds;
            }
        }

        messageIds.add(new OutputMessage(2299, PrintStyle.ONLY_TITLE));
        messageIds.add(new OutputMessage(0, PrintStyle.BREAK));
        return messageIds;
    }
}
