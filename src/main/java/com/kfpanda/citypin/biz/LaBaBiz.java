package com.kfpanda.citypin.biz;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kfpanda.citypin.bean.LaBa;
import com.kfpanda.citypin.bean.LaBaComment;

public interface LaBaBiz {
	
	public int saveLaBa(LaBa laBa);
	public List<LaBa> findRoundLaBa(Double lng0, Double lng1, Double lat0, Double lat1, long startTime, long endTime, Pageable pageable);
	public List<LaBa> findLaBaByLocation(String location, long startTime, long endTime, Pageable pageable);
	public List<LaBa> findUserLaBa(String account, Pageable pageable);
	public void ytAdd(Long lbid);
	public int ytCount(Long lbid);
	public int laBaComment(LaBaComment comment);
}
