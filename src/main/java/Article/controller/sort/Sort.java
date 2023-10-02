package Article.controller.sort;

import Article.model.Article;

import java.util.Comparator;

public class Sort {
    protected int order = 1;

    public Comparator<Article> setDirection(int direction) {
        if(direction == 2) {
            order = -1;
        }

        return (Comparator<Article>)this;
    }
}