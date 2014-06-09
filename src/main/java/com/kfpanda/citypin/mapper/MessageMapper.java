package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.kfpanda.citypin.bean.Message;

public interface MessageMapper {
	
	@Insert("INSERT INTO message(createtime,updatetime,title,frm,intro,content) VALUES(#{createTime},#{updateTime},#{title},#{frm},#{intro},#{content})")
	public int saveMessage(Message msg);
	
	public List<Message> findMsgs();
}
