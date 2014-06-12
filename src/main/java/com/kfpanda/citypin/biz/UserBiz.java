package com.kfpanda.citypin.biz;

import com.kfpanda.citypin.bean.Users;

public interface UserBiz {
	
	public Users findUser(String account);
	public int saveUser(Users user);
	
}
