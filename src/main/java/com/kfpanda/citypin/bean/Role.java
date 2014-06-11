package com.kfpanda.citypin.bean;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{
	private static final long serialVersionUID = 1831867074823621228L;
	
	private Long rid;
	private String name;
	private String role;
	
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String getAuthority() {
		return role;
	}
}
