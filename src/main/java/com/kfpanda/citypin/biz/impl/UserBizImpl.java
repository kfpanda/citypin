package com.kfpanda.citypin.biz.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.Users;
import com.kfpanda.citypin.biz.UserBiz;
import com.kfpanda.citypin.mapper.RoleMapper;
import com.kfpanda.citypin.mapper.UsersMapper;

@Repository
public class UserBizImpl implements UserBiz{
	private static final Logger logger = LoggerFactory.getLogger(UserBizImpl.class);
	
	private static Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	@Resource(name="usersMapper")
	private UsersMapper userMapper;
	@Resource(name="roleMapper")
	private RoleMapper roleMapper;
	
	public Users findUser(String account){
		return userMapper.findUser(account);
	}
	
	public int saveUser(Users user){
		int rlt = -1;
		try{
			//密码加密
			user.setPasswd(encoder.encodePassword(user.getPasswd(), user.getAccount()));
			rlt = userMapper.saveUser(user);
			//建立用户角色关系
			roleMapper.saveUserRole(user.getAccount(), new Long(1));
		}catch(Exception ex){
			logger.error("用户入库失败：", ex);
		}
		return rlt;
	}

	@Override
	public int updateUser(Users user) {
		if(StringUtils.isNotBlank(user.getPasswd())){
			userMapper.updateUserPsd(user.getAccount(), encoder.encodePassword(user.getPasswd(), user.getAccount()));
		}
		return userMapper.updateUsers(user);
	}
	
	@Override
	public int updateUserPasswd(String account, String passwd, String newPasswd) {
		Users user = userMapper.findUser(account);
		if(user != null && user.getPasswd().equals(encoder.encodePassword(passwd, account))){
			return userMapper.updateUserPsd(account, encoder.encodePassword(newPasswd, account));
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String test = encoder.encodePassword("123456", "13065725857");
		System.out.println(test);
	}

	@Override
	public int passwdValid(String account, String passwd) {
		Users user = userMapper.findUser(account);
		if(user != null && user.getPasswd().equals(encoder.encodePassword(passwd, account))){
			return 1;
		}
		return -1;
	}
}
