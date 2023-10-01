package Article.controller;

import Article.model.ArticleRepository;

public class Pagination {
    private int currentPageNo = 1;
    private int itemsCountPerPage = 3;
    private int pageCntPerBlock = 5;
    private int totalCount = 0;
    private ArticleRepository articleRepository;
    public int getEndPageNo() {
        return getStartPageNo() + pageCntPerBlock - 1 ;
    }
    public int getStartPageNo() {
        return (getPageBlockNo() - 1) * pageCntPerBlock + 1;
    }
    public int getPageBlockNo() {
        return (int)(Math.ceil((double)currentPageNo / pageCntPerBlock));
    }
    public double getPageCount() {
        return Math.ceil((double)totalCount / itemsCountPerPage);
    }
    public int getStartIdx() {
        int startIdx = (currentPageNo - 1) * itemsCountPerPage;

        if(startIdx < 0) {
            startIdx = 1;
        }

        return  startIdx;
    }

    public int getEndIdx() {
        int endIdx = getStartIdx() + itemsCountPerPage;

        if(endIdx >= totalCount) {
            endIdx = totalCount;
        }

        return endIdx;
    }


    public Pagination() {
        articleRepository = new ArticleRepository();
        this.totalCount = articleRepository.getTotalArticleCount();
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }
}
