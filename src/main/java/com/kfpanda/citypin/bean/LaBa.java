package com.kfpanda.citypin.bean;

import java.io.Serializable;
import java.util.List;

public class LaBa implements Serializable{
	private static final long serialVersionUID = 3186000949316620993L;
	private Long lbid;
	private Long createTime;
	private Long updateTime;
	private Integer type;
	private Integer jyou;
	private Integer jgTime;
	private String dcCase;
	private Long ttTime;
	private String location;
	private String tag;
	private String img;
	private String huaTi;
	private Double lng;
	private Double lat;
	private String account;
	private List<LaBaComment> comments;
	
	public Long getLbid() {
		return lbid;
	}
	public void setLbid(Long lbid) {
		this.lbid = lbid;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getJyou() {
		return jyou;
	}
	public void setJyou(Integer jyou) {
		this.jyou = jyou;
	}
	public Integer getJgTime() {
		return jgTime;
	}
	public void setJgTime(Integer jgTime) {
		this.jgTime = jgTime;
	}
	public String getDcCase() {
		return dcCase;
	}
	public void setDcCase(String dcCase) {
		this.dcCase = dcCase;
	}
	public Long getTtTime() {
		return ttTime;
	}
	public void setTtTime(Long ttTime) {
		this.ttTime = ttTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getHuaTi() {
		return huaTi;
	}
	public void setHuaTi(String huaTi) {
		this.huaTi = huaTi;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public List<LaBaComment> getComments() {
		return comments;
	}
	public void setComments(List<LaBaComment> comments) {
		this.comments = comments;
	}
	
}
