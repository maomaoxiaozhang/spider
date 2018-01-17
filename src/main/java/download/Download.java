package download;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lenovo on 2018/1/17.
 * 使用inputstream获取歌曲文件，将MP3歌曲下载至本地
 */
public class Download {
    public static String FILE_PATH = "C:\\Users\\lenovo\\Desktop\\webmagic\\netcloud\\%s.mp3";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

    public static void download(String str, String name) {
        String file = String.format(FILE_PATH, name);
        if (exist(str)) {
            try {
                URL url = new URL(str);
                InputStream in = url.openStream();
                OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                int i;
                while ((i = in.read()) != -1) {
                    out.write(i);
                }
                out.close();
                in.close();
                System.out.println(name + " 已下载完成 ...");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            //歌曲不存在，需要从酷狗下载
            System.out.println("从酷狗下载");
            System.out.println(name + " 已下载完成 ...");
        }

    }

    public static boolean exist(String str) {
        try {
            URL url = new URL(str);
            URLConnection connection = url.openConnection();
            String content_type = connection.getContentType();
            if (content_type.contains("audio")) {
                return true;
            }else if (content_type.contains("text/html")){
                System.out.println("暂无版权");
                return false;
            }else {
                System.out.println("未知错误");
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
