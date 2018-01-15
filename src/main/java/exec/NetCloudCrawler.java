package exec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.Board;
import dao.Lyric;
import dao.Song;
import pipeline.PlayMusicPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2018/1/14.
 */
public class NetCloudCrawler implements PageProcessor{
    public static Spider spider;

    //搜索页地址
    private String discoverListUrl = "https://music.163.com/discover/playlist/?order=new&cat=%E5%85%A8%E9%83%A8&limit=35&offset=0";
    //搜索页每页最多显示35个
    private String limit = "35";
    //搜索项初始偏移值
    private String offset = "3499";
    //播放列表页地址
    private String playListUrl = "https://music.163.com//playlist?id=%s";
    //歌曲页地址
    private String songUrl = "https://music.163.com%s";
    //歌词页地址
    private String lyricUrl = "http://music.163.com/api/song/lyric?id=%s&lv=1&kv=1&tv=-1";
    //外链地址
//    private String outChainUrl = "http://music.163.com/outchain/player?type=2&id=%s&auto=0&height=60";
    private String outChainUrl = "http://music.163.com/song/media/outer/url?id=%s.mp3&auto=1&height=60";

    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Song> songList = new ArrayList<>();
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        String url = page.getUrl().get();
        if (url.contains("discover")) {
            Html html = page.getHtml();
            List<String> pageList = html.xpath("//div[@class='u-cover u-cover-1']/img/@src").all();
            List<String> idList = html.xpath("//div[@class='bottom']/a/@data-res-id").all();
            for (int i = 0; i < pageList.size(); i++) {
                Board board = new Board();
                String id = idList.get(i);
                board.setId(id);
                String boardUrl = String.format(playListUrl, id);
                board.setBoardUrl(boardUrl);
                board.setPageUrl(pageList.get(i));
                page.addTargetRequest(boardUrl);
            }
            page.setSkip(true);
        }else if (url.contains("playlist")) {
            Html html = page.getHtml();
            Selectable selectable = html.xpath("//div[@id='song-list-pre-cache']/ul/li");
            List<String> urlList = selectable.xpath("/li/a/@href").all();
            for (int i = 0; i < urlList.size(); i++) {
                page.addTargetRequest(String.format(songUrl, urlList.get(i)));
            }
            page.setSkip(true);
        }else if (url.contains("lyric")) {
            Html html = page.getHtml();
            JSONObject object = JSON.parseObject(html.xpath("//body/text()").get());
            JSONObject transUser = object.getJSONObject("transUser");
            Lyric lyric = new Lyric();
            lyric.setLyricUrl(url);
            if (transUser != null) {
                String nickName = transUser.getString("nickname");
                String userid = transUser.getString("userid");
                String uptime = transUser.getString("uptime");
                lyric.setNickName(nickName);
                lyric.setUptime(uptime);
                lyric.setUserId(userid);
            }
            String songId = url.split("=|&")[1];
            Song song = findById(songId);
            String originLyricStr = object.getJSONObject("lrc").getString("lyric");
            List<String> originList = new ArrayList<>();
            if (originLyricStr != null) {
                originList = parseLyric(originLyricStr);
            }
            lyric.setOriginLyrics(originList);
            String transLyricStr = object.getJSONObject("tlyric").getString("lyric");
            List<String> transList = new ArrayList<>();
            if (transLyricStr != null) {
                transList = parseLyric(transLyricStr);
            }
            lyric.setTransLyrics(transList);
            song.setLyric(lyric);
//            System.out.println(song);
            page.setSkip(true);
        }else {
            Html html = page.getHtml();
            String id = url.split("=")[1];
            String site = parseSite(html.xpath("//head/title/text()").get());
            String[] keywords = html.xpath("//head/meta[@name='keywords']/@content").get().split("，");
            Song song = new Song();
            song.setUrl(url);
            song.setId(id);
            song.setSite(site);
            song.setTime(String.valueOf(System.currentTimeMillis()));
            song.setUrl(url);
            song.setName(keywords[0].trim());
            song.setAuthor(keywords[1].trim());
            song.setAlbum(keywords[2].trim());
            songList.add(song);
            page.addTargetRequest(String.format(lyricUrl, id));
            if (song.getName().contains(name))
                page.putField("music", song.getId());
            else
                page.putField("name", song.getName());
        }
    }

    public String parseSite(String site) {
        Pattern pattern = Pattern.compile("网易云音乐|酷狗音乐|QQ音乐");
        Matcher matcher = pattern.matcher(site);
        if (matcher.find())
            return matcher.group();
        return "未知来源";
    }

    public Song findById(String id) {
        for (Song song : songList) {
            if (song.getId().equals(id))
                return song;
        }
        return null;
    }

    //匹配所有汉字、英文字符；此处为匹配"[]\n"形式
    public List<String> parseLyric(String string) {
//        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5a-zA-Z]");
        Pattern pattern = Pattern.compile("\\[.*?\\n");
        Matcher matcher = pattern.matcher(string);
        List<String> list = new ArrayList<>();
        while (matcher.find())
            list.add(matcher.group());
        return list;
    }

    public String getDiscoverListUrl() {
        return discoverListUrl;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        NetCloudCrawler crawler = new NetCloudCrawler();
        String url = crawler.discoverListUrl;
//        String url = "https://music.163.com/song?id=40915177";
        spider = Spider.create(crawler);
        spider.addUrl(url)
                .thread(3)
                .addPipeline(new PlayMusicPipeline())
                .run();
    }
}
