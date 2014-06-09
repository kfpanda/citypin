package com.kfpanda.citypin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.kfpanda.citypin.bean.UserRole;
import com.kfpanda.citypin.bean.Users;

public interface UsersMapper {
	
	@Insert("INSERT INTO users(account,passwd,phone, nkname) VALUES(#{account},#{passwd},#{phone},#{nkName})")
	public int saveUser(Users user);
	@Insert("INSERT INTO user_role(account, role) VALUES(#{account}, #{role})")
	public int saveUserRole(UserRole userRole);
	
	@Select("SELECT * FROM users WHERE account = #{account}")
	public Users findUser(String account);
	
	@Select("SELECT u.*, ur.role FROM users u left join user_role ur on u.account=ur.account WHERE u.account = #{account}")
	public UserRole findUserRole(String account);
	//INSERT INTO users(account,passwd,phone, nkname) VALUES('lhl','asdf', '123432425', 'lhlnk');
}
