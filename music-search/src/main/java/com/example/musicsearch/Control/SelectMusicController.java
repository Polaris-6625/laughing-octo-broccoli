package com.example.musicsearch.Control;

import com.alibaba.fastjson.JSON;
import com.example.musicsearch.Dao.DaoMapper;
import com.example.musicsearch.Unity.MusicInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SelectMusicController {
    @Autowired
    private DaoMapper daoMapper;


    @CrossOrigin(origins = "*")
    @PostMapping("/searchMusic")
    @ResponseBody
    public String getMuiscBySearch(String flag){
//        System.out.println("flag is "+flag);
        List<MusicInf> musicInfs = daoMapper.getMusicInf(flag);
        if (musicInfs.isEmpty()) {
            MusicInf musicInf = new MusicInf();
            musicInf.setId(0);
            musicInf.setName("");
            musicInf.setAuthor("");
            String json = JSON.toJSONString(musicInf);
            return json;
        }
        else {
            String json = JSON.toJSONString(musicInfs);
//            System.out.println(json);
            return json;
        }
    }
}
