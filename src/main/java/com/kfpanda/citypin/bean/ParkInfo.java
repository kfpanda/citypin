package com.kfpanda.citypin.bean;

public class ParkInfo {
	
	private Long pid;
	private Long createTime;
	private Long updateTime;
	private String pName;
	private String recId;
	private String devId;
	private String address;
	private Double lat;
	private Double lng;
	private Integer isPub;
	
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
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
	public String getPName() {
		return pName;
	}
	public void setPName(String pName) {
		this.pName = pName;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Integer getIsPub() {
		return isPub;
	}
	public void setIsPub(Integer isPub) {
		this.isPub = isPub;
	}
	
	
}
