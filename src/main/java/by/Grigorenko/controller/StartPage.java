package by.Grigorenko.controller;


import by.Grigorenko.dao.UrlsDao;
import by.Grigorenko.model.Urls;
import by.Grigorenko.util.GenerationShortUrl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class StartPage {
    @Autowired
    private UrlsDao urlsDao;
    @Autowired
    private GenerationShortUrl generationShortUrl;

    //this just provides  start page
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView main(Model model) {
        model.addAttribute("url", new Urls());
        return new ModelAndView("main");
    }

    //this  generates a short url
    @RequestMapping(value = "generationShortUrl", method = RequestMethod.POST)
    public ModelAndView generationShortUrl(@ModelAttribute("url") Urls url, Model model, BindingResult result) throws NotFoundException {
        if (urlsDao.getUrlByLongUrl(url.getLongUrl()) == null) {
            urlsDao.addUrl(url);
            url.setShortUrl(generationShortUrl.convertTo62Base(url.getId()));
            urlsDao.editUrl(url);
            model.addAttribute("url", url);

        } else {
            url = urlsDao.getUrlByLongUrl(url.getLongUrl());
            url.setShortUrl(generationShortUrl.convertTo62Base(url.getId()));
            urlsDao.editUrl(url);
            model.addAttribute("url", url);


        }

        return new ModelAndView("main");
    }

    //this  redirects to original url
    @RequestMapping(value = "clipper/{shortUrl}", method = RequestMethod.GET)
    public RedirectView redirectToAnotherUrl(@PathVariable String shortUrl) {
        Urls urls = urlsDao.getUrlByShortUrl(shortUrl);
        return new RedirectView(urls.getLongUrl());
    }

    //this  returns  original url
    @RequestMapping(value = "returnLongUrl", method = RequestMethod.GET)
    public ModelAndView returnLongUrl(@ModelAttribute("url") Urls url, Model model, BindingResult result) throws NotFoundException {
        String[] output = url.getShortUrl().split("localhost:8080/clipper/");
        url.setShortUrl(output[1]);
        if (urlsDao.getUrlByShortUrl(url.getShortUrl()) != null) {
            Urls url1 = urlsDao.getUrlByShortUrl(url.getShortUrl());
            model.addAttribute("url", url1);

        }

        return new ModelAndView("main");
    }
}