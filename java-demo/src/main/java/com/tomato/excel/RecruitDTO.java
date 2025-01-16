package com.tomato.excel;

import com.tomato.mysql.FiledLabel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitDTO {
    @ExcelAnnotation(displayName = "招录机关（单位）")
    @FiledLabel(columnLabel = "recruit_org")
    private String recruitOrg;
    @ExcelAnnotation(displayName = "职位名称")
    @FiledLabel(columnLabel = "job_title")
    private String jobTitle;
    @ExcelAnnotation(displayName = "职位代码")
    @FiledLabel(columnLabel = "job_code")
    private Integer jobCode;
    @ExcelAnnotation(displayName = "招录人数")
    @FiledLabel(columnLabel = "recruit_nums")
    private Integer recruitNums;
    @ExcelAnnotation(displayName = "报考人数")
    @FiledLabel(columnLabel = "apply_nums")
    private Integer applyNums;
    @ExcelAnnotation(displayName = "专业")
    @FiledLabel(columnLabel = "major")
    private String major;
    @ExcelAnnotation(displayName = "更新时间")
    @FiledLabel(columnLabel = "update_time")
    private Date updateTime;

    @ExcelAnnotation(displayName = "职位代码及名称")
    private String jobCodeOrg;

    @Override
    public String toString() {
        return "RecruitDTO{" +
                "recruitOrg='" + recruitOrg + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobCode=" + jobCode +
                ", recruitNums=" + recruitNums +
                ", applyNums=" + applyNums +
                ", major='" + major + '\'' +
                ", updateTime=" + updateTime +
                ", jobCodeOrg='" + jobCodeOrg + '\'' +
                '}';
    }
}
