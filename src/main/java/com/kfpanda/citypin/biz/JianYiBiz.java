package com.kfpanda.citypin.biz;

import java.util.List;

import com.kfpanda.citypin.bean.JianYi;

public interface JianYiBiz {
	
	public int saveJianYi(JianYi jianYi);
	public List<JianYi> findJianYi(String account);
	public List<JianYi> findJianYiAll();
}
