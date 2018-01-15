package dao;

/**
 * Created by lenovo on 2018/1/14.
 */
public class Song {

    //音乐名称
    private String name;
    //专辑
    private String album;
    //演唱者
    private String author;
    //持续时间
    private String last;
    //收录时间
    private String time;
    //网站来源
    private String site;
    //歌词
    private Lyric lyric;
    //id
    private String id;
    private String songUrl;

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Lyric getLyric() {
        return lyric;
    }

    public void setLyric(Lyric lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //地址
    private String url;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", author='" + author + '\'' +
                ", last='" + last + '\'' +
                ", time='" + time + '\'' +
                ", site='" + site + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", lyric=" + lyric +
                '}';
    }
}
