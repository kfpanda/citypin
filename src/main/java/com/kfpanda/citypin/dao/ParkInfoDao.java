package com.kfpanda.citypin.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.dao.sql.ParkInfoSql;

@Repository
public class ParkInfoDao {
	private static final Logger logger = LoggerFactory.getLogger(ParkInfoDao.class);
	
	@Resource(name="parkJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource(name="parkNameJdbcTemplate")
	private NamedParameterJdbcTemplate nameJdbcTemplate;
	private RowMapper<ParkInfo> rowMapper = new BeanPropertyRowMapper<ParkInfo>(ParkInfo.class);
	
//	private SQLExpression sqlExpression = null;//new SQLExpressionSupport();
	
	public int saveParkInfo(ParkInfo paramDto) {
		SqlParameterSource mapSource = new BeanPropertySqlParameterSource(paramDto);
		return nameJdbcTemplate.update(ParkInfoSql.INSERT_PARK, mapSource);
	}
	
	public int upsert(ParkInfo paramDto){
		SqlParameterSource mapSource = new BeanPropertySqlParameterSource(paramDto);
		long ctime = new Date().getTime();
		int result = nameJdbcTemplate.update(ParkInfoSql.INSERT_UPDATE_PARK, mapSource);
		logger.debug("userinfo updateorinsert  time:" + (new Date().getTime() - ctime));
		return result;
	}
	
	public List<ParkInfo> findParkInfos(Double latX0, Double latX1,
			Double lngY0, Double lngY1) {
		Map<String, Double> params = new HashMap<String, Double>();
		params.put("latX0", latX0);
		params.put("latX1", latX1);
		params.put("lngY0", lngY0);
		params.put("lngY1", lngY1);
		List<ParkInfo> parks = nameJdbcTemplate.query(ParkInfoSql.FIND_PARKS_BYPOS, 
									params, rowMapper);
		return parks;
	}
	
	/**
	 * 查询条件：
	 * account, username, sex, guanzhu
	 * @param paramDto
	 * @return
	 */
	/*public List<ParkInfo> findParkInfo(ParkInfo paramDto) {
		SQLExpression sqlExpression = new SQLExpressionSupport();
		SqlParameterSource mapSource = new MapSqlParameterSource();
		if(StringUtils.isNotBlank(paramDto.getStarttime()) && StringUtils.isNotBlank(paramDto.getEndtime())){
			mapSource.addValue("STARTTIME", paramDto.getStarttime(), Types.VARCHAR);
			mapSource.addValue("ENDTIME", paramDto.getEndtime(), Types.VARCHAR);
			sqlExpression.setVariable("updatetime", "1");
		}
		
		if( StringUtils.isNotBlank(paramDto.getOpenid()) ){
			mapSource.addValue("OPENID", paramDto.getOpenid(), Types.VARCHAR);
			sqlExpression.setVariable("openid", "1");
		}
		if( StringUtils.isNotBlank(paramDto.getAccount()) ){
			mapSource.addValue("ACCOUNT", "%" + paramDto.getAccount() + "%", Types.VARCHAR);
			sqlExpression.setVariable("account", "1");
		}
		if( StringUtils.isNotBlank(paramDto.getUsername()) ){
			mapSource.addValue("USERNAME", "%" + paramDto.getUsername() + "%", Types.VARCHAR);
			sqlExpression.setVariable("username", "1");
		}
		if( StringUtils.isNotBlank(paramDto.getSex()) ){
			mapSource.addValue("SEX", paramDto.getSex(), Types.VARCHAR);
			sqlExpression.setVariable("sex", "1");
		}
		if( StringUtils.isNotBlank(paramDto.getGuanzhu()) ){
			mapSource.addValue("GUANZHU", paramDto.getGuanzhu(), Types.VARCHAR);
			sqlExpression.setVariable("guanzhu", "1");
		}
		
		mapSource.addValue("STARTNUMBER", paramDto.getStartNum(), Types.INTEGER);
		mapSource.addValue("ENDNUMBER", paramDto.getEndNum(), Types.INTEGER);
		
		String sort = StringUtils.isNotBlank(paramDto.getSort()) 
				? paramDto.getSort() : "ui.updatetime desc";
		sqlExpression.setVariable("sort", sort);
		
		String sql = sqlExpression.parser(UserinfoSQL.FIND_USERINFO);
		long start = System.currentTimeMillis();
		RowMapper<ParkInfo> rowMapper = new BeanPropertyRowMapper<ParkInfo>(ParkInfo.class);
		List<ParkInfo> userinfoList = nameJdbcTemplate.query(sql, mapSource, rowMapper);
		logger.debug("find_secret_time:="+(System.currentTimeMillis() - start));
		return userinfoList;
	}*/
	
	public ParkInfo findPark(Long pid){
		RowMapper<ParkInfo> rowMapper = new BeanPropertyRowMapper<ParkInfo>(ParkInfo.class);
		List<ParkInfo> parkList = jdbcTemplate.query(ParkInfoSql.FIND_PARK_BYID, rowMapper, pid);
		if(parkList.size() > 0){
			return parkList.get(0);
		}
		return null;
	}
	
}
