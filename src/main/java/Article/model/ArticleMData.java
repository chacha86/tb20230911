package Article.model;

public class ArticleMData {
    private int lastArticleId;
    private int totalArticleCount;

    public ArticleMData() {
    }

    public ArticleMData(int lastArticleId, int totalArticleCount) {
        this.lastArticleId = lastArticleId;
        this.totalArticleCount = totalArticleCount;
    }

    public int getLastArticleId() {
        return lastArticleId;
    }

    public void setLastArticleId(int lastArticleId) {
        this.lastArticleId = lastArticleId;
    }

    public int getTotalArticleCount() {
        return totalArticleCount;
    }

    public void setTotalArticleCount(int totalArticleCount) {
        this.totalArticleCount = totalArticleCount;
    }
}
