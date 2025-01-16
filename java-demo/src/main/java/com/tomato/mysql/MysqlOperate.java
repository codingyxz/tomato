package com.tomato.mysql;

import com.tomato.excel.RecruitDTO;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

public class MysqlOperate {


    static String SQL = "select * from recruit";

    @Test
    public void test() {
        Map<String, String> labelMap = constructLabelMap(Arrays.asList(RecruitDTO.class.getDeclaredFields()));
        labelMap.forEach((k, v) -> {
            System.out.println(k + "--" + v);
        });
    }

    /**
     * 增删改操作
     *
     * @param sql
     * @param args
     */
    public void update(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1、获取连接
            conn = JDBCUtilS.generateConn();
            // 2、预编译sql语句，获取ps的实例
            ps = conn.prepareStatement(sql);
            // 3、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 4、执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilS.closeResource(conn, ps);
        }
    }

    /**
     * 查询多条记录
     *
     * @param clazz 实体类
     * @param sql
     * @param args  sql占位符参数
     * @param <T>
     * @return
     */
    public <T> List<T> selectList(Class<T> clazz, String sql, Object... args) {
        List<T> resultList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtilS.generateConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            Map<String, String> labelMap = constructLabelMap(Arrays.asList(clazz.getDeclaredFields()));
            while (rs.next()) {
                resultList.add(constructInstance(clazz, labelMap, metaData, rs, columnCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilS.closeResource(conn, ps, rs);
        }
        return resultList;
    }

    /**
     * 构造表记录行的对象实例
     *
     * @param clazz
     * @param labelMap
     * @param metaData
     * @param rs
     * @param columnCount
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T> T constructInstance(Class<T> clazz, Map<String, String> labelMap, ResultSetMetaData metaData, ResultSet rs, int columnCount) throws Exception {
        // 反射获取对象实例
        T t = clazz.getDeclaredConstructor().newInstance();
        // 迭代填充属性值
        for (int i = 0; i < columnCount; i++) {
            // 获取列值
            Object columnValue = rs.getObject(i + 1);
            // 获取列明（优先别名）
            String columnLabel = metaData.getColumnLabel(i + 1);
            // 反射填充属性值
            Field filed = clazz.getDeclaredField(labelMap.get(columnLabel));
            filed.setAccessible(true);
            filed.set(t, columnValue);
        }
        return t;
    }


    /**
     * 获取实体类属性与数据库字段的映射关系
     *
     * @param fieldList
     * @return
     */
    private Map<String, String> constructLabelMap(List<Field> fieldList) {
        Map<String, String> fieldMap = new HashMap<>();

        for (Field field : fieldList) {
            FiledLabel label = field.getDeclaredAnnotation(FiledLabel.class);
            String key = label == null ? field.getName() : label.columnLabel();
            fieldMap.put(key, field.getName());
        }
        return fieldMap;
    }


}
