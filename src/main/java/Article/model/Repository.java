package Article.model;

import Article.controller.Pagination;
import util.FileManager;

import java.util.ArrayList;
import java.util.List;

public interface Repository {
    void insert(String title, String body, int memberId, int hit);

    void delete(Article article);

    Article findById(int id);

    ArrayList<Article> findAllArticles();

    List<Article> findArticlesByPage(Pagination pagination);


    ArrayList<Article> findByTitle(String keyword);

    void update(int id, String title, String body);
    List<Article> getSortedArticles(int sortType, int direction, Pagination pagination);
}
