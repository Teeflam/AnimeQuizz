package com.example.animequizz;

public class PresentationGenre {
    private String genreName;
    private String imageUrl;
    private int genreNum;

    public PresentationGenre(String genreName, String imageUrl, int genreNum) {
        this.genreName = genreName;
        this.imageUrl = imageUrl;
        this.genreNum = genreNum;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getGenreNum() {
        return genreNum;
    }
}
