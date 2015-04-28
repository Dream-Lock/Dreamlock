package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.states.IState;

public class CanNotPickUp implements IState {
    @Override
    public void doAction() {
        System.out.println("I cant pick up this!!");
    }
}
