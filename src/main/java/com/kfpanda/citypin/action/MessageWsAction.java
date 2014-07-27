package com.kfpanda.citypin.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfpanda.citypin.biz.MsgBiz;

@Controller("messageWsAction")
@RequestMapping("/msg")
public class MessageWsAction extends BaseAction{
	private static final Logger logger = LoggerFactory.getLogger(MessageWsAction.class);
	
	@Resource(name="msgBizImpl")
	private MsgBiz msgBiz;
	
	@RequestMapping(value = "/search")
	public @ResponseBody Object msgSearch() {
		
		String account = getAuthAccount();
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "user is not auth.");
		}
		return this.getResult(msgBiz.msgSearch(account));
	}
	
	@RequestMapping(value = "/send")
	public @ResponseBody Object msgSend(@RequestParam(value = "account") String account,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "frm", required=false) String from,
			@RequestParam(value = "content") String content) {
		
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "account is not empty or null.");
		}
		if(StringUtils.isBlank(title)){
			return this.getResult(-1, "title is not empty or null.");
		}
		if(StringUtils.isBlank(content)){
			return this.getResult(-1, "content is not empty or null.");
		}
		if(StringUtils.isBlank(from)){
			from = "system";
		}
		return this.getResult(msgBiz.msgSearch(account));
	}
	
}