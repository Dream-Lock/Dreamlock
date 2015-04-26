package com.dreamlock.parsers.commandParser.models;

import com.dreamlock.parsers.commandParser.constants.TokenType;

public class Lexeme {
    private int id;
    private String typedString;
    private TokenType tokenType;

    public Lexeme(int id, String typedString, TokenType tokenType) {
        this.id = id;
        this.typedString = typedString;
        this.tokenType = tokenType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypedString() {
        return typedString;
    }

    public void setTypedString(String typedString) {
        this.typedString = typedString;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}

