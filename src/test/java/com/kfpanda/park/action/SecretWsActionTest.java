package com.kfpanda.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kfpanda.citypin.action.ParkWsAction;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/applicationContext.xml", "/spring/spring-mvc.xml"})
public class SecretWsActionTest {
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Autowired
    private ParkWsAction secretWsAction;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    	mockMvc = MockMvcBuilders.standaloneSetup(secretWsAction).build();
    }
    
    @Test
	public void checkAuth() throws Exception{
		ResultActions result = this.mockMvc.perform(get("/secret/svr")
				.param("signature", "signature").param("timestamp", "123456")
				.param("nonce", "nonce").param("echostr", "echostr").accept(MediaType.TEXT_HTML));
		MockHttpServletResponse response = result.andReturn().getResponse();
		System.out.println(response.getContentAsString());
	}
    
    @Test
	public void answerQuestion() throws Exception{
//    	ResultActions result = mockMvc.perform(fileUpload("/file/upload").file("a1", "ABC".getBytes("UTF-8")));
		String xml = "<xml>"
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[您好]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>";
    	ResultActions result = this.mockMvc.perform(post("/secret/svr")
    			.content(xml.getBytes()).accept(MediaType.APPLICATION_XML));
    	
    	MockHttpServletResponse response = result.andReturn().getResponse();
		System.out.println(response.getContentAsString());
    	
//        this.mockMvc.perform(get("/accounts/1").accept("application/json;charset=UTF-8"))
//          .andExpect(status().isOk())
//          .andExpect(content().contentType("application/json"))
//          .andExpect(jsonPath("$.name").value("Lee");
	}
}
