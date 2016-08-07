package by.Grigorenko.dao;

import by.Grigorenko.model.Urls;

/**
 * Created by Andrei on 05.08.2016.
 */
//
public interface UrlsDao {
    Urls getUrl(int id);

    void deleteUrl(Urls url);

    void addUrl(Urls url);

    void editUrl(Urls url);

    Urls getUrlByLongUrl(String url);

    Urls getUrlByShortUrl(String url);
}
