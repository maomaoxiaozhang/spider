package play;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by lenovo on 2018/1/17.
 * 播放本地音乐
 */
public class Play {
    public static String FILE_PATH = "C:\\Users\\lenovo\\Desktop\\webmagic\\netcloud\\%s.mp3";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

    public static void play(String name) {
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(String.format(FILE_PATH, name)));
            Thread t = new Thread(() -> {
                Player player = null;
                try {
                    player = new Player(stream);
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
