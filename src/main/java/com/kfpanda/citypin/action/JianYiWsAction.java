package com.kfpanda.citypin.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfpanda.citypin.bean.JianYi;
import com.kfpanda.citypin.biz.JianYiBiz;

@Controller("jianYiWsAction")
@RequestMapping("/jianyi")
public class JianYiWsAction extends BaseAction{
	private static final Logger logger = LoggerFactory.getLogger(JianYiWsAction.class);
	
	@Resource(name="jianYiBizImpl")
	private JianYiBiz jianYiBiz;
	
	@RequestMapping(value = "/save")
	public @ResponseBody Object msgSend(@RequestParam(value = "account") String account,
			@RequestParam(value = "content") String content) {
		
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "account is not empty or null.");
		}
		if(StringUtils.isBlank(content)){
			return this.getResult(-1, "content is not empty or null.");
		}
		JianYi jianYi = new JianYi();
		jianYi.setAccount(account);
		jianYi.setContent(content);
		jianYi.setCreateTime(System.currentTimeMillis());
		return this.getResult(jianYiBiz.saveJianYi(jianYi));
	}
	
	@RequestMapping(value = "/find")
	public @ResponseBody Object msgSearch(@RequestParam(value = "account", required=false) String account) {
		
		List<JianYi> data = null;
		if(StringUtils.isBlank(account)){
			data = jianYiBiz.findJianYiAll();
		}else{
			data = jianYiBiz.findJianYi(account);
		}
		return this.getResult(data);
	}
	
}