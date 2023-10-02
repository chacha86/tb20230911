package Article.model;

import Article.controller.Pagination;
import Article.controller.sort.SortFactory;
import util.Util;

import java.util.*;

// Dao
// Repository
public class ArticleRepository implements Repository {

    private static ArrayList<Article> articles = new ArrayList<>();
    private int lastArticleId = 4;

    public ArticleRepository() {

        if (articles.size() > 0) {
            return;
        }

        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 1, 10, Util.getCurrentDate());
        Article a2 = new Article(2, "자바 질문좀 할게요~.", "냉무", 2,  102, Util.getCurrentDate());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 1,  55, Util.getCurrentDate());

        articles.add(a2);
        articles.add(a3);
        articles.add(a1);
    }

    public int getTotalCount() {
        return articles.size();
    }

    public void insert(String title, String content, int memberId, int hit) {
        Article article = new Article(lastArticleId, title, content, memberId, hit, Util.getCurrentDate());
        articles.add(article);
        lastArticleId++;
    }

    public void delete(Article article) {
        //articles.remove(i); // 위치 기반으로 삭제
        articles.remove(article);// 값 기반 삭제
    }

    public ArrayList<Article> findAllArticles() {
        return articles;
    }


    public Article findById(int id) {

        Article target = null;

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (id == article.getId()) {
                target = article;
            }
        }

        return target;
    }

    public ArrayList<Article> findByTitle(String keyword) {
        ArrayList<Article> searchedArticles = new ArrayList<>();

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            String title = article.getTitle();

            if (title.contains(keyword)) {
                searchedArticles.add(article);
            }
        }

        return searchedArticles;
    }

    public void update(int articleId, String title, String content) {
        Article article = findById(articleId);
        article.setTitle(title);
        article.setContent(content);
    }

    @Override
    public List<Article> getSortedArticles(int sortType, int direction, Pagination pagination) {
        return null;
    }

    public ArrayList<Article> findArticlesByPage(Pagination pagination) {
        ArrayList<Article> searchedArticles = new ArrayList<>();

        int startIndex = pagination.getStartIdx();
        int endIndex = pagination.getEndIdx();

        for (int i = startIndex; i < endIndex; i++) {
            Article article = articles.get(i);
            searchedArticles.add(article);
        }

        return searchedArticles;
    }

    public void sortArticles(int sortTarget, int direction, Pagination pagination) {
        Collections.sort(articles, new SortFactory().getSort(sortTarget).setDirection(direction));
    }
}

