package com.kfpanda.citypin.bean;

import java.io.Serializable;

public class LaBaComment implements Serializable{
	private static final long serialVersionUID = -5727386370347118482L;
	private Long lcId;
	private Long lbId;
	private String account;
	private Long createTime;
	private Long updateTime;
	private String content;
	private Double lng;
	private Double lat;
	
	public Long getLcId() {
		return lcId;
	}
	public void setLcId(Long lcId) {
		this.lcId = lcId;
	}
	public Long getLbId() {
		return lbId;
	}
	public void setLbId(Long lbId) {
		this.lbId = lbId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
}
