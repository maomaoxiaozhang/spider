package exec;

import aes.AES;
import com.alibaba.fastjson.JSONObject;
import dao.Comments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/1/16.
 * fastjson真的是66666666
 */
public class NetCloudComment {
    private static final String COMMENT_URL = "https://music.163.com/weapi/v1/resource/comments/R_SO_4_%s/?csrf_token=";
    private static List<Comments> commentsList = new ArrayList<>();

    public List<Comments> getCommentsList(String id) {
        String body = AES.postGetComents(String.format(COMMENT_URL, id), 0);
        JSONObject object = JSONObject.parseObject(body);
        int total = object.getIntValue("total");
        int pages = total / 20 + (total % 20 == 0 ? 0 : 1);
        for (int i = 0; i < pages; i++) {
            String content = AES.postGetComents(String.format(COMMENT_URL, id), i);
            JSONObject contentObj = JSONObject.parseObject(content);
            Comments comments = contentObj.toJavaObject(Comments.class);
            commentsList.add(comments);
        }
        return commentsList;
    }

    public static void showComments(String id) {
        String body = AES.postGetComents(String.format(COMMENT_URL, id), 0);
        JSONObject object = JSONObject.parseObject(body);
        int total = object.getIntValue("total");
        int pages = total / 20 + (total % 20 == 0 ? 0 : 1);
        for (int i = 0; i < pages; i++) {
            String content = AES.postGetComents(String.format(COMMENT_URL, id), i);
            JSONObject contentObj = JSONObject.parseObject(content);
            Comments comments = contentObj.toJavaObject(Comments.class);
            System.out.println(comments);
        }
    }


    //警示后人
//    public static void parse(JSONObject jsonObject) {
//        parseTop(jsonObject, "topComments");
//    }
//
//    public static void parseTop(JSONObject jsonObject, String key) {
//        JSONArray array = jsonObject.getJSONArray(key);
//        if (array != null) {
//            for (int i = 0; i < array.size(); i++) {
//                JSONObject object = array.getJSONObject(i);
//                Comment comment = object.toJavaObject(Comment.class);
//                System.out.println(comment);
//            }
//        }
//    }
}
