package com.kfpanda.citypin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kfpanda.citypin.bean.Region;

public interface RegionMapper {
	public final String REGION_FIELD = "province,city,towns";
	public final String REGION_PROP = "#{province},#{city},#{towns}";
	
	@Insert("INSERT INTO region(" + REGION_FIELD + ") VALUES(" + REGION_PROP +")")
	public int saveRegion(Region region);
	
	@Update("INSERT INTO region(" + REGION_FIELD + ") VALUES(" + REGION_PROP + ") ON DUPLICATE KEY "
			+ "UPDATE province=#{province}, city=#{city}, towns=#{towns}")
	public int upsertRegion(Region region);
	@Select("SELECT rgno," + REGION_FIELD + " FROM region WHERE rgno=#{rgno}")
	public Region findRegion(Long rgno);
}
