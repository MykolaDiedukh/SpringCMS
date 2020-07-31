package pl.coderslab.springcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.springcms.dao.CategoryDao;
import pl.coderslab.springcms.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CategoryController {

    private CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }
    @RequestMapping("/category/all")
    @ResponseBody
    public String showAll(){
        List<Category> categories = categoryDao.getAll();
//        return categories;
        return categories.stream()
                .map(Category::toString)
                .collect(Collectors.joining(", \r\n <br>"));
    }

    //    - zapis encji
    @RequestMapping("/category/add")
    @ResponseBody
    public String add() {
        Category category = new Category();
        category.setName("Programing");
        category.setDescription("U are clever that choose programing");
        categoryDao.create(category);
        return "Id to:"
                + category.getId();
    }

    //    - edycja encji
    @RequestMapping("/category/update/{id}/{name}/{description}")
    @ResponseBody
    public String update(@PathVariable long id, @PathVariable String name, @PathVariable String description) {
        Category category = categoryDao.getById(id);
        category.setName(name);
        category.setDescription(description);
        categoryDao.update(category);
        return category.toString();
    }

    //- pobieranie
    @RequestMapping("/category/get/{id}")
    @ResponseBody
    public String get(@PathVariable long id) {
        Category category = categoryDao.getById(id);
        return category.toString();
    }

    //- usuwanie
    @RequestMapping("/category/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable long id) {
        Category category = categoryDao.getById(id);
        categoryDao.delete(category);
        return "deleted";
    }

    @GetMapping("category/add-form")
    public String addForm (Model model){
        model.addAttribute("category",new Category());
        return "/category/add-form";
    }

    @PostMapping("category/add-form")
    public String addFormPost(@ModelAttribute Category category){
        categoryDao.create(category);
        return "redirect:all";
    }
}
