package pl.coderslab.springcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.springcms.dao.ArticleDao;
import pl.coderslab.springcms.dao.AuthorDao;
import pl.coderslab.springcms.dao.CategoryDao;
import pl.coderslab.springcms.entity.Article;
import pl.coderslab.springcms.entity.Author;
import pl.coderslab.springcms.entity.Category;

@Controller
public class ArticleController {
    private AuthorDao authorDao;
    private ArticleDao articleDao;
    private CategoryDao categoryDao;

    public ArticleController(AuthorDao authorDao, ArticleDao articleDao, CategoryDao categoryDao){
        this.authorDao = authorDao;
        this.articleDao = articleDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/article/add")
    @ResponseBody
    public String add(){

        Author author = new Author();
        author.setFirstName("Majk");
        author.setLastName("Delta");
        authorDao.create(author);
        Author author1 = new Author();
        author1.setFirstName("Piotr");
        author1.setLastName("Walentajn");
        authorDao.create(author1);

        Category category = new Category();
        category.setName("Horor");
        category.setDescription("U can pipi");
        categoryDao.create(category);
        Category category1 = new Category();
        category1.setName("Komedy");
        category1.setDescription("U can't pipi");
        categoryDao.create(category1);

        Article article = new Article();
        article.setTitle("Looks good");
        article.setAuthor(author);
        article.setContent("Magic");
        article.setCreatedOn(new Article().getCreatedOn());
        article.getCategories().add(category);
        articleDao.create(article);

        return "All was added";
    }
}
