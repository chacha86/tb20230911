package Article.controller.sort;

import Article.model.Article;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
public class SortFactory {

    Map<Integer, Sort> sortMap = new HashMap<>();

    public SortFactory() {
        sortMap.put(1, new SortById());
        sortMap.put(2, new SortByHit());
    }
    public Sort getSort(int sortTarget) {
        return sortMap.get(sortTarget);
    }
}


class SortById extends Sort implements Comparator<Article> {

    @Override
    public int compare(Article o1, Article o2) {
        if(o1.getId() > o2.getId()) {
            return order;
        }
        return -1 * order;
    }
}

class SortByHit extends Sort implements Comparator<Article> {
    @Override
    public int compare(Article o1, Article o2) {
        if(o1.getHit() > o2.getHit()) {
            return order;
        }
        return -1 * order;
    }
}
