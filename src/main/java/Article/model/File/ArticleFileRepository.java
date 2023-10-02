package Article.model.File;

import Article.controller.Pagination;
import Article.controller.sort.SortFactory;
import Article.model.Article;
import Article.model.Repository;
import util.FileManager;
import util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleFileRepository implements Repository {
    private static final String FILE_PATH = "data/article/";
    private static final String LAST_ID_PATH = "data/articleLastId/";
    private static final String LAST_ID_FILE_NAME = "articleLastId.txt";
    private static final String LAST_ID_FILE_PATH = LAST_ID_PATH + LAST_ID_FILE_NAME;
    private static final String LAST_ID_INIT_VAL = "0";
    private static final String ARTICLE_FILE_PREFIX = "article_";
    private static final String ARTICLE_FILE_SUFFIX = ".json";
    private static final int START_NO = 0;
    private static final int END_NO = Integer.MAX_VALUE - 1;

    private List<Article> orderTargetList = new ArrayList<>();
    public ArticleFileRepository() {

        if (getLastArticleId() > 0) {
            return;
        }

        insert("안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 1, 10);
        insert("자바 질문좀 할게요~.",  "냉무", 2, 102);
        insert("정처기 따야되나요?", "냉무", 1, 55);
    }

    public int getLastArticleId() {
        String result = FileManager.loadFromFileOrNull(LAST_ID_FILE_PATH);
        if (result == null || result.isEmpty()) {
            FileManager.saveToFile(LAST_ID_FILE_PATH, LAST_ID_INIT_VAL);
            result = LAST_ID_INIT_VAL;
        }
        return Integer.parseInt(result);
    }

    public void insert(String title, String content, int memberId, int hit) {
        int articleId = getLastArticleId() + 1;
        FileManager.saveToFile(LAST_ID_FILE_PATH, String.valueOf(articleId));

        Article article = new Article(articleId, title, content, memberId, hit, Util.getCurrentDate());
        String jsonStr = Util.convertObjectToJson(article);
        String path = getArticleFilePath(article.getId());
        FileManager.saveToFile(path, jsonStr);
    }

    private String getArticleFilePath(int id) {
        return FILE_PATH + ARTICLE_FILE_PREFIX + id + ARTICLE_FILE_SUFFIX;
    }

    public static int getTotalCount() {

        // 개수를 메타데이터로 저장하는 방법에 대해 나중에 생각해보기.
        return FileManager.getFilesInRange(FILE_PATH, START_NO, END_NO).size();
    }

    public Article findById(int id) {
        String filePath = getArticleFilePath(id);
        String jsonStr = FileManager.loadFromFileOrNull(filePath);
        Article article = Util.getObjectFromJsonOrNull(jsonStr, Article.class);
        return article;
    }

    public void update(int articleId, String title, String content) {
        Article article = findById(articleId);
        article.setTitle(title);
        article.setContent(content);
        String jsonStr = Util.convertObjectToJson(article);
        String filePath = getArticleFilePath(articleId);
        FileManager.saveToFile(filePath, jsonStr);
    }

    @Override
    public List<Article> getSortedArticles(int sortTarget, int direction, Pagination pagination) {
        if(orderTargetList.isEmpty()) orderTargetList = findAllArticles();
        System.out.println("orderTargetList : " + orderTargetList.size());
        Collections.sort(orderTargetList, new SortFactory().getSort(sortTarget).setDirection(direction));
        return getPagedArticles(orderTargetList, pagination);
    }

    private List<Article> getPagedArticles(List<Article> articles, Pagination pagination) {
        List<Article> pagedArticles = new ArrayList<>();
        for(int i = pagination.getStartIdx(); i < pagination.getEndIdx(); i++) {
            pagedArticles.add(articles.get(i));
        }

        return pagedArticles;
    }
    public void delete(Article article) {
        String filePath = getArticleFilePath(article.getId());
        FileManager.deleteFile(filePath);
    }

    public ArrayList<Article> findAllArticles() {
        List<String> allfiles = FileManager.getFilesInRange(FILE_PATH, START_NO, END_NO);
        ArrayList<Article> articles = new ArrayList<>();
        for (String file : allfiles) {
            String fileString = FileManager.loadFromFileOrNull(file);
            articles.add(Util.getObjectFromJsonOrNull(fileString, Article.class));
        }

        return articles;
    }

    public ArrayList<Article> findByTitle(String keyword) {
        ArrayList<Article> searchedArticles = new ArrayList<>();
        ArrayList<Article> articles = findAllArticles();
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            String title = article.getTitle();
            if (title.contains(keyword)) {
                searchedArticles.add(article);
            }
        }
        return searchedArticles;
    }

    public List<Article> findArticlesByPage(Pagination pagination) {
        List<String> files = FileManager.getFilesInRange(FILE_PATH, pagination.getStartIdx(), pagination.getEndIdx());
        List<Article> pagedArticles = new ArrayList<>();
        for (String file : files) {
            String jsonStr = FileManager.loadFromFileOrNull(file);
            pagedArticles.add(Util.getObjectFromJsonOrNull(jsonStr, Article.class));
        }
        return pagedArticles;
    }
}