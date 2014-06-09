package com.kfpanda.citypin.bean;

public class OrderInfo {
	private long ono;
	private long createTime;
	private long updateTime;
	private String account;
	private long pno;
	private long stime;
	private long etime;
	private double price;
	private double cost;
	public long getOno() {
		return ono;
	}
	public void setOno(long ono) {
		this.ono = ono;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public long getPno() {
		return pno;
	}
	public void setPno(long pno) {
		this.pno = pno;
	}
	public long getStime() {
		return stime;
	}
	public void setStime(long stime) {
		this.stime = stime;
	}
	public long getEtime() {
		return etime;
	}
	public void setEtime(long etime) {
		this.etime = etime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}
