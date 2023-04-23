package com.example.dailysharing.Dao;

import com.example.dailysharing.enity.Daily;
import com.example.dailysharing.enity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DaoMapper {
    List<Daily> getDaily(Integer start,Integer end);
    String getUserName(String root);
    void insertDaily(String writer,String content,Integer count,String people);
    User checkUser(String root, String password);
}
