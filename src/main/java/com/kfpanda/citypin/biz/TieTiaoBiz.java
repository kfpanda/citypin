package com.kfpanda.citypin.biz;

import java.util.List;

import com.kfpanda.citypin.bean.TieTiao;

public interface TieTiaoBiz {
	
	public int saveTieTiao(TieTiao tieTiao);
	public List<TieTiao> findTieTiao(String account);
	public List<TieTiao> findRoundTieTiao(Double lngX0, Double lngX1,Double latY0, Double latY1);
}
