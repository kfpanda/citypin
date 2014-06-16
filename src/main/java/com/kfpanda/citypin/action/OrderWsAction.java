package com.kfpanda.citypin.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfpanda.citypin.biz.OrderBiz;

@Controller("orderWsAction")
@RequestMapping("/order")
public class OrderWsAction extends BaseAction{
	private final Logger logger = Logger.getLogger(OrderWsAction.class);
	
	@Resource(name="orderBizImpl")
	private OrderBiz orderBiz;
	
	@RequestMapping(value = "/search/{account}", method = RequestMethod.POST)
	public @ResponseBody Object orderFind(@PathVariable(value="account")String account) {
		
		if(StringUtils.isBlank(account)){
			return this.getResult(-1, "account isn't null or empty.");
		}
		return this.getResult(orderBiz.orderFind(account));
	}
	
}