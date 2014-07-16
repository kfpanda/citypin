package com.kfpanda.citypin.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfpanda.citypin.bean.Users;
import com.kfpanda.citypin.biz.UserBiz;

@Controller("userWsAction")
@RequestMapping("/user")
public class UserWsAction extends BaseAction{
	private static final Logger logger = LoggerFactory.getLogger(UserWsAction.class);
	@Resource(name="userBizImpl")
	private UserBiz userBiz;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Object register(
            @RequestParam(value = "account") String account,
            @RequestParam(value = "passwd") String passwd,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "nkname", required=false) String nkName,
            @RequestParam(value = "uname", required=false) String uName,
            @RequestParam(value = "level", defaultValue="1") Integer level,
            @RequestParam(value = "levscore", defaultValue="0") Integer levScore,
            @RequestParam(value = "score", defaultValue="0") Integer score,
            @RequestParam(value = "status", defaultValue="1") Integer status,
            @RequestParam(value = "address", required=false) String address,
            @RequestParam(value = "vehtype", required=false) String vehType,
            @RequestParam(value = "remark", required=false) String remark) {
		
		if(StringUtils.isBlank(account) || account.length() < 4){
			return this.getResult(-1, "account isn't null or empty or len < 4.");
		}
		if(StringUtils.isBlank(phone) || phone.length() != 11){
			return this.getResult(-1, "phone isn't null or empty or len != 11.");
		}
		if(StringUtils.isBlank(passwd) || passwd.length() < 8){
			return this.getResult(-1, "password isn't null or empty or len < 8.");
		}
		if(StringUtils.isBlank(nkName)){
			nkName = account;
		}
		Users user = new Users();
		user.setAccount(account);
		user.setAddress(address);
		user.setCreateTime(System.currentTimeMillis());
		user.setLevel(level);
		user.setLevScore(levScore);
		user.setNkName(nkName);
		user.setPasswd(passwd);
		user.setPhone(phone);
		user.setRemark(remark);
		user.setScore(score);
		user.setStatus(status);
		user.setuName(uName);
		user.setUpdateTime(System.currentTimeMillis());
		user.setVehType(vehType);
		
		return this.getResult(userBiz.saveUser(user));
	}
	
	@RequestMapping(value = "/info")
	public @ResponseBody Object parkFind() {
		
		String account = getAuthAccount();
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "user is not auth.");
		}
		Users user = userBiz.findUser(account);
		return this.getResult(user);
	}
}
