package pl.coderslab.springcms.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.springcms.entity.Article;
import pl.coderslab.springcms.entity.Author;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class ArticleDao {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Article article) {
        entityManager.persist(article);
    }

    public void update(Article article) {
        entityManager.merge(article);
    }

    public Article getById(Long id) {
        return entityManager.find(Article.class, id);
    }

    public void delete(Article article) {
        entityManager.remove(entityManager.contains(article) ? article : entityManager.merge(article));
    }

    public List<Article> getAll(){
        Query query = this.entityManager.createQuery("SELECT a FROM Article a");
        return query.getResultList();
    }

    public List<Article> getLastFive(){
        Query query = this.entityManager.createQuery("SELECT a FROM Article a ORDER BY a.createdOn DESC").setMaxResults(5);
        return query.getResultList();
    }



}
