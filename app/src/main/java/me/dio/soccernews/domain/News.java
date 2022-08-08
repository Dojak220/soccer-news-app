package me.dio.soccernews.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News {
    @PrimaryKey
    public int id;
    public String title;
    public String subtitle;
    public String image;
    public String link;
    public boolean favorite;

    public News(int id, String title, String subtitle, String image, String link, boolean favorite) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.link = link;
        this.favorite = favorite;
    }

}
