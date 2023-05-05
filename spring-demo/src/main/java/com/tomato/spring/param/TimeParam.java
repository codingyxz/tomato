package com.tomato.spring.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @Author zhxy
 * @Date 2023/3/27 2:30 下午
 * @Version V1.0
 **/

@Getter
@Setter
public class TimeParam {


    private String name;
    @JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
    private LocalDate showDate;
}
