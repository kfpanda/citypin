package com.kfpanda.citypin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.JianYi;
import com.kfpanda.citypin.biz.JianYiBiz;
import com.kfpanda.citypin.mapper.JianYiMapper;

@Repository
public class JianYiBizImpl implements JianYiBiz{
	private static final Logger logger = LoggerFactory.getLogger(JianYiBizImpl.class);
	
	@Resource(name="jianYiMapper")
	private JianYiMapper jianYiMapper;
	
	@Override
	public int saveJianYi(JianYi jianYi) {
		return jianYiMapper.saveJianYi(jianYi);
	}

	@Override
	public List<JianYi> findJianYi(String account) {
		return jianYiMapper.findJianYi(account);
	}

	@Override
	public List<JianYi> findJianYiAll() {
		return jianYiMapper.findJianYiAll();
	}
	

}
