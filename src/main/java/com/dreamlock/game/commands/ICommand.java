package com.dreamlock.game.commands;

/**
 * Created by tommy on 28/4/2015.
 */
public interface ICommand {
    String execute();

    String execute(String[] strings);
}
