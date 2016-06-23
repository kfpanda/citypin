package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.Role;
import com.kfpanda.citypin.bean.Users;

@Repository
public interface UsersMapper {
	
	@Insert("INSERT INTO users(account,passwd,phone, nkname, createtime, updatetime, uname, hpic, yt, level, levscore, score, status, location, address, vehtype, remark)" 
			+ " VALUES(#{account},#{passwd},#{phone},#{nkName},#{createTime},#{updateTime},#{uName},#{hPic},#{yt},#{level},#{levScore},#{score},#{status},#{location},#{address},#{vehType},#{remark})")
	public int saveUser(Users user);
	
	@Insert("INSERT INTO user_role(account, rid) VALUES(#{account}, #{rid})")
	public int saveUserRole(@Param("account")String account, @Param("rid")Long roleId);
	
	@Select("SELECT * FROM users WHERE account = #{account}")
	public Users findUser(String account);
	
	@Select("SELECT r.* FROM users u left join user_role ur on u.account=ur.account left join role r on ur.rid=r.rid WHERE u.account = #{account}")
	public List<Role> findRoles(String account);
	
	@Update("UPDATE users SET passwd=#{passwd} where account=#{account}")
	public int updateUserPsd(@Param("account")String account, @Param("passwd")String passwd);
	
	@Update("UPDATE users SET updatetime=#{updateTime}, phone=#{phone}, nkname=#{nkName}, uname=#{uName}, hpic=#{hPic},"
			+ "location=#{location}, address=#{address}, vehtype=#{vehType}, remark=#{remark}"
			+ " where account=#{account}")
	public int updateUsers(Users user);
	//INSERT INTO users(account,passwd,phone, nkname) VALUES('lhl','asdf', '123432425', 'lhlnk');
}
