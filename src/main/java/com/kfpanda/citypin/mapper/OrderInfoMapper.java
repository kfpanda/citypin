package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.kfpanda.citypin.bean.OrderInfo;

public interface OrderInfoMapper {
	public final String ORDER_FIELD = "createtime, updatetime, account, pno, stime, etime, price, cost";
	@Insert("INSERT INTO order_info(" + ORDER_FIELD + ") VALUES(#{createTime},#{updateTime},#{account},#{pno},#{stime},#{etime},#{price},#{cost})")
	public int saveOrder(OrderInfo order);
//	@Insert("INSERT INTO user_order(account, ono) VALUES(#{account}, #{ono})")
//	public int saveUserOrder(@Param("account")String account, @Param("ono")Long ono);
	
	@Select("SELECT " + ORDER_FIELD + " FROM order_info WHERE account=#{account}")
	public List<OrderInfo> findOrders(String account);
}
