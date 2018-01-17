package aes;

import com.google.common.collect.ImmutableMap;
import org.jsoup.Jsoup;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lenovo on 2018/1/15.
 */
public class AES {

    /*生成post请求所需参数*/
    private static final String ENCSECKEY = "257348aecb5e556c066de214e531faadd1c55d814f9be95fd06d6bff9f4c7a41f831f6394d5a3fd2e3881736d94a02ca919d952872e7d0a50ebfa1769a7a62d512f5f1ca21aec60bc3819a9c3ffca5eca9a0dba6d6f7249b06f5965ecfff3695b54e1c28f3f624750ed39e7de08fc8493242e26dbc4484a01c76f739e135637c";
    private static String FIRST_PARAM = "{rid:\"\", offset:\"offsetNum\", total:\"true\", limit:\"20\", csrf_token:\"\"}";
    private static final String FORTH_PARAM = "0CoJUm6Qyw8W8jud";
    private static final String AES_IV = "0102030405060708";

    public static String postGetComents(String url, int pageNum) {
        String firstParam = FIRST_PARAM.replaceAll("offsetNum", pageNum*20+"");
        String temp = AES(AES(firstParam, FORTH_PARAM), "FFFFFFFFFFFFFFFF");
        try {
            org.jsoup.Connection.Response response = Jsoup.connect(url)
                    .data("params", temp)
                    .data("encSecKey", ENCSECKEY)
                    .method(org.jsoup.Connection.Method.POST).execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //得到post需要的参数
    public static Params postGetParams(int pageNum) {
        String firstParam = FIRST_PARAM.replaceAll("offsetNum", pageNum * 20 + "");
        String temp = AES(AES(firstParam, FORTH_PARAM), "FFFFFFFFFFFFFFFF");
        Params params = new Params();
        params.setParams(temp);
        params.setEncSecKey(ENCSECKEY);
        return params;
    }

    public static String AES(String str, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            return new BASE64Encoder().encode(encrypted);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "";
    }
}
