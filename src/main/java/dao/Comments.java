package dao;

import java.util.List;

/**
 * Created by lenovo on 2018/1/16.
 */
public class Comments {
    private List<Comment> topComments;
    private List<Comment> hotComments;
    private List<Comment> comments;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Comment> getTopComments() {
        return topComments;
    }

    public void setTopComments(List<Comment> topComments) {
        this.topComments = topComments;
    }

    public List<Comment> getHotComments() {
        return hotComments;
    }

    public void setHotComments(List<Comment> hotComments) {
        this.hotComments = hotComments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "topComments=" + topComments +
                ", hotComments=" + hotComments +
                ", comments=" + comments +
                ", total=" + total +
                '}';
    }
}
