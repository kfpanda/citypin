package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.Role;
import com.kfpanda.citypin.bean.RoleRes;

@Repository
public interface RoleMapper {
	
	@Insert("INSERT INTO role(name, role) VALUES(#{name}, #{role})")
	public int saveRole(Role role);
	
	@Insert("INSERT INTO user_role(account, rid) VALUES(#{account}, #{rid})")
	public int saveUserRole(@Param("account") String account, @Param("rid") Long roleId);
	
	@Select("SELECT role, name FROM role WHERE role=#{role}")
	public Role findRole(String role);
	
	@Select("SELECT r.* FROM users u left join user_role ur on u.account=ur.account left join role r on ur.rid=r.rid WHERE u.account = #{account}")
	public List<Role> findRoles(String account);
	
	@Select("SELECT r.rid, re.rno, r.role, re.url FROM role r left join role_res rr on r.rid=rr.rid left join resource re on rr.rno=re.rno")
	public List<RoleRes> findRoleRes();
	
}
