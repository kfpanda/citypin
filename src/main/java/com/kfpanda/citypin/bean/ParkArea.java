package com.kfpanda.citypin.bean;

public class ParkArea {
	private Long pano;
	private Long createTime;
	private Long updateTime;
	private Long rgno;
	private String area;
	private Integer pnum;
	private Double lat;
	private Double lng;
	private Double price;
	private String remark;
	
	public Long getPano() {
		return pano;
	}
	public void setPano(Long pano) {
		this.pano = pano;
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
	public Long getRgno() {
		return rgno;
	}
	public void setRgno(Long rgno) {
		this.rgno = rgno;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
