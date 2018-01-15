package browser;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lenovo on 2018/1/14.
 */
public class Method {
    public static void main(String[] args) {
        String url = "www.baidu.com";
        method_3(url);
    }

    //processBuilder创建进程，指定命令（浏览器exe，url）
    public static void method_1(String url) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", url);
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取desktop操作权限，访问浏览器
    public static void method_2(String url) {
        try {
            URI uri = new URI(url);
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE))
                desktop.browse(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //通过cmd调用系统默认浏览器打开
    public static void method_3(String url) {
        try {
            Runtime.getRuntime().exec("cmd /c start " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
