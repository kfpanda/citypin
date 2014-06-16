package com.kfpanda.citypin.biz.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.Users;
import com.kfpanda.citypin.biz.UserBiz;
import com.kfpanda.citypin.mapper.RoleMapper;
import com.kfpanda.citypin.mapper.UsersMapper;

@Repository
public class UserBizImpl implements UserBiz{
	private final Logger logger = Logger.getLogger(UserBizImpl.class);
	
	private static Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	@Resource(name="usersMapper")
	private UsersMapper userMapper;
	@Resource(name="roleMapper")
	private RoleMapper roleMapper;
	
	public Users findUser(String account){
		return userMapper.findUser(account);
	}
	
	public int saveUser(Users user){
		//密码加密
		user.setPasswd(encoder.encodePassword(user.getPasswd(), user.getAccount()));
		int rlt = userMapper.saveUser(user);
		//建立用户角色关系
		roleMapper.saveUserRole(user.getAccount(), new Long(1));
		
		return rlt;
	}
}
