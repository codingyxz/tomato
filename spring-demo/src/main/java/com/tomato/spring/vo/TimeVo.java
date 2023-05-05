package com.tomato.spring.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @Author zhxy
 * @Date 2023/3/27 2:21 下午
 * @Version V1.0
 **/

@Getter
@Setter
public class TimeVo {

    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate showDate;

}
