package com.example.dailysharing.enity;

import lombok.Data;

@Data
public class Daily {
    private Integer id;
    private String writer;
    private String content;
    private Integer count;
    private String people;
}