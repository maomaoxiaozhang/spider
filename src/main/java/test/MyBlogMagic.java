package test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by lenovo on 2018/1/12.
 */
public class MyBlogMagic implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1);
    @Override
    public void process(Page page) {
        System.out.println(page.getHtml());
        List<String> linkes = page.getHtml().links().regex("http://blog\\.csdn\\.net/.+").all();
        System.out.println(linkes.size());
        page.addTargetRequests(linkes);
        for (int i = 0; i < linkes.size(); i++) {
            System.out.println(linkes.get(i));
        }
        System.out.println("=========================================================");
//        List<String> titleList = page.getHtml().xpath("//span[@class='link_title']/a/text()").all();
//        List<String> readList = page.getHtml().xpath("//span[@class='link_view']/text()").all();
//        List<String> commentList = page.getHtml().xpath("//span[@class='link_comments']/text()").all();
//        for (int i = 0; i < titleList.size(); i++) {
//            if (i == 0) {
//                System.out.println("###################################################");
//                System.out.println("###################################################");
//            }
//            System.out.println("题目："+ titleList.get(i));
//            System.out.println("阅读人数："+ readList.get(i).replace("(","").replace(")",""));
//            System.out.println("评论次数："+ commentList.get(i).replace("(","").replace(")",""));
//            if (i!=titleList.size()-1) {
//                System.out.println("*******************我是可爱的分割线*******************\n");
//
//            }
//        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MyBlogMagic())
                .addUrl("http://blog.csdn.net/h295928126")
                .run();
    }
}
