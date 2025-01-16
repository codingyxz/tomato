package com.tomato.excel;

import java.awt.Color;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel单元格样式
 */
public class ExcelStyle {
    /**
     * 表格标题单元格样式
     *
     * @param workbook
     * @param style
     * @return
     */
    public static XSSFCellStyle setTitleStyle(XSSFWorkbook workbook, XSSFCellStyle style) {
        // 设置背景色
        style.setFillForegroundColor(new XSSFColor(new Color(255, 255, 255)));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置边框
        style.setBorderBottom(BorderStyle.THIN); // 下边框
        style.setBorderRight(BorderStyle.THIN);// 右边框
        style.setBorderLeft(BorderStyle.THIN);// 左边框
        style.setBorderTop(BorderStyle.THIN);// 上边框
        // 设置单元格的中心水平对齐-居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格的垂直对齐类型-居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成字体
        XSSFFont font = workbook.createFont();
        // 设置字体类型
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 10);
        // 粗体字体
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;

    }

    /**
     * 表格主体单元格样式
     *
     * @param workbook
     * @param style
     * @return
     */
    public static XSSFCellStyle setBodyStyle(XSSFWorkbook workbook, XSSFCellStyle style) {
        // 设置背景色
        // style.setFillForegroundColor(new XSSFColor(new Color(255,255,255)));
        // style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置边框
        style.setBorderBottom(BorderStyle.THIN); // 下边框
        style.setBorderRight(BorderStyle.THIN);// 右边框
        style.setBorderLeft(BorderStyle.THIN);// 左边框
        style.setBorderTop(BorderStyle.THIN);// 上边框
        // 生成字体
        XSSFFont font = workbook.createFont();
        // 字体类型
        font.setFontName("微软雅黑");
        // 设置字体大小
        font.setFontHeightInPoints((short) 9);
        // 普通字体
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }
}
