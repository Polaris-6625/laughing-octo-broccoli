package com.example.musicsend.control;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;

@Controller
public class sendMusic {
    @CrossOrigin("*")
    @GetMapping("/music")
    @ResponseBody
    public byte[] sendMusic(String musicName) throws Exception {
        // /home/ubuntu/static/
        String basic = "/home/ubuntu/static/";
        basic = basic + musicName + ".mp3";
        File file = new File(basic);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] b = new byte[(int) file.length()];
        fileInputStream.read(b);
        return b;
    }
}
