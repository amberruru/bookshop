package com.bookshop.controller.test;

import com.bookshop.BookShopAdminApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by zhaokai on 2018/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookShopAdminApplication.class)
public class BookControllerTest {

    //上下文
    @Autowired
    private WebApplicationContext webApplicationContext;

    //虚拟mvc环境
    private MockMvc mockMvc;

    //测试方法前执行，实例化mockMvc
    @Before
    public void before(){
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get("/book")//构建请求
                .param("name","666")//参数，所有参赛为字符串，在实际方法中自动转换
                .param("sort","name,desc","id,desc")//排序
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())//测试要求请求结果状态为成功
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))//测试要求返回的json长度是3
                .andReturn().getResponse().getContentAsString();//最后返回值给result
        /**
         * 其他请求结果看文档https://github.com/json-path/JsonPath
         */
    }

    @Test
    public void whenQueryInfoSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/book/1")
            .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("6666"));
    }

}
