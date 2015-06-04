package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;

import java.util.List;
import java.util.Map;

public interface ICommand {
    List<OutputMessage> execute(IGameContext gameContext);

    List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words);
}
