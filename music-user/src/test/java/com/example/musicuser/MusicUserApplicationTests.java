package com.example.musicuser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicUserApplicationTests {

//    @Test
//    public void test(){
//        // 通过签名生成验证对象
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("LyyWebSite")).build();
//        System.out.println("Test is "+daoMapper.getRoot("root"));
//        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb290Ijoi5YiY5a6H5rSLIiwiZXhwIjoxNjc4Njg5NDU4LCJ1c2VybmFtZSI6InJvb3QifQ.v-KUwifJ0D8Tv8lj2BsylbYtgTqvaFeDae-ZZWfLu4w");
//        String user = String.valueOf(verify.getClaim("username"));
////        String result = trim(user,"\"");
//        Trim trim = new Trim();
//        String result = Trim.trim(user,"\"");
//        System.out.println(result.equals("root"));
//        System.out.println("user is :"+result);
//        String permissions = daoMapper.getPermissions(result);
//        String permissions2 = daoMapper.getPermissions("root");
//        System.out.println("Test 2 is "+verify.getClaim("username"));
//        System.out.println("p is :"+permissions);
//        System.out.println("p2 is "+permissions2);
//        System.out.println(verify.getClaim("root"));
//        System.out.println(verify.getClaim("username"));
//        System.out.println("令牌过期时间："+verify.getExpiresAt());
//
//    }

}
