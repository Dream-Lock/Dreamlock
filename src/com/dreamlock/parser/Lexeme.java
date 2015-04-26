package com.dreamlock.parser;

public class Lexeme {
    private int id;
    private String description;
    private TokenType tokenType;

    public Lexeme(String description, int id, TokenType tokenType) {
        this.description = description;
        this.id = id;
        this.tokenType = tokenType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}

