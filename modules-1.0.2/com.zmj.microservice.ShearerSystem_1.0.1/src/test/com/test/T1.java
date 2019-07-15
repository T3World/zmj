package com.test;

import com.alibaba.fastjson.JSON;
import com.zmj.microservice.common.history.base.BaseController;
import com.zmj.microservice.common.history.constant.TableNameConstant;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.common.mybatis.core.SessionFactory;
import com.zmj.microservice.common.mybatis.mapper.HistoryMapper;
import com.zmj.microservice.common.redis.util.RedisUtil;
import com.zmj.microservice.shearersystem.ShearerSystemApplication;
import com.zmj.microservice.shearersystem.service.ShearerSystemServiceImpl;
import org.apache.ibatis.session.SqlSession;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShearerSystemApplication.class)
@WebAppConfiguration
public class T1 {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier("stringRedisTemplate2")
    private StringRedisTemplate stringRedisTemplate2;

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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/ShearerSystem/getTractionSpeedData")
                .content("{\"dataSourceName\":\"zaokuang/jiangzhuang/605\"," +
                        "\"startTime\":\"1557878400000\"," +
                        "\"stateIds\":\"08\"," +
                        "\"endTime\":\"1557878400000\"}")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String data = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void testMapper() throws Exception {
        String dbName = "zzmj_db_zaokuang_fucunmeidian_3s605";
        SqlSession sqlSession = sessionFactory.openSession(dbName, new Class[]{HistoryMapper.class}, new String[]{"mappers/HistoryMapper.xml"});
        HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);
        String[] strings = mapper.showTableLike(TableNameConstant.SHEARER_ENCODER_POSITION_TABLE_NAME);
        String s = JSON.toJSONString(strings);
        System.out.println(s);
    }

    @Test
    public void testFlush(){
//        ShearerSystemServiceImpl s = new ShearerSystemServiceImpl();
//        new BaseController().tryCatch(()->new SysResult<>(s.getDrumHeightData(null)));
        RedisUtil.flushdb(stringRedisTemplate2);
        System.out.println("flushdb!");
    }
}
