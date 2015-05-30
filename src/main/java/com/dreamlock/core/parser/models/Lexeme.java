package com.dreamlock.core.parser.models;

import com.dreamlock.core.parser.constants.TokenType;

public class Lexeme {
    private int id;
    private String typedString;
    private TokenType tokenType;
    private int uniqueId;

    public Lexeme(int id, String typedString, TokenType tokenType, int uniqueId) {
        this.id = id;
        this.typedString = typedString;
        this.tokenType = tokenType;
        this.uniqueId = uniqueId;
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

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }
}

