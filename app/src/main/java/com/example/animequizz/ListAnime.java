package com.example.animequizz;

import java.util.List;

public class ListAnime {

    List<Anime> animeItem;


    public ListAnime(List<Anime> animeItem){
        this.animeItem = animeItem;
    }

    public List<Anime> getAnimeItem() {
        return animeItem;
    }

    public void setAnimeItem(List<Anime> animeItem) {
        this.animeItem = animeItem;
    }
}
