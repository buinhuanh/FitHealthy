package com.nhn.fitness.data.model.chat;

import java.util.List;

public class MessageModel {
    private List<Content> contents;

    public MessageModel(List<Content> contents) {
        this.contents = contents;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
