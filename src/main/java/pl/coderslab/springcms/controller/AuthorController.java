package pl.coderslab.springcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.springcms.dao.AuthorDao;
import pl.coderslab.springcms.entity.Author;
import pl.coderslab.springcms.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorController {

    private AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @RequestMapping("/author/all")
    @ResponseBody
    public String showAll(){
        List<Author> authors = authorDao.getAll();
//        return categories;
        return authors.stream()
                .map(Author::toString)
                .collect(Collectors.joining(", \r\n <br>"));
    }

    //    - zapis encji
    @RequestMapping("/author/add")
    @ResponseBody
    public String add() {
        Author author = new Author();
        author.setFirstName("Programing");
        author.setLastName("U are clever that choose programing");
        authorDao.create(author);
        return "Id to:"
                + author.getId();
    }

    //    - edycja encji
    @RequestMapping("/author/update/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String update(@PathVariable long id, @PathVariable String firstName, @PathVariable String lastName) {
        Author author = authorDao.getById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }

    //- pobieranie
    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String get(@PathVariable long id) {
        Author author = authorDao.getById(id);
        return author.toString();
    }

    //- usuwanie
    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable long id) {
        Author author = authorDao.getById(id);
        authorDao.delete(author);
        return "deleted";
    }

    @GetMapping("author/add-form")
    public String addForm (Model model){
        model.addAttribute("author",new Author());
        return "/author/add-form";
    }

    @PostMapping("author/add-form")
    public String addFormPost(@ModelAttribute Author author){
        authorDao.create(author);
        return "redirect:all";
    }
}
