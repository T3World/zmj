package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonObject;
import com.zmj.RatioApplication;
import com.zmj.microservice.arithmetic.shearer.pojo.ShearerRatio;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RatioApplication.class)
@WebAppConfiguration
public class T1 {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    @Qualifier("stringRedisTemplate1")
    private StringRedisTemplate template;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{

        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种

    }
    @Test
    public void getHello() throws  Exception{
        /**

         * 1、mockMvc.perform执行一个请求。

         * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。

         * 3、ResultActions.param添加请求传值

         * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型

         * 5、ResultActions.andExpect添加执行完成后的断言。

         * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情

         *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。

         * 5、ResultActions.andReturn表示执行完成后返回相应的结果。

         */
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/kaijilv")
                .content("{\"dataSourceName\":\"zaokuang/qiwu/217\"" +
                        ",\"startTime\":\"1555689600000\"" +
                        ",\"endTime\":\"1557331200000\"}")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String data = mvcResult.getResponse().getContentAsString();
        System.out.println(data);
        SysResult<ShearerRatio> sysResult = JSONObject.parseObject(data,new TypeReference<SysResult<ShearerRatio>>(){});
        List<ShearerRatio> list = sysResult.getData();

        String path = "E:\\ZMJ_JAVA\\document\\test.xls";
        File file = new File(path);
        boolean f = false ;
        if (!file.exists())
            f = file.createNewFile();

        OutputStream out = new FileOutputStream(file);
        //创建新的工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建第一个sheet(页),并命名
        Sheet sheet1 = wb.createSheet("sheet1");
        //手动设置列宽,第一个参数表示第几列,第二个参数表示列宽,n为列高的像素值
//        sheet1.setColumnWidth(1,(int) 35.7*150);
        //创建第一行
        Row row0 = sheet1.createRow(0);

        //创建两种单元格格式
        HSSFCellStyle cellStyle1 = wb.createCellStyle();
        HSSFCellStyle cellStyle2 = wb.createCellStyle();

        //创建两种字体
        HSSFFont font1 = wb.createFont();
        HSSFFont font2 = wb.createFont();

        //创建第一种字体样式用于列名
        font1.setFontHeightInPoints((short) 10);
        font1.setColor(IndexedColors.BLACK.getIndex());
        font1.setBold(true);//我猜是加粗
        font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        //创建第二种字体样式用于值
        font2.setFontHeightInPoints((short) 10);
        font2.setColor(IndexedColors.BLACK.getIndex());
        font2.setBold(false);//我猜是加粗

        // 设置第一种单元格的样式（用于列名）
        cellStyle1.setFont(font1);
        cellStyle1.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle1.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle1.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle1.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cellStyle2.setFont(font2);
        cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);

        //设置列名
        String[] rowName = new String[]{"date","startTime","endTime","samDur","commDur","runDur","commRatio","runRatio"};
        Cell cellx;
        for (int i = 0; i< rowName.length;i++){
            cellx = row0.createCell((short)i);
            cellx.setCellStyle(cellStyle1);
            cellx.setCellValue(rowName[i]);
        }
        //设置值
        ShearerRatio shearerRatio;
        for (int i = 0;i<list.size();i++){
            shearerRatio = list.get(i);
            Row rowx = sheet1.createRow(i+1);

            Cell  cell0 = rowx.createCell(0);
            cell0.setCellStyle(cellStyle2);
            cell0.setCellValue(shearerRatio.getDate());

            Cell  cell1 = rowx.createCell(1);
            cell1.setCellStyle(cellStyle2);
            cell1.setCellValue(shearerRatio.getStartTime());

            Cell  cell2 = rowx.createCell(2);
            cell2.setCellStyle(cellStyle2);
            cell2.setCellValue(shearerRatio.getEndTime());

            Cell  cell3 = rowx.createCell(3);
            cell3.setCellStyle(cellStyle2);
            cell3.setCellValue(shearerRatio.getSamDur());

            Cell  cell4 = rowx.createCell(4);
            cell4.setCellStyle(cellStyle2);
            cell4.setCellValue(shearerRatio.getCommDur());

            Cell  cell5 = rowx.createCell(5);
            cell5.setCellStyle(cellStyle2);
            cell5.setCellValue(shearerRatio.getRunDur());

            Cell  cell6 = rowx.createCell(6);
            cell6.setCellStyle(cellStyle2);
            cell6.setCellValue(shearerRatio.getCommRatio());

            Cell  cell7 = rowx.createCell(7);
            cell7.setCellStyle(cellStyle2);
            cell7.setCellValue(shearerRatio.getRunRatio());

        }
        wb.write(out);

    }

    @Test
    public void redis(){
        String key = "zzmj_db_zaokuang_qiwu_217_20190429";
        Set<String> members = template.opsForSet().members(key);
        System.out.println(JSON.toJSONString(members));

    }

    @Test
    public void gezhonglv(){

    }
}
