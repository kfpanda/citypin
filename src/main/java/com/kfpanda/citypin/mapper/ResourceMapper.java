package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.Resource;

@Repository
public interface ResourceMapper {
	
	@Insert("INSERT INTO resource(name, pid, url) VALUES(#{name}, #{pid}, #{url})")
	public int saveResource(Resource resource);
	
	@Insert("INSERT INTO role_res(rid, rno) VALUES(#{rid}, #{rno})")
	public int saveRoleResource(@Param("rid") Long roleId, @Param("rno") Long rno);
	
	@Select("SELECT rno, pid, name, status, type, url FROM resource")
	public List<Resource> findResources();
	
	@Select("SELECT r.rno, r.pid, r.name, r.status, r.type, r.url FROM resource r, role_res rr WHERE r.rno=rr.rno and rr.rid=#{rid}")
	public List<Resource> findRoleResources(Long roleId);
	
	@Select("SELECT r.rno, r.pid, r.name, r.status, r.type, r.url FROM resource r, role_res rr, users u, user_role ur WHERE u.account=ur.account and ur.rid=rr.rid and rr.rno=r.rno and ur.account=#{account}")
	public List<Resource> findUserResources(String account);
	
}
