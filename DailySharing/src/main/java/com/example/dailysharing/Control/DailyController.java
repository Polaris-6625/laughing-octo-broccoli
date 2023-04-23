package com.example.dailysharing.Control;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.dailysharing.Dao.DaoMapper;
import com.example.dailysharing.enity.Daily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DailyController {

    @Autowired
    private DaoMapper daoMapper;

    @PostMapping("/daily")
    @ResponseBody
    public String getDaily(String val_flag) {
        int flag = Integer.parseInt(val_flag);
        int start = 4 * flag - 4;
        int end = 4;
        List<Daily> list = daoMapper.getDaily(start,end);
        for (Daily daily:list) {
            daily.setWriter(daoMapper.getUserName(daily.getWriter()));
        }
        return JSON.toJSONString(list);
    }

    @PostMapping("/addDaily")
    @ResponseBody
    public String addDaily(String content,String writer,String token) {
        Daily daily = new Daily();
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("LyyWebSite")).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        System.out.println(verify.getClaim("root").toString());
        System.out.println(verify.getClaim("password").toString());
        System.out.println(daoMapper.checkUser(verify.getClaim("root").toString().replace("\"",""),verify.getClaim("password").toString().replace("\"","")));
        System.out.println(daoMapper.checkUser("root","123456"));
        if (daoMapper.checkUser(verify.getClaim("root").toString().replace("\"",""),verify.getClaim("password").toString().replace("\"","")) != null) {
            daily.setCount(0);
            daily.setPeople("[]");
            daily.setContent(content);

            daily.setWriter(writer);
            daoMapper.insertDaily(daily.getWriter(),daily.getContent(),daily.getCount(),daily.getPeople());
            return "success";
        }
        else {
            return "failed";
        }
    }
}
