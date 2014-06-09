package com.kfpanda.citypin.mapper;

import org.apache.ibatis.annotations.Insert;

import com.kfpanda.citypin.bean.OrderInfo;

public interface OrderInfoMapper {
	
	@Insert("INSERT INTO order_info(createtime, updatetime, account, pno, stime, etime, price, cost) VALUES(#{createTime},#{updateTime},#{account},#{pno},#{stime},#{etime},#{price},#{cost})")
	public int saveOrder(OrderInfo order);
	
}
