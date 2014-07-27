package com.kfpanda.citypin.bean;

public class TieTiao {
	private Long ttid;
	private Long createTime;
	private Long updateTime;
	private String ttTime;
	private String area;
	private Double lng;
	private Double lat;
	private Integer wzNum;
	private String wzDetail;
	private Integer type;
	private String account;
	
	public Long getTtid() {
		return ttid;
	}
	public void setTtid(Long ttid) {
		this.ttid = ttid;
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
	public String getTtTime() {
		return ttTime;
	}
	public void setTtTime(String ttTime) {
		this.ttTime = ttTime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public Integer getWzNum() {
		return wzNum;
	}
	public void setWzNum(Integer wzNum) {
		this.wzNum = wzNum;
	}
	public String getWzDetail() {
		return wzDetail;
	}
	public void setWzDetail(String wzDetail) {
		this.wzDetail = wzDetail;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
