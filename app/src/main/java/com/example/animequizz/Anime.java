package com.example.animequizz;

import java.io.Serializable;

public class Anime implements Serializable {
    String name;
    String urlImage;

    public Anime(String name, String urlImage){
        this.name = name;
        this.urlImage= urlImage;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
