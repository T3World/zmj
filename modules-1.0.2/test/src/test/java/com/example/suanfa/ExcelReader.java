package com.example.suanfa;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    @Test
    public void readExcel(){
        String path = "E:\\ZMJ_JAVA\\document\\上湾51203工作面数据.xls";
        InputStream in = null;
        List<List<String>> lists = new ArrayList<>();
        Workbook wb = null;
        //读取文件
        try {
            in = new FileInputStream(path);
            wb = new HSSFWorkbook(in);

        //读取第一个工作页sheet
        Sheet sheet = wb.getSheetAt(0);
        //第一行 为 标题
        for (Row row : sheet){
            ArrayList<String> list = new ArrayList<>();
            for (Cell cell: row){
                //根据不同类型转化成字符串
                cell.setCellType(Cell.CELL_TYPE_STRING);
                list.add(cell.getStringCellValue());
            }
        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
