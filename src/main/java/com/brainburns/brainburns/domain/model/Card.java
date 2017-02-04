package com.brainburns.brainburns.domain.model;

/**
 * Created by arthan on 31.01.2017. | Project brainburns
 */

public class Card {
    private long id = -1; // initialize with incorrect value
    private long deskId;
    private String writing;
    private String transcription;
    private String meaning;

    public Card() {
    }

    public Card(String writing, String transcription, String meaning) {
        this.writing = writing;
        this.transcription = transcription;
        this.meaning = meaning;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setDeskId(long deskId) {
        this.deskId = deskId;
    }

    public long getDeskId() {
        return deskId;
    }
}
