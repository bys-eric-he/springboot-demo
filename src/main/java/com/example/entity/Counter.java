package com.example.entity;

public class Counter {

    private double multiplier;
    private Piano piano;
    private User user;
    private String song;

    public Counter() {
    }

    public Counter(double multiplier, String song, Piano piano) {
        this.multiplier = multiplier;
        this.song = song;
        this.piano = piano;
    }

    public Counter(double multiplier, String song, User user) {
        this.multiplier = multiplier;
        this.song = song;
        this.user = user;
    }

    public Counter(double multiplier, String song) {
        this.multiplier = multiplier;
        this.song = song;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }


    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Piano getPiano() {
        return piano;
    }
}