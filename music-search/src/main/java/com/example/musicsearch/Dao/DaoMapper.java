package com.example.musicsearch.Dao;

import com.example.musicsearch.Unity.MusicInf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DaoMapper {
    List<MusicInf> getMusicInf(String name);
}
