package com.example.animequizz;

public class PresentationGenre {
    private String genreName;
    private String imageUrl;

    public PresentationGenre(String imageUrl, String genreName) {
        this.genreName = genreName;
        this.imageUrl = imageUrl;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
