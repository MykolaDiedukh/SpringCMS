package pl.coderslab.springcms.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.springcms.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Author author) {
        entityManager.persist(author);
    }

    public void update(Author author) {
        entityManager.merge(author);
    }

    public Author getById(Long id) {
        return entityManager.find(Author.class, id);
    }

    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    public List<Author> getAll(){
        Query query = this.entityManager.createQuery("SELECT a FROM Author a");
        return query.getResultList();
    }
}
