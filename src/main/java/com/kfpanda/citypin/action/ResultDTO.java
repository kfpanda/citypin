package com.kfpanda.citypin.action;

public class ResultDTO {
	private int r;
	private Object data;
	private String msg;
	private Page page;
	
	public ResultDTO(int r, Object data, String msg){
		this.r = r;
		this.data = data;
		this.msg = msg;
	}
	
	public ResultDTO(int r, Object data, String msg, Page page){
		this.r = r;
		this.data = data;
		this.msg = msg;
		this.page = page;
	}
	
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}

class Page{
	private int tPage;
	private long tElem;
	
	public Page(int tPage, long tElem){
		this.tPage = tPage;
		this.tElem = tElem;
	}

	public int gettPage() {
		return tPage;
	}
	public void settPage(int tPage) {
		this.tPage = tPage;
	}
	public long gettElem() {
		return tElem;
	}
	public void settElem(long tElem) {
		this.tElem = tElem;
	}
}
