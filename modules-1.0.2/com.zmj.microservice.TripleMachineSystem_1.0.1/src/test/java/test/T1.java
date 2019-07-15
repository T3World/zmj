package test;

import com.zmj.microservice.TripleMachineApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TripleMachineApplication.class)
@WebAppConfiguration
public class T1 {
    @Autowired
    private WebApplicationContext webApplicationContext;


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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/ConveyorSystem/getConveyorHeadHighSpeedMoterCurrent")
                .content("{\"dataSourceName\":\"zaokuang/qiwu/217\",\"startTime\":\"1555516800000\",\"endTime\":\"1555516800000\"}")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String data = mvcResult.getResponse().getContentAsString();

    }
//    @Test
//    public void testMapper() throws Exception {
//        String dbName = "zzmj_db_zaokuang_fucunmeidian_3s605";
//        SqlSession sqlSession = mUtil.openSession(dbName, new Class[]{HistoryMapper.class}, new String[]{"mappers/HistoryMapper.xml"});
//        HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);
//        String[] strings = mapper.showTableLike(TableNameConstant.SHEARER_ENCODER_POSITION_TABLE_NAME);
//        String s = JSON.toJSONString(strings);
//        System.out.println(s);
//    }
}
