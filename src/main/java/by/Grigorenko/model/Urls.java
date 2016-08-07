package by.Grigorenko.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andrei on 05.08.2016.
 */
//class  describes url-entity
@Entity
@Table(name = "urls")
public class Urls implements Serializable {
    @Id
    @Column(name = "id_url")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_long_url")
    private String longUrl;
    @Column(name = "name_short_url")
    private String shortUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Urls urls = (Urls) o;

        if (id != urls.id) return false;
        if (!longUrl.equals(urls.longUrl)) return false;
        return shortUrl.equals(urls.shortUrl);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + longUrl.hashCode();
        result = 31 * result + shortUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Urls{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
