package Article.controller;

import Article.model.ArticleRepository;
import Article.model.File.ArticleFileRepository;

public class Pagination {

    private int currentPageNo = 1;
    private int itemsCountPerPage = 3;
    private int pageCntPerBlock = 5;
    private int totalCnt = 0;

    public Pagination() {
        this.totalCnt = getTotalCnt();
    }

    public int getStartIdx() {
        return (currentPageNo - 1) * itemsCountPerPage;
    }

    public int getEndIdx() {
        int endIdx = getStartIdx() + itemsCountPerPage;
        if(endIdx >= getTotalCnt()) {
            endIdx = getTotalCnt();
        }
        return endIdx;
    }

    public int getTotalPageCnt() {
        return (int)Math.ceil((double)getTotalCnt() / itemsCountPerPage);
    }

    public int getCurrentPageBlock() {
        return (int)Math.ceil((double)currentPageNo / pageCntPerBlock);
    }

    public int getStartPageOfBlock() {
        return (getCurrentPageBlock() - 1) * pageCntPerBlock + 1;
    }

    public int getEndPageOfBlock() {
        int endPageOfBlock = getStartPageOfBlock() + pageCntPerBlock - 1;
        if(endPageOfBlock >= getTotalPageCnt()) {
            endPageOfBlock = getTotalPageCnt();
        }
        return endPageOfBlock;
    }

    public boolean isPrevBlock() {
        return getCurrentPageBlock() > 1;
    }

    public boolean isNextBlock() {
        return getCurrentPageBlock() < getTotalPageCnt();
    }

    public boolean hasNextPage() {
        return currentPageNo < getTotalPageCnt();
    }

    public boolean hasPrevPage() {
        return currentPageNo > 1;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void nextPageBlock() {
        if(isNextBlock()) {
            currentPageNo = getEndPageOfBlock() + 1;
        }
    }
    public void prevPageBlock() {
        if(isPrevBlock()) {
            currentPageNo = getStartPageOfBlock() - 1;
        }
    }

    public void nextPage() {
        if(hasNextPage()) {
            currentPageNo++;
        }
    }

    public void prevPage() {
        if(hasPrevPage()) {
            currentPageNo--;
        }
    }

    public int getTotalCnt() {
        return ArticleFileRepository.getTotalCount();
    }

    public void resetPage() {
        currentPageNo = 1;
    }
}
