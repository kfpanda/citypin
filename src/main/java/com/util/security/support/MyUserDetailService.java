package com.util.security.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kfpanda.citypin.bean.Users;
import com.kfpanda.citypin.mapper.UsersMapper;

//你就可以从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期
@Service
public class MyUserDetailService implements UserDetailsService  {
	@Autowired
	private UsersMapper userMapper;
//	@Autowired
//	private PubAuthoritiesResourcesDao pubAuthoritiesResourcesDao;
	 
	@Override
	public UserDetails loadUserByUsername(String account)
			throws UsernameNotFoundException, DataAccessException {
			
		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		if(account == null){	return null;	}
		Users pubUser = userMapper.findUser(account);
		if(pubUser == null){	return null;	}
		//缓存当前用户信息
//		CacheFactory.createUserCache().initInfo();
//		CacheFactory.createUserCache().setUserOfCurr(pubUser);
		//取得用户的密码,将密码转化成小写
		String password = pubUser.getPasswd();
		
		//取得用户的权限
//		List<PubRoles> rolesSet = pubUser.getRoles();
//		roles.addAll(rolesSet);
		//判断用户是否有效
		boolean userEnable = (pubUser.getStatus() > 0) ? true : false;
		
        User user = new User(account.trim(),
        		password, userEnable, true, true, true, roles);
        
        return user;
	}

}
