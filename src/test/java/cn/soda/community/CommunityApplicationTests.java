package cn.soda.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {
        test();
    }

    private void test() {
//        String string = "access_token=cb181667a90c22b72ee64f53307c2d6e278a242d&scope=user&token_type=bearer";
//        System.out.println(string.split("&")[0].split("=")[1]);
//        java.util.Date date = new java.util.Date();
//        java.sql.Date  data1=new java.sql.Date(date.getTime());
//        System.out.println("this time:" + data1);
//        double a = 1.25;
//        int x = 3, y=8;
//        System.out.println(Math.ceil(a));
//        System.out.println(Math.ceil(y*1.0/x));
        List<Integer> list = null;
        list.add(12);
        System.out.println(list);
    }

}
