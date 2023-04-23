package com.example.musicsend.control;

import com.alibaba.fastjson.JSON;
import com.example.musicsend.Dao.DaoMapper;
import com.example.musicsend.Unity.MusicReal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PagingSend {
    @Autowired
    private DaoMapper daoMapper;

    @CrossOrigin("*")
    @PostMapping("/getPaging")
    @ResponseBody
    public String retrunPaging(String val_flag) {
        if(val_flag != null) {
            int flag = Integer.parseInt(val_flag);
            int start = 10 * flag - 10;
            int end = 10;
            System.out.println("start:"+start+",end:"+end);
            List<MusicReal> list =  daoMapper.getPagingList(start,end);
            System.out.println("val_flag:"+val_flag);
            String json = JSON.toJSONString(list);
            return json;
        }
        return "暂无";
    }
}
