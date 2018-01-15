package pipeline;

import exec.NetCloudCrawler;
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
    private static final String OUTCHAIN_URL = "http://music.163.com/outchain/player?type=2&id=%s&auto=1&height=60";

    @Override
    public void process(ResultItems resultItems, Task task) {
        String id = (String)resultItems.get("music");
        if (id == null) {
            System.out.println((String) resultItems.get("name"));
        }else {
            try {
                ProcessBuilder process = new ProcessBuilder(CHROME_LOCATION, String.format(OUTCHAIN_URL, id));
                process.start();
                NetCloudCrawler.spider.stop();
                System.out.println("over");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
