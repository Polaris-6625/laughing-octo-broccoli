package com.example.musicuser.Dao;

import com.example.musicuser.Unity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DaoMapper {
    void UserRegist(String username,String root,String password,String sex,String permissions);

    User checkUser(String root, String password);

    String getRoot(String root);
    void createTable_history(String root_History);
    void createTable_collection(String root_collection);
    String getPermissions(String root);
}
