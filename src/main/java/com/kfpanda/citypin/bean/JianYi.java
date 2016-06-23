package com.kfpanda.citypin.bean;

import java.io.Serializable;

public class JianYi implements Serializable{
	
	private static final long serialVersionUID = 7086733004075119681L;
	private Long jyid;
	private Long createTime;
	private String account;
	private String content;
	
	public Long getJyid() {
		return jyid;
	}
	public void setJyid(Long jyid) {
		this.jyid = jyid;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}