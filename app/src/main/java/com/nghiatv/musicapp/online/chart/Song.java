package com.nghiatv.musicapp.online.chart;

public class Song {
    private int no;
    private int color;
    private float rank;
    private int avatar;
    private String song;
    private String songer;

    public Song(int no, int color, float rank, int avatar, String song, String songer) {
        this.no = no;
        this.color = color;
        this.rank = rank;
        this.avatar = avatar;
        this.song = song;
        this.songer = songer;
    }

    public Song(float rank, int avatar, String song, String songer) {
        this.rank = rank;
        this.avatar = avatar;
        this.song = song;
        this.songer = songer;
    }

    public int getNo() {
        return no;
    }

    public int getColor() {
        return color;
    }

    public float getRank() {
        return rank;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getSong() {
        return song;
    }

    public String getSonger() {
        return songer;
    }
}
