package com.kfpanda.citypin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import com.kfpanda.citypin.bean.LaBa;
import com.kfpanda.citypin.bean.LaBaComment;

public interface LaBaMapper {
	public final String LABA_FIELD = "createtime,updatetime,type,jyou,jgtime,dccase,tttime,location,tag,img,huati,lng,lat,account";
	public final String LABA_PROP = "#{createTime},#{updateTime},#{type},#{jyou},#{jgTime},#{dcCase},#{ttTime},#{location},#{tag},#{img},#{huaTi},#{lng},#{lat},#{account}";
	
	@Insert("INSERT INTO laba(" + LABA_FIELD + ") VALUES(" + LABA_PROP + ")")
	public int saveLaBa(LaBa laBa);
	
	@Select("SELECT lbid, " + LABA_FIELD + ", count(1) as wznum FROM laba WHERE lng>=#{lngX0} and lng<=#{lngX1} "
			+ "and lat>=#{latY0} and lat <=#{latY1} and createtime >= #{startTime} and createtime < #{endTime} order by #{sort}")
	public List<LaBa> findRoundLaBa(@Param("lngX0")Double lngX0, @Param("lngX1")Double lngX1, @Param("latY0")Double latY0, 
			@Param("latY1")Double latY1, @Param("startTime")Long startTime, @Param("endTime")Long endTime, @Param("sort")String sort);
	
	@ResultMap(value="laBaResult")
	@Select("SELECT lb.*, lc.* FROM laba lb, labacomment lc WHERE lb.lbid=lc.lbid and lb.location like CONCAT('%',#{location},'%') "
			+ " and lb.createtime >= #{startTime} and lb.createtime < #{endTime} order by lb.createtime desc limit #{pageable.offset},#{pageable.pageSize}")
	public List<LaBa> findLaBaByLocation(@Param("location")String location, @Param("startTime")Long startTime, 
			@Param("endTime")Long endTime, @Param("pageable")Pageable pageable, @Param("sort")String sort);
	
	@Select("SELECT lbid, " + LABA_FIELD + " FROM laba WHERE account=#{account} ORDER BY createtime desc limit #{pageable.offset},#{pageable.pageSize}")
	public List<LaBa> findUserLaBa(@Param("account")String account, @Param("pageable")Pageable pageable, @Param("sort")String sort);
	
	@Select("UPDATE laba SET jyou=jyou+1 WHERE lbid=#{lbid} ")
	public void ytAdd(long lbid);
	
	@Select("SELECT jyou FROM laba WHERE lbid=#{lbid} ")
	public int ytCount(long lbid);
	
	@Insert("INSERT INTO labacomment(lbid, account, createtime, updatetime, content, lng, lat)"
			+ " VALUES(#{lbId}, #{account}, #{createTime}, #{updateTime}, #{content}, #{lng}, #{lat})")
	public int laBaComment(LaBaComment comment);
}
