package com.example.musicsend.Dao;

import com.example.musicsend.MusicReal;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DaoMapper {
    public List<MusicReal> getAllList();
}
