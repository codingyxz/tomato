package com.tomato.hutool;

import cn.hutool.json.JSONArray;
import com.tomato.entity.Employee;
import com.tomato.entity.StatusEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhxy
 * @Date 2023/6/18 6:08 下午
 * @Version V1.0
 **/
public class HutoolJsonTest {
    public static void main(String[] args) {


        Map map = new HashMap<>();

        Employee employee = new Employee().setName("1111").setAge(18).setStatus(StatusEnum.SUCCESS);

        map.put("test", Arrays.asList(employee));

        List<Map<String, Object>> result = (List<Map<String, Object>>) map.get("test");

        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(result);
        List<Employee> employees = jsonArray.toList(Employee.class);


        System.out.println(employees);


    }
}
