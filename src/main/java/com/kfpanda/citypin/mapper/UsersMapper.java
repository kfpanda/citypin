package com.kfpanda.citypin.mapper;

import org.apache.ibatis.annotations.Select;

import com.kfpanda.citypin.bean.Users;

public interface UsersMapper {
	
	@Select("SELECT * FROM users WHERE account = #{account}")
	public Users findUser(String account);
	//INSERT INTO users(account,passwd,phone, nkname) VALUES('lhl','asdf', '123432425', 'lhlnk');
}
