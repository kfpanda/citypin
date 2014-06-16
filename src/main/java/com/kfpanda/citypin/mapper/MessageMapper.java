package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kfpanda.citypin.bean.Message;

public interface MessageMapper {
	
	@Insert("INSERT INTO message(createtime,updatetime,title,frm,intro,content) VALUES(#{createTime},#{updateTime},#{title},#{frm},#{intro},#{content})")
	public int saveMessage(Message msg);
	@Insert("INSERT INTO user_msg(account, mid) VALUES(#{account}, #{mid})")
	public int saveUserMsg(@Param("account")String account, @Param("mid")Long mid);
	
	@Select("SELECT m.* FROM message m, user_msg um WHERE m.mid=um.mid and um.account=#{account}")
	public List<Message> findMsgs(String account);
	
	@Select("SELECT * FROM message ")
	public List<Message> findMsgsAll();
}
