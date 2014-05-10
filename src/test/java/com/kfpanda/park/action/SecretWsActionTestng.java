package com.kfpanda.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kfpanda.citypin.action.ParkWsAction;

@WebAppConfiguration()
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml", "classpath:spring/spring-mvc.xml"})
public class SecretWsActionTestng extends AbstractTestNGSpringContextTests{
	
	private final Logger logger = Logger.getLogger(SecretWsActionTestng.class);
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
//    
    @Resource(name="secretWsAction")
    private ParkWsAction secretWsAction;

    @BeforeMethod
    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    	mockMvc = MockMvcBuilders.standaloneSetup(secretWsAction).build();
    }
    
    @Test
	public void checkAuth() throws Exception{
		ResultActions result = this.mockMvc.perform(get("/secret/svr")
				.param("signature", "signature").param("timestamp", "123456")
				.param("nonce", "nonce").param("echostr", "echostr"));
		MockHttpServletResponse response = result.andReturn().getResponse();
		System.out.println(response.getContentAsString());
	}
    
    @DataProvider(name="message")
	public Object[][] msgProvider(){
		return new Object[][]{
				{" <xml>"	//文本消息
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[this is a test]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 {"<xml>"	//图片消息
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[image]]></MsgType>"
				 + "<PicUrl><![CDATA[this is a url]]></PicUrl>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 {"<xml>"	//语言消息
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[voice]]></MsgType>"
				 + "<Scale><![CDATA[0]]></Scale>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 {"<xml>"	//地理位置消息
		         + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
				 + "<CreateTime>1351776360</CreateTime>"
				 + "<MsgType><![CDATA[location]]></MsgType>"
				 + "<Location_X>23.134521</Location_X>"
				 + "<Location_Y>113.358803</Location_Y>"
				 + "<Scale>20</Scale>"
				 + "<Label><![CDATA[位置信息]]></Label>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 {"<xml>"	//链接消息
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
				 + "<CreateTime>1351776360</CreateTime>"
				 + "<MsgType><![CDATA[link]]></MsgType>"
				 + "<Title><![CDATA[公众平台官网链接]]></Title>"
				 + "<Description><![CDATA[公众平台官网链接]]></Description>"
				 + "<Url><![CDATA[url]]></Url>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>" },
				 {"<xml>"	//事件推送消息
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[FromUser]]></FromUserName>"
				 + "<CreateTime>123456789</CreateTime>"
				 + "<MsgType><![CDATA[event]]></MsgType>"
				 + "<Event><![CDATA[EVENT]]></Event>"
				 + "<EventKey><![CDATA[EVENTKEY]]></EventKey>"
				 + "</xml>"},
				 
				 //收录类 的消息入库
				 {" <xml>"	
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[**]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 
				 //收录类 的消息入库
				 {" <xml>"	
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[！！ 我的密码我选择]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 
				 //查询类 查询文本秘密
				 {" <xml>"	
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[？？]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 
				 //查询类 查询语音秘密
				 {" <xml>"	
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[？？语音]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 
				 //建议意见提交
				 {" <xml>"	
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[我的建议提出宝贵的意见，工作做的非常好。]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"},
				 
				 //加关注
				 {" <xml>"	
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser1]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[event]]></MsgType>"
				 + "<Event>subscribe</Event>"
				 + "<EventKey></EventKey>"
				 + "<Scale>0</Scale>"
				 + "</xml>"},
				 
				//密码回复
				 {" <xml>"	//文本消息
				 + "<ToUserName><![CDATA[toUser]]></ToUserName>"
				 + "<FromUserName><![CDATA[fromUser]]></FromUserName> "
				 + "<CreateTime>1348831860</CreateTime>"
				 + "<MsgType><![CDATA[text]]></MsgType>"
				 + "<Content><![CDATA[12345:密码的回复，很好的秘密]]></Content>"
				 + "<MsgId>1234567890123456</MsgId>"
				 + "</xml>"}
		};
	}
    
    @Test(dataProvider="message")
	public void answerQuestion(String msg) throws Exception{
    	ResultActions result = this.mockMvc.perform(post("/secret/svr")
    			.content(msg.getBytes()).accept(MediaType.APPLICATION_XML));
    	
    	MockHttpServletResponse response = result.andReturn().getResponse();
		System.out.println("--------------------------------------------");
    	
//        this.mockMvc.perform(get("/accounts/1").accept("application/json;charset=UTF-8"))
//          .andExpect(status().isOk())
//          .andExpect(content().contentType("application/json"))
//          .andExpect(jsonPath("$.name").value("Lee");
	}
}
