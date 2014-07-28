package com.kfpanda.citypin.biz;

import java.util.List;

import com.kfpanda.citypin.bean.WeiZ;

public interface WeiZBiz {
	
	public int saveWeiZ(WeiZ weiz);
	public List<WeiZ> findWeiZ(String account);
	public List<WeiZ> findHttpWeiZ(String province, String city, String abbr, 
			String carNo, String cjNo, String buyYear, String buyMonth);
}
