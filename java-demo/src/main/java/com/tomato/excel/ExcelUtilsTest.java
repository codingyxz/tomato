package com.tomato.excel;

import com.tomato.mysql.FiledLabel;
import com.tomato.mysql.MysqlOperate;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelUtilsTest {

    @Test
    public void importTest() throws Exception {
        ExcelUtils<RecruitDTO> excelTest = new ExcelUtils<>(RecruitDTO.class);
        File file = new File("/Users/linkk/Downloads/20250105113453066.xls");
        List<RecruitDTO> result = (ArrayList<RecruitDTO>) excelTest.importExcel(file, "Sheet1", null, null);

        String sql = "insert into recruit (recruit_org,job_title,job_code,recruit_nums,apply_nums,major) values (?,?,?,?,?,?)";

        // 输出导入信息
        MysqlOperate operate = new MysqlOperate();
        for (int i = 0; i < result.size(); i++) {
            RecruitDTO recruit = result.get(i);
            operate.update(sql, recruit.getRecruitOrg(), recruit.getJobTitle(), recruit.getJobCode(), recruit.getRecruitNums(), recruit.getApplyNums(), recruit.getMajor());
        }

    }

    public void testUpdateTime(){


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        java.util.Date parse = sdf.parse("2025-01-08");
//
//
//        LocalDate localDate = LocalDate.of(2025, 1, 8);
//        new Date(localDate.toEpochDay())

//        String sql = "update recruit set apply_nums = ? , update_time = ?  where job_code = ?";
//        MysqlOperate operate = new MysqlOperate();
//        operate.update(sql,111, );

    }

    @Test
    public void updateTest() throws Exception {

        List<String> list = selectList();

        // 23084012
//        File file = new File("/Users/linkk/Downloads/peoples/1_8_more.xlsx");

        // 23084012
//        File file = new File("/Users/linkk/Downloads/peoples/1_9_more.xlsx");

        // 23084012
//        File file = new File("/Users/linkk/Downloads/peoples/1_10_more.xlsx");

        // 23021101,23084012
//        File file = new File("/Users/linkk/Downloads/peoples/1_11_more.xlsx");

//        File file = new File("/Users/linkk/Downloads/peoples/1_12_less.xlsx");

        // 23002011,23021101,23082012,23084012,23165012,23171012
        File file = new File("/Users/linkk/Downloads/peoples/1_12_more.xlsx");

        ExcelUtils<RecruitDTO> excelTest = new ExcelUtils<>(RecruitDTO.class);
        List<RecruitDTO> result = (ArrayList<RecruitDTO>) excelTest.importExcel(file, "Sheet1", 4, null);
        MysqlOperate operate = new MysqlOperate();
        String sql = "update recruit set apply_nums = ? where job_code = ?";
        List<String> total = new ArrayList<>();
        for (RecruitDTO dto : result) {
            String jobCodeOrg = dto.getJobCodeOrg();
            String[] split = jobCodeOrg.split("-");
            String jobCode = split[0];
            if (list.contains(jobCode)) {
                System.out.println("更新job_code: " + jobCode);
                total.add(jobCode);
                operate.update(sql, dto.getApplyNums(), jobCode);
            }
        }

        System.out.println(String.join(",", total));

    }

    private List<String> selectList() {
        String sql = "select job_code from recruit";
        MysqlOperate operate = new MysqlOperate();
        List<RecruitDTO> dtoList = operate.selectList(RecruitDTO.class, sql);
        return dtoList.stream().map(item -> item.getJobCode().toString()).collect(Collectors.toList());
    }

    @Test
    public void exportTest() throws Exception {
        // 构建一个TestDTO来模拟导出
        List<RecruitDTO> list = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            RecruitDTO pojo = new RecruitDTO();
//            pojo.setLoginName("登录名"+i);
//            pojo.setUserName("用户名"+i);
//            pojo.setMoney(new Long(1000+i));
//            pojo.setCreateTime(new Date());
//            pojo.setAge(28);
            list.add(pojo);
        }
        // 构造输出对象，可以从response输出，直接向用户提供下载
        OutputStream out = new FileOutputStream("D:\\excelTest.xlsx");
        // excel工具类
        ExcelUtils<RecruitDTO> ex = new ExcelUtils<>(RecruitDTO.class);
        // 导出
        ex.exportExcel(null, null, null, list, out);
        out.close();
    }
}