package com.tomato.excel;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel工具类
 */
public class ExcelUtils<T> {

    /**
     * 导入导出的类
     */
    Class<T> clazz;

    Map<String, Method> fieldMap;

    /**
     * 日期格式
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ExcelUtils(Class<T> clazz) throws NoSuchMethodException {
        this.clazz = clazz;
        this.fieldMap = abstractAnnFieldList();
    }

    /**
     * 标有Annotation的字段标题名以及对应的set方法
     *
     * @return
     * @throws NoSuchMethodException
     */
    private Map<String, Method> abstractAnnFieldList() throws NoSuchMethodException {
        Field[] fields = clazz.getDeclaredFields();
        // 标有Annotation的字段标题名以及对应的set方法
        Map<String, Method> fieldMap = new HashMap<>();
        // 循环读取所有字段，找到标有Annotation的字段并将字段标题名和对应的set方法存到map中
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelAnnotation excelAnnotation = field.getAnnotation(ExcelAnnotation.class);
            if (excelAnnotation != null) {
                String fieldName = field.getName();
                String setMethodName = "set"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Method setMethod = clazz.getMethod(setMethodName, field.getType());
                // 将这个method以Annotation的名字为key来存入
                fieldMap.put(excelAnnotation.displayName(), setMethod);
            }
        }
        return fieldMap;
    }

    /**
     * 导出excel表格
     *
     * @param sheetName sheet标题（默认值为Sheet1）
     * @param startRow  起始行号（第一行是标题，内容接在其后）
     * @param startCol  起始列号（默认值为0）
     * @param dataset   需要导出的数据集合（默认值为0）
     * @param out       输出流
     */
    public void exportExcel(String sheetName, Integer startRow, Integer startCol,
                            Collection<T> dataset, OutputStream out) throws Exception {
        // 参数校验
        if (Objects.isNull(dataset) || dataset.isEmpty()) {
            System.out.println("导出数据为空！");
            return;
        }
        if (Objects.isNull(out)) {
            throw new Exception("输出流为空！");
        }
        sheetName = StringUtils.isNotBlank(sheetName) ? sheetName : "Sheet1";
        startRow = Objects.isNull(startRow) ? 0 : startRow;
        startCol = Objects.isNull(startCol) ? 0 : startCol;

        // 得到所有字段
        Field[] fields = clazz.getDeclaredFields();

        List<String> titleList = new ArrayList<>();
        List<Method> getMethodList = new ArrayList<>();
        // 遍历所有filed
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelAnnotation excelAnnotation = field.getAnnotation(ExcelAnnotation.class);
            // 如果设置了annotation，则需要添加到标题集合中并解析出get方法
            if (excelAnnotation != null) {
                String displayName = excelAnnotation.displayName();
                // 添加到标题列表
                titleList.add(displayName);
                // 需要导出字段get方法名
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                // 将get方法添加到集合中
                Method getMethod = clazz.getMethod(getMethodName);
                getMethodList.add(getMethod);
            }
        }
        // 集合的迭代器
        Iterator<T> its = dataset.iterator();
        // 在内存中创建一个Excel文件对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建Sheet页
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth(20);

        // 写入标题行
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        ExcelStyle.setTitleStyle(workbook, titleStyle);

        XSSFRow titleRow = sheet.createRow(startRow);
        for (int i = 0; i < titleList.size(); i++) {
            // 创建单元格并设置标题
            XSSFCell cell = titleRow.createCell(startCol + i);
            cell.setCellStyle(titleStyle);
            XSSFRichTextString text = new XSSFRichTextString(titleList.get(i));
            cell.setCellValue(text);
        }
        // 写入所有内容行
        XSSFCellStyle bodyStyle = workbook.createCellStyle();
        ExcelStyle.setBodyStyle(workbook, bodyStyle);

        int index = startRow;
        XSSFRow bodyRow = null;
        while (its.hasNext()) {
            //从第startRow+1行开始写，第startRow行是标题
            index++;
            bodyRow = sheet.createRow(index);
            T curObject = (T) its.next();
            for (int k = 0; k < getMethodList.size(); k++) {
                XSSFCell cell = bodyRow.createCell(startCol + k);
                cell.setCellStyle(bodyStyle);
                Method getMethod = getMethodList.get(k);
                Object value = getMethod.invoke(curObject);
                String textValue = getValue(value);
                cell.setCellValue(textValue);
            }
        }
        workbook.write(out);
        // 关闭资源
        workbook.close();
    }

    /**
     * 根据不同类型获取对应的内容（导出用）
     *
     * @param value
     * @return
     */
    public String getValue(Object value) {
        String textValue = "";
        if (value == null) return textValue;
        if (value instanceof Boolean) {
            boolean bValue = (Boolean) value;
            textValue = "是";
            if (!bValue) {
                textValue = "否";
            }
        } else if (value instanceof Date) {
            Date date = (Date) value;
            textValue = dateFormat.format(date);
        } else {
            textValue = value.toString();
        }
        return textValue;
    }

    /**
     * 导入excel表格成Collection<T>
     *
     * @param file
     * @param sheetName
     * @param startRow
     * @param startCol
     * @return
     * @throws Exception
     */
    public Collection<T> importExcel(File file, String sheetName, Integer startRow, Integer startCol) throws Exception {
        // 参数校验
        if (Objects.isNull(file)) {
            System.out.println("文件为空!");
            return null;
        }
        // 将传入的File构造为FileInputStream;
        FileInputStream in = new FileInputStream(file);
        Workbook workbook = null;
        if (file.getName().endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(in);
        } else if (file.getName().endsWith("xls")) {
            workbook = new HSSFWorkbook(in);
        } else {
            throw new Exception("文件格式错误!");
        }

        startRow = Objects.isNull(startRow) ? 0 : startRow;
        startCol = Objects.isNull(startCol) ? 0 : startCol;

        // 得到对应sheet页
        Sheet sheet = StringUtils.isNotBlank(sheetName) ?
                workbook.getSheet(sheetName) : workbook.getSheetAt(0);

        // 得到标题行
        Row titleRow = sheet.getRow(startRow);
        // key：列号，value：对应标题名称
        Map<Integer, String> titleMap = new HashMap<>();
        // 获取标题行中的最后一列的列号
        int lastColNum = titleRow.getLastCellNum();
        for (int i = startCol; i < lastColNum; i++) {
            Cell cell = titleRow.getCell(i);
            String value = cell.getStringCellValue();
            titleMap.put(i, value);
        }

        // 解析内容
        startRow++;
        Collection<T> dataset = new ArrayList<>();
        // 获取Sheet页中的最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        while (startRow <= lastRowNum) {
            Row bodyRow = sheet.getRow(startRow);
            T curObject = parseRowBody(bodyRow, startCol, lastColNum, titleMap);
            dataset.add(curObject);
            startRow++;
        }
        return dataset;
    }

    private T parseRowBody(Row bodyRow, int startCol, int lastColNum, Map<Integer, String> titleMap) throws Exception {
        // 创建传入类的实例
        T curObject = clazz.getDeclaredConstructor().newInstance();
        for (int i = startCol; i <= lastColNum; i++) {
            fillFieldValue(bodyRow.getCell(i), titleMap.get(i), curObject);
        }
        return curObject;
    }

    /**
     * 将单元格的值填充到实体对应的属性中
     *
     * @param cell
     * @param titleName
     * @param curObject
     * @throws Exception
     */
    private void fillFieldValue(Cell cell, String titleName, T curObject) throws Exception {
        if (!fieldMap.containsKey(titleName)) {
            return;
        }
        // 如果这一列的标题和类中的某一列的Annotation相同，则调用此类的的set方法，进行设值
        Method setMethod = fieldMap.get(titleName);
        // 得到setter方法的参数列表
        Type[] parameterTypes = setMethod.getGenericParameterTypes();
        // 只需要第一个参数的类型
        String paramType = parameterTypes[0].toString();
        cell.setCellType(CellType.STRING);
        if (paramType.equals("class java.lang.String")) {
            setMethod.invoke(curObject, cell.getStringCellValue());
        } else if (paramType.equals("class java.util.Date")) {
            setMethod.invoke(curObject, dateFormat.parse(cell.getStringCellValue()));
        } else if (paramType.equals("class java.lang.Boolean")) {
            Boolean boolname = true;
            if (cell.getStringCellValue().equals("否")) {
                boolname = false;
            }
            setMethod.invoke(curObject, boolname);
        } else if (paramType.equals("class java.lang.Integer")) {
            setMethod.invoke(curObject, Integer.parseInt(cell.getStringCellValue()));
        } else if (paramType.equals("class java.lang.Long")) {
            setMethod.invoke(curObject, Long.parseLong(cell.getStringCellValue()));
        }
    }

}
