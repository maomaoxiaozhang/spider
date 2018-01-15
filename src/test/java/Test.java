import aes.AES;
import aes.Params;
import com.google.common.collect.ImmutableMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import sun.misc.BASE64Encoder;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2018/1/13.
 */
public class Test {


    public static void main(String[] args) {
        String temp = "O5/yxckUkfK03FP34r7bgJVnmX5k2/G/l+JCIrgOQwyl+J53UnkgD1kh9z4b6IVTfsjcxGSpGImw\n" +
                "ZD9kofBZOQpl3loQC/Is8Gf4T83ZIXpxymyVtKozsy+juHa9Ygsg";
        String ENCSECKEY = "257348aecb5e556c066de214e531faadd1c55d814f9be95fd06d6bff9f4c7a41f831f6394d5a3fd2e3881736d94a02ca919d952872e7d0a50ebfa1769a7a62d512f5f1ca21aec60bc3819a9c3ffca5eca9a0dba6d6f7249b06f5965ecfff3695b54e1c28f3f624750ed39e7de08fc8493242e26dbc4484a01c76f739e135637c";

        try {
            org.jsoup.Connection.Response response = Jsoup.connect("https://music.163.com/weapi/v1/resource/comments/R_SO_4_66842/?csrf_token=")
                    .data(ImmutableMap.of("params", temp, "encSecKey", ENCSECKEY))
                    .method(org.jsoup.Connection.Method.POST).execute();
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
