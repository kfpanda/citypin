package com.kfpanda.citypin.biz;

import com.kfpanda.citypin.bean.Users;

public interface UserBiz {
	
	public Users findUser(String account);
	public int saveUser(Users user);
	public int updateUser(Users user);
	public int updateUserPasswd(String account, String passwd, String newPasswd);
	public int passwdValid(String account, String passwd);
}
