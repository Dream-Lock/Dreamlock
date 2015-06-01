package com.dreamlock.core.game.models;

import com.dreamlock.core.message_system.constants.PrintStyle;

public class OutputMessage {
    private Integer id;
    private PrintStyle printStyle;

    public OutputMessage(Integer id, PrintStyle printStyle) {
        this.id = id;
        this.printStyle = printStyle;
    }

    public OutputMessage(Integer id) {
        this.id = id;
        this.printStyle = PrintStyle.DEFAULT;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
