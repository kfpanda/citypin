package com.kfpanda.citypin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.TieTiao;
import com.kfpanda.citypin.biz.TieTiaoBiz;
import com.kfpanda.citypin.mapper.TieTiaoMapper;

@Repository
public class TieTiaoBizImpl implements TieTiaoBiz{
	private static final Logger logger = LoggerFactory.getLogger(TieTiaoBizImpl.class);
	
	@Resource(name="tieTiaoMapper")
	private TieTiaoMapper tieTiaoMapper;

	@Override
	public int saveTieTiao(TieTiao TieTiao) {
		return tieTiaoMapper.saveTieTiao(TieTiao);
	}
	
	@Override
	public List<TieTiao> findTieTiao(String account) {
		return tieTiaoMapper.findTieTiao(account);
	}

	@Override
	public List<TieTiao> findRoundTieTiao(Double lngX0, Double lngX1,Double latY0, Double latY1) {
		return tieTiaoMapper.findRoundTieTiao(lngX0, lngX1, latY0, latY1);
	}
}
