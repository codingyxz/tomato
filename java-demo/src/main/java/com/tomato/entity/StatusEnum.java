package com.tomato.entity;

/**
 * @Author zhxy
 * @Date 2023/6/18 6:09 下午
 * @Version V1.0
 **/
public enum StatusEnum {


    SUCCESS(""),
    PROCESSING(""),
    FAIL(""),
    PART_FAIL(""),
    ;

    private String desc;


    StatusEnum(String desc) {
        this.desc = desc;
    }
}
