package com.kfpanda.citypin.action;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfpanda.citypin.bean.LaBa;
import com.kfpanda.citypin.bean.LaBaComment;
import com.kfpanda.citypin.biz.LaBaBiz;

@Controller("laBaAction")
@RequestMapping("/laba")
public class LaBaAction extends BaseAction{
	private static final Logger logger = LoggerFactory.getLogger(LaBaAction.class);
	@Resource(name="laBaBizImpl")
	private LaBaBiz laBaBiz;
	
	@RequestMapping(value = "/pub")
	public @ResponseBody Object save(
			@RequestParam(value = "createtime", required=false) Long createTime,
            @RequestParam(value = "updatetime", required=false) Long updateTime,
            @RequestParam(value = "account") String account,
            @RequestParam(value = "type") Integer type,
            @RequestParam(value = "jyou", required=false) Integer jyou,
            @RequestParam(value = "jgtime", required=false) Integer jgTime,
            @RequestParam(value = "dccase", required=false) String dcCase,
            @RequestParam(value = "tttime", required=false) Long ttTime,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "tag", required=false) String tag,
            @RequestParam(value = "img", required=false) String img,
            @RequestParam(value = "huati", required=false) String huaTi,
            @RequestParam(value = "lng", required=false) Double lng,
            @RequestParam(value = "lat", required=false) Double lat) {
		
//		if(StringUtils.isBlank(account) || account.length() < 4){
//			return this.getResult(-1, "account isn't null or empty or len < 4.");
//		}
		if(type == null || type < 0){
			return this.getResult(-1, "type isn't null.");
		}
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "account isn't null or empty.");
		}
		if(StringUtils.isBlank(location)){
			return this.getResult(-1, "location isn't null or empty.");
		}
		if(type == 2 && (jgTime == null || jgTime < 0)){
			return this.getResult(-1, "jgtime isn't null.");
		}
		if(type == 1 || type == 2){
			//类型为1 或者 2时 lng 和 lat必须要有
			if(lng == null || lat == null){
				return this.getResult(-1, "lng, lat isn't null.");
			}
		}
		LaBa laBa = new LaBa();
		laBa.setAccount(account);
		laBa.setCreateTime(createTime == null ? System.currentTimeMillis() : createTime);
		laBa.setUpdateTime(updateTime == null ? System.currentTimeMillis() : updateTime);
		laBa.setDcCase(dcCase);
		laBa.setHuaTi(huaTi);
		laBa.setImg(img);
		laBa.setJgTime(jgTime);
		laBa.setJyou(jyou == null ? 0 : jyou);
		laBa.setLocation(location);
		laBa.setTtTime(ttTime);
		laBa.setTag(tag);
		laBa.setLat(lat);
		laBa.setLng(lng);
		laBa.setType(type);
		
		return this.getResult(laBaBiz.saveLaBa(laBa));
	}
	
	@RequestMapping(value = "/find/round")
	public @ResponseBody Object findLaBa(
//			@RequestParam(value = "lng0") Double lng0,
//            @RequestParam(value = "lng1") Double lng1,
//            @RequestParam(value = "lat0") Double lat0,
//            @RequestParam(value = "lat1") Double lat1,
			@RequestParam(value = "location") String location,
			@RequestParam(value = "starttime") String startTime,
            @RequestParam(value = "endtime", required=false) String endTime,
            @PageableDefaults(pageNumber=1, value=10, sort="createTime=desc") Pageable pageable) {
		
		if(StringUtils.isBlank(location)){
			return this.getResult(-1, "location is not null or empty.");
		}
		long sTime = 0, eTime = 0;
		try{
			sTime = DateUtils.parseDate(startTime, "yyyyMMdd").getTime();
            if(StringUtils.isNotBlank(endTime)){
            	eTime = DateUtils.parseDate(endTime, "yyyyMMdd").getTime();
            }else{
                //日期加一天
                Date dt = DateUtils.addDays(new Date(), 1);
                //去除 天以后的时间
                dt = DateUtils.truncate(dt, Calendar.DAY_OF_MONTH);
                eTime = dt.getTime();
            }
        }catch(ParseException e){
            logger.error("error: 日期解析出错，startTime({}) 或者 endtime({}) 日期参数格式不正确。", startTime, endTime);
            logger.error("", e);
            return this.getResult(-1, "starttime或endtime日期格式错误！");
        }
		List<LaBa> laBaList = laBaBiz.findLaBaByLocation(location, sTime, eTime, pageable);
		return this.getResult(laBaList);
	}
	
	@RequestMapping(value = "/find")
	public @ResponseBody Object findUserLaBa(
			@RequestParam(value = "account") String account,
			@PageableDefaults(pageNumber=1, value=20, sort="createTime=desc") Pageable pageable) {
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "account is not null or empty.");
		}
		
		List<LaBa> laBaList = laBaBiz.findUserLaBa(account, pageable);
		return this.getResult(laBaList);
	}
	
	@RequestMapping(value = "/yt/count")
	public @ResponseBody Object ytCount(@RequestParam(value = "lbid") Long lbid) {
//		if(StringUtils.isBlank(account)){
//			return this.getResult(-1, "account is not null or empty.");
//		}
		return this.getResult(Integer.toString(laBaBiz.ytCount(lbid)));
	}
	
	@RequestMapping(value = "/yt/1")
	public @ResponseBody Object nuQiAdd(@RequestParam(value = "lbid") Long lbid) {
		if(lbid < 1){
			return this.getResult(-1, "lbid is not null or empty.");
		}
		laBaBiz.ytAdd(lbid);
		return this.getResult();
	}
	
	@RequestMapping(value = "/comment")
	public @ResponseBody Object comment(@RequestParam(value = "lbid") Long lbid,
			@RequestParam(value = "account") String account,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "lng", required=false) Double lng,
			@RequestParam(value = "lat", required=false) Double lat) {
		if(lbid < 1){
			return this.getResult(-1, "lbid is not null or empty.");
		}
		if(StringUtils.isBlank(account) && StringUtils.isBlank(content)){
			return this.getResult(-1, "account, content is not null or empty.");
		}
		LaBaComment comment = new LaBaComment();
		comment.setLbId(lbid);
		comment.setAccount(account);
		comment.setContent(content);
		comment.setLng(lng);
		comment.setLat(lat);
		comment.setCreateTime(System.currentTimeMillis());
		comment.setUpdateTime(System.currentTimeMillis());
		laBaBiz.laBaComment(comment);
		return this.getResult();
	}
}
