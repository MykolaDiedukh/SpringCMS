package pl.coderslab.springcms.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.springcms.dao.AuthorDao;
import pl.coderslab.springcms.entity.Author;

public class AuthorConverter implements Converter<String, Author> {
    @Autowired
    private AuthorDao authorDao;

    @Override
    public Author convert(String id) {
        return this.authorDao.findById(Long.parseLong(id));
    }
}