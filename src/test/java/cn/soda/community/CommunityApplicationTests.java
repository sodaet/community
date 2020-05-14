package cn.soda.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {
        test();
    }

    private void test() {
        String string = "access_token=cb181667a90c22b72ee64f53307c2d6e278a242d&scope=user&token_type=bearer";
        System.out.println(string.split("&")[0].split("=")[1]);
    }

}
