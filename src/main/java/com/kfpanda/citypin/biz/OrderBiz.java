package com.kfpanda.citypin.biz;

import java.util.List;

import com.kfpanda.citypin.bean.OrderInfo;

public interface OrderBiz {
	
	public List<OrderInfo> orderFind(String account);
}
