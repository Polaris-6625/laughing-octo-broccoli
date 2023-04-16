package com.example.musicuser.Control;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.musicuser.Config.JedisConnectionFactory;
import com.example.musicuser.Dao.DaoMapper;
import com.example.musicuser.Tools.RandomValidateCodeUtil;
import com.example.musicuser.Unity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.HashMap;

@Controller
public class MusicController {

    @Autowired
    private DaoMapper daoMapper;

    @GetMapping("/ceshi")
    @ResponseBody
    public String returnString() {
        System.out.println("这是ceshi的Controller");
        return "成功访问";
    }

    @CrossOrigin
    @PostMapping("/regist")
    @ResponseBody
    public String registUser(String username,String root,String password,String sex,String checkText) {
        if (!username.equals("") && !password.equals("") && !root.equals("") && !sex.equals("")) {
            Jedis jedis = JedisConnectionFactory.getJedis();
            if (checkText.equals(jedis.get("Captcha-Register:"+checkText))) {
                System.out.println("username:"+username+",password:"+password+",name:"+root+",sex:"+sex);
                String permissions = "ordinary";
                daoMapper.UserRegist(username,password,root,sex,permissions);
                daoMapper.createTable_history(root);
                daoMapper.createTable_collection(root);
                return "successfully";
            }
            else {
                return "failed";
            }
        }
        else {
            return "failed";
        }
    }

    @CrossOrigin
    @GetMapping("/getRegisterCaptcha")
    @ResponseBody
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {

        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            RandomValidateCodeUtil randomValidateCodeUtil = new RandomValidateCodeUtil();

            // 直接返回图片
            randomValidateCodeUtil.getRandomCodeImage(request, response);
            Jedis jedis = JedisConnectionFactory.getJedis();
            jedis.set("Captcha:"+randomValidateCodeUtil.getString(),randomValidateCodeUtil.getString());
            jedis.expire("Captcha-Register:"+randomValidateCodeUtil.getString(),180);
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @CrossOrigin
    @GetMapping("/getLoginCaptcha")
    @ResponseBody
    public void getLoginCaptcha(HttpServletRequest request, HttpServletResponse response) {

        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            RandomValidateCodeUtil randomValidateCodeUtil = new RandomValidateCodeUtil();

            // 直接返回图片
            randomValidateCodeUtil.getRandomCodeImage(request, response);
            Jedis jedis = JedisConnectionFactory.getJedis();
            jedis.set("Captcha-Login:"+randomValidateCodeUtil.getString(),randomValidateCodeUtil.getString());
            jedis.expire("Captcha-Login:"+randomValidateCodeUtil.getString(),180);
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @CrossOrigin
    @PostMapping("/Login")
    @ResponseBody
    public String LoginFunction(String root, String password, String Captcha) {
        Jedis jedis = JedisConnectionFactory.getJedis();
        System.out.println(jedis.get("Captcha:"+Captcha));
        if (Captcha.equals(jedis.get("Captcha-Login:"+Captcha))) {
            User user = daoMapper.checkUser(root, password);
            if (user == null) {
                System.out.println("账号或密码错误");
                return "failed";
            }
            else {
                jedis.del("Captcha-Login:"+Captcha);
//
                HashMap<String, Object> map = new HashMap<>();

                Calendar instance = Calendar.getInstance();
                // 20秒后令牌token失效
                instance.add(Calendar.SECOND,3600);

                String token = JWT.create()
                        .withHeader(map) // header可以不写，因为默认值就是它
                        .withClaim("username", daoMapper.getRoot(root))  //payload
                        .withClaim("root", root)
                        .withClaim("permissions",daoMapper.getPermissions(root))
                        .withExpiresAt(instance.getTime()) // 指定令牌的过期时间
                        .sign(Algorithm.HMAC256("LyyWebSite"));//签名

                System.out.println(token);
                //jedis.set("",token);
                return token;
            }
        }
        else {
            System.out.println("验证码错误");
            return "failed";
        }
    }
}
