package pl.coderslab.springcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.springcms.dao.ArticleDao;
import pl.coderslab.springcms.dao.AuthorDao;
import pl.coderslab.springcms.dao.CategoryDao;
import pl.coderslab.springcms.entity.Article;
import pl.coderslab.springcms.entity.Author;
import pl.coderslab.springcms.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping("/article/all")
    @ResponseBody
    public String showAll(){
        List<Article> articles = articleDao.getAll();
//        return categories;
        return articles.stream()
                .map(Article::toString)
                .collect(Collectors.joining(", \r\n <br>"));
    }

    @GetMapping("/article/addNew")
    @ResponseBody
    public String addNew(){

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

        Article article1 = new Article();
        article1.setTitle("Looks not good");
        article1.setAuthor(author1);
        article1.setContent("Not Magic");
        article1.setCreatedOn(new Article().getCreatedOn());
        article1.getCategories().add(category1);
        articleDao.create(article1);

        return "All was added";
    }

    //    - zapis encji
    @RequestMapping("/article/add")
    @ResponseBody
    public String add() {
        Article article = new Article();
        article.setTitle("Impossibles");
        article.setCreatedOn(new Article().getCreatedOn());
        articleDao.create(article);
        return "Id to:"
                + article.getId();
    }

    //    - edycja encji
    @RequestMapping("/article/update/{id}/{title}/")
    @ResponseBody
    public String update(@PathVariable long id, @PathVariable String title) {
        Article article = articleDao.getById(id);
        article.setTitle(title);
        article.setUpdatedOn(new Article().getUpdatedOn());
        articleDao.update(article);
        return article.toString();
    }

    //- pobieranie
    @RequestMapping("/article/get/{id}")
    @ResponseBody
    public String get(@PathVariable long id) {
        Article article = articleDao.getById(id);
        return article.toString();
    }

    //- usuwanie
    @RequestMapping("/article/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable long id) {
        Article article = articleDao.getById(id);
        articleDao.delete(article);
        return "deleted";
    }

    @GetMapping("article/add-form")
    public String addForm (Model model){
        model.addAttribute("article",new Article());
        return "/article/add-form";
    }

    @PostMapping("article/add-form")
    public String addFormPost(@ModelAttribute Article article){
        articleDao.create(article);
        return "redirect:all";
    }

    @ModelAttribute("authors")
    public List<Author> authors(){
        return authorDao.getAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryDao.getAll();
    }
}
