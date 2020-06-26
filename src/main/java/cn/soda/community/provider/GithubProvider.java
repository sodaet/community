package cn.soda.community.provider;

import cn.soda.community.dto.AccessTokenDTO;
import cn.soda.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            //            System.out.println(token);
            return string.split("&")[0].split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            String string = response.body().string();
            return JSON.parseObject(string, GithubUser.class);
        } catch (Exception ignored) {
        }
        return null;
    }
}
