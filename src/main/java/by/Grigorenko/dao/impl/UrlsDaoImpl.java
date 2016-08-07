package by.Grigorenko.dao.impl;

import by.Grigorenko.dao.UrlsDao;
import by.Grigorenko.model.Urls;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andrei on 05.08.2016.
 */
@Repository
@Transactional
public class UrlsDaoImpl implements UrlsDao {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Urls getUrl(int id) {
        return getSession().get(Urls.class, id);
    }

    @Override
    public void deleteUrl(Urls url) {
        getSession().delete(url);
    }

    @Override
    public void addUrl(Urls url) {
        getSession().persist(url);

    }

    @Override
    public void editUrl(Urls url) {
        getSession().merge(url);

    }

    @Override
    public Urls getUrlByLongUrl(String url) {
        String query = "FROM Urls urls WHERE urls.longUrl like '" + url + "'";
        List<Urls> urlsList = getSession().createQuery(query).list();
        if (urlsList.size() == 0) return null;
        else return urlsList.get(0);
    }

    @Override
    public Urls getUrlByShortUrl(String url) {
        String query = "FROM Urls urls WHERE urls.shortUrl like '" + url + "'";
        List<Urls> urlsList = getSession().createQuery(query).list();
        if (urlsList.size() == 0) return null;
        else return urlsList.get(0);
    }
}
