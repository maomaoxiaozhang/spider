package exec;

import pipeline.PlayMusicPipeline;
import us.codecraft.webmagic.Spider;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by lenovo on 2018/1/14.
 */
public class Exec {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            NetCloudCrawler crawler = new NetCloudCrawler();
            System.out.println("输入歌曲名称进行搜索");
            String name = scanner.next();
            if (name.equals("over"))
                break;
            else if (name.equals("shutdown")) {
                Runtime.getRuntime().exec("");
                continue;
            }
            crawler.setName(name);
            String url = crawler.getDiscoverListUrl();
            crawler.spider = Spider.create(crawler);
            crawler.spider
                    .addUrl(url)
                    .thread(4)
                    .addPipeline(new PlayMusicPipeline())
                    .run();
        }
    }
}
