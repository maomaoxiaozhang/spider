package dao;

import java.util.List;

/**
 * Created by lenovo on 2018/1/14.
 */
public class Lyric {

    private List<String> originLyrics;
    private List<String> transLyrics;
    private String userId;
    private String nickName;
    private String uptime;
    private String lyricUrl;

    public String getLyricUrl() {
        return lyricUrl;
    }

    public void setLyricUrl(String lyricUrl) {
        this.lyricUrl = lyricUrl;
    }

    public List<String> getTransLyrics() {
        return transLyrics;
    }

    public void setTransLyrics(List<String> transLyrics) {
        this.transLyrics = transLyrics;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public List<String> getOriginLyrics() {

        return originLyrics;
    }

    public void setOriginLyrics(List<String> originLyrics) {
        this.originLyrics = originLyrics;
    }

    @Override
    public String toString() {
        return "Lyric{" +
                "originLyrics=" + originLyrics +
                ", transLyrics=" + transLyrics +
                ", userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", uptime='" + uptime + '\'' +
                '}';
    }
}
