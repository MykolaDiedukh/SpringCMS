package pl.coderslab.springcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.springcms.dao.ArticleDao;
import pl.coderslab.springcms.entity.Article;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomePageController {
    private ArticleDao articleDao;

    public HomePageController(ArticleDao articleDao){
        this.articleDao = articleDao;
    }

    @GetMapping("")
    @ResponseBody
    public List<Article> home(){
        List<Article> articles = articleDao.getLastFive();
        return articles;
    }
}
