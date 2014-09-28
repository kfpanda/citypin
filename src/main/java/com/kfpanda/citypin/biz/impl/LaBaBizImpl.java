package com.kfpanda.citypin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.LaBa;
import com.kfpanda.citypin.bean.LaBaComment;
import com.kfpanda.citypin.biz.LaBaBiz;
import com.kfpanda.citypin.mapper.LaBaMapper;

@Repository
public class LaBaBizImpl implements LaBaBiz{
	private static final Logger logger = LoggerFactory.getLogger(LaBaBizImpl.class);
	
	@Resource(name="laBaMapper")
	private LaBaMapper laBaMapper;

	@Override
	public int saveLaBa(LaBa laBa) {
		return laBaMapper.saveLaBa(laBa);
	}

	@Override
	public List<LaBa> findRoundLaBa(Double lng0, Double lng1, Double lat0, Double lat1, long startTime, long endTime, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<LaBa> findLaBaByLocation(String location, long startTime,
			long endTime, Pageable pageable) {
		return laBaMapper.findLaBaByLocation(location, startTime, endTime, pageable, "");
	}

	@Override
	public List<LaBa> findUserLaBa(String account, Pageable pageable) {
		return laBaMapper.findUserLaBa(account, pageable, "");
	}

	@Override
	public void ytAdd(Long lbid) {
		laBaMapper.ytAdd(lbid);
	}
	
	@Override
	public int ytCount(Long lbid) {
		return laBaMapper.ytCount(lbid);
	}

	@Override
	public int laBaComment(LaBaComment comment) {
		return laBaMapper.laBaComment(comment);
	}

}
