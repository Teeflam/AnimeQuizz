package com.example.animequizz;

import java.io.Serializable;

public class Anime implements Serializable {
    // Attribut
    String name;
    String urlImage;

    // Constructor
    public Anime(String name, String urlImage){
        this.name = name;
        this.urlImage= urlImage;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
