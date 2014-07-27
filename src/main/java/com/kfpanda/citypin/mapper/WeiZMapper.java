package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.kfpanda.citypin.bean.WeiZ;

public interface WeiZMapper {
	public final String WEIZHANG_FIELD = "createtime,updatetime,wztime,carno,cjno,area,lng,lat,koufen,fakuan,wzdetail,type,account";
	public final String WEIZHANG_PROP = "#{createTime},#{updateTime},#{wzTime},#{carNo},#{cjNo},#{area},#{lng},#{lat},#{kouFen},#{faKuan},#{wzDetail},#{type},#{account}";
	
	@Insert("INSERT INTO weizhang(" + WEIZHANG_FIELD + ") VALUES(" + WEIZHANG_PROP + ")")
	public int saveWeiZ(WeiZ weiz);
	
	@Select("SELECT wz.wzid, " + WEIZHANG_FIELD + " FROM weizhang wz WHERE wz.account=#{account}")
	public List<WeiZ> findWeiZ(String account);
	
	@Select("SELECT * FROM weizhang ")
	public List<WeiZ> findWeiZAll();
}
