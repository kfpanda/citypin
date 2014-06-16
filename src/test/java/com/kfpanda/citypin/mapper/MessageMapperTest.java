package com.kfpanda.citypin.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kfpanda.citypin.bean.Message;
import com.kfpanda.citypin.bean.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"/spring/spring-application.xml", "/spring/spring-mvc.xml", "/spring/spring-mybatis.xml"})
public class MessageMapperTest {
	
	@Resource
	private MessageMapper msgMapper;
	
	@Test
	public void saveMessage(){
		Message msg = new Message();
		msg.setCreateTime(System.currentTimeMillis());
		msg.setUpdateTime(System.currentTimeMillis());
		msg.setFrm("system");
		msg.setIntro("欢迎新用户注册");
		msg.setContent("欢迎新用户注册");
		msg.setTitle("新用户");
		int rlt = msgMapper.saveMessage(msg);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void saveUserMsg(){
		String account = "lhl";
		Long msgId = new Long(1);
		int rlt = msgMapper.saveUserMsg(account, msgId);
		Assert.assertEquals(rlt, 1);
	}
	
	@Test
	public void findMsgs(){
		List<Message> msgList = msgMapper.findMsgs("lhl");
		Assert.assertEquals(msgList.get(0).getTitle(), "新用户");
	}
	
	@Test
	public void findMsgsAll(){
		List<Message> msgList = msgMapper.findMsgsAll();
		Assert.assertTrue(msgList.size() > 0);
	}
	
}
