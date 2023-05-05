package com.tomato.hutool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONUtil;
import com.tomato.entity.Computer;
import com.tomato.entity.Employee;
import org.springframework.beans.BeanUtils;


import java.util.Date;

/**
 * @Author zhxy
 * @Date 2023/3/27 4:26 下午
 * @Version V1.0
 **/
public class HutoolTest {
    public static void main(String[] args) {

        Employee employee = new Employee();
        employee.setAge(11);
        employee.setName("zhang3");

        Computer computer = new Computer();
        computer.setDate(new Date());
        computer.setName("c1");
        computer.setDesc("test");
//        employee.setComputer(computer);


        Employee item = new Employee();
//        item.setName("lisi");
        item.setName(null);
        item.setAge(null);

        Computer itemCom = new Computer();
//        itemCom.setDesc("copy");
        item.setComputer(itemCom);
        System.out.println(JSONUtil.toJsonPrettyStr(employee));
        System.out.println("--------------------------------------");
        BeanUtil.copyProperties(item, employee, CopyOptions.create().setIgnoreNullValue(true));

        System.out.println(JSONUtil.toJsonPrettyStr(employee));

    }

}
