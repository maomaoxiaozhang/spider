package pipeline;

import download.Download;
import exec.NetCloudComment;
import exec.NetCloudCrawler;
import play.Play;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lenovo on 2018/1/13.
 */
public class PlayMusicPipeline implements Pipeline {
    private static final String CHROME_LOCATION = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
    private static final String OUTCHAIN_URL = "http://music.163.com/song/media/outer/url?id=%s.mp3";

    @Override
    public void process(ResultItems resultItems, Task task) {
        String id = (String)resultItems.get("id");
        String name = resultItems.get("name");
        if (id == null) {
            System.out.println((String) resultItems.get("name"));
        }else {
            //打开浏览器播放外链地址
//            try {
//                ProcessBuilder process = new ProcessBuilder(CHROME_LOCATION, String.format(OUTCHAIN_URL, id));
//                process.start();
//                NetCloudCrawler.spider.stop();
//                NetCloudComment.showComments(id);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            NetCloudCrawler.spider.stop();
//            NetCloudComment.showComments(id);
            Download.download(String.format(OUTCHAIN_URL, id), name);
            Play.play(name);
        }
    }
}
