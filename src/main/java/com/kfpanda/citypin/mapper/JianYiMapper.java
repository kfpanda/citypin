package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.kfpanda.citypin.bean.JianYi;

public interface JianYiMapper {
	
	@Insert("INSERT INTO jianyi(createtime,account,content) VALUES(#{createTime},#{account},#{content})")
	public int saveJianYi(JianYi jianYi);
	
	@Select("SELECT * FROM jianyi WHERE account=#{account}")
	public List<JianYi> findJianYi(String account);
	
	@Select("SELECT * FROM jianyi ")
	public List<JianYi> findJianYiAll();
}
