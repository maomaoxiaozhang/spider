package dao;

import java.util.List;

/**
 * Created by lenovo on 2018/1/16.
 */
public class Comment {
    private User user;
    private List<BeReplied> beReplied;
    private int commentId;
    private int likedCount;
    private boolean liked;
    private long time;
    private String content;
    private boolean isRemoveHotComment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BeReplied> getBeReplied() {
        return beReplied;
    }

    public void setBeReplied(List<BeReplied> beReplied) {
        this.beReplied = beReplied;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(int likedCount) {
        this.likedCount = likedCount;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRemoveHotComment() {
        return isRemoveHotComment;
    }

    public void setRemoveHotComment(boolean removeHotComment) {
        isRemoveHotComment = removeHotComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", beReplied=" + beReplied +
                ", commentId=" + commentId +
                ", likedCount=" + likedCount +
                ", liked=" + liked +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", isRemoveHotComment=" + isRemoveHotComment +
                '}';
    }
    public static class BeReplied {
        private User user;
        private String content;
        private int status;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "BeReplied{" +
                    "user=" + user +
                    ", content='" + content + '\'' +
                    ", status=" + status +
                    '}';
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}
