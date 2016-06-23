package com.kfpanda.citypin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
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
	@Select("SELECT lb.hpic as lb_hpic,lb.nkname as lb_nkname,lb.lbid as lb_lbid, lb.account as lb_account, lb.createtime as lb_createtime, lb.updatetime as lb_updatetime,"
			+ "lb.dccase as lb_dccase, lb.huati as lb_huati, lb.img as lb_img, lb.jgtime as lb_jgtime, lb.jyou as lb_jyou,"
			+ "lb.lat as lb_lat, lb.lng as lb_lng, lb.location as lb_location, lb.tag as lb_tag, lb.tttime as lb_tttime, lb.type as lb_type,"
			+ " lc.lcid, lc.account as lc_account, lc.createtime as lc_createtime, lc.updatetime as lc_updatetime,"
			+ "lc.lng as lc_lng, lc.lat as lc_lat, lc.content as lc_content, lc.lbid as lc_lbid, u.hpic as lc_hpic "
			+ "FROM (select lb.*, u.hpic, u.nkname from laba lb, users u where lb.account=u.account" // and  lb.location like CONCAT('%',#{location},'%') "
			+ " and lb.createtime >= #{startTime} and lb.createtime < #{endTime}"
			+ ")lb LEFT JOIN labacomment lc ON lb.lbid=lc.lbid LEFT JOIN users u ON lc.account=u.account order by lb.createtime desc limit #{pageable.offset},#{pageable.pageSize}")
	public List<LaBa> findLaBaByLocation(@Param("location")String location, @Param("startTime")Long startTime, 
			@Param("endTime")Long endTime, @Param("pageable")Pageable pageable, @Param("sort")String sort);
	
	@ResultMap(value="laBaResult")
	@Select("SELECT lb.hpic as lb_hpic,lb.nkname as lb_nkname,lb.lbid as lb_lbid, lb.account as lb_account, lb.createtime as lb_createtime, lb.updatetime as lb_updatetime,"
			+ "lb.dccase as lb_dccase, lb.huati as lb_huati, lb.img as lb_img, lb.jgtime as lb_jgtime, lb.jyou as lb_jyou,"
			+ "lb.lat as lb_lat, lb.lng as lb_lng, lb.location as lb_location, lb.tag as lb_tag, lb.tttime as lb_tttime, lb.type as lb_type,"
			+ " lc.lcid, lc.account as lc_account, lc.createtime as lc_createtime, lc.updatetime as lc_updatetime,"
			+ "lc.lng as lc_lng, lc.lat as lc_lat, lc.content as lc_content, lc.lbid as lc_lbid, u.hpic as lc_hpic "
			+ "FROM (select lb.*, u.hpic, u.nkname from laba lb, users u where lb.account=u.account "
			+ " and lb.account=#{account}"
			// and lb.createtime >= #{startTime} and lb.createtime < #{endTime}
			+ ")lb LEFT JOIN labacomment lc ON lb.lbid=lc.lbid LEFT JOIN users u ON lc.account=u.account order by lb.createtime desc limit #{pageable.offset},#{pageable.pageSize}")
	public List<LaBa> findUserLaBa(@Param("account")String account, @Param("pageable")Pageable pageable, @Param("sort")String sort);
	
	@Select("UPDATE laba SET jyou=jyou+1 WHERE lbid=#{lbid} ")
	public void ytAdd(long lbid);
	
	@Select("SELECT jyou FROM laba WHERE lbid=#{lbid} ")
	public int ytCount(long lbid);
	
	@Select("SELECT lb.*, u.hpic, u.nkname FROM labacomment lb left join users u on lb.account=u.account "
			+ " WHERE lb.lbid=#{lbid} ")
	public List<LaBaComment> laBaCommentFind(long lbid);
	
	@Insert("INSERT INTO labacomment(lbid, account, createtime, updatetime, content, lng, lat)"
			+ " VALUES(#{lbId}, #{account}, #{createTime}, #{updateTime}, #{content}, #{lng}, #{lat})")
	public int saveLaBaComment(LaBaComment comment);
	
	@Select("SELECT count(1) as labacount, sum(jyou) as ytcount FROM laba where account=#{account}")
	public Map<String, Integer> laBaStat(@Param("account")String account);
}
