package com.example.musicsend.control;

import com.alibaba.fastjson.JSON;
import com.example.musicsend.Dao.DaoMapper;
import com.example.musicsend.MusicReal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class sendList {
    @Autowired
    private DaoMapper daoMapper;

    @CrossOrigin
    @GetMapping("/musicList")
    @ResponseBody
    public String getMusicList() {
        List<MusicReal> MusicList = daoMapper.getAllList();
        String musicJson = JSON.toJSONString(MusicList);
        System.out.println("1");
        return  musicJson;
    }
}
