package com.example.musicsend.Dao;

import com.example.musicsend.Unity.MusicReal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DaoMapper {
    public List<MusicReal> getAllList();
    public List<MusicReal> getPagingList(int start,int end);
}
